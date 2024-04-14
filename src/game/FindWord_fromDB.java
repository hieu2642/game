/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.sql.*;


public class FindWord_fromDB {
    private Connection connection;
    private String dbFile = "D:/lib_java/dict_hh.db";
    public FindWord_fromDB() {
        // Kết nối đến cơ sở dữ liệu SQLite
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] getRandomWordInfo() {
        String[] wordInfo = new String[3];

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT word, pronounce, description FROM av ORDER BY RANDOM() LIMIT 1");

            if (resultSet.next()) {
                String word = resultSet.getString("word");
                String pronounce = resultSet.getString("pronounce");
                String description = resultSet.getString("description");
                
                wordInfo[0] = word;
                wordInfo[1] = pronounce;
                wordInfo[2] = description;
            } else {
                System.out.println("No words found in the database");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wordInfo;
    }
}
