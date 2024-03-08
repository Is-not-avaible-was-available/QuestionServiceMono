package com.learning.QuizServiceMono.Repositories;

import com.learning.QuizServiceMono.Models.Question;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findQuestionsByCategory(String category);


   @Query(value = "SELECT * FROM question Where category = :category order by RAND() LIMIT :limit" , nativeQuery = true)

    List<Question> findQuestionsByCategory(@Param("category")String category, @Param("limit") int numQ);
}
