package com.emintufan.carrentalsystem.controllers;

import com.emintufan.carrentalsystem.dto.requests.CreateCustomerRequest;
import com.emintufan.carrentalsystem.services.abstracts.CustomerService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/customer")
@NoArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/register")
    public String customerRegister( @RequestBody CreateCustomerRequest customerRequest) {
        boolean isRegistered = customerService.RegisterCustomer(customerRequest);
        return "register";
    }

    @PostMapping(value = "/login")
    public boolean customerLogin(@RequestBody Map<String, String> information) {
        String email = information.get("email");
        String password = information.get("password");
        boolean isLogin = customerService.LoginCustomer(email, password);
        return isLogin;
    }

    @PutMapping(value = "/update/{id}")
    public boolean customerUpdate(@PathVariable int id,
                                  @RequestBody CreateCustomerRequest customerRequest) {
        boolean user = customerService.UpdateCustomerProfile(id, customerRequest);
        return user;
    }

}
