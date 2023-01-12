package org.rapidPay.service;

import org.rapidPay.models.RapidCustomer;
import org.rapidPay.models.RapidPaymentMethod;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApplicationScoped
public class PaymentMethodService {

    public RapidPaymentMethod createPaymentMethod(@NotNull String type, @NotNull String details, String last4, RapidCustomer rapidCustomer) {
        RapidPaymentMethod rapidPaymentMethod = new RapidPaymentMethod();
        rapidPaymentMethod.type = type;
        rapidPaymentMethod.details = details;
        rapidPaymentMethod.persist();
        return rapidPaymentMethod;
    }

    public List<RapidPaymentMethod> getAllPaymentMethods() {
        return RapidPaymentMethod.listAll();
    }

    public RapidPaymentMethod getPaymentMethodById(String id) {
        RapidPaymentMethod rapidPaymentMethod = RapidPaymentMethod.findById(id);
        if (rapidPaymentMethod == null) {
            throw new IllegalArgumentException("RapidPayment Method not found");
        }
        return rapidPaymentMethod;
    }

    public RapidPaymentMethod updatePaymentMethod(String id, String type, String details) {
        RapidPaymentMethod rapidPaymentMethodToUpdate = RapidPaymentMethod.findById(id);
        if (rapidPaymentMethodToUpdate == null) {
            throw new IllegalArgumentException("RapidPayment Method not found");
        }
        rapidPaymentMethodToUpdate.type = type;
        rapidPaymentMethodToUpdate.details = details;
        rapidPaymentMethodToUpdate.persist();
        return rapidPaymentMethodToUpdate;
    }

    public boolean deletePaymentMethod(String id) {
        RapidPaymentMethod rapidPaymentMethodToDelete = RapidPaymentMethod.findById(id);
        if (rapidPaymentMethodToDelete == null) {
            throw new IllegalArgumentException("RapidPayment Method not found");
        }
        rapidPaymentMethodToDelete.delete();
        return true;
    }


    public RapidPaymentMethod updatePaymentMethod(String id, RapidPaymentMethod rapidPaymentMethod) {
        RapidPaymentMethod rapidPaymentMethodToUpdate = RapidPaymentMethod.findById(id);
        if (rapidPaymentMethodToUpdate == null) {
            throw new IllegalArgumentException("RapidPayment Method not found");
        }
        rapidPaymentMethodToUpdate.type = rapidPaymentMethod.type;
        rapidPaymentMethodToUpdate.details = rapidPaymentMethod.details;
        rapidPaymentMethodToUpdate.persist();
        return rapidPaymentMethodToUpdate;
    }
}
