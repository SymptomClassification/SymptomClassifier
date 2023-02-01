package com.lancaster.SymptomChapter.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassifiedSymptom {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int chapterId;
    private int subchapterId;
    private int secondsubId;


}
