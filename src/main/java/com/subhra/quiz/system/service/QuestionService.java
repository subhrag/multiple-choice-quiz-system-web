package com.subhra.quiz.system.service;

import java.util.List;
import java.util.Map;

import com.subhra.quiz.system.persistence.model.Question;

public interface QuestionService {

	
	public List<Question> getQuestions();
	public String validateAnswers(Map<Long, Long> answerMap);
	
}
