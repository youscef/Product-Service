package org.sid.inventoryservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SpringBootApplication
public class InventoryServiceApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		productRepository.save(new Product(null,"iphone",3000));
		productRepository.save(new Product(null,"Laptop",1000));
		productRepository.save(new Product(null,"SmartTV",2000));
		productRepository.save(new Product(null,"voiture",6000));

		productRepository.findAll().forEach(System.out::println);
	}
}
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class Product{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
}
@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product,Long>{

}
@Projection(name = "PProduct",types = Product.class)
interface ProductProjection{
	public String getName();
	public double getPrice();


}