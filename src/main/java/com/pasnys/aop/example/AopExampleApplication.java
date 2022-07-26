package com.pasnys.aop.example;

import com.pasnys.aop.example.service.UserBussines;
import com.pasnys.aop.example.service.ClientBussines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * In this method we implement the CommandLineRunner interface to run the methods that we need to execute to be
 * intercepted it by the AOP.
 *
 * In this example we will intercept all the methods that belong to the package com.pasnys.aop.example.service
 */
@SpringBootApplication
public class AopExampleApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserBussines bussines;

	@Autowired
	private ClientBussines bussines2;

	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(AopExampleApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		logger.info(bussines.requestAcces());
		try {
			logger.info(bussines2.calculateSomething());
		} catch(Exception e){
			e.getMessage();
		}

	}
}
