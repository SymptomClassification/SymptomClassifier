package com.lancaster.SymptomChapter.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    @Id @GeneratedValue
    private int id;
    private String name;
}
