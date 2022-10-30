package com.example.zuulApigateway.zuulApigateway.Controller;


import com.example.zuulApigateway.zuulApigateway.Model.LoginRequest;
import com.example.zuulApigateway.zuulApigateway.Model.LoginResponse;
import com.example.zuulApigateway.zuulApigateway.Model.UserRegistration;
import com.example.zuulApigateway.zuulApigateway.Repository.UserRegistrationRepository;
import com.example.zuulApigateway.zuulApigateway.Repository.UserRepository;
import com.example.zuulApigateway.zuulApigateway.Service.UserDetailsService;
import com.example.zuulApigateway.zuulApigateway.config.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1.0/market/company")
@Slf4j
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    /**
     * User Login
     * @param loginRequest
     * @return
     * @throws Exception
     */
    /**
     * User Login
     * @param loginRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody LoginRequest loginRequest)
            throws Exception {
        log.debug("In User login()");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect User-name or password ", e);

        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        String loginStatus ="success";
        UserRegistration user = userRegistrationRepository.findByUserName(loginRequest.getUserName());
        return ResponseEntity.ok(new LoginResponse(jwt,loginStatus, user));
    }


    @PostMapping("/register")
    public ResponseEntity<UserRegistration> registerUser(@RequestBody UserRegistration userRegistration) throws Exception {
        UserRegistration user = userDetailsService.createUser(userRegistration);
        log.info("User created successfully!!!...");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}