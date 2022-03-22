package com.groupsurvey.surveymanager;

import com.groupsurvey.surveymanager.model.Employee;
import com.groupsurvey.surveymanager.model.Question;
import com.groupsurvey.surveymanager.model.Survey;
import com.groupsurvey.surveymanager.repository.QuestionRepository;
import com.groupsurvey.surveymanager.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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

	public void create1Question() {
		System.out.println("Testing question creation");
		questionRepo.save(new Question(1, "Is lemon yum???", new String[]{"yes", "no"}, "no"));
		questionRepo.save(new Question(2, "Juicy yes???", new String[]{"yes", "no"}, "yes"));
		System.out.println("Done that");
	}

	public void create1Survey() {
		System.out.println("Testing survey creation");
		surveyRepository.save(new Survey(new Employee("Liam", "Sparling"), new String[]{"yes", "yes"}));
		System.out.println("Done that");
	}

	public void run(String... args) {
		System.out.println("Starting CMD");
		create1Question();
		create1Survey();
		System.out.println("Ending CMD");
	}

}
