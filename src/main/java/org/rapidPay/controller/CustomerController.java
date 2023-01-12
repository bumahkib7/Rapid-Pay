package org.rapidPay.controller;

import org.rapidPay.models.RapidCustomer;
import org.rapidPay.service.CustomerService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
@Produces("application/json")
@Consumes("application/json")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @POST
    @Transactional
    public Response createCustomer(RapidCustomer rapidCustomer) {
        RapidCustomer newRapidCustomer = customerService.createCustomer(rapidCustomer.name, rapidCustomer.email);
        return Response.status(201).entity(newRapidCustomer).build();
    }

    @GET
    public List<RapidCustomer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GET
    @Path("/{id}")
    public RapidCustomer getCustomerById(@PathParam("id") String id) {
        return customerService.getCustomerById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public RapidCustomer updateCustomer(@PathParam("id") String id, RapidCustomer rapidCustomer) {
        return customerService.updateCustomer(id, rapidCustomer.name, rapidCustomer.email);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") String id) {
        customerService.deleteCustomer(id);
        return Response.status(204).build();
    }
}
