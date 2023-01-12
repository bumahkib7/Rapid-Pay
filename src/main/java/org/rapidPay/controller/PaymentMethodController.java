package org.rapidPay.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.rapidPay.models.RapidPaymentMethod;
import org.rapidPay.service.PaymentMethodService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/paymentMethods")
@Produces("application/json")
@Consumes("application/json")
@RequiredArgsConstructor
public class PaymentMethodController {

    @Inject
    PaymentMethodService paymentMethodService;
    @Inject
    Stripe stripe;
    @Inject
    @ConfigProperty(name = "stripe.api.key")
    String stripeApiKey;

    @Inject
    com.stripe.model.PaymentMethod stripePaymentMethod;


    public PaymentMethodController(String stripeApiKey) {
        this.stripeApiKey = stripeApiKey;
    }

    @POST
    @Transactional
    public Response createPaymentMethod(RapidPaymentMethod rapidPaymentMethod) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("type", rapidPaymentMethod.type);
        params.put("card", rapidPaymentMethod.details);
        com.stripe.model.PaymentMethod paymentMethod1 = com.stripe.model.PaymentMethod.create(params);
        stripePaymentMethod = paymentMethod1;
        RapidPaymentMethod newRapidPaymentMethod = paymentMethodService.createPaymentMethod(paymentMethod1.getId(),
                paymentMethod1.getType(), paymentMethod1.getCard().getLast4(), rapidPaymentMethod.rapidCustomer);
        return Response.status(201).entity(newRapidPaymentMethod).build();
    }


    @GET
    public List<RapidPaymentMethod> getAllPaymentMethods() {
        return paymentMethodService.getAllPaymentMethods();
    }

    @GET
    @Path("/{id}")
    public RapidPaymentMethod getPaymentMethodById(@PathParam("id") String id) {
        return paymentMethodService.getPaymentMethodById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public RapidPaymentMethod updatePaymentMethod(@PathParam("id") String id, RapidPaymentMethod rapidPaymentMethod) {
        return paymentMethodService.updatePaymentMethod(id, rapidPaymentMethod);
    }
}