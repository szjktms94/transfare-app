package com.monese.interviewtest.transferapp;

import com.monese.interviewtest.transferapp.controller.TransferController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransferappApplicationTests {

	@Test
	public void saveTransaction() {
		TransferController transactionController = new TransferController();

	}

}
