import Input.{Coin, Turn}
import Transformations._
import scala.language.implicitConversions

object Actions {
  type Action = Machine => Transformation

  private val coinAction: Action = machine => {
    if (machine.locked) unlockMachine andThen addCoin else addCoin
  }
  private val turnAction: Action = machine => {
    if (machine.locked) doNothing else takeCandy andThen lockMachine
  }

  private val actions: Map[Input, Transformation] = Map(
    Coin -> coinAction,
    Turn -> turnAction
  )

  def apply(input: Input): Transformation = {
    actions(input)
  }

  private implicit def transitionPickerToMachineTransition(machineMachineTransition: Action): Transformation = {
    machine => machineMachineTransition(machine)(machine)
  }
}