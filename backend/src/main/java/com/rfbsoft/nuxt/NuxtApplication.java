package com.rfbsoft.nuxt;

import com.rfbsoft.nuxt.entities.Book;
import com.rfbsoft.nuxt.entities.Role;
import com.rfbsoft.nuxt.entities.User;
import com.rfbsoft.nuxt.repositories.BookRepository;
import com.rfbsoft.nuxt.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class NuxtApplication {



	public static void main(String[] args) {
		SpringApplication.run(NuxtApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository , UserService userService) {
		return (args) -> {

			// create books
			bookRepository.save(new Book("Thinking in Java", "0136597238"));
			bookRepository.save(new Book("Beginning Java 2", "1861002238"));
			bookRepository.save(new Book("Java Gently", "0201342979"));
			bookRepository.save(new Book("Java 2 Platform Unleashed", "0672316315"));

			// fetch all books
			System.out.println("Books found with findAll():");
			System.out.println("---------------------------");
			for (Book book : bookRepository.findAll()) {
				System.out.println(book.toString());
			}
			System.out.println();

			// fetch book by id
			Book book = bookRepository.findById(1L).get();
			System.out.println("Book found with findById(1L):");
			System.out.println("-----------------------------");
			System.out.println(book.toString());
			System.out.println();

			// fetch book by ISBN
			Book bookWithIBSN = bookRepository.findByIsbn("0201342979");
			System.out.println("Book found with findByEmail('0201342979'):");
			System.out.println("------------------------------------------");
			System.out.println(bookWithIBSN.toString());
			System.out.println();

			// fetch all books that contain keyword `java`
			System.out.println("Books that contain keyword 'java':");
			System.out.println("----------------------------------");
			for (Book b : bookRepository.findByTitleContaining("java")) {
				System.out.println(b.toString());
			}
			System.out.println();

			// update book title
			Book bookUpdate = bookRepository.findById(2L).get();

			bookUpdate.setTitle("Java Gently 2nd Edition");
			bookRepository.save(bookUpdate);
			System.out.println("Update book title:");
			System.out.println("------------------");
			System.out.println(bookUpdate.toString());
			System.out.println();

			// total books in DB
			System.out.println("Total books in DB:");
			System.out.println("------------------");
			System.out.println(bookRepository.count());
			System.out.println();

			// delete all books
//			bookRepository.deleteAll();



			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword("admin");
			admin.setEmail("admin@email.com");
			admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

			userService.signup(admin);

			User client = new User();
			client.setUsername("client");
			client.setPassword("client1234");
			client.setEmail("client@email.com");
			client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

			userService.signup(client);


		};
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}






}
