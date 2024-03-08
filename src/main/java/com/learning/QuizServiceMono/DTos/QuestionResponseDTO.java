package com.learning.QuizServiceMono.DTos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponseDTO {
    private Long id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String category;
    private String difficultyLevel;
}
