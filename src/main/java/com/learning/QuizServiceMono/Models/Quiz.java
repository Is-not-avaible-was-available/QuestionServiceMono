package com.learning.QuizServiceMono.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Getter
@Setter
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> questionIds;
    private int score;
}
