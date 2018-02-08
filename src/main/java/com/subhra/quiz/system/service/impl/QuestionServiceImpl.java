package com.subhra.quiz.system.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.subhra.quiz.system.persistence.dto.Question;
import com.subhra.quiz.system.persistence.dto.QuestionAnswer;
import com.subhra.quiz.system.persistence.repository.QuestionAnswerRepository;
import com.subhra.quiz.system.persistence.repository.QuestionRepository;
import com.subhra.quiz.system.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuestionAnswerRepository questionAnswerRepository;

	@Override
	public List<Question> getQuestions() {
		return questionRepository.findAll();
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
