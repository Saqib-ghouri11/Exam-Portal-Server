package com.example.examserver.entities.exam;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;
    private String image;
    private String answer;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    @ManyToOne
    private Quiz quiz;


}
