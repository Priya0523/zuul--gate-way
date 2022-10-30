package com.example.zuulApigateway.zuulApigateway.Service;

import com.example.zuulApigateway.zuulApigateway.Exception.UserAlreadyExistsException;
import com.example.zuulApigateway.zuulApigateway.Model.UserRegistration;
import com.example.zuulApigateway.zuulApigateway.Repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRegistrationRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserRegistration userRegistration = repository.findByUserName(userName);
        return new User(userRegistration.getUserName(), userRegistration.getPassword(), new ArrayList<>());
    }

    public UserRegistration createUser(UserRegistration registerRequest) throws Exception {
        log.info("Initiate registering the new user to the User_Details");
        if (repository.findByUserNameOrEmailId(registerRequest.getUserName(), registerRequest.getEmailId())
                .isEmpty()) {
            if (registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
                UserRegistration user = addObjectToUserFromRegistration(registerRequest);
                repository.save(registerRequest);
                //kafkaProducer.sendMessage("User created...");
                log.info("Registration successfully completed");
                return repository.save(user);
            } else {
                throw new Exception("New Password and confirm password must be same..!");
            }
        } else {
            throw new UserAlreadyExistsException("User Already exists so you cannot add this user");
        }
    }

    public UserRegistration addObjectToUserFromRegistration(UserRegistration userRegistration) {
        UserRegistration user = new UserRegistration();
        user.setUserName(userRegistration.getUserName());
        user.setFirstName(userRegistration.getFirstName());
        user.setLastName(userRegistration.getLastName());
        user.setEmailId(userRegistration.getEmailId());
        return user;
    }
}
