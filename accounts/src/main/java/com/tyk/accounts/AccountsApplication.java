package com.tyk.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(info = @Info(title = "Accounts microServices REST API Documentation",
                                 description = "TYKRHIT Account Microserevices Of REST Api Documentation",
                                 version = "v1",
                                 contact = @Contact(name = "Yedukondalu Thammisetty",email = "yedukondalu8978@gmail.com",url = "www.tykrohithacount.com"),
                                 license = @License(name = "Apache 2.0",url = "www.tykrohithacount.com"))
                                 )
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
