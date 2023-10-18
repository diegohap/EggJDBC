package com.eggjdbc;

import com.eggjdbc.model.Persona;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        ArrayList<Persona> personas = new ArrayList<>();
        String url = "jdbc:mysql://localhost:33060/db_pr";
        String username = "root";
        String password = "";
        Properties properties = new Properties();
        try(InputStream input = Main.class.getResourceAsStream("/config.properties")){
            properties.load(input);
            password = properties.getProperty("db.password");
        }
        catch (IOException e){
            System.out.println(e.toString());
        }


        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM personas");
            while(rs.next()){
                Persona p = new Persona();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                personas.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        personas.forEach(System.out::println);
    }
}