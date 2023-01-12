package org.rapidPay.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class RapidPaymentIntent extends PanacheEntity {
    @NotBlank
    public String status;

    @NotBlank
    public BigDecimal amount;

    @NotBlank
    public String currency;

    public String paymentMethodId;

    public String customerId;

    @ManyToOne
    public RapidPayment rapidPayment;

    @ManyToOne
    public RapidCustomer rapidCustomer;

    @ManyToOne
    public RapidPaymentMethod rapidPaymentMethod;

    public static RapidPaymentIntent findByPaymentIntentId(String paymentIntentId) {
        return find("id", paymentIntentId).firstResult();
    }

    public static List<RapidPaymentIntent> findAllPaymentIntents() {
        return listAll();
    }

    public void deletePaymentIntent() {
        delete();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RapidPaymentIntent that = (RapidPaymentIntent) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
