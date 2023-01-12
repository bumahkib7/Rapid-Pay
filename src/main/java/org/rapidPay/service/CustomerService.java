package org.rapidPay.service;

import org.jboss.logging.Logger;
import org.rapidPay.models.RapidCustomer;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    private final Logger logger = Logger.getLogger(CustomerService.class);

    public RapidCustomer createCustomer(@NotNull String name, @NotNull String email) {
        logger.info("Creating rapidCustomer");
        RapidCustomer rapidCustomer = new RapidCustomer();
        rapidCustomer.name = name;
        rapidCustomer.email = email;
        logger.info("Persisting rapidCustomer");
        rapidCustomer.persist();
        logger.info("RapidCustomer persisted");
        return rapidCustomer;
    }

    public List<RapidCustomer> getAllCustomers() {
        logger.info("Getting all customers");
        return RapidCustomer.listAll();
    }

    public RapidCustomer getCustomerById(String id) {
        RapidCustomer rapidCustomer = RapidCustomer.findById(id);
        logger.info("Getting rapidCustomer by id");
        if (rapidCustomer == null) {
            throw new IllegalArgumentException("RapidCustomer not found");
        }
        logger.info("RapidCustomer found");
        return rapidCustomer;
    }

    public RapidCustomer updateCustomer(String id, String name, String email) {
        logger.info("Updating rapidCustomer");
        RapidCustomer rapidCustomerToUpdate = RapidCustomer.findById(id);
        if (rapidCustomerToUpdate == null) {
            throw new IllegalArgumentException("RapidCustomer not found");
        }
        rapidCustomerToUpdate.name = name;
        rapidCustomerToUpdate.email = email;
        logger.info("Persisting rapidCustomer");
        rapidCustomerToUpdate.persist();
        logger.info("RapidCustomer updated");
        return rapidCustomerToUpdate;
    }

    public void deleteCustomer(String id) {
        RapidCustomer rapidCustomerToDelete = RapidCustomer.findById(id);
        if (rapidCustomerToDelete == null) {
            throw new IllegalArgumentException("RapidCustomer not found");
        }
        logger.info("Deleting rapidCustomer");
        rapidCustomerToDelete.delete();
        logger.info("RapidCustomer deleted");
    }
}
