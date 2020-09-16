package com.wagnerrsoliveira.coursemc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wagnerrsoliveira.coursemc.domain.Adress;
import com.wagnerrsoliveira.coursemc.domain.Category;
import com.wagnerrsoliveira.coursemc.domain.City;
import com.wagnerrsoliveira.coursemc.domain.Client;
import com.wagnerrsoliveira.coursemc.domain.OrderItem;
import com.wagnerrsoliveira.coursemc.domain.OrderMain;
import com.wagnerrsoliveira.coursemc.domain.Payment;
import com.wagnerrsoliveira.coursemc.domain.PaymentBankSlip;
import com.wagnerrsoliveira.coursemc.domain.PaymentCard;
import com.wagnerrsoliveira.coursemc.domain.Product;
import com.wagnerrsoliveira.coursemc.domain.State;
import com.wagnerrsoliveira.coursemc.domain.enums.ClientType;
import com.wagnerrsoliveira.coursemc.domain.enums.PaymentStatus;
import com.wagnerrsoliveira.coursemc.repositories.AdressRepository;
import com.wagnerrsoliveira.coursemc.repositories.CategoryRepository;
import com.wagnerrsoliveira.coursemc.repositories.CityRepository;
import com.wagnerrsoliveira.coursemc.repositories.ClientRepository;
import com.wagnerrsoliveira.coursemc.repositories.OrderItemRepository;
import com.wagnerrsoliveira.coursemc.repositories.OrderRepository;
import com.wagnerrsoliveira.coursemc.repositories.PaymentRepository;
import com.wagnerrsoliveira.coursemc.repositories.ProductRepository;
import com.wagnerrsoliveira.coursemc.repositories.StateRepository;

@SpringBootApplication
public class CoursemcApplication implements CommandLineRunner {

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
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(CoursemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category category1 = new Category(null, "Computing");
		Category category2 = new Category(null, "Office");
		Category category3 = new Category(null, "Bed & bath");
		Category category4 = new Category(null, "Electronics");
		Category category5 = new Category(null, "Gardening");
		Category category6 = new Category(null, "Decoration");
		Category category7 = new Category(null, "Perfumery");
		
		Product product1 = new Product(null, "Computer", 2000.00);
		Product product2 = new Product(null, "Printer", 800.00);
		Product product3 = new Product(null, "Mouse", 80.00);

		category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
		category2.getProducts().addAll(Arrays.asList(product2));

		product1.getCategories().addAll(Arrays.asList(category1));
		product2.getCategories().addAll(Arrays.asList(category1, category2));
		product3.getCategories().addAll(Arrays.asList(category1));

		categoryRepository.saveAll(Arrays.asList(category1, category2,category3,category4,category5,category6,category7));
		productRepository.saveAll(Arrays.asList(product1, product2, product3));

		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "São Paulo");

		City city1 = new City(null, "Uberlândia", state1);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Campinas", state2);

		state1.getCities().addAll(Arrays.asList(city1));
		state2.getCities().addAll(Arrays.asList(city2, city3));

		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));

		Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.INDIVIDUAL);
		client1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Adress adress1 = new Adress(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", client1, city1);
		Adress adress2 = new Adress(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", client1, city2);

		client1.getAdresses().addAll(Arrays.asList(adress1, adress2));

		clientRepository.saveAll(Arrays.asList(client1));
		adressRepository.saveAll(Arrays.asList(adress1, adress2));

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		OrderMain order1 = new OrderMain(null, dateFormat.parse("30/07/2020 10:32"), client1, adress1);
		OrderMain order2 = new OrderMain(null, dateFormat.parse("10/08/2020 10:32"), client1, adress2);
		
		Payment payment1 = new PaymentCard(null,PaymentStatus.PAID,order1,6);
		order1.setPayment(payment1);
		

		Payment payment2 = new PaymentBankSlip(null,PaymentStatus.PENDING,order2,dateFormat.parse("20/10/2020 00:00"),null);
		order2.setPayment(payment2);
		
		
		client1.getOrders().addAll(Arrays.asList(order1,order2));
		
		orderRepository.saveAll(Arrays.asList(order1,order2));
		
		paymentRepository.saveAll(Arrays.asList(payment1,payment2));
		
		OrderItem item1 = new OrderItem(order1, product1, 0.00, 1, 2000.00);
		OrderItem item2 = new OrderItem(order1, product3, 0.00, 2, 80.00);
		OrderItem item3 = new OrderItem(order2, product2, 100.00, 1, 800.00);
		
		order1.getItems().addAll(Arrays.asList(item1,item2));
		order2.getItems().addAll(Arrays.asList(item3));
		
		product1.getItems().addAll(Arrays.asList(item1));
		product2.getItems().addAll(Arrays.asList(item3));
		product3.getItems().addAll(Arrays.asList(item2));
		
		orderItemRepository.saveAll(Arrays.asList(item1,item2,item3));

	}

}
