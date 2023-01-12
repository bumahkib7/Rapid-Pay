package org.rapidPay.models;

import com.stripe.exception.StripeException;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class RapidPaymentMethod extends PanacheEntity {
    public String type;
    public String details;

    @ManyToOne
    public RapidCustomer rapidCustomer;

    @OneToMany(mappedBy = "rapidPaymentMethod", cascade = CascadeType.ALL)
    @ToString.Exclude
    public List<RapidPayment> rapidPayments;

    public static RapidPaymentMethod findByPaymentMethodId(String paymentMethodId) {
        return find("id", paymentMethodId).firstResult();
    }

    public static List<RapidPaymentMethod> findAllPaymentMethods() {
        return listAll();
    }

    public static RapidPaymentMethod createPaymentMethod(Map<String, Object> params) throws StripeException {
        com.stripe.Stripe.getApiBase();
        com.stripe.model.PaymentMethod stripePaymentMethod = com.stripe.model.PaymentMethod.create(params);
        RapidPaymentMethod rapidPaymentMethod = new RapidPaymentMethod();
        rapidPaymentMethod.type = stripePaymentMethod.getType();
        rapidPaymentMethod.details = stripePaymentMethod.getCard().getLast4();
        rapidPaymentMethod.persist();
        return rapidPaymentMethod;
    }

    public static RapidPaymentMethod retrieve(String id) throws StripeException {
        RapidPaymentMethod stripeRapidPaymentMethod = RapidPaymentMethod.retrieve(id);
        RapidPaymentMethod rapidPaymentMethod = findById(stripeRapidPaymentMethod.id);
        if (rapidPaymentMethod == null) {
            rapidPaymentMethod = new RapidPaymentMethod();
            rapidPaymentMethod.id = stripeRapidPaymentMethod.id;
            // set other fields
            rapidPaymentMethod.persist();
        }
        return rapidPaymentMethod;
    }

    public void update() throws StripeException {
        RapidPaymentMethod stripeRapidPaymentMethod = RapidPaymentMethod.retrieve(String.valueOf(this.id));
        // update fields
        stripeRapidPaymentMethod.update();
        persist();
    }

    public void delete() {
        try {
            RapidPaymentMethod.retrieve(String.valueOf(this.id)).delete();
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
        delete();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RapidPaymentMethod that = (RapidPaymentMethod) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
