package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.repository.CardRepository;

@Profile("!prod")
@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired CardRepository rep;
	@Override
	public void run(String... args) throws Exception {
	// rep.save(new Card());
		System.out.println("Any initializer code!!");
	}

}
