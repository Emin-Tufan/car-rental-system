package com.emintufan.carrentalsystem.services.abstracts;

import com.emintufan.carrentalsystem.dto.requests.CreateCustomerRequest;
import com.emintufan.carrentalsystem.entitys.User;
import com.emintufan.carrentalsystem.entitys.Vehicle;

import java.util.List;

public interface CustomerService {

    boolean RegisterCustomer(CreateCustomerRequest customerRequest);
    User findUserById(int id);
    boolean deleteUser(User user);
    boolean addUser(CreateCustomerRequest customerRequest);

    boolean LoginCustomer( String email, String password);

    boolean UpdateCustomerProfile(int id, CreateCustomerRequest customerRequest);

    boolean AddToCart(CreateCustomerRequest customerRequest);

    boolean RemoveFromCart(CreateCustomerRequest customerRequest);

    Vehicle PlaceOrder(Vehicle vehicle);
}
