package com.subhra.quiz.system.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.subhra.quiz.system.dto.jaxb.Answers;
import com.subhra.quiz.system.dto.jaxb.Questions;
import com.subhra.quiz.system.dto.jaxb.QuestionsXml;
import com.subhra.quiz.system.persistence.model.Answer;
import com.subhra.quiz.system.persistence.model.Question;
import com.subhra.quiz.system.persistence.model.QuestionAnswer;
import com.subhra.quiz.system.persistence.repository.AnswerRepository;
import com.subhra.quiz.system.persistence.repository.QuestionAnswerRepository;
import com.subhra.quiz.system.persistence.repository.QuestionRepository;
import com.subhra.quiz.system.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	QuestionAnswerRepository questionAnswerRepository;

	@Override
	public List<Question> getQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public void addQuestions(MultipartFile multiPartFile) throws JAXBException, IOException {
		List<Question> savedQuestionList = this.getQuestions();
		List<Questions> questionsList = this.getQuestionsFromXml(multiPartFile);

		List<Questions> toBeAddedquestionList = questionsList.stream()
				.filter(l1 -> savedQuestionList.stream()
						.noneMatch(l2 -> l2.getQuestionDescription().equals(l1.getQuestionText())))
				.collect(Collectors.toList());

		List<Questions> toBeUpdatedquestionList = questionsList.stream()
				.filter(l1 -> savedQuestionList.stream()
						.anyMatch(l2 -> l2.getQuestionDescription().equals(l1.getQuestionText())))
				.collect(Collectors.toList());

		if (!toBeUpdatedquestionList.isEmpty() && toBeUpdatedquestionList != null) {
			this.updateAnswers(toBeUpdatedquestionList, savedQuestionList);
		}
		if (!toBeAddedquestionList.isEmpty() && toBeAddedquestionList != null) {

			for (Questions toBeAddedQuestions : toBeAddedquestionList) {

				this.saveQuestionDto(toBeAddedQuestions, toBeAddedQuestions.getListOfAnswers());
				/*
				 * 
				 * List<Answer> answerList = new ArrayList<>(); Question questionDto = new
				 * Question();
				 * 
				 * questionDto.setQuestionDescription(questions.getQuestionText()); for (Answers
				 * answer : questions.getListOfAnswers()) { Answer answerDto = new Answer();
				 * answerDto.setAnswerText(answer.getAnswerText()); answerList.add(answerDto); }
				 * questionDto.setAnswerList(answerList);
				 * 
				 * questionRepository.save(questionDto);
				 * 
				 */}

		}

	}

	public void updateAnswers(List<Questions> inputQuestionList, List<Question> dbQuestionList) {
		for (Questions inputQuestion : inputQuestionList) {
			for (Question dbQuestion : dbQuestionList) {
				if (inputQuestion.getQuestionText().equalsIgnoreCase(dbQuestion.getQuestionDescription())) {
					List<Answers> inputAnswers = inputQuestion.getListOfAnswers();
					List<Answer> dbAnswers = dbQuestion.getAnswerList();
					List<Answers> diffAnswers = inputAnswers.stream().filter(
							a1 -> dbAnswers.stream().noneMatch(a2 -> a2.getAnswerText().equals(a1.getAnswerText())))
							.collect(Collectors.toList());
					
					if (diffAnswers == null || diffAnswers.isEmpty() ) {
						if(inputAnswers.size()<dbAnswers.size()) {
							
							questionRepository.delete(dbQuestion.getQuestionId());
							this.saveQuestionDto(inputQuestion, inputAnswers);
						
						}
					} 

						questionRepository.delete(dbQuestion.getQuestionId());
						this.saveQuestionDto(inputQuestion, inputAnswers);
					
					// questionRepository.findOne(dbQuestion.)
				}
			}
		}
	}

	public void saveQuestionDto(Questions inputQuestion, List<Answers> answerList) {

		List<Answer> answersList = new ArrayList<>();
		Question questionDto = new Question();

		questionDto.setQuestionDescription(inputQuestion.getQuestionText());
		for (Answers updateAnswer : answerList) {
			Answer answer = new Answer();
			answer.setAnswerText(updateAnswer.getAnswerText());
			answersList.add(answer);
		}
		questionDto.setAnswerList(answersList);
		questionRepository.save(questionDto);

	}

	// Unmarshalling
	private List<Questions> getQuestionsFromXml(MultipartFile multiPartFile) throws JAXBException, IOException {

		StreamSource source = null;
		JAXBContext jaxbContext = JAXBContext.newInstance(QuestionsXml.class);
		JAXBIntrospector introspector = jaxbContext.createJAXBIntrospector();
		File file = this.convert(multiPartFile);
		source = new StreamSource(file);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		/*
		 * QuestionsXml questionsXml = (QuestionsXml) ((javax.xml.bind.JAXBElement)
		 * jaxbUnmarshaller .unmarshal(new
		 * File("src\\main\\resources\\quiz.xml"))).getValue();
		 */

		JAXBElement<QuestionsXml> element = jaxbUnmarshaller.unmarshal(source, QuestionsXml.class);
		QuestionsXml questionsXml = (QuestionsXml) introspector.getValue(element);
		// QuestionsXml questionsXml= (QuestionsXml)jaxbUnmarshaller.unmarshal(file);
		System.out.println(".........." + questionsXml);
		List<Questions> questionList = questionsXml.getRootQuestion();
		return questionList;

	}

	private static File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	// Marshalling
	private void getXmlFromQuestions() throws JAXBException {
		QuestionsXml questionsXml = new QuestionsXml();

		Questions question = new Questions();
		question.setQuestionId("1");
		question.setQuestionText("This is 1?");

		Answers answer1 = new Answers();
		answer1.setAnswerId("1");
		answer1.setAnswerText("ans 1");
		Answers answer2 = new Answers();
		answer2.setAnswerId("2");
		answer2.setAnswerText("ans 2");
		List<Answers> answerList = new ArrayList<>();
		answerList.add(answer1);
		answerList.add(answer2);
		question.setListOfAnswers(answerList);
		List<Questions> questionsList = new ArrayList<>();
		questionsList.add(question);
		questionsXml.setRootQuestion(questionsList);
		File file = new File("src\\main\\resources\\quiz.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(QuestionsXml.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(questionsXml, file);
		jaxbMarshaller.marshal(questionsXml, System.out);
	}

	@Override
	public String validateAnswers(Map<Long, Long> answerMap) {

		List<QuestionAnswer> questionAnswerList = questionAnswerRepository.findAll();
		int score = 0;
		Map<Long, Long> questionAnswerMap = new LinkedHashMap<>();
		questionAnswerMap = questionAnswerList.stream()
				.collect(Collectors.toMap(QuestionAnswer::getId, QuestionAnswer::getCorrectAnswerId));

		for (Entry<Long, Long> mapEntry : answerMap.entrySet()) {

			Long answerKey = (Long) mapEntry.getKey();

			if (questionAnswerMap.get(answerKey).equals(mapEntry.getValue())) {

				score++;
			}
		}

		return createDisplayMessage(score, answerMap.size());
	}

	private String createDisplayMessage(int score, int questionCount) {
		String message = "";
		if (score == questionCount / 2) {
			message = "Average ";
		} else if (score > questionCount / 2) {
			message = "Good ";
		} else {
			message = "Bad ";
		}

		return message + score + "/" + questionCount;
	}

}
