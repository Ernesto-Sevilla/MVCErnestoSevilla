/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.*; //Se manda a llamar todas las clases de la declaración util 

/**
 *
 * @author PC09
 */
public class DAOAutor {
    
    //Creamos metodo para insertar datos a la base de datos utilizando las consultas MySQL.
    public Autor Insertar(String cedula, String nombres, String apellidos,
            String email, java.sql.Date fechaNac) {
        String transaccion = "INSERT INTO Autor (cedula,nombres,apellidos,email,fechaNac) VALUES('"
                + nombres + "', '"
                + apellidos + "', '"
                + email + "', '"
                + cedula + "', '"
                + fechaNac + "')";
        //Definimos una condición if para asegurarnos que los dato se ayan insertado correctamente.
        if (new DataBase().Actualizar(transaccion) > 0) {
            return new Autor(cedula, nombres, apellidos, email, fechaNac);
            //Si todo sale vien crea una nueva instancia en la clase de autor,
            //Con las variables asignadas
        }
        //Para despues nuevamente devolver un valor null a la clase Autor para asi evitar errores de codigo
         return null;       
    }
    //Creamos metodo para actualizar datos a la base de datos utilizando las consultas MySQL.
    public int Actualizar(int id, String nombres, String apellidos,
            String email,String cedula, java.sql.Date fechaNac) {
                String transaccion = "UPDATE Autor SET nombres='"
                + nombres + "', apellidos='"
                + apellidos + "', email= '"
                + email + "', fechaNac= '"
                + fechaNac + "', cedula='"
                + cedula + "' WHERE id_autor="
                + id;
                //retorna una nueva instancia a la clase DataBase del
                //metodo actualizar que esta alijada en la clase de base de datos
                return new DataBase().Actualizar(transaccion);
            }
    //Mandamos a llamar el metodo Obtener datos de la clase DataBase
    public List ObtenerDatos() {
        String transaccion = "SELECT * FROM Autor";
        List<Map> registros = new DataBase().Listar(transaccion);
        //Creamos una nueva ArrayList llamada Autor declarada con un nuevo objeto autores
        //Creando una nueva lista vacia
        List<Autor> autores = new ArrayList();
        //Obtenemos datos de la base de datos
        for (Map registro : registros) {
            Autor aut = new Autor((int) registro.get("id_autor"),
                    (String) registro.get("nombres"),
                    (String) registro.get("apellidos"),
                    (String) registro.get("email"),
                    (String) registro.get("cedula"),
                    (java.sql.Date) registro.get("fechaNac"));
            //Añadiendo un nuevo listado lleno con los valores extraidos al objeto aut
            autores.add(aut);
        }
        //Retorna una nueva ArrayList vacia
        return autores;
    }
    
    //Creamos metodo para eliminar un registro de la base de datos utilizando el ID como referencia 
    public int Eliminar(int id) {
        //Declaramos una variable de tipo String llamada transaccion que almacena
        //el comando para eliminar un registro
        String transaccion = "DELETE FROM Autor WHERE id_autor='"+ id +"'";
        //Una vez actualizado retorna el metodo Actualizar aplicando el comando almacenado
        return new DataBase().Actualizar(transaccion);
    }
}
