package com.example.demo;

import com.example.demo.before.Contract;
import com.example.demo.before.ContractRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private ContractRepository repository;

	@Test
	public void add() {
		Contract contract = new Contract(
				"무한상사",
				1.0,
				"percent",
				"round"
		);
		repository.save(contract);
		Contract saved = repository.findAll().get(0);
		assertThat(saved.getCommission(), is(1.0));


	}

}
