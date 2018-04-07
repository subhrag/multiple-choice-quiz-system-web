package com.subhra.quiz.system.dto.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "questions")
//@XmlType(propOrder = { "questionText", "listOfAnswers"})
@XmlSeeAlso({Questions.class})
public class QuestionsXml {

	private List<Questions> rootQuestion;
	
	public QuestionsXml() {

	}

	public List<Questions> getRootQuestion() {
		return rootQuestion;
	}

	@XmlElement(name = "question")
	public void setRootQuestion(List<Questions> rootQuestion) {
		this.rootQuestion = rootQuestion;
	}



}
