package com.adopt.cacheExample;

import com.adopt.cacheExample.model.ClientService;
import com.adopt.cacheExample.service.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@SpringBootApplication
public class CacheExampleApplication implements CommandLineRunner {

	@Autowired
	private CacheManager cacheManager;

	private static final Logger LOG = LoggerFactory.getLogger(CacheExampleApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(CacheExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		long dbStartTime = System.currentTimeMillis();
		ClientService dbResult = cacheManager.getClientServiceCache().getOrderDB("servicerType");
		LOG.info("DB Result: {}", dbResult);
		LOG.info("Time used to fetch data from database: " + (System.currentTimeMillis() - dbStartTime));

		long dbStartTime1 = System.currentTimeMillis();
		ClientService dbResult1 = cacheManager.getClientServiceCache().getOrderDB("servicerType");
		LOG.info("DB Result: {}", dbResult1);
		LOG.info("Time used to fetch data from cache: " + (System.currentTimeMillis() - dbStartTime1));

	}

}
