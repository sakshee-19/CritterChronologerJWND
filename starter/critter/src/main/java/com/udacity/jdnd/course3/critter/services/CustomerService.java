package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entites.Customer;
import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.exceptions.NotFoundException;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public Customer createCustomer(Customer customer){
        Customer saved;
        saved = customerRepository.save(customer);
        return saved;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        System.out.println(customers);
        return customers;
    }


    public Customer getCustomerById(Long id) {
        Optional <Customer> cust = customerRepository.findById(id);
        if(cust.isPresent()){
            return cust.get();
        }
        throw new NotFoundException("Customer with id "+id+" Not found");
    }

    public Customer getOwnerByPetId(Long petId) {
        logger.info("**START getOwnerByPetId petId={}", petId);
//        Customer customer = customerRepository.getByPetId(petId);
        Customer customer = customerRepository.getByPetIds_Id(petId);
//        if (customer.isPresent()) return customer.get();
        logger.info("**END getOwnerByPetId petId={} customer={}", petId, customer);
        return customer;
    }

    public Customer addNewPetToCustomer(List<Pet> pets, Long customerId){
        logger.info("**START** addNewPetToCustomer pets={}, customerId={}", pets,customerId);
        Customer customer = getCustomerById(customerId);
        if(customer != null){
            logger.info("Customer is present");
            customer.getPetIds().addAll(pets);
        }
        logger.info("**END** addNewPetToCustomer");
        return customerRepository.save(customer);
    }

    public Customer removePetFromCustomer(Long petId, Long customerId){
        logger.info("**START** removePetFromCustomer petId={}, customerId={}", petId,customerId);
        Customer customer = getCustomerById(customerId);
        if(customer != null){
            logger.info("Customer is present");
            customer.getPetIds().removeIf(pet -> pet.getId() == petId);
        }
        logger.info("**END** removePetFromCustomer");
        return customerRepository.save(customer);
    }
}
