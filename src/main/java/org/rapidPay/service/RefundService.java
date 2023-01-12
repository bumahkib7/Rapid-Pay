package org.rapidPay.service;

import com.stripe.exception.StripeException;
import org.jboss.logmanager.Level;
import org.jboss.logmanager.Logger;
import org.rapidPay.models.RapidCharge;
import org.rapidPay.models.RapidRefund;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.Map;

@ApplicationScoped
public class RefundService {
    private static final Logger LOG = Logger.getLogger(RefundService.class.getName());

    public RapidRefund createRefund(String chargeId, Map<String, Object> refundParams) {
        try {
            com.stripe.model.Refund stripeRefund = com.stripe.model.Refund.create(refundParams);
            RapidRefund refund = new RapidRefund();
            refund.amount = BigDecimal.valueOf(stripeRefund.getAmount());
            refund.charge = RapidCharge.findById(stripeRefund.getCharge());
            refund.created = stripeRefund.getCreated();
            refund.currency = stripeRefund.getCurrency();
            refund.persist();
            return refund;
        } catch (StripeException e) {
            LOG.log(Level.SEVERE, "Error creating refund", e);
            return null;
        }
    }

    public RapidRefund retrieveRefund(String refundId) {
        try {
            com.stripe.model.Refund stripeRefund = com.stripe.model.Refund.retrieve(refundId);
            RapidRefund refund = RapidRefund.findById(stripeRefund.getId());
            if (refund == null) {
                refund = new RapidRefund();
                refund.amount = BigDecimal.valueOf(stripeRefund.getAmount());
                refund.charge = org.rapidPay.models.RapidCharge.findById(stripeRefund.getCharge());
                refund.created = stripeRefund.getCreated();
                refund.currency = stripeRefund.getCurrency();
                refund.persist();
            }
            return refund;
        } catch (StripeException e) {
            LOG.log(Level.SEVERE, "Error retrieving refund", e);
            return null;

        }
    }


    public RapidRefund updateRefund(String refundId, Map<String, Object> updateParams) {
        try {
            com.stripe.model.Refund stripeRefund = com.stripe.model.Refund.retrieve(refundId);
            stripeRefund.update(updateParams);
            RapidRefund refund = RapidRefund.findById(stripeRefund.getId());
            refund.amount = BigDecimal.valueOf(stripeRefund.getAmount());
            refund.charge = org.rapidPay.models.RapidCharge.findById(stripeRefund.getCharge());
            refund.created = stripeRefund.getCreated();
            refund.currency = stripeRefund.getCurrency();
            refund.persist();
            return refund;
        } catch (StripeException e) {
            LOG.log(Level.SEVERE, "Error updating refund", e);
            return null;
        }
    }

    public boolean deleteRefund(String refundId) {
        try {
            com.stripe.model.Refund.retrieve(refundId).cancel();
            RapidRefund refund = RapidRefund.findById(refundId);
            refund.delete();
            return true;
        } catch (StripeException e) {
            LOG.log(Level.SEVERE, "Error deleting refund", e);
            return false;
        }
    }
}
