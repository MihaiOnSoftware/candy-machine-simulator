object Transformations {
  type Transformation = Machine => Machine

  val unlockMachine: Transformation = machine => machine.copy(locked = false)
  val lockMachine: Transformation = machine => machine.copy(locked = true)

  val addCoin: Transformation = machine => machine.copy(coins = machine.coins + 1)
  val takeCandy: Transformation = machine => machine.copy(candies = machine.candies - 1)

  val doNothing: Transformation = identity[Machine]
}
