package rosa.victor.reservation.reservation

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient
import rosa.victor.reservation.inventory.Car
import rosa.victor.reservation.inventory.InventoryClient
import rosa.victor.reservation.rental.RentalClient
import java.time.LocalDate

@ApplicationScoped
class ReservationService(
  val reservationRepository: ReservationRepository,
  val inventoryClient: InventoryClient,
  @RestClient val rentalClient: RentalClient,
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

  fun createReservation(reservation: Reservation): Reservation {
    val result = reservationRepository.save(reservation)
    val userId = "x"  // dummy data, in later parts we will use a proper userId

    if (reservation.startDay == LocalDate.now()) {

      // call the rental service using the rental client
      val rental = result.id?.let { rentalClient.start(userId, it) }
    }
    return result
  }
}