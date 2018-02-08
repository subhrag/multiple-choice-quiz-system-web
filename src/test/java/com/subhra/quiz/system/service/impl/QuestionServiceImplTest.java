package com.subhra.quiz.system.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.subhra.quiz.system.persistence.dto.QuestionAnswer;
import com.subhra.quiz.system.persistence.repository.QuestionAnswerRepository;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

	@Mock
	QuestionAnswerRepository questionAnswerRepository;

	@InjectMocks
	QuestionServiceImpl questionServiceImpl;

	@Before
	public void setUp() {
		when(questionAnswerRepository.findAll()).thenReturn(getAllCorrectQuestionAnswers());

	}

	private List<QuestionAnswer> getAllCorrectQuestionAnswers(){
		List<QuestionAnswer> questionAnswerList = new ArrayList<>();
		QuestionAnswer questionAnswer1 = new QuestionAnswer();
		questionAnswer1.setId(1L);
		questionAnswer1.setCorrectAnswerId(1L);
		
		QuestionAnswer questionAnswer2 = new QuestionAnswer();
		questionAnswer2.setId(2L);
		questionAnswer2.setCorrectAnswerId(3L);

		QuestionAnswer questionAnswer3 = new QuestionAnswer();
		questionAnswer3.setId(3L);
		questionAnswer3.setCorrectAnswerId(4L);
		
		QuestionAnswer questionAnswer4 = new QuestionAnswer();
		questionAnswer4.setId(4L);
		questionAnswer4.setCorrectAnswerId(7L);
		
		questionAnswerList.add(questionAnswer1);
		questionAnswerList.add(questionAnswer2);
		questionAnswerList.add(questionAnswer3);
		questionAnswerList.add(questionAnswer4);
		
		return questionAnswerList;
	}

	@Test
	public void testValidateAnswers1() {

		String validateAnswers = questionServiceImpl.validateAnswers(this.createMap1());
		assertEquals("Average 2/4" , validateAnswers);
	}
	
	@Test
	public void testValidateAnswers2() {

		String validateAnswers = questionServiceImpl.validateAnswers(this.createMap2());
		assertEquals("Good 3/4" , validateAnswers);
	}
	
	private Map<Long, Long> createMap1()
    {
        Map<Long, Long> createMap = new LinkedHashMap<>();
        createMap.put(1L, 2L);
        createMap.put(2L, 3L);
        createMap.put(3L, 5L);
        createMap.put(4L, 7L);
        return createMap;
    }
	
	private Map<Long, Long> createMap2()
    {
        Map<Long, Long> createMap = new LinkedHashMap<>();
        createMap.put(1L, 1L);
        createMap.put(2L, 3L);
        createMap.put(3L, 5L);
        createMap.put(4L, 7L);
        return createMap;
    }
}
