package com.groupsurvey.surveymanager.restcontroller;

import com.groupsurvey.surveymanager.model.Survey;
import com.groupsurvey.surveymanager.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public ResponseEntity addSurvey(@RequestBody Survey survey) {
        surveyService.addSurvey(survey);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateSurveyResponses(@RequestBody Survey survey) {
        surveyService.updateSurveyResponses(survey);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Survey>> getAllSurveys() {
        return ResponseEntity.ok(surveyService.getAllSurveys());
    }

    @GetMapping("/{firstName}/{lastName}")
    public ResponseEntity<Survey> getSurveyByName(@PathVariable String firstName, @PathVariable String lastName) {
        System.out.println(firstName);
        System.out.println(lastName);
        return ResponseEntity.ok(surveyService.getSurveyByName(firstName, lastName));
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public ResponseEntity deleteSurvey(@PathVariable String firstName, @PathVariable String lastName) {
        surveyService.deleteSurvey(firstName, lastName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/results/{firstName}/{lastName}")
    public ResponseEntity<Float> checkSurvey(@PathVariable String firstName, @PathVariable String lastName) {
        return ResponseEntity.ok(surveyService.checkSurvey(firstName, lastName));
    }

}
