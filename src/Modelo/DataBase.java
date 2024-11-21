/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

//Importamos paquetes que utilizaremos a lo largo del codigo.
import java.sql.*;
import java.util.*;

/**
 *
 * @author PC09
 */
public class DataBase {
    //Declaramos variables que almacenan el inicio de secion y la dirección de la base de datos.
    public final String URL="jdbc:mysql://localhost:3306/publicacion2";
    public final String user="root";
    public final String password="123456789";
    
    //Declara una variable de tipo Connection llamada conexion
    private Connection conexion; 
    
    public DataBase(){
        //Intenta realizar la conexion a la base de datos utilizando las variables 
        //declaradas si lo hace manda un mensaje de conexión exitosa, si no, mandara 
        //un reporte de error al ouput de la ejecución
        try{
            conexion=DriverManager.getConnection(URL, user, password);
            System.out.println("Conexión Establecida");
        }catch(SQLException e){
            System.out.println("Error de conexión");
            e.printStackTrace();
        }
    }
    //Crea un metodo para Actualizar datos en la base de datos
    public int Actualizar(String consulta){
        //Intenta modificar las consultas de la base de datos utilizando comandos MySQL
        try{
            Statement st=conexion.createStatement();
            //Retorna el numero de filas afectadas de la consulta en este caso retornara 1; eso creo =)
            return st.executeUpdate(consulta);
        }catch (SQLException e){
            e.printStackTrace();
        }
        //Una vez terminado retornara 0 como valos inicial nuevamente.
        return 0;
    }
    
    //Crea metodo para organizar datos de las consultas previamente ejecutadas
    private List OrganizarDatos(ResultSet rs){
        //Crea una lista que almacenará cada fila de datos con forma de mapa utilizando
        //map<String, Object>
        List filas=new ArrayList();
        try{
            //Se obtendran el numero de columnas que almacena el resultSet
            int numColumnas=rs.getMetaData().getColumnCount();
            //Este codigo recorrera cada fila  
            while(rs.next()){
                //Crea un nuevo mapa que almacenará sus valores
                Map<String, Object> renglon=new HashMap();
                //recorre cada columna en la fila
                for(int i=1; i<=numColumnas; i++) {
                    //Se obtiene el nombre de la columna
                    String nombreCampo=rs.getMetaData().getColumnName(i);
                    Object valor=rs.getObject(nombreCampo);
                    //Se obtiene el valor de la columna actual
                    renglon.put(nombreCampo, valor);
                }
                //Agrega el mapa al renglon
                filas.add(renglon);
            }   
        }catch(SQLException e){
            e.printStackTrace();
        }
        //Retorna una nueva ArrayList vacia
        return filas;
    }
    //Creamos metodo de List llamado Listar
    public List Listar(String consulta){
        //Se declara el rs como vacio para luego almacenar el resultado de la consulta MySQL
        ResultSet rs=null;
        List resultado=new ArrayList();
        try{
            //Intenta crear el espacio para las consultas MySQL y la almacena en la variable rs
            Statement st=conexion.createStatement();
            rs=st.executeQuery(consulta);
            //Invoca el metodo organizar datos con el rs como argumento permitiendo acceder a los datos
            resultado=OrganizarDatos(rs);
        }catch(SQLException e){
            System.out.println("No se realizó la consulta");
            e.printStackTrace();
        }
        return resultado;
    }
    
    //Intenta cerrar la conexion si no, mandará un reporte al output
    public void cerrarConexion(){
        try{
            conexion.close();
        }catch(SQLException e){
        e.printStackTrace();
        }
    }
        
}
