package com.groupsurvey.surveymanager;

import com.groupsurvey.surveymanager.model.*;
import com.groupsurvey.surveymanager.repository.QuestionRepository;
import com.groupsurvey.surveymanager.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
@EnableMongoRepositories
public class SurveyManagerApplication implements CommandLineRunner {

	@Autowired
	QuestionRepository questionRepo;
	@Autowired
	SurveyRepository surveyRepository;

	public static void main(String[] args) {
		SpringApplication.run(SurveyManagerApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin", "Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	public void create1Question() {
		System.out.println("Testing question creation");
		questionRepo.save(new Question(1,
				"What's the annotation to make a simple class of data in python?",
				new QuestionResponse[]{
						new QuestionResponse(0, "@Classdata"),
						new QuestionResponse(1, "@dataMethod"),
						new QuestionResponse(2, "@dataclass"),
						new QuestionResponse(3, "@DataClass"),
						new QuestionResponse(4, "@ClassData")},
		2));
	}

	public void create1Survey() {
		System.out.println("Testing survey creation");
		surveyRepository.save(new Survey(new Employee("Liam", "Sparling"),
				new SurveyResponse[]{
						new SurveyResponse(1, 2),
						new SurveyResponse(2, 0)}));
	}

	public void run(String... args) {
		System.out.println("Starting CMD");
		create1Question();
		create1Survey();
		System.out.println("Ending CMD");
	}

}
