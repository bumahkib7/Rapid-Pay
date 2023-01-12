package org.rapidPay.controller;

import org.rapidPay.models.RapidPaymentIntent;
import org.rapidPay.service.PaymentIntentService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/paymentIntents")
@Produces("application/json")
@Consumes("application/json")
public class PaymentIntentController {

    @Inject
    PaymentIntentService paymentIntentService;

    @POST
    @Transactional
    public Response createPaymentIntent(RapidPaymentIntent rapidPaymentIntent) {
        RapidPaymentIntent newRapidPaymentIntent = paymentIntentService.createPaymentIntent(rapidPaymentIntent.amount, rapidPaymentIntent.currency, rapidPaymentIntent.status);
        return Response.status(201).entity(newRapidPaymentIntent).build();
    }

    @GET
    public List<RapidPaymentIntent> getAllPaymentIntents() {
        return paymentIntentService.getAllPaymentIntents();
    }

    @GET
    @Path("/{id}")
    public RapidPaymentIntent getPaymentIntentById(@PathParam("id") String id) {
        return paymentIntentService.getPaymentIntentById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public RapidPaymentIntent updatePaymentIntent(@PathParam("id") String id, RapidPaymentIntent rapidPaymentIntent) {
        return paymentIntentService.updatePaymentIntent(id, rapidPaymentIntent.amount, rapidPaymentIntent.currency, rapidPaymentIntent.status);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePaymentIntent(@PathParam("id") String id) {
        paymentIntentService.deletePaymentIntent(id);
        return Response.status(204).build();
    }
}
