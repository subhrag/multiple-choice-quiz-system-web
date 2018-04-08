package com.subhra.quiz.system.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ANSWER")
public class Answer {

	@Id
	@Column(name = "ANSWER_ID")
	@SequenceGenerator(name = "answer_seq" , sequenceName = "answer_sequence" , allocationSize = 1, initialValue=20 )
	@GeneratedValue(generator = "answer_seq", strategy = GenerationType.SEQUENCE)
	private Long answerId;
	
	//@Column(name = "ANSWER_NUMBER")
	//private String answerNumber;
	
	

	@Column(name = "ANSWER_TEXT")
	private String answerText;
	

	public Answer() {};
	
	public Long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
	
	/*public String getAnswerNumber() {
		return answerNumber;
	}

	public void setAnswerNumber(String answerNumber) {
		this.answerNumber = answerNumber;
	}
	*/
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
