package com.example.examserver.entities.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Integer maxMarks;
    private Integer numberOfQuestions;
    private String title;
    private boolean active;

    @ManyToOne
    private Category category;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "quiz")
    @JsonIgnore
    private Set<Questions> setOfQuestions=new HashSet<>();


}
