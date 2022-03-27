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
	//Runs the SpringBoot server

	@Autowired
	QuestionRepository questionRepository;
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
		questionRepository.save(new Question("0",
				"What's the decorator to make a simple class of data in python?",
				new QuestionResponse[]{
						new QuestionResponse(0, "@Classdata"),
						new QuestionResponse(1, "@dataMethod"),
						new QuestionResponse(2, "@dataclass"),
						new QuestionResponse(3, "@DataClass"),
						new QuestionResponse(4, "@ClassData"),
				},
				2));
		questionRepository.save(new Question("1",
				"What's the difference between list and tuple?",
				new QuestionResponse[]{
						new QuestionResponse(0, "in operator"),
						new QuestionResponse(1, "mutability"),
						new QuestionResponse(2, "indexing"),
						new QuestionResponse(3, "size"),
						new QuestionResponse(4, "speed"),
				},
				1));
		questionRepository.save(new Question("2",
				"Denotion for private methods in python?",
				new QuestionResponse[]{
						new QuestionResponse(0, "my_func_"),
						new QuestionResponse(1, "my_func__"),
						new QuestionResponse(2, "#my_func"),
						new QuestionResponse(3, "_my_func"),
						new QuestionResponse(4, "my_func#"),
				},
				3));
		questionRepository.save(new Question("3",
				"Does python support multiple inheritance?",
				new QuestionResponse[]{
						new QuestionResponse(0, "yes"),
						new QuestionResponse(1, "no"),
				},
				0));
		questionRepository.save(new Question("4",
				"Angular syntax for saving and reading local storage",
				new QuestionResponse[]{
						new QuestionResponse(0, "localStorage.setData('myObjectItem', myObject.items()); localStorage.getData('myObjectItem');"),
						new QuestionResponse(1, "localStorage.setItem('myObjectItem', myObject.items()); localStorage.getItem('myObjectItem');"),
				},
				1));
	}

	public void create1Survey() {
		System.out.println("Testing survey creation");
		surveyRepository.save(new Survey(new Employee("liam", "sparling"),
				new SurveyResponse[]{
						new SurveyResponse("0", 2),
						new SurveyResponse("1", 1),
						new SurveyResponse("2", 3),
						new SurveyResponse("3", 0),
						new SurveyResponse("4", 0)}));
	}

	public void run(String... args) {
		System.out.println("Starting CMD");
//		create1Question();
//		create1Survey();
		System.out.println("Ending CMD");
	}

}
