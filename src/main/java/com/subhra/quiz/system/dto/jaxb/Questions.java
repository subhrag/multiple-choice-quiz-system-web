package com.subhra.quiz.system.dto.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement()
@XmlType(propOrder = { "questionText", "listOfAnswers"})
public class Questions {

	private String questionId;
	private String questionText;
	private List<Answers> listOfAnswers;

	public Questions() {

	}

	public String getQuestionId() {
		return questionId;
	}

	@XmlAttribute(name = "id")
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionText() {
		return questionText;
	}

	@XmlElement(name = "title")
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<Answers> getListOfAnswers() {
		return listOfAnswers;
	}

	@XmlElement(name = "answer")
	public void setListOfAnswers(List<Answers> listOfAnswers) {
		this.listOfAnswers = listOfAnswers;
	}

}
