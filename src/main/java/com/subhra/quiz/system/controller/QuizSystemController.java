package com.subhra.quiz.system.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.subhra.quiz.system.dto.QuestionAnswerForm;
import com.subhra.quiz.system.exception.EmptyAnswerException;
import com.subhra.quiz.system.persistence.model.Question;
import com.subhra.quiz.system.service.QuestionService;

@Controller
public class QuizSystemController {

	@Autowired
	QuestionService questionService;

	@GetMapping("/get-question")
	public ModelAndView getQuestions() {
		ModelAndView modelAndView = new ModelAndView("viewQuestion");

		List<Question> questionList = questionService.getQuestions();

		modelAndView.addObject("questions", questionList);
		return modelAndView;
	}

	@PostMapping("/submit-answer")
	public ModelAndView addAnswers(@ModelAttribute("questionAndAnswer") QuestionAnswerForm answerSubmitted)
			throws EmptyAnswerException {

		ModelAndView modelAndView = new ModelAndView("result");
		Map<Long, Long> answerSubmittedMap = answerSubmitted.getQuestionAnswerMap();
		if (answerSubmittedMap == null || answerSubmittedMap.isEmpty()) {
			throw new EmptyAnswerException("All Questions are rquired to be answered !");
		}
		List<Question> questionList = questionService.getQuestions();
		String validAnswers = questionService.validateAnswers(answerSubmittedMap);
		modelAndView.addObject("questions", questionList);
		modelAndView.addObject("message", validAnswers);

		Map<String, String> questionAnswerMap = answerSubmitted.getQuestionAnswerMap().entrySet().stream()
				.collect(Collectors.toMap(e -> String.valueOf(e.getKey()), e -> String.valueOf(e.getValue())));
		modelAndView.addObject("submittedAnswers", questionAnswerMap);

		return modelAndView;
	}

	@ExceptionHandler(EmptyAnswerException.class)
	public String exceptionHandler(EmptyAnswerException msg) {
		
		return msg.getMessage();
	}

}
