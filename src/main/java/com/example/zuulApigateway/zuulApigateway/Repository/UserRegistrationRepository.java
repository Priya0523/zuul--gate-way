package com.example.zuulApigateway.zuulApigateway.Repository;

import com.example.zuulApigateway.zuulApigateway.Model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, String> {

    @Query(value="SELECT * FROM User_Details WHERE user_name = ?1",nativeQuery = true)
    UserRegistration findByUserName(String userName);

    @Query(value="SELECT * FROM User_Details WHERE user_name = ?1 OR email_Id = ?2",nativeQuery = true)
    List<UserRegistration> findByUserNameOrEmailId(String userName, String emailId);


}
