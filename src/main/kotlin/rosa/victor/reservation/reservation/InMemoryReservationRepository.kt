package rosa.victor.reservation.reservation

import jakarta.inject.Singleton
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicLong

@Singleton
class InMemoryReservationRepository : ReservationRepository {

  private val id: AtomicLong = AtomicLong(0)
  private val store: MutableList<Reservation> = CopyOnWriteArrayList()

  override fun findAll(): List<Reservation> = Collections.unmodifiableList(store)

  override fun save(reservation: Reservation): Reservation {
    reservation.id = id.incrementAndGet()
    store.add(reservation)
    return reservation
  }

}