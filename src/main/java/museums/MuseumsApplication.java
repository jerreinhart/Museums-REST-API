package museums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MuseumsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MuseumsApplication.class, args);
	}
}
