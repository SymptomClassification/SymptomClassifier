package com.lancaster.symptomchapter.repository;

import com.lancaster.symptomchapter.model.SpacyChapters;
import com.lancaster.symptomchapter.model.SpacyClassifiedSymptom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SpacyClassificationRepository {

    private Connection con = null;

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

    public List<SpacyChapters> fetchSpacyChapters() {
        List<SpacyChapters> chapters = new ArrayList<>();
        SpacyChapters chapter;
        String selectAll = "SELECT * FROM spacychapters";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(selectAll);

            while (rs.next()) {
                chapter = new SpacyChapters();
                chapter.setId(rs.getInt("id"));
                chapter.setName(rs.getString("name"));
                chapter.setChapterId(rs.getInt("chapterId"));
                chapters.add(chapter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return chapters;
    }

    public int saveSpacyClassifiedSymptom(SpacyClassifiedSymptom symptom) {
        String create = "INSERT INTO spacyclassifiedsymptom (symptomId, chapter, subchapter) " +
                "VALUES (?, ?, ?) ";
        try {
            PreparedStatement stm = getDBConnection().prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, symptom.getSymptomId());
            stm.setString(2, symptom.getChapter());
            stm.setString(3, symptom.getSubchapter());
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Creating spacy classified symptom failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
