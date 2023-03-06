package com.lancaster.symptomchapter.repository;

import com.lancaster.symptomchapter.model.KeywordClassifiedSymptom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class KeywordClassifiedSymptomRepository {

    private Connection con = null;
    private final String TABLE_NAME = "keywordclassifiedsymptom";

    @Value("${spring.datasource.url}")
    String dbUrl;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;

    private Connection getDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        return con;
    }

    public KeywordClassifiedSymptom saveClassifiedSymptom(KeywordClassifiedSymptom keywordClassifiedSymptom) {
        String create = "INSERT INTO " + TABLE_NAME + " (symptomId, chapterId, subchapterId, secondsubId) " +
                " VALUES (?, ?, ?, ?)";


        try {
            PreparedStatement stm = getDBConnection().prepareStatement(create);
            stm.setInt(1, keywordClassifiedSymptom.getSymptomId());
            stm.setInt(2, keywordClassifiedSymptom.getChapterId());
            stm.setInt(3, keywordClassifiedSymptom.getSubchapterId());
            stm.setInt(4, keywordClassifiedSymptom.getSecondsubId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return keywordClassifiedSymptom;
    }


}
