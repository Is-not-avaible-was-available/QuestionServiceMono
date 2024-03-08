package com.learning.QuizServiceMono.Models;

import com.learning.QuizServiceMono.DTos.QuestionResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String question;
    private String category;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAns;
    private String difficultyLevel;

    public QuestionResponseDTO toQuestionResponseDTO(){
        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO();
        questionResponseDTO.setId(this.getId());
        questionResponseDTO.setQuestion(this.getQuestion());
        questionResponseDTO.setOption1(this.getOption1());
        questionResponseDTO.setOption2(this.getOption2());
        questionResponseDTO.setOption3(this.getOption3());
        questionResponseDTO.setOption4(this.getOption4());
        questionResponseDTO.setCategory(this.getCategory());
        questionResponseDTO.setDifficultyLevel(this.getDifficultyLevel());
        return questionResponseDTO;
    }
}
