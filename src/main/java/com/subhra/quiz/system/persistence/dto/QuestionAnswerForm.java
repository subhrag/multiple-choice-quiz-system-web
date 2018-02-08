package com.subhra.quiz.system.persistence.dto;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuestionAnswerForm {

	List<Question> questionList = new ArrayList<>(); ;
	Map<Long, Long> questionAnswerMap = new LinkedHashMap<>();;
	
	


	public List<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}
	public Map<Long, Long> getQuestionAnswerMap() {
		return questionAnswerMap;
	}
	public void setQuestionAnswerMap(Map<Long, Long> questionAnswerMap) {
		this.questionAnswerMap = questionAnswerMap;
	}
}