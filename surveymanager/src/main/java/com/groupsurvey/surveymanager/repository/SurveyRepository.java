package com.groupsurvey.surveymanager.repository;

import com.groupsurvey.surveymanager.model.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface SurveyRepository extends MongoRepository<Survey, String> {

    @Query("{'_id.firstName': '?0', '_id.lastName': '?1'}")
    Optional<Survey> findByName(String firstName, String lastName);

    @Query("{'_id.firstName': '?0', '_id.lastName': '?1'}")
    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    @Query("{'_id.firstName': '?0', '_id.lastName': '?1'}")
    void deleteByName(String firstName, String lastName);
}
