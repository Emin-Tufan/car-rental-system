package com.emintufan.carrentalsystem.services.abstracts;

import com.emintufan.carrentalsystem.dto.request.CreateUserRequest;
import com.emintufan.carrentalsystem.dto.request.CreateLoginRequest;
import com.emintufan.carrentalsystem.dto.response.AuthenticationResponse;
import com.emintufan.carrentalsystem.dto.response.CreateLast5RegisteredUserResponse;
import com.emintufan.carrentalsystem.dto.response.CreateUserResponse;
import com.emintufan.carrentalsystem.entities.Vehicle;

import java.util.List;

public interface UserService {

    AuthenticationResponse registerCustomer(CreateUserRequest customerRequest);
    List<CreateUserResponse> getAllUsers();
    long count();
    List<CreateLast5RegisteredUserResponse> getLast5Users();

    CreateUserResponse findUserById(int id);

    boolean deleteUser(int  id);

    boolean addUser(CreateUserRequest customerRequest);

    AuthenticationResponse authenticationResponse(CreateLoginRequest loginRequest);

    boolean updateCustomerProfile(int id, CreateUserRequest customerRequest);

    boolean AddToCart(CreateUserRequest customerRequest);
    AuthenticationResponse authenticationResponseForAdmin(CreateLoginRequest loginRequest);

    boolean RemoveFromCart(CreateUserRequest customerRequest);

    Vehicle PlaceOrder(Vehicle vehicle);

}
