object CandyMachineSimulation {

  type MachineResult = ((Int, Int), Machine)
  val machineToResult: Machine => MachineResult = machine => {
    ((machine.coins, machine.candies), machine)
  }

  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = {
    State(
      inputs.foldLeft(identity[Machine] _) { (simulation, input) =>
        simulation andThen Actions(input)
      } andThen machineToResult
    )
  }
}
