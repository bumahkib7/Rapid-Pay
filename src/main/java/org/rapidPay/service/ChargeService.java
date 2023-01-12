package org.rapidPay.service;

import org.rapidPay.models.*;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class ChargeService {

    public RapidCharge createCharge(BigDecimal amount,
                               @NotNull String currency,
                               @NotNull RapidPayment rapidPayment,
                               @NotNull RapidCustomer rapidCustomer,
                               @NotNull RapidPaymentMethod rapidPaymentMethod) {
        RapidCharge charge = new RapidCharge();
        charge.amount = amount;
        charge.currency = currency;
        charge.rapidPayment = rapidPayment;
        charge.rapidCustomer = rapidCustomer;
        charge.rapidPaymentMethod = rapidPaymentMethod;
        charge.persist();
        // Send notification to rapidCustomer and/or administrator

        return charge;
    }

    public List<RapidCharge> getAllCharges() {
        return org.rapidPay.models.RapidCharge.listAll();
    }

    public RapidCharge getChargeById(String id) {
        RapidCharge charge = org.rapidPay.models.RapidCharge.findByChargeId(id);
        if (charge == null) {
            throw new IllegalArgumentException("Charge not found");
        }
        return charge;
    }

    public RapidCharge updateCharge(String id, BigDecimal amount, String currency, String status) {
        RapidCharge chargeToUpdate = org.rapidPay.models.RapidCharge.findByChargeId(id);
        if (chargeToUpdate == null) {
            throw new IllegalArgumentException("Charge not found");
        }
        chargeToUpdate.amount = amount;
        chargeToUpdate.currency = currency;
        chargeToUpdate.status = status;
        chargeToUpdate.persist();
        return chargeToUpdate;
    }

    public void deleteCharge(String id) {
        RapidCharge chargeToDelete = org.rapidPay.models.RapidCharge.findByChargeId(id);
        if (chargeToDelete == null) {
            throw new IllegalArgumentException("Charge not found");
        }
        chargeToDelete.delete();
    }

    public RapidRefund refund(String chargeId, BigDecimal amount, String currency) {
        RapidCharge charge = org.rapidPay.models.RapidCharge.findByChargeId(chargeId);
        if (charge == null) {
            throw new IllegalArgumentException("Charge not found");
        }
        if (!charge.status.equals("succeeded")) {
            throw new IllegalArgumentException("Cannot refund a charge that is not succeeded");
        }
        RapidRefund refund = new RapidRefund();
        refund.amount = amount;
        refund.currency = currency;
        refund.charge = charge;
        refund.persist();
        // Send notification to rapidCustomer and/or administrator
        return refund;
    }

    public boolean validatePayment(String paymentMethodId, BigDecimal amount) {
        RapidPaymentMethod rapidPaymentMethod = RapidPaymentMethod.findByPaymentMethodId(paymentMethodId);
        if (rapidPaymentMethod == null) {
            throw new IllegalArgumentException("Invalid rapidPayment method");
        }

        if (amount.compareTo(new BigDecimal(0)) < 1) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        // Additional validation checks can be added here, such as checking the rapidCustomer's credit limit or verifying that the rapidPayment method is valid


        return true;
    }

}