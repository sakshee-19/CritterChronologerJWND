package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entites.Customer;
import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.createCustomer(convertToEntityCustomer(customerDTO));
        return convertToDTOCustomer(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return convertToDTOCustomer(customerService.getAllCustomers());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getOwnerByPetId(petId);
        if (customer != null)
            return convertToDTOCustomer(customer);
        return null;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    private Customer convertToEntityCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        if (customerDTO.getPetIds() == null || customerDTO.getPetIds().isEmpty())
            return customer;
        for (Long petIds : customerDTO.getPetIds()){
            Pet pet = new Pet();
            pet.setId(petIds);
            customer.addPet(pet);
        }
        return customer;
    }

    private CustomerDTO convertToDTOCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        List<Pet> pets = customer.getPetIds();
        BeanUtils.copyProperties(customer, customerDTO, "petIds");
        if (pets != null && !pets.isEmpty()){
                for(Pet pet : pets){
                customerDTO.addPetIds(pet.getId());
            }
        }
        return customerDTO;
    }

    private List<CustomerDTO> convertToDTOCustomer(List<Customer> customerList) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer: customerList) {
            CustomerDTO customerDTO = convertToDTOCustomer(customer);
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

}
