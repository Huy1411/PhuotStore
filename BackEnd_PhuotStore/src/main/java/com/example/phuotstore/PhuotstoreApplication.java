package com.example.phuotstore;


import com.example.phuotstore.model.Role;
import com.example.phuotstore.model.User;
import com.example.phuotstore.repository.RoleRepository;
import com.example.phuotstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
public class PhuotstoreApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;


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

        Role adminRole = new Role();
        adminRole.setRoleID(1);
        adminRole.setRoleName("ROLE_ADMIN");

        Role managerRole = new Role();
        managerRole.setRoleID(2);
        managerRole.setRoleName("ROLE_MANAGER");

        Role userRole = new Role();
        userRole.setRoleID(3);
        userRole.setRoleName("ROLE_USER");

        roleRepository.saveAll(Arrays.asList(adminRole, managerRole, userRole));

        User userAdmin = new User("rootadmin",
            "rootadmin@gmail.com",
            encoder.encode("12345678"));

        userAdmin.getRoles().add(adminRole);

        userRepository.save(userAdmin);

    }
}
