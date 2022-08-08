package com.rest.webservices.restfullwebservices;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfullWebServicesApplication {

	public static void main(String[] args) throws ParseException, IOException {
//		System.out.println(new ExcelImport().excelConvertor());
	
		SpringApplication.run(RestfullWebServicesApplication.class, args);
		
	}

}