package com.example.quizapp.entity;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ResultEntity {

	private List<Boolean> correctAns;
	private int score;
	private int percentageCorrect;

	private Map<String, Integer> CorrectWithCategory;
	private Map<String, Integer> questionWithCategory;

	public Map<String, Integer> getQuestionWithCategory() {
		return questionWithCategory;
	}

	public ResultEntity(List<Boolean> correctAns, int score, int percentageCorrect,
			Map<String, Integer> correctWithCategory, Map<String, Integer> questionWithCategory) {
		super();
		this.correctAns = correctAns;
		this.score = score;
		this.percentageCorrect = percentageCorrect;
		CorrectWithCategory = correctWithCategory;
		this.questionWithCategory = questionWithCategory;
	}
	public void setQuestionWithCategory(Map<String, Integer> questionWithCategory) {
		this.questionWithCategory = questionWithCategory;
	}

	public ResultEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public List<Boolean> getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(List<Boolean> correctAns) {
		this.correctAns = correctAns;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPercentageCorrect() {
		return percentageCorrect;
	}

	public void setPercentageCorrect(int percentageCorrect) {
		this.percentageCorrect = percentageCorrect;
	}

	public Map<String, Integer> getCorrectWithCategory() {
		return CorrectWithCategory;
	}

	public void setCorrectWithCategory(Map<String, Integer> correctWithCategory) {
		CorrectWithCategory = correctWithCategory;
	}

}
