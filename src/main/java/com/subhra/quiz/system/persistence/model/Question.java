package com.subhra.quiz.system.persistence.model;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTION")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "QUESTION_ID")
	private Long questionId;
	
	//@Column(name = "QUESTION_NUMBER", nullable = false)
	//private String questionNumber;
	
	@Column(name = "QUESTION_DESCRIPTION", nullable = false)
	private String questionDescription;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "QUESTION_ID" , nullable = false)
	private List<Answer> answerList;
	
	
	public Question() {};

	public Long getQuestionId() {
		return questionId;
	}


	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}


	/*public String getQuestionNumber() {
		return questionNumber;
	}


	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}*/


	public String getQuestionDescription() {
		return questionDescription;
	}


	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}


	public List<Answer> getAnswerList() {
		return answerList;
	}


	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}


	
	
}
