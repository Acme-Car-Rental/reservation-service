package rosa.victor.reservation.rental

import java.time.LocalDate

data class Rental(
  val id: Long?,
  val userId: String,
  val reservationId: Long,
  val startDate: LocalDate,
)
