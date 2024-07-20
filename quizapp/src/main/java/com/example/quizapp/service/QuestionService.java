package com.example.quizapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.quizapp.dao.QuestionDao;
import com.example.quizapp.entity.QuizEntity;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<QuizEntity> getAllQuestions() {
    	
        return questionDao.findAll();
    }
    public List<Map<String,Object>> getAllPythonJavaQuestions() {
         String PythonJava = "select *  from Questions where category = 'Python' or category =  'java'";
         List<Map<String,Object>> pythonJava = jdbcTemplate.queryForList(PythonJava);
        return pythonJava;
    }
    
    public List<Map<String,Object>> getAllHindiQuestions() {
        String Hindi = "select *  from Questions where category = 'Hindi'";
        List<Map<String,Object>> hindi = jdbcTemplate.queryForList(Hindi);
       return hindi;
   }
    
    public List<Map<String,Object>> getAllEnglishQuestions() {
        String English = "select *  from Questions where category = 'English'";
        List<Map<String,Object>> english = jdbcTemplate.queryForList(English);
       return english;
   }
    
    public List<Map<String,Object>> getAllHystoryQuestions() {
        String Hystory = "select *  from Questions where category = 'Hystory'";
        List<Map<String,Object>> hystory = jdbcTemplate.queryForList(Hystory);
       return hystory;
   }
    
    public QuizEntity saveQuestion(QuizEntity quizEntity) {
        return questionDao.save(quizEntity);
    }
    
    public List<QuizEntity> getQuestionByCategory(String category) {
    	return questionDao.findByCategory(category);
    }
}
