package tup.stockTracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

//@ComponentScan(basePackages = {"tup.stockTracking.Usuarios.Service"})
public class StockTrackingApplication {

	public static void main(String[] args) {

		SpringApplication.run(StockTrackingApplication.class, args);
	}

}
