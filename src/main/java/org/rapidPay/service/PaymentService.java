package org.rapidPay.service;

import org.rapidPay.models.RapidPayment;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class PaymentService {

    public RapidPayment createPayment(@NotNull BigDecimal amount, @NotNull String currency) {
        RapidPayment rapidPayment = new RapidPayment();
        rapidPayment.amount = amount;
        rapidPayment.currency = currency;
        rapidPayment.persist();
        return rapidPayment;
    }

    public List<RapidPayment> getAllPayments() {
        return RapidPayment.listAll();
    }

    public RapidPayment getPaymentById(String id) {
        RapidPayment rapidPayment = RapidPayment.findById(id);
        if (rapidPayment == null) {
            throw new IllegalArgumentException("RapidPayment not found");
        }
        return rapidPayment;
    }

    public RapidPayment updatePayment(String id, BigDecimal amount, String currency) {
        RapidPayment rapidPaymentToUpdate = RapidPayment.findById(id);
        if (rapidPaymentToUpdate == null) {
            throw new IllegalArgumentException("RapidPayment not found");
        }
        rapidPaymentToUpdate.amount = amount;
        rapidPaymentToUpdate.currency = currency;
        rapidPaymentToUpdate.persist();
        return rapidPaymentToUpdate;
    }

    public void deletePayment(String id) {
        RapidPayment rapidPaymentToDelete = RapidPayment.findById(id);
        if (rapidPaymentToDelete == null) {
            throw new IllegalArgumentException("RapidPayment not found");
        }
        rapidPaymentToDelete.delete();

    }
}
