
package com.learning.QuizServiceMono.DTos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizQuestionDTO {
    private String quesTitle;
    private String Option1;
    private String option2;
    private String option3;
    private String option4;
    private String difficulty;
    private String category;
}
