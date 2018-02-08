package com.subhra.quiz.system.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.subhra.quiz.system.persistence.dto.QuestionAnswer;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long>{
	
}
