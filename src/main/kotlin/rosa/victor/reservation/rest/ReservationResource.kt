package rosa.victor.reservation.rest

import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestQuery
import rosa.victor.reservation.inventory.Car
import rosa.victor.reservation.reservation.Reservation
import rosa.victor.reservation.reservation.ReservationService
import java.time.LocalDate

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
class ReservationResource(val reservationService: ReservationService) {

  @GET
  @Path("availability")
  fun availability(@RestQuery startDay: LocalDate, @RestQuery endDay: LocalDate): Collection<Car> {
    return reservationService.availability(startDay, endDay)
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  fun createReservation(reservation: Reservation): Reservation {
    return reservationService.createReservation(reservation)
  }
}