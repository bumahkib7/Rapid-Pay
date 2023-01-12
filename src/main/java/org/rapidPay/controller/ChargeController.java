package org.rapidPay.controller;

import org.rapidPay.models.RapidCharge;
import org.rapidPay.models.RapidRefund;
import org.rapidPay.service.ChargeService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

@Path("/charges")
@Produces("application/json")
@Consumes("application/json")
public class ChargeController {

    @Inject
    ChargeService chargeService;

    @POST
    @Transactional
    public Response createCharge(RapidCharge charge) {
        validatePayment(charge.rapidPaymentMethod.id, charge.amount);
        RapidCharge newCharge = chargeService.createCharge(charge.amount, charge.currency, charge.rapidPayment, charge.rapidCustomer, charge.rapidPaymentMethod);
        return Response.status(201).entity(newCharge).build();
    }

    @GET
    public List<RapidCharge> getAllCharges() {
        return chargeService.getAllCharges();
    }

    @GET
    @Path("/{id}")
    public RapidCharge getChargeById(@PathParam("id") String id) {
        return chargeService.getChargeById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public RapidCharge updateCharge(@PathParam("id") String id, RapidCharge charge) {
        validatePayment(charge.rapidPaymentMethod.id, charge.amount);
        return chargeService.updateCharge(id, charge.amount, charge.currency, charge.status);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCharge(@PathParam("id") String id) {
        chargeService.deleteCharge(id);
        return Response.status(204).build();
    }

    @POST
    @Path("/{id}/refunds")
    @Transactional
    public RapidRefund refund(@PathParam("id") String id, RapidRefund refund) {
        return chargeService.refund(id, refund.amount, refund.currency);
    }

    private void validatePayment(Long paymentMethodId, BigDecimal amount) {
        if (!chargeService.validatePayment(String.valueOf(paymentMethodId), amount)) {
            throw new BadRequestException("Invalid rapidPayment data");
        }
    }
}
