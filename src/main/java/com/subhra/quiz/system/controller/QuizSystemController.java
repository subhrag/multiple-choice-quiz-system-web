package com.subhra.quiz.system.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.subhra.quiz.system.dto.QuestionAnswerForm;
import com.subhra.quiz.system.exception.EmptyAnswerException;
import com.subhra.quiz.system.persistence.model.Question;
import com.subhra.quiz.system.service.QuestionService;

@Controller
public class QuizSystemController {

	@Autowired
	QuestionService questionService;
	
	@GetMapping("/upload-file")
	public ModelAndView uploadQuestionFile() {
		ModelAndView modelAndView = new ModelAndView("viewQuestionsFromXml");
		return modelAndView;
				
	}

	
	
	@PostMapping("/add-questions")
	public RedirectView  addQuestionsFromXml(@RequestParam("xmlFile") MultipartFile multiPartFile, RedirectAttributes redirect) throws IOException {
	
			if(multiPartFile.getOriginalFilename().isEmpty()) {
				throw new FileNotFoundException("File is not found !" );
			}
			
			
			RedirectView rv = null;
			try {
				
				questionService.addQuestions(multiPartFile);
				System.out.println("file added");
				 rv = new RedirectView();
				rv.setUrl("get-question");
			} catch (JAXBException e) {
				
				e.printStackTrace();
			}
			return rv;
		}

	
	@GetMapping("get-question")
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
