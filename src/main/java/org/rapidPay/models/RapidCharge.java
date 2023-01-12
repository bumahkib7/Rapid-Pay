package org.rapidPay.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class RapidCharge extends PanacheEntity {
    public BigDecimal amount;
    public String currency;
    public Instant createdAt;
    public String status;

    @ManyToOne
    public RapidPayment rapidPayment;

    @ManyToOne
    public RapidCustomer rapidCustomer;

    @ManyToOne
    public RapidPaymentMethod rapidPaymentMethod;

    public static RapidCharge findByChargeId(String chargeId) {
        return find("id", chargeId).firstResult();
    }

    public static List<RapidCharge> findAllCharges() {
        return listAll();
    }

    public void deleteCharge() {
        delete();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RapidCharge charge = (RapidCharge) o;
        return id != null && Objects.equals(id, charge.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}