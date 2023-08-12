package com.emintufan.carrentalsystem;

import jdk.jshell.execution.Util;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.hibernate.type.descriptor.java.DateJavaType;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class CarRentalSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(CarRentalSystemApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

}
