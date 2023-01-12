package org.rapidPay.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class RapidRefund extends PanacheEntity {
    public BigDecimal amount;
    public String currency;
    public Instant createdAt;
    public String status;

    @ManyToOne
    public RapidCharge charge;
    public Long created;

    public static RapidRefund findByRefundId(String refundId) {
        return find("id", refundId).firstResult();
    }

    public static List<RapidRefund> findAllRefunds() {
        return listAll();
    }

    public void deleteRefund() {
        delete();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RapidRefund refund = (RapidRefund) o;
        return id != null && Objects.equals(id, refund.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "id", id,
                "amount", amount,
                "currency", currency,
                "createdAt", createdAt,
                "status", status,
                "charge", charge,
                "created", created
        );
    }
}
