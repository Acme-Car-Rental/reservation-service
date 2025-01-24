package rosa.victor.reservation.inventory

interface InventoryClient {
  fun allCars(): List<Car>
}