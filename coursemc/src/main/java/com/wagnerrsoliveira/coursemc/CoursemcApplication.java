package com.wagnerrsoliveira.coursemc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wagnerrsoliveira.coursemc.domain.Adress;
import com.wagnerrsoliveira.coursemc.domain.Category;
import com.wagnerrsoliveira.coursemc.domain.City;
import com.wagnerrsoliveira.coursemc.domain.Client;
import com.wagnerrsoliveira.coursemc.domain.Product;
import com.wagnerrsoliveira.coursemc.domain.State;
import com.wagnerrsoliveira.coursemc.domain.enums.ClientType;
import com.wagnerrsoliveira.coursemc.repositories.AdressRepository;
import com.wagnerrsoliveira.coursemc.repositories.CategoryRepository;
import com.wagnerrsoliveira.coursemc.repositories.CityRepository;
import com.wagnerrsoliveira.coursemc.repositories.ClientRepository;
import com.wagnerrsoliveira.coursemc.repositories.ProductRepository;
import com.wagnerrsoliveira.coursemc.repositories.StateRepository;

@SpringBootApplication
public class CoursemcApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AdressRepository adressRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CoursemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category category1 = new Category(null, "Computing");
		Category category2 = new Category(null, "Office");
		
		Product product1 = new Product(null,"Computer",2000.00);
		Product product2 = new Product(null,"Printer",800.00);
		Product product3 = new Product(null,"Mouse",80.00);
		
		category1.getProducts().addAll(Arrays.asList(product1,product2,product3));
		category2.getProducts().addAll(Arrays.asList(product2));
		
		product1.getCategories().addAll(Arrays.asList(category1));
		product2.getCategories().addAll(Arrays.asList(category1,category2));
		product3.getCategories().addAll(Arrays.asList(category1));
		
		
		categoryRepository.saveAll(Arrays.asList(category1,category2));
		productRepository.saveAll(Arrays.asList(product1,product2,product3));
		
		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "São Paulo");
		
		City city1 = new City(null, "Uberlândia",state1);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Campinas",state2);
		
		state1.getCities().addAll(Arrays.asList(city1));
		state2.getCities().addAll(Arrays.asList(city2,city3));

		stateRepository.saveAll(Arrays.asList(state1,state2));
		cityRepository.saveAll(Arrays.asList(city1,city2,city3));
		
		Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.INDIVIDUAL);
		client1.getPhones().addAll(Arrays.asList("27363323","93838393"));
		
		Adress adress1 = new Adress(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", client1, city1);
		Adress adress2 = new Adress(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", client1, city2);
		
		client1.getAdresses().addAll(Arrays.asList(adress1,adress2));
		
		clientRepository.saveAll(Arrays.asList(client1));
		adressRepository.saveAll(Arrays.asList(adress1,adress2));
		
	}

}
