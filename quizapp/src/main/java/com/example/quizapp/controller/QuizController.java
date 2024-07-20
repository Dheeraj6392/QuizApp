package com.example.quizapp.controller;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.entity.QuizEntity;
import com.example.quizapp.entity.ResultEntity;
import com.example.quizapp.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/questions")
public class QuizController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private QuestionService questionService;
	@Autowired
	ResultEntity resultEntity;

	@GetMapping("/all")
	public List<QuizEntity> getAllQuestions() {
		log.warn("hello");
		log.info("hii");
		return questionService.getAllQuestions();
	}
	@GetMapping("/PythonJava")
	public List<Map<String,Object>> getAllPythonJavaQuestions() {
		log.warn("I am from PythonJava");
		log.info("I am from PythonJava");
		return questionService.getAllPythonJavaQuestions();
	}
	
	@GetMapping("/English")
	public List<Map<String,Object>> getAllEnglishQuestions() {
		log.warn("I am from English");
		log.info("I am from English");
		return questionService.getAllEnglishQuestions();
	}
	
	@GetMapping("/Hindi")
	public List<Map<String,Object>> getAllHindiQuestions() {
		log.warn("I am from PythonJava");
		log.info("I am from PythonJava");
		return questionService.getAllHindiQuestions();
	}
	
	@GetMapping("/Hystory")
	public List<Map<String,Object>> getAllHystoryQuestions() {
		log.warn("I am from PythonJava");
		log.info("I am from PythonJava");
		return questionService.getAllHystoryQuestions();
	}

	@PostMapping("/add")
	public QuizEntity addQuestion(@RequestBody QuizEntity quizEntity) {
		return questionService.saveQuestion(quizEntity);
	}

	@GetMapping("category/{category}")
	public List<QuizEntity> getQuestionByCategory(@PathVariable String category) {
		return questionService.getQuestionByCategory(category);
	}

	
	 @PostMapping("/countMatches")
    public ResultEntity countMatches(@RequestBody List<String> inputArray){

		 List<Integer> questionId= new ArrayList<>();
		 List<Integer> questionAnsIn= new ArrayList<>();
		 for (int i = 0; i < inputArray.size(); i++) {
			 questionId.add(Integer.parseInt(inputArray.get(i).split("_")[0]));
			 questionAnsIn.add(Integer.parseInt(inputArray.get(i).split("_")[1]));
		 }
		 System.out.println(questionId);
		 System.out.println(questionAnsIn);
		 String stringQuesId = questionId.stream()
                 .map(String::valueOf)
                 .collect(Collectors.joining(",", "(", ")"));
		
		
		 
		 List<Boolean> correctAns = new ArrayList<>();
		 
		 
		 
        ResultEntity rs = new ResultEntity();
        
        int count = 0;

        String queryCorrectAnswers = "select correct from Questions where id in "+ stringQuesId;
        String queryDistinctCategories = "SELECT DISTINCT category FROM Questions where id in "+ stringQuesId;
        String queryAllQuestions = "SELECT * FROM Questions  where id in "+ stringQuesId;

        List<Map<String, Object>> questionsTable = jdbcTemplate.queryForList(queryAllQuestions);
        List<Integer> correctAnswers = jdbcTemplate.queryForList(queryCorrectAnswers, Integer.class);
        List<String> categories = jdbcTemplate.queryForList(queryDistinctCategories, String.class);
       
        
        // Initialize correct answer count map for each category
        Map<String, Integer> correctWithCategory = new HashMap<>();
        Map<String, Integer> questionWithCategory = new HashMap<>();
        for (String category : categories) {
            correctWithCategory.put(category, 0);
            questionWithCategory.put(category, 0);
            
        }
//        int matchingCount=0;
//        for (int i = 0; i < questionAnsIn.size(); i++) {
//            if (questionAnsIn.get(i).equals(correctAnswers.get(i))) {
//                matchingCount++;
//            }
//        }
        for (int i = 0; i < questionAnsIn.size(); i++) {
        	 String cat = (String) questionsTable.get(i).get("category");      	 
        	 questionWithCategory.put(cat, correctWithCategory.get(cat) + 1);
            if (correctAnswers.get(i).equals(questionAnsIn.get(i))) {
                count++;
                correctAns.add(true);

                // Increment the count for the corresponding category
                String category = (String) questionsTable.get(i).get("category");
                correctWithCategory.put(category, correctWithCategory.get(category) + 1);
            } else {
                correctAns.add(false);
            }
        }

        rs.setCorrectAns(correctAns);
        rs.setScore(count);
        rs.setPercentageCorrect((count * 100) / correctAnswers.size());
        rs.setCorrectWithCategory(correctWithCategory);
        rs.setQuestionWithCategory(questionWithCategory);

        return rs;
    }
}
