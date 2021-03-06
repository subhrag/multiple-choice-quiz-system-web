package com.subhra.quiz.system.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.subhra.quiz.system.persistence.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	
}
