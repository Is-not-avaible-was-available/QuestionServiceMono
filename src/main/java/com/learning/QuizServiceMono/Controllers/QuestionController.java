package com.learning.QuizServiceMono.Controllers;

import com.learning.QuizServiceMono.DTos.CreateQuestionRequestDTO;
import com.learning.QuizServiceMono.DTos.QuestionResponseDTO;
import com.learning.QuizServiceMono.Exceptions.NotFoundException;
import com.learning.QuizServiceMono.Services.QuestionService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<QuestionResponseDTO>> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody CreateQuestionRequestDTO requestDTO){
      return  questionService.createQuestion(requestDTO.getQuestion(), requestDTO.getCategory(),
                requestDTO.getOption1(), requestDTO.getOption2(), requestDTO.getOption3(), requestDTO.getOption4(),
                requestDTO.getCorrectAns(), requestDTO.getDifficultyLevel());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable("id") Long id) throws NotFoundException {
        return questionService.deleteQuestion(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponseDTO> updateQuestionById(@PathVariable("id") Long id,
                                                     @RequestBody CreateQuestionRequestDTO updatedQuestion) throws NotFoundException {
        return questionService.updateQuestion(id, updatedQuestion);
    }
}
