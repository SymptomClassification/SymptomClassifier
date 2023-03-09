package com.lancaster.symptomchapter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.lang.Nullable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpacyClassifiedSymptom {
    @Id
    @GeneratedValue
    private int id;
    private int symptomId;
    private String chapter;
    @Nullable
    private String subchapter;
}
