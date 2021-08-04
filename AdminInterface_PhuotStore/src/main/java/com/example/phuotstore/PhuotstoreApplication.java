package com.example.phuotstore;

import com.example.phuotstore.model.CRole;
import com.example.phuotstore.model.Role;
import com.example.phuotstore.repository.CRoleRepository;
import com.example.phuotstore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@SpringBootApplication
public class PhuotstoreApplication implements CommandLineRunner {

    @Autowired
    private CRoleRepository cRoleRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(PhuotstoreApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        CRole userRole = new CRole();
        userRole.setcRoleID((long) 1);
        userRole.setcRoleName("ROLE_USER");
        cRoleRepository.saveAll(Arrays.asList(userRole));

        Role adminRole = new Role();
        adminRole.setRoleID((long) 1);
        adminRole.setRoleName("ROLE_ADMIN");

        Role managerRole = new Role();
        managerRole.setRoleID((long) 2 );
        managerRole.setRoleName("ROLE_MANAGER");

        Role employeeRole = new Role();
        employeeRole.setRoleID((long) 3 );
        employeeRole.setRoleName("ROLE_EMPLOYEE");
        roleRepository.saveAll(Arrays.asList(adminRole, managerRole, employeeRole));
    }

}
