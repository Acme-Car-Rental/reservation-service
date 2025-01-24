package rosa.victor.reservation.reservation

import java.time.LocalDate

data class Reservation(
  var id: Long?,
  val carId: Long,
  val startDay: LocalDate,
  val endDay: LocalDate,
) {
  fun isReserved(startDay: LocalDate, endDay: LocalDate): Boolean {
    return (!(endDay.isBefore(startDay) || startDay.isAfter(endDay)))
  }
}
