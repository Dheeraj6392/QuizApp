package com.example.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizapp.entity.QuizEntity;

public interface QuestionDao extends JpaRepository<QuizEntity, Integer> {
List<QuizEntity>findByCategory(String category);

}
