package rosa.victor.reservation.reservation

interface ReservationRepository {

  fun findAll(): List<Reservation>
  fun save(reservation: Reservation): Reservation
}