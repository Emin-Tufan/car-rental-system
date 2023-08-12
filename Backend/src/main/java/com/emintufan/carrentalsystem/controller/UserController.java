package com.emintufan.carrentalsystem.controller;

import com.emintufan.carrentalsystem.dto.request.CreateUserRequest;
import com.emintufan.carrentalsystem.dto.request.CreateLoginRequest;
import com.emintufan.carrentalsystem.dto.response.AuthenticationResponse;
import com.emintufan.carrentalsystem.dto.response.CreateLast5RegisteredUserResponse;
import com.emintufan.carrentalsystem.dto.response.CreateUserResponse;
import com.emintufan.carrentalsystem.exceptions.EntityExceptions;
import com.emintufan.carrentalsystem.services.abstracts.EmailService;
import com.emintufan.carrentalsystem.services.abstracts.UserService;
import jakarta.mail.MessagingException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/customer")
@NoArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;
    private EmailService emailService;

    @Autowired
    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> customerRegister(@RequestBody CreateUserRequest customerRequest) {
        AuthenticationResponse response = userService.registerCustomer(customerRequest);
        URI uri = URI.create("/customer/register");
        if (response == null) {
            throw new EntityExceptions("User Already Exists!");
        } else
            return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> customerLogin(@RequestBody CreateLoginRequest loginRequest) {

        try {
            AuthenticationResponse response = userService.authenticationResponse(loginRequest);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            throw new EntityExceptions("Invalid Username or Password");
        }
    }
    @PostMapping("/login-admin")
    public ResponseEntity<AuthenticationResponse> adminLogin(@RequestBody CreateLoginRequest loginRequest) {
        try {
            AuthenticationResponse response = userService.authenticationResponseForAdmin(loginRequest);

            if (response != null) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            throw new EntityExceptions("Invalid Username or Password");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> customerUpdate(@PathVariable int id,
                                                 @RequestBody CreateUserRequest customerRequest) {
        boolean isUpdated = userService.updateCustomerProfile(id, customerRequest);
        if (isUpdated) {
            return ResponseEntity.ok("Customer profile successfully updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with ID: " + id);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateUserResponse> getUser(@PathVariable int id) {
        CreateUserResponse user = userService.findUserById(id);
        if(user != null)
            return ResponseEntity.ok(user);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CreateUserResponse>> getUserList() {
        try {
            List<CreateUserResponse> getUserDetailList =
                    userService.getAllUsers();
            if(!getUserDetailList.isEmpty())
                return ResponseEntity.ok(getUserDetailList);
            else
                return (ResponseEntity.status(HttpStatus.NOT_FOUND).build());

        } catch (Exception e) {
            throw new EntityExceptions("Users Not Found !");

        }
    }
    @GetMapping("/users-count")
    public ResponseEntity<Long> usersCount() {
        try {
            long getCount = userService.count();
            return ResponseEntity.ok(getCount);
        } catch (Exception e) {
            throw new EntityExceptions("Users Not Found !");
        }
    }
    @GetMapping("/users-last")
    public ResponseEntity<List<CreateLast5RegisteredUserResponse>> getLast5User() {
        try {
            List<CreateLast5RegisteredUserResponse> users =
                    userService.getLast5Users();
            if(!users.isEmpty())
                return ResponseEntity.ok(users);
            else
                return ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new EntityExceptions("Users Not Found !");
        }
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) throws MessagingException {

        try {
            String response = emailService.forgotPassword(email);
            if (!response.isEmpty() && !response.isBlank())
                return (ResponseEntity.status(HttpStatus.OK).body(response));
            else
                return (ResponseEntity.status(HttpStatus.NOT_FOUND).body("String errorMessage = User with email"  + email + " not found"));
        }
        catch (Exception e)
        {
            throw new EntityExceptions("String errorMessage = User with email"  + email + " not found");
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable int id)
    {
        boolean isDeleted = userService.deleteUser(id);
        if(isDeleted)
            return (ResponseEntity.ok(isDeleted));
        else
            return (ResponseEntity.badRequest().body(isDeleted));
    }
}
