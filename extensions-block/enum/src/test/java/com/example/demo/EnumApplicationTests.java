package com.example.demo;

import com.example.demo.after.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class EnumApplicationTests {

	@Autowired
	private EnumContractRepository enumContractRepository;

	@Test
	public void add() {
		enumContractRepository.save(new EnumContract(
				"무한상사",
				1.0,
				EnumContract.CommissionType.MONEY,
				EnumContract.CommissionCutting.ROUND));

		EnumContract saved = enumContractRepository.findOne(1L);

		assertThat(saved.getCommissionType(), is(EnumContract.CommissionType.MONEY));
		assertThat(saved.getCommissionCutting(), is(EnumContract.CommissionCutting.ROUND));
	}
}
