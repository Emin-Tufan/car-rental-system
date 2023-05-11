package com.emintufan.carrentalsystem.services.concretes;

import com.emintufan.carrentalsystem.dao.UserDao;
import com.emintufan.carrentalsystem.dto.requests.CreateCustomerRequest;
import com.emintufan.carrentalsystem.entitys.User;
import com.emintufan.carrentalsystem.entitys.Vehicle;
import com.emintufan.carrentalsystem.mappers.ModelMapperManager;
import com.emintufan.carrentalsystem.services.abstracts.CustomerService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class CustomerImpl implements CustomerService {

    private UserDao customerDao;
    private ModelMapperManager modelMapperManager;

    @Autowired
    public CustomerImpl(UserDao customerDao, ModelMapperManager modelMapperManager) {
        this.customerDao = customerDao;
        this.modelMapperManager = modelMapperManager;
    }

    @Override
    public boolean RegisterCustomer(CreateCustomerRequest customerRequest) {
        User customer = modelMapperManager.forRequest().map(customerRequest, User.class);
        User user = this.customerDao.save(customer);

        if (user == null)
            return false;
        return true;
    }

    @Override
    public boolean LoginCustomer(String email, String password) {
        User user;
        if(email != null && password != null)
        {
            user = customerDao.findByEmail(email);
            if(user != null)
            {
                if(user.getPassword().equals(password))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean UpdateCustomerProfile(int id,CreateCustomerRequest customerRequest) {
        Optional<User> userOpt = customerDao.findById(id);
        if(userOpt == null)
            return false;
        User user = userOpt.get();
        modelMapperManager.forResponse().map(customerRequest,user);
        customerDao.save(user);
        return true;
    }

    @Override
    public User findUserById(int id)
    {
        Optional<User> user = customerDao.findById(id);
        return user.get();
    }

    @Override
    public boolean deleteUser(User user) {

        if(user != null){
            customerDao.delete(user);
            return true;
        }
        else
         return false;
    }

    @Override
    public boolean addUser(CreateCustomerRequest customerRequest) {
        User user = modelMapperManager.forRequest().map(customerRequest,User.class);
        customerDao.save(user);
        return true;
    }


    @Override
    public boolean AddToCart(CreateCustomerRequest customerRequest) {
        return false;
    }

    @Override
    public boolean RemoveFromCart(CreateCustomerRequest customerRequest) {
        return false;
    }

    @Override
    public Vehicle PlaceOrder(Vehicle vehicle) {
        return null;
    }

}