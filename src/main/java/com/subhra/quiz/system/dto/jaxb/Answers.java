package com.subhra.quiz.system.dto.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement()
public class Answers {

	private String answerId;
	
	private String answerText;
	
	public Answers() {}

	public String getAnswerId() {
		return answerId;
	}
	@XmlAttribute(name = "id")
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getAnswerText() {
		return answerText;
	}

	@XmlValue()
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	
}
