package com.lancaster.SymptomChapter.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpacyChapters {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int chapterId;
}
