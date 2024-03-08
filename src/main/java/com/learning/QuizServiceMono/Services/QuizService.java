package com.learning.QuizServiceMono.Services;

import com.learning.QuizServiceMono.DTos.QuizQuestionDTO;
import com.learning.QuizServiceMono.Exceptions.NotFoundException;
import com.learning.QuizServiceMono.Models.Question;
import com.learning.QuizServiceMono.Models.Quiz;
import com.learning.QuizServiceMono.Repositories.QuestionRepository;
import com.learning.QuizServiceMono.Repositories.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    public QuizService(QuestionRepository questionRepository, QuizRepository quizRepository){
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }
    public ResponseEntity<String> createQuiz(String title, String category, int totalQuestions) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        List<Question> questions = questionRepository.findQuestionsByCategory(category, totalQuestions);
        List<Long> questionIds = new ArrayList<>();
        for(Question question : questions){
            questionIds.add(question.getId());
        }
        quiz.setQuestionIds(questionIds);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Success!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuizQuestionDTO>> findQuizQuestions(Long id) throws NotFoundException {
        Optional<Quiz> quizOptional= quizRepository.findById(id);
        if(quizOptional.isEmpty()){
            throw new NotFoundException("Not found!");
        }
        Quiz quiz = quizOptional.get();
        List<Long> questionIds = quiz.getQuestionIds();
        List<Question> questions = questionRepository.findAllById(questionIds);
        List<QuizQuestionDTO> quizQuestionDTOS = new ArrayList<>();
        for(Question question:questions){
            quizQuestionDTOS.add(convertToQuizQuestionDto(question));
        }
        return new ResponseEntity<>(quizQuestionDTOS, HttpStatus.OK);
    }
    public QuizQuestionDTO convertToQuizQuestionDto(Question question){
        QuizQuestionDTO quizQuestionDTO = new QuizQuestionDTO();
        quizQuestionDTO.setQuesTitle(question.getQuestion());
        quizQuestionDTO.setCategory(question.getCategory());
        quizQuestionDTO.setDifficulty(question.getDifficultyLevel());
        quizQuestionDTO.setOption1(question.getOption1());
        quizQuestionDTO.setOption2(question.getOption2());
        quizQuestionDTO.setOption3(question.getOption3());
        quizQuestionDTO.setOption4(question.getOption4());
        return quizQuestionDTO;
    }


    public ResponseEntity<String> getScore(List<String> givenAnswers, Long quizId) throws NotFoundException {

        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if(quizOptional.isEmpty()){
            throw new NotFoundException("Not Found");
        }
        Quiz quiz = quizOptional.get();

        List<Long> questionIds = quiz.getQuestionIds();
        List<Question> questions = questionRepository.findAllById(questionIds);
        int right = 0;
        for(Question question:questions){
            for(String ans: givenAnswers){
                if(question.getCorrectAns().equals(ans)){
                    right++;
                }
            }
        }
        return new ResponseEntity<>("Your score is "+right, HttpStatus.OK);
    }
}
