package org.rapidPay.controller;

import org.rapidPay.models.RapidPayment;
import org.rapidPay.service.PaymentService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/payments")
@Produces("application/json")
@Consumes("application/json")
public class PaymentController {

    @Inject
    PaymentService paymentService;

    @POST
    @Transactional
    public Response createPayment(RapidPayment rapidPayment) {
        RapidPayment newRapidPayment = paymentService.createPayment(rapidPayment.amount, rapidPayment.currency);
        return Response.status(201).entity(newRapidPayment).build();
    }

    @GET
    public List<RapidPayment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GET
    @Path("/{id}")
    public RapidPayment getPaymentById(@PathParam("id") String id) {
        return paymentService.getPaymentById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public RapidPayment updatePayment(@PathParam("id") String id, RapidPayment rapidPayment) {
        return paymentService.updatePayment(id, rapidPayment.amount, rapidPayment.currency);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePayment(@PathParam("id") String id) {
        paymentService.deletePayment(id);
        return Response.status(204).build();
    }

}
