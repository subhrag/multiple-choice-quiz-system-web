package com.subhra.quiz.system.persistence.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "QUESTION_ANSWER")
public class QuestionAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "QUESTION_ID")
	private Long Id;
	
	
	@Column(name = "ANSWER_ID", nullable = false)
	private Long correctAnswerId;
	
	
	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public Long getCorrectAnswerId() {
		return correctAnswerId;
	}


	public void setCorrectAnswerId(Long correctAnswerId) {
		this.correctAnswerId = correctAnswerId;
	}


	public QuestionAnswer() {};

}