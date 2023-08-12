package com.emintufan.carrentalsystem.services.concretes;

import com.emintufan.carrentalsystem.configuration.JwtService;
import com.emintufan.carrentalsystem.dao.UserDao;
import com.emintufan.carrentalsystem.dto.request.CreateUserRequest;
import com.emintufan.carrentalsystem.dto.request.CreateLoginRequest;
import com.emintufan.carrentalsystem.dto.response.AuthenticationResponse;
import com.emintufan.carrentalsystem.dto.response.CreateLast5RegisteredUserResponse;
import com.emintufan.carrentalsystem.dto.response.CreateUserResponse;
import com.emintufan.carrentalsystem.entities.User;
import com.emintufan.carrentalsystem.entities.Vehicle;
import com.emintufan.carrentalsystem.mapper.ModelMapperManager;
import com.emintufan.carrentalsystem.services.abstracts.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ModelMapperManager modelMapperManager;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

    @Override
    public AuthenticationResponse registerCustomer(CreateUserRequest customerRequest) {
        if (userDao.findByEmail(customerRequest.getEmail()).isPresent()) {
            return null;
        }
        User user = modelMapperManager.forRequest().map(customerRequest, User.class);
        user.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
        userDao.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public ArrayList<CreateUserResponse> getAllUsers() {
        ArrayList<User> users = (ArrayList<User>) userDao.findAll();
        ArrayList<CreateUserResponse> response =
                (ArrayList<CreateUserResponse>) users.stream().map(e ->
                                modelMapperManager.forResponse().map(e, CreateUserResponse.class))
                        .collect(Collectors.toList());
        return response;
    }

    @Override
    public long count() {
        long usersCount = userDao.count();
        return usersCount;
    }

    @Override
    public List<CreateLast5RegisteredUserResponse> getLast5Users() {
            String jpql = "SELECT u FROM User u ORDER BY u.id DESC";
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setMaxResults(5);
            List<User> userList = query.getResultList();
            List<CreateLast5RegisteredUserResponse> users =
                    userList.stream().map(x -> modelMapperManager.forResponse()
                            .map(x, CreateLast5RegisteredUserResponse.class))
                            .collect(Collectors.toList());
        return  users;
    }

    @Override
    public boolean updateCustomerProfile(int id, CreateUserRequest customerRequest) {
        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isPresent()) {
            User updatedUser = modelMapperManager.forResponse().map(customerRequest,User.class);
            userDao.save(updatedUser);
            return true;
        }
        return false;
    }


    @Override
    public CreateUserResponse findUserById(int id) {
        Optional<User> user = userDao.findById(id);
        if (user.isPresent())
        {
            CreateUserResponse response =
                    modelMapperManager.forResponse().map(user.get(),CreateUserResponse.class);
            return (response);
        }
        return (null);
    }

    @Override
    public boolean deleteUser(int id) {

        Optional<User> user = userDao.findById(id);
        if (user.isPresent()) {
            userDao.delete(user.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(CreateUserRequest customerRequest) {
        User user = modelMapperManager.forRequest().map(customerRequest, User.class);
        userDao.save(user);
        return true;
    }


    @Override
    public boolean AddToCart(CreateUserRequest customerRequest) {
        return false;
    }

    @Override
    public boolean RemoveFromCart(CreateUserRequest customerRequest) {
        return false;
    }

    @Override
    public Vehicle PlaceOrder(Vehicle vehicle) {
        return null;
    }


    @Override
    public AuthenticationResponse authenticationResponse(CreateLoginRequest loginRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user = userDao.findByEmail(loginRequest.getEmail())
                .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

    @Override
    public AuthenticationResponse authenticationResponseForAdmin(CreateLoginRequest loginRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user = userDao.findByEmail(loginRequest.getEmail())
                .orElseThrow();
        if (user.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN"))) {
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        else
            return null;
    }


}