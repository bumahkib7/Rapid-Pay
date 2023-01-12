package org.rapidPay.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
public class RapidCustomer extends PanacheEntity {
    @NotBlank
    public String name;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    public String phone;

    @NotBlank
    public String shippingAddress;

    @NotBlank
    public String billingAddress;

    public Instant createdAt;

    @OneToMany(mappedBy = "rapidCustomer", cascade = CascadeType.ALL)
    @ToString.Exclude
    public List<RapidPayment> rapidPayments;

    public static RapidCustomer findByCustomerId(String customerId) {
        return find("id", customerId).firstResult();
    }

    public static List<RapidCustomer> findAllCustomers() {
        return listAll();
    }

    public void deleteCustomer() {
        delete();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RapidCustomer rapidCustomer = (RapidCustomer) o;
        return id != null && Objects.equals(id, rapidCustomer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
