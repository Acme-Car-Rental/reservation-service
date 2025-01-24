package rosa.victor.reservation.reservation

import jakarta.enterprise.context.ApplicationScoped
import rosa.victor.reservation.inventory.Car
import rosa.victor.reservation.inventory.InventoryClient
import java.time.LocalDate

@ApplicationScoped
class ReservationService(
  private val reservationRepository: ReservationRepository,
  private val inventoryClient: InventoryClient
) {

  fun availability(startDay: LocalDate, endDay: LocalDate): Collection<Car> {
    // obtain all cars from inventory
    val availableCars = inventoryClient.allCars()

    // create a map from id to car
    val carsById: HashMap<Long, Car> = HashMap()
    for (car in availableCars) {
      carsById[car.id] = car
    }

    reservationRepository.findAll()
      .filter { reservation -> reservation.isReserved(startDay, endDay) }
      .forEach { reservation -> carsById.remove(reservation.carId) }

    return carsById.values
  }

  fun createReservation(reservation: Reservation): Reservation = reservationRepository.save(reservation)


}