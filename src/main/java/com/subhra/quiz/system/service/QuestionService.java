package com.subhra.quiz.system.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.springframework.web.multipart.MultipartFile;

import com.subhra.quiz.system.dto.jaxb.Questions;
import com.subhra.quiz.system.dto.jaxb.QuestionsXml;
import com.subhra.quiz.system.persistence.model.Question;

public interface QuestionService {

	
	public List<Question> getQuestions();
	public String validateAnswers(Map<Long, Long> answerMap);
	//public List<Questions> getQuestionsFromXml(MultipartFile multiPartFile) throws JAXBException;
	public void addQuestions(MultipartFile multiPartFile) throws JAXBException, IOException ;
	//public void getXmlFromQuestions() throws JAXBException;
}
