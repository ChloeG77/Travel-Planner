package com.laioffer.travel_planner_backend.controller;


import com.laioffer.travel_planner_backend.entity.Role;
import com.laioffer.travel_planner_backend.entity.RoleName;
import com.laioffer.travel_planner_backend.entity.Trip;
import com.laioffer.travel_planner_backend.entity.User;
import com.laioffer.travel_planner_backend.message.request.LoginForm;
import com.laioffer.travel_planner_backend.message.request.SignUpForm;
import com.laioffer.travel_planner_backend.message.response.JwtResponse;
import com.laioffer.travel_planner_backend.repository.RoleRepository;
import com.laioffer.travel_planner_backend.repository.UserRepository;
import com.laioffer.travel_planner_backend.security.JwtProvider;
import com.laioffer.travel_planner_backend.service.UserDetailsServiceImpl;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserDetailsServiceImpl userService;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {
        // System.out.print(loginRequest);
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        List<Trip> trips = user.getAllTrips();
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt, trips));
    }
    
    @PostMapping("/logout")
    public String authenticateUser() {
//        System.out.print(loginRequest);
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = jwtProvider.generateJwtToken(authentication);
        return "success";
    }
    
    
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpForm signUpRequest) {
        System.out.println(signUpRequest);
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>("Fail -> Username is already taken!",
                HttpStatus.BAD_REQUEST);
        }
        
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Fail -> Email is already in use!",
                HttpStatus.BAD_REQUEST);
        }
        
        String birthday = signUpRequest.getDateOfBirth();
        // Creating user's account
        User user = new User(signUpRequest.getEmail(), signUpRequest.getUsername(),
            encoder.encode(signUpRequest.getPassword()), Date.valueOf(birthday),
            signUpRequest.getGender());
        user.setEnabled(true);

//        Set<String> strRoles = signUpRequest.getRole();
        Set<String> strRoles = new HashSet<>();
        strRoles.add("user");
        Set<Role> roles = new HashSet<>();
        
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                        .orElseThrow(
                            () -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);
                    
                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                        .orElseThrow(
                            () -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);
                    
                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                        .orElseThrow(
                            () -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });
        
        user.setRoles(roles);
        userRepository.save(user);
        
        return ResponseEntity.ok().body("User registered successfully!");
    }
}