package com.lancaster.SymptomChapter.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordClassifiedSymptom {
    @Id
    @GeneratedValue
    private int id;
    private int symptomId;
    private int chapterId;
    @Nullable
    private int subchapterId;
    @Nullable
    private int secondsubId;

}
