package com.learning.QuizServiceMono.DTos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizResponseDTO {
    private Long quizId;
    private List<String> givenAnswers;
}
