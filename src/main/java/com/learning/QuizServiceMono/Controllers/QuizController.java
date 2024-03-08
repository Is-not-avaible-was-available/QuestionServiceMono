package com.learning.QuizServiceMono.Controllers;


import com.learning.QuizServiceMono.DTos.CreateQuestionRequestDTO;
import com.learning.QuizServiceMono.DTos.CreateQuizRequestDTO;
import com.learning.QuizServiceMono.DTos.QuizQuestionDTO;
import com.learning.QuizServiceMono.DTos.QuizResponseDTO;
import com.learning.QuizServiceMono.Exceptions.NotFoundException;
import com.learning.QuizServiceMono.Services.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController{

    private final QuizService quizService;
    private QuizController(QuizService quizService){
        this.quizService = quizService;
    }
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody CreateQuizRequestDTO requestDTO){
        return quizService.createQuiz(requestDTO.getTitle(),
                requestDTO.getCategory(),requestDTO.getTotalQuestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuizQuestionDTO>> findQuizQuestions(@PathVariable("id") Long id) throws NotFoundException {
        return quizService.findQuizQuestions(id);
    }

    @PostMapping("/submit")
    public ResponseEntity<String> getScore(@RequestBody QuizResponseDTO quizResponseDTO) throws NotFoundException {
        return quizService.getScore(quizResponseDTO.getGivenAnswers(), quizResponseDTO.getQuizId());
    }
}
