package com.learning.QuizServiceMono.DTos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionRequestDTO {
    private String question;
    private String category;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAns;
    private String difficultyLevel;
}
