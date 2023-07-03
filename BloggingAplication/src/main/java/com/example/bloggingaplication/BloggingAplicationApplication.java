package com.example.bloggingaplication;

import com.example.bloggingaplication.config.AppConstants;
import com.example.bloggingaplication.entity.Role;
import com.example.bloggingaplication.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@SpringBootApplication
public class BloggingAplicationApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BloggingAplicationApplication.class, args);

    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("123456"));
        try{
            Role role = new Role();
            role.setId(AppConstants.ADMIN_USER);
            role.setRoleName("ADMIN_USER");

            Role role1 = new Role();
            role1.setId(AppConstants.NORMAL_USER);
            role1.setRoleName("NORMAL_USER");

            List<Role> roles = List.of(role, role1);
            List<Role> roles1 = this.roleRepository.saveAll(roles);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
