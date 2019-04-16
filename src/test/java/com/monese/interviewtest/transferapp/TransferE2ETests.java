package com.monese.interviewtest.transferapp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransferE2ETests {

    //transfer Money back
    @After
    public void clean() throws IOException {
        String json = "{\"sourceAccountId\":2,\"targetAccountId\": 1,\"amount\" : 200.5}";
            callTransferEndpoint(json);
    }

    @Test
    public void givenTwoAccount_whenMoneyTransferHappened_thenTwoAccountBalanceCorrect()
            throws IOException {

        // Given an account (id:1) with balance 1000.0
        double sourceAccountBalance = callGetBalanceEndpoint("1");
        assertEquals(sourceAccountBalance, 1000,0.001);

        // and Given an different account (id:2) with balance 0.0
        double targetAccountBalance = callGetBalanceEndpoint("2");
        assertEquals(targetAccountBalance, 0,0.001);

        // When transfer endpoint is called
        String json = "{\"sourceAccountId\":1,\"targetAccountId\": 2,\"amount\" : 200.5}";
        callTransferEndpoint(json);

        // Then the first account's balance is 800 and the second 400
        double sourceAccountBalanceAfterTransfer = callGetBalanceEndpoint("1");
        assertEquals(sourceAccountBalanceAfterTransfer, 799.5,0.001);

        // and Given an diffrent account (id:2) with balance 0.0
        double targetAccountBalanceAfterTransfer = callGetBalanceEndpoint("2");
        assertEquals(targetAccountBalanceAfterTransfer, 200.5,0.001);
    }

    private void callTransferEndpoint(String jsonContent) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/transaction");
        StringEntity entity = new StringEntity(jsonContent);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        client.execute(httpPost);
        client.close();
    }

    private double callGetBalanceEndpoint(String accountId) throws IOException {
        HttpUriRequest request1 = new HttpGet("http://localhost:8080/getBalance?accountId=" + accountId);
        HttpResponse response1 = HttpClientBuilder.create().build().execute(request1);
        HttpEntity entity1 = response1.getEntity();
        return Double.parseDouble(EntityUtils.toString(entity1));
    }
}
