package com.learning.QuizServiceMono.Repositories;

import com.learning.QuizServiceMono.Models.Question;
import com.learning.QuizServiceMono.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long>{

    @Query(value = "select * from Quiz", nativeQuery = true)
    List<Quiz> findAllQuiz();

}
