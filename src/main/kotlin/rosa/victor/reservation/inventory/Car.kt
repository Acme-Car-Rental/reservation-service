package rosa.victor.reservation.inventory

data class Car(
  val id: Long? = null,
  val licensePlateNumber: String = "Unknown",
  val manufacturer: String = "Unknown",
  val model: String = "Unknown"
)
