package pe.gob.pj.conexiones.infraestructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"pe.gob.pj.conexiones.*"})
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringBootInit extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootInit.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootInit.class, args);
	}

}
