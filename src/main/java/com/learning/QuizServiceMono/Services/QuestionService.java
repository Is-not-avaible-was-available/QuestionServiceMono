package com.learning.QuizServiceMono.Services;

import com.learning.QuizServiceMono.DTos.CreateQuestionRequestDTO;
import com.learning.QuizServiceMono.DTos.QuestionResponseDTO;
import com.learning.QuizServiceMono.Exceptions.NotFoundException;
import com.learning.QuizServiceMono.Models.Question;
import com.learning.QuizServiceMono.Repositories.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }
    public ResponseEntity<List<QuestionResponseDTO>> getAllQuestions() {
       List<Question> questions= questionRepository.findAll();
       List<QuestionResponseDTO> questionResponseDTOS= new ArrayList<>();
       for(Question question:questions){
           questionResponseDTOS.add(question.toQuestionResponseDTO());
       }
       return new ResponseEntity<>(questionResponseDTOS, HttpStatus.OK);
    }

    public ResponseEntity<String> createQuestion(String question,String category,String option1,String option2,
                               String option3, String option4, String correctAns,
                               String difficultyLevel) {
        Question newQuestion = new Question();
        newQuestion.setQuestion(question);
        newQuestion.setCategory(category);
        newQuestion.setOption1(option1);
        newQuestion.setOption2(option2);
        newQuestion.setOption3(option3);
        newQuestion.setOption4(option4);
        newQuestion.setCorrectAns(correctAns);
        newQuestion.setDifficultyLevel(difficultyLevel);
        questionRepository.save(newQuestion);
        return new ResponseEntity<>("Successfully created a question!", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuestion(Long id) throws NotFoundException {
            Optional<Question> optionalQuestion = questionRepository.findById(id);
            if(optionalQuestion.isEmpty()){
                throw new NotFoundException("Question with id "+id+" is not found!");
            }
            Question question = optionalQuestion.get();
            questionRepository.delete(question);
        return new ResponseEntity<>("Successfully deleted the question with id "+id, HttpStatus.OK);
    }

    public ResponseEntity<QuestionResponseDTO> updateQuestion(Long id,
                                                 CreateQuestionRequestDTO updatedQuestion) throws NotFoundException {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isEmpty()){
            throw new NotFoundException("Question with id "+id+" is not found!");
        }
        Question question = optionalQuestion.get();
        question.setQuestion(updatedQuestion.getQuestion());
        question.setCategory(updatedQuestion.getCategory());
        question.setOption1(updatedQuestion.getOption1());
        question.setOption2(updatedQuestion.getOption2());
        question.setOption3(updatedQuestion.getOption3());
        question.setOption4(updatedQuestion.getOption4());
        question.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
        question.setCorrectAns(updatedQuestion.getCorrectAns());
        Question updated = questionRepository.save(question);
        QuestionResponseDTO response = updated.toQuestionResponseDTO();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
