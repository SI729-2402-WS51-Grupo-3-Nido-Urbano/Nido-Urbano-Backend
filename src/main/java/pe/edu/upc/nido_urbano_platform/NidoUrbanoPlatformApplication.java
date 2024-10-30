package pe.edu.upc.nido_urbano_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class NidoUrbanoPlatformApplication {

	public static void main(String[] args) {

		SpringApplication.run(NidoUrbanoPlatformApplication.class, args);
	}

}
