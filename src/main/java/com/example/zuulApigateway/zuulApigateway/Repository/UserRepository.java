package com.example.zuulApigateway.zuulApigateway.Repository;//package com.example.fse.eStockCompanyService.Repository;

import com.example.zuulApigateway.zuulApigateway.Model.UserDetails;
import com.example.zuulApigateway.zuulApigateway.Model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserRegistration, String> {

   @Query(value="SELECT * FROM User_Details WHERE user_name = ?1",nativeQuery = true)
   UserDetails findByUserName(String userName);

}
