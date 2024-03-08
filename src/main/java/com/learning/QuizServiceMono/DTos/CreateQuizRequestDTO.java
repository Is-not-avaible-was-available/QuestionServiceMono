package com.learning.QuizServiceMono.DTos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateQuizRequestDTO {

    private String category;
    private int totalQuestions;
    private String title;
}
