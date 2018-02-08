package com.subhra.quiz.system.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ANSWER")
public class Answer {

	@Id
	@Column(name = "ANSWER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long answerId;
	
	@Column(name = "ANSWER_TEXT")
	private String answerText;
	

	public Answer() {};
	
	public Long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	
	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", answerText=" + answerText + "]";
	}
}
