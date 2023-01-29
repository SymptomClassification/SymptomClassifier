package com.lancaster.SymptomChapter.repository;

import com.lancaster.SymptomChapter.model.Chapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ChapterRepository {
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

    public List<Chapter> fetchChapters() {
        List<Chapter> chapters = new ArrayList<>();
        Chapter chapter;
        String selectAll = "SELECT * FROM chapter";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(selectAll);

            while (rs.next()) {
                chapter = new Chapter();
                chapter.setId(rs.getInt("id"));
                chapter.setName(rs.getString("name"));
                chapters.add(chapter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return chapters;
    }

    public void saveChapter(Chapter chapter) {
        String create = "INSERT INTO chapter (name) " +
                " VALUES (?)";

        try {
            PreparedStatement stm = getDBConnection().prepareStatement(create);
            stm.setString(1, chapter.getName());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Chapter> fetchChapterWithName(String name) {
        Optional<Chapter> ch = Optional.empty();
        Chapter chapter = new Chapter();
        String select = "SELECT * FROM chapter WHERE name = '" + name + "'";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(select);

            while (rs.next()) {
                chapter.setId(rs.getInt("id"));
                chapter.setName(rs.getString("name"));

                ch = Optional.of(chapter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ch;
    }

    public Optional<Chapter> fetchChapterWithId(int id) {
        Optional<Chapter> ch = Optional.empty();
        Chapter chapter = new Chapter();
        String select = "SELECT * FROM chapter WHERE id = " + id;

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(select);

            while (rs.next()) {
                chapter.setId(rs.getInt("id"));
                chapter.setName(rs.getString("name"));

                ch = Optional.of(chapter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ch;
    }

    public Optional<Chapter> updateChapter(Chapter chapter, int id) {
        Optional<Chapter> ch = fetchChapterWithId(id);
        String update = "UPDATE chapter SET id = ?, name = ? WHERE id = ? ";

        if (!ch.isPresent()) {
            ch = Optional.empty();
            return ch;
        }

        try {
            PreparedStatement stm = getDBConnection().prepareStatement(update);
            stm.setLong(1, id);
            stm.setString(2, chapter.getName());
            stm.setInt(3, id);

            stm.executeUpdate();
            ch = fetchChapterWithId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ch;
    }

}
