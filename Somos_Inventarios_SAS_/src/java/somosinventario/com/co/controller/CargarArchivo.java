/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package somosinventario.com.co.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
/**
 *
 * @author Andrés Camilo Osorio
 */

@ManagedBean
@SessionScoped
/**
 * La clase Cargar Archivo se usa para Leer el archivo csv,realizar cortado e implementar la Query de importación
 * @author Andres Camilo Osorio
 * @author Andres Galvis
 * @author Giancarlo Vásquez
 * @version 1.0
 * @since Somos_Inventario_SAS
 */
public class CargarArchivo {
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    Connection cx;
    String bd ="inventariosas";
    String url = "jdbc:mysql://localhost:3306/"+bd;
    String user = "root";
    String pass = "mariadb";
    
    private Part file;
    
       /**
    * El método conectar se encarga de ingresar a la base de datos para poder realizar los Queries
    */
     public Connection conectar(){
         try {
             Class.forName("org.mariadb.jdbc.Driver");
             cx = DriverManager.getConnection(url,user,pass);
         } catch (ClassNotFoundException  | SQLException ex) {
         }
         return cx;
    }
     
       /**
    * El método cargarEquipo se encarga de leer el archivos csv de Equipos, leer linea por linea, partir en cada coma y
    * hacer la Query a la BD para que quede agregada
    */
    public void cargarEquipo(){
        try{
            InputStream input=file.getInputStream();
            
            File f=new File("/Users/Andrés Camilo Osorio/Desktop/hola.txt");
            System.out.println(file.getInputStream());
            if(!f.exists()){
                f.createNewFile();
            }
      
            FileOutputStream output=new FileOutputStream(f);
            byte[] buffer=new byte[1024];
            int length;
            while((length=input.read(buffer))>0){
                output.write(buffer, 0, length);
            }
            
            try (FileReader reader = new FileReader(f)) {
            BufferedReader buufr = new BufferedReader(reader);

            //esta variable almacena la linea del archivo txt
            String line = "";
            //se realiza el ciclo hasta que ya no hayan mas datos en el txt
            while ( (line=buufr.readLine()) != null) {
                String lineaUsu = line;
                String [] vect = lineaUsu.split(",");
                Connection con = conectar();    
                ps = con.prepareStatement("INSERT INTO equipos(referencia,nombre,precio,fecha_ingreso) VALUES(?,?,?,?) ");
                ps.setString(1, vect[0]);
                ps.setString(2, vect[1]);
                ps.setInt(3,Integer.valueOf(vect[2]));
                ps.setDate(4, Date.valueOf(vect[3]));
                resp = ps.executeUpdate();
            }
            buufr.close();
        }catch(IOException e){
            System.out.println("Error de lectura en disco (borraron el archivo, archivo pirata)");
        }
            input.close();
            output.close();
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
         FacesMessage msg = new FacesMessage("Equipo Cargado con éxito!!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    /**
    * El método cargarProducto se encarga de leer el archivos csv de Productos, leer linea por linea, partir en cada coma y
    * hacer la Query a la BD para que quede agregada
    */
        public void cargarProducto(){
        try{
            InputStream input=file.getInputStream();
            
            File f=new File("/Users/Andrés Camilo Osorio/Desktop/hola.txt");
            System.out.println(file.getInputStream());
            if(!f.exists()){
                f.createNewFile();
            }
      
            FileOutputStream output=new FileOutputStream(f);
            byte[] buffer=new byte[1024];
            int length;
            while((length=input.read(buffer))>0){
                output.write(buffer, 0, length);
            }
            try (FileReader reader = new FileReader(f)) {
            BufferedReader buufr = new BufferedReader(reader);

            //esta variable almacena la linea del archivo txt
            String line = "";
            //se realiza el ciclo hasta que ya no hayan mas datos en el txt
            while ( (line=buufr.readLine()) != null) {
                String lineaUsu = line;
                String [] vect = lineaUsu.split(",");
                Connection con = conectar();    
                ps = con.prepareStatement("INSERT INTO productos(id,nombre) VALUES(?,?) ");
                ps.setString(1, vect[0]);
                ps.setString(2, vect[1]);
                resp = ps.executeUpdate();
            }
            //cerramos el buffer
            buufr.close();
        }catch(IOException e){
            System.out.println("Error de lectura en disco (borraron el archivo, archivo pirata)");
        }
            input.close();
            output.close();
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
         FacesMessage msg = new FacesMessage("Productos cargados con éxito!!!!.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
        
    /**
    * El método cargarUsuario se encarga de leer el archivos csv de Usuarios, leer linea por linea, partir en cada coma y
    * hacer la Query a la BD para que quede agregada
    */
            public void cargarUsuario(){
        try{
            InputStream input=file.getInputStream();
            
            File f=new File("/Users/Andrés Camilo Osorio/Desktop/hola.txt");
            System.out.println(file.getInputStream());
            if(!f.exists()){
                f.createNewFile();
            }
            FileOutputStream output=new FileOutputStream(f);
            byte[] buffer=new byte[1024];
            int length;
            while((length=input.read(buffer))>0){
                output.write(buffer, 0, length);
            }

            try (FileReader reader = new FileReader(f)) {
            BufferedReader buufr = new BufferedReader(reader);

            //esta variable almacena la linea del archivo txt
            String line = "";
            //se realiza el ciclo hasta que ya no hayan mas datos en el txt
            while ( (line=buufr.readLine()) != null) {
                String lineaUsu = line;
                //separamos los datos de la linea, todos estan separados con el /
                String [] vect = lineaUsu.split(",");
                System.out.println("hola: dato"+vect[0]);
                System.out.println("hola: dato 2"+vect[1]);
                System.out.println(vect.length);
                Connection con = conectar();    
                ps = con.prepareStatement("INSERT INTO usuarios(USERNAME,PASSWORD) VALUES(?,?) ");
                ps.setString(1, vect[0]);
                ps.setString(2, vect[1]);
                resp = ps.executeUpdate();
            }
            //cerramos el buffer
            buufr.close();
        }catch(IOException e){
            System.out.println("Error de lectura en disco (borraron el archivo, archivo pirata)");
        }
            input.close();
            output.close();
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
         FacesMessage msg = new FacesMessage("Usuarios cargados con éxito!!!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

}
    

