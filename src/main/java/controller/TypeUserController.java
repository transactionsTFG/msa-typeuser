package controller;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import business.services.TypeUserServices;
import business.typeuser.TypeUserDTO;
import msa.commons.controller.agency.reservationairline.ReservationAirlineRequestDTO;
import msa.commons.controller.agency.reservationbooking.CreateAirlineAndHotelReservationDTO;
import msa.commons.controller.hotel.booking.CreateHotelBookingDTO;

@Path("/typeuser")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TypeUserController {
    private static final Logger logger = LogManager.getLogger(TypeUserController.class);
    
    @EJB
    private TypeUserServices typeUserService;
    
    @GET
    @Path("/{id}")
    public Response getTypeUserById(@PathParam("id") Long id) {
        logger.info("GET /typeuser/{}", id);
        TypeUserDTO typeUser = typeUserService.getTypeUserById(id);
        if (typeUser != null) 
            return Response.ok(typeUser).build();
        else 
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @EJB
    public void setTypeUserService(TypeUserServices typeUserService) {
        this.typeUserService = typeUserService;
    }
}
