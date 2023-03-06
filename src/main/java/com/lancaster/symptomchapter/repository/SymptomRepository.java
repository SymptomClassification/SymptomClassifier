package com.lancaster.symptomchapter.repository;

import com.lancaster.symptomchapter.model.Symptom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class SymptomRepository {
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

    public int saveSymptom(Symptom symptom) {
        String create = "INSERT INTO symptoms (symptom) VALUES (?)";
        try {
            PreparedStatement stm = getDBConnection().prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, symptom.getSymptom());
            stm.executeUpdate();
            // Retrieve the auto-generated ID
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                symptom.setId(id);
            } else {
                throw new SQLException("No ID was generated");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return symptom.getId();
    }


}
