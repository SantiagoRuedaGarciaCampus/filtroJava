/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ejemplo.filtro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class Filtro {

    public static void main(String[] args) {
            menu();
    }
    public static void menu(){
        
        System.out.println("Menu ninja");
        System.out.println("Mostrar ninjas --- 1");
        System.out.println("Misiones de un ninja --- 2");
        System.out.println("Misiones completadas --- 3");
        System.out.println("Asignar mision --- 4");
        System.out.println("Completar mision --- 5");
        System.out.println("Marcar como completadas todas las misiones --- 6");
        System.out.println("Mostrar todas las misiones completadas --- 7");
        System.out.println("Salir --- 8");
        Scanner entrada = new Scanner(System.in);
        int e = entrada.nextInt();
        switch (e){
            case 1:
            {
                case1();
                break;
            }
            case 2:
            {
                case2();
                break;
            }
            case 3:
            {
                case3();
            }


        }
    }
    public static void case1(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost", "filtro", "filtro");        
            Statement sentencia = con.createStatement();
            sentencia.executeUpdate("use ninja");
            ResultSet rs = sentencia.executeQuery("SELECT * FROM ninja n JOIN habilidad mn where n.ID = mn.ID_ninja");
            while(rs.next()){
            System.out.println(rs.getString("n.ID")+", "+rs.getString("n.Nombre")+", "+rs.getString("n.Rango")+", "+rs.getString("n.aldea")+", "+rs.getString("mn.Nombre")+": "+rs.getString("mn.Descripcion"));
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo completar la tarea. error en: " + ex.getLocalizedMessage());
        }finally{
            menu();
        }
    }
    public static void case2(){
        try {
                    System.out.println("ID del ninja:");
                    Scanner entrada = new Scanner(System.in);
                    int idninja = entrada.nextInt();
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ninja", "filtro", "filtro");
                    Statement sentencia = con.createStatement();
                    ResultSet rs = sentencia.executeQuery("select m.* from mision m join misionninja mn on mn.id_minsion = m.id join ninja n on n.id = mn.id_ninja where n.id = "+idninja+";");
                    System.out.println("Misiones: ");
                    while(rs.next()){
                        System.out.println(rs.getString("m.ID")+", "+
                        rs.getString("m.descripcion")+", "+
                        rs.getString("m.recompensa"));
                    }
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("No se pudo completar la tarea. error en: " + ex.getLocalizedMessage());
                }finally{
                    menu();
                }
    }
    public static void case3(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ninja", "filtro", "filtro");
            Statement sentencia = con.createStatement();
            ResultSet rs = sentencia.executeQuery(" select * from misionninja mn join mision m on mn.id_minsion = m.id where mn.fechafin like \"%\";");
            System.out.println("Misiones completadas:");
            while(rs.next()){
                System.out.println(rs.getString("m.id")+", "+
                        rs.getString("m.descripcion")+", "+
                        rs.getString("m.rango")+", "+
                        rs.getString("m.recompensa"));
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo completar la tarea. error en: " + ex.getLocalizedMessage());
        }finally{
            menu();
        }
    }
}
