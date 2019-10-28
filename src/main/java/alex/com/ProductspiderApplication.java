package alex.com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("alex.com.martproduct.mapper")
public class ProductspiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductspiderApplication.class, args);
	}

}
