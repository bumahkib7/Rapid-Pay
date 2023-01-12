package org.rapidPay.controller;

import org.rapidPay.models.RapidRefund;
import org.rapidPay.service.RefundService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/refunds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RefundController {

    @Inject
    RefundService refundService;

    @POST
    public Response createRefund(@QueryParam("chargeId") String chargeId, RapidRefund refund) {
        RapidRefund createdRefund = refundService.createRefund(chargeId, refund.toMap());
        if (createdRefund == null) {
            return Response.status(500).build();
        }
        return Response.status(201).entity(createdRefund).build();
    }

    @GET
    @Path("/{refundId}")
    public Response retrieveRefund(@PathParam("refundId") String refundId) {
        RapidRefund refund = refundService.retrieveRefund(refundId);
        if (refund == null) {
            return Response.status(404).build();
        }
        return Response.ok(refund).build();
    }

    @PUT
    @Path("/{refundId}")
    public Response updateRefund(@PathParam("refundId") String refundId, RapidRefund refund) {
        RapidRefund updatedRefund = refundService.updateRefund(refundId, refund.toMap());
        if (updatedRefund == null) {
            return Response.status(500).build();
        }
        return Response.ok(updatedRefund).build();
    }

    @DELETE
    @Path("/{refundId}")
    public Response deleteRefund(@PathParam("refundId") String refundId) {
        boolean success = refundService.deleteRefund(refundId);
        if (!success) {
            return Response.status(500).build();
        }
        return Response.status(204).build();
    }
}
