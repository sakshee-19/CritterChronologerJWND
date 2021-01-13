package com.udacity.jdnd.course3.critter;

import com.udacity.jdnd.course3.critter.entites.Customer;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Dummy controller class to verify installation success. Do not use for
 * your project work.
 */
@RestController
public class CritterController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/test")
    public String test(){
        return "Critter Starter installed successfully";
    }

    @PostMapping("/create")
    public ResponseEntity createCustomer(@RequestBody Customer body){
        Customer res = customerService.createCustomer(body);
        return new ResponseEntity(res, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getAllCustomer(){
        List<Customer> res = customerService.getAllCustomers();
        return new ResponseEntity(res, HttpStatus.CREATED);
    }
}
