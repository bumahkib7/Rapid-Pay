package org.rapidPay.service;

import org.rapidPay.models.RapidPaymentIntent;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class PaymentIntentService {

    public RapidPaymentIntent createPaymentIntent(@NotNull BigDecimal amount, @NotNull String currency, @NotNull String status) {
        RapidPaymentIntent rapidPaymentIntent = new RapidPaymentIntent();
        rapidPaymentIntent.amount = amount;
        rapidPaymentIntent.currency = currency;
        rapidPaymentIntent.status = status;
        rapidPaymentIntent.persist();
        return rapidPaymentIntent;
    }

    public List<RapidPaymentIntent> getAllPaymentIntents() {
        return RapidPaymentIntent.listAll();
    }

    public RapidPaymentIntent getPaymentIntentById(String id) {
        RapidPaymentIntent rapidPaymentIntent = RapidPaymentIntent.findById(id);
        if (rapidPaymentIntent == null) {
            throw new IllegalArgumentException("RapidPayment Intent not found");
        }
        return rapidPaymentIntent;
    }

    public RapidPaymentIntent updatePaymentIntent(String id, BigDecimal amount, String currency, String status) {
        RapidPaymentIntent rapidPaymentIntentToUpdate = RapidPaymentIntent.findById(id);
        if (rapidPaymentIntentToUpdate == null) {
            throw new IllegalArgumentException("RapidPayment Intent not found");
        }
        rapidPaymentIntentToUpdate.amount = amount;
        rapidPaymentIntentToUpdate.currency = currency;
        rapidPaymentIntentToUpdate.status = status;
        rapidPaymentIntentToUpdate.persist();
        return rapidPaymentIntentToUpdate;
    }

    public void deletePaymentIntent(String id) {
        RapidPaymentIntent rapidPaymentIntentToDelete = RapidPaymentIntent.findById(id);
        if (rapidPaymentIntentToDelete == null) {
            throw new IllegalArgumentException("RapidPayment Intent not found");
        }
        rapidPaymentIntentToDelete.delete();

    }

}


