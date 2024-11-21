/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.*;

/**
 *
 * @author PC09
 */
public class DAORevista {
    
    public Revista Insertar(String titulo, int ayo, String issn,
            float precio, java.sql.Time Horaventa) {
        String transaccion = "INSERT INTO Revista (titulo, ayo, issn, precio, Horaventa) VALUES('"
                + titulo + "', '"
                + ayo + "', '"
                + issn + "', '"
                + precio + "', '"
                + Horaventa + "')";
        if (new DataBase().Actualizar(transaccion) > 0) {
            return new Revista(titulo, ayo, issn, precio, Horaventa);
        }
         return null;       
    }
    
     public int Actualizar(int numero, String titulo, int ayo, String issn,
            float precio, java.sql.Time Horaventa) {
        
        String transaccion = "UPDATE Revista SET titulo='"
                + titulo + "', ayo='"
                + ayo + "', issn= '"
                + issn + "', precio= '"
                + precio + "', Horaventa='"
                + Horaventa + "' WHERE numero="
                + numero ; 
        
        return new DataBase().Actualizar(transaccion);
    }
     
      public List ObtenerDatos() {
        String transaccion = "SELECT * FROM Revista";
        List<Map> registros = new DataBase().Listar(transaccion);
        List<Revista> revistas = new ArrayList();
        for (Map registro : registros) {
            Revista aut = new Revista(
                    (int) registro.get("numero"),
                    (String) registro.get("titulo"),
                    (int) registro.get("ayo"),
                    (String) registro.get("issn"),
                    (float) registro.get("precio"),
                    (java.sql.Time) registro.get("Horaventa"));
            revistas.add(aut);
        }
        return revistas;
    }
      
       public int Eliminar(int numero) {
        String transaccion = "DELETE FROM Revista WHERE numero='"+ numero +"'";
        
        return new DataBase().Actualizar(transaccion);
    }
}
