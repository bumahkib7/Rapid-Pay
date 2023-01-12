package org.rapidPay.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class RapidPayment extends PanacheEntity {
    @NotBlank
    public BigDecimal amount;
    public String currency;
    public Instant createdAt;
    public String status;
    public String customerId;
    public String paymentMethodId;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "customer_id", nullable = false, unique = true)
    private RapidCustomer rapidCustomer;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "payment_method_id", nullable = false, unique = true)
    private RapidPaymentMethod rapidPaymentMethod;


    public static RapidPayment findByPaymentId(String paymentId) {
        return find("id", paymentId).firstResult();
    }

    public static RapidPayment findByCustomerId(String customerId) {
        return find("customerId", customerId).firstResult();
    }

    public static List<PanacheEntityBase> findAllPayments() {
        return listAll();
    }

    public void deletePayment() {
        delete();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RapidPayment rapidPayment = (RapidPayment) o;
        return id != null && Objects.equals(id, rapidPayment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
