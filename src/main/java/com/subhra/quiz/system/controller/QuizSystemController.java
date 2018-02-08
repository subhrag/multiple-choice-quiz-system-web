package com.subhra.quiz.system.controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.subhra.quiz.system.persistence.dto.Question;
import com.subhra.quiz.system.persistence.dto.QuestionAnswerForm;
import com.subhra.quiz.system.service.QuestionService;

@Controller
public class QuizSystemController {

	@Autowired
	QuestionService questionService;
	
	QuestionAnswerForm questionAnswerForm = new QuestionAnswerForm();
	
	
	@GetMapping("/get-question")
	public ModelAndView getQuestions() {
		ModelAndView modelAndView = new ModelAndView("viewQuestion");
		
		List<Question> questionList = questionService.getQuestions();

		modelAndView.addObject("questions", questionList);
		modelAndView.addObject("questionAnswerForm", questionAnswerForm);
		return modelAndView;
	}
	
	@PostMapping("/submit-answer")
	public ModelAndView addAnswer(@ModelAttribute("questionAndAnswer") QuestionAnswerForm answerSubmitted) {
		System.out.println(answerSubmitted.getQuestionAnswerMap()+".....answerSubmitted");
		
		ModelAndView modelAndView = new ModelAndView("result");
		
		Map<String,String> questionAnswerMap = answerSubmitted.getQuestionAnswerMap().entrySet().stream()
		.collect(Collectors.toMap(e -> String.valueOf(e.getKey()),
				e -> String.valueOf(e.getValue())));
				
		List<Question> questionList = questionService.getQuestions();
		String validAnswers = questionService.validateAnswers(answerSubmitted.getQuestionAnswerMap());
		modelAndView.addObject("questions", questionList);
		modelAndView.addObject("message", validAnswers);
		modelAndView.addObject("submittedAnswers",questionAnswerMap);
		
		return modelAndView;
	}
	
}
