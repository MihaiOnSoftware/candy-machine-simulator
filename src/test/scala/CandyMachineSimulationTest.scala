import Input.{Coin, Turn}
import org.scalatest.{FlatSpec, Matchers}

class CandyMachineSimulationTest extends FlatSpec with Matchers {

  "simulate" should "return the correct number of candies and coins" in {
    val startingMachine = Machine(locked = true, 5, 10)
    val inputs = List(Coin, Turn, Coin, Turn, Coin, Turn, Coin, Turn)
    val result = CandyMachineSimulation.simulateMachine(inputs).run(startingMachine)

    result._2 shouldBe Machine(locked = true, 1, 14)
    result._1 shouldBe(14, 1)
  }

  it should "return the same number of candies and coins if no actions are taken" in {
    val startingMachine = Machine(locked = true, 5, 10)
    val result = CandyMachineSimulation.simulateMachine(List.empty[Input]).run(startingMachine)

    result._2 shouldBe Machine(locked = true, 5, 10)
    result._1 shouldBe(10, 5)
  }

  it should "ignore invalid actions" in {
    val startingMachine = Machine(locked = true, 5, 10)
    val inputs = List(Turn, Turn, Turn, Turn)
    val result = CandyMachineSimulation.simulateMachine(inputs).run(startingMachine)

    result._2 shouldBe Machine(locked = true, 5, 10)
    result._1 shouldBe(10, 5)
  }

  it should "accumulate coins but only allow one turn for all coins since the last turn" in {
    val startingMachine = Machine(locked = true, 5, 10)
    val inputs = List(Coin, Coin, Coin, Turn, Turn)
    val result = CandyMachineSimulation.simulateMachine(inputs).run(startingMachine)

    result._2 shouldBe Machine(locked = true, 4, 13)
    result._1 shouldBe(13, 4)
  }

  it should "not allow taking candies when the machine is empty" in {
    val startingMachine = Machine(locked = true, 0, 10)
    val inputs = List(Coin, Turn, Coin, Turn, Coin, Turn, Coin, Turn)
    val result = CandyMachineSimulation.simulateMachine(inputs).run(startingMachine)

    result._2 shouldBe Machine(locked = true, 0, 14)
    result._1 shouldBe(14, 0)
  }

}
