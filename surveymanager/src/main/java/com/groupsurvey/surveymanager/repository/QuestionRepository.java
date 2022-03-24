package com.groupsurvey.surveymanager.repository;

import com.groupsurvey.surveymanager.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, String> {
    // Repository for the question
    @Query("{'_id': ?0}")
    Optional<Question> findById(String id); // for finding a question using its id
}
