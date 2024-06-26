package com.example.secureapp;

import com.example.secureapp.security.entities.AppRole;
import com.example.secureapp.security.entities.AppUser;
import com.example.secureapp.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecureAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecureAppApplication.class, args);
    }


    @Bean
    CommandLineRunner start(AccountService accountService){
        return args -> {

            accountService.addNewRole(new AppRole(null,"USER"));
            accountService.addNewRole(new AppRole(null,"ADMIN"));
            accountService.addNewRole(new AppRole(null,"CUSTOMER_MANAGER"));
            accountService.addNewRole(new AppRole(null,"PRODUCT_MANAGER"));
            accountService.addNewRole(new AppRole(null,"BILLS_MANAGER"));


            // Creating roles
            /*accountService.addNewRole(new AppRole(null,"USER"));
            accountService.addNewRole(new AppRole(null,"ADMIN"));
            accountService.addNewRole(new AppRole(null,"CUSTOMER_MANAGER"));
            accountService.addNewRole(new AppRole(null,"PRODUCT_MANAGER"));
            accountService.addNewRole(new AppRole(null,"BILLS_MANAGER"));

            // Creating users
            accountService.addNewUser(new AppUser(null,"user1","12345",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"admin","12345",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"user2","12345",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"user3","12345",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"user4","12345",new ArrayList<>()));

            // Add roles to users
            accountService.addRoleToUser("user1","USER");

            accountService.addRoleToUser("admin","USER");
            accountService.addRoleToUser("admin","ADMIN");

            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("user2","CUSTOMER_MANAGER");

            accountService.addRoleToUser("user3","USER");
            accountService.addRoleToUser("user3","PRODUCT_MANAGER");

            accountService.addRoleToUser("user4","USER");
            accountService.addRoleToUser("user4","BILLS_MANAGER");
            */


        };
    }

}
