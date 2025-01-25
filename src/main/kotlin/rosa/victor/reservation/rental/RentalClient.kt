package rosa.victor.reservation.rental

import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.jboss.resteasy.reactive.RestPath

@Path("rental")
@RegisterRestClient(baseUri = "http://localhost:8082")
interface RentalClient {

  @POST
  @Path("/start/{userId}/{reservationId}")
  fun start(@RestPath userId: String, @RestPath reservationId: Long): Rental


}