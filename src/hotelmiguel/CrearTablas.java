package hotelmiguel;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;


// @author Miguel
 
public class CrearTablas {
    
    public static void crearTablas() {
        
        try{
            Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost:3307/?user=root&password=usbw");
            Statement sentencia=conexion.createStatement();
            
            sentencia.execute("CREATE DATABASE IF NOT EXISTS HOTEL_MIGUEL");
            sentencia.execute("USE HOTEL_MIGUEL");
            
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS hoteles ( "
                    + "idHotel INT(4) NOT NULL, "
                    + "nombre VARCHAR(40) NOT NULL, "
                    + "telefono CHAR(9) NOT NULL, "
                    + "habitaciones INT(3) NOT NULL, "
                    + "pvp FLOAT NOT NULL, "
                    + "PRIMARY KEY (idHotel))");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS clientes ( "
                    + "dni CHAR(9) NOT NULL, "
                    + "nombre VARCHAR(40) NOT NULL, "
                    + "telefono CHAR(9) NOT NULL,"
                    + "PRIMARY KEY (dni)) "
                    + "");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS reservas ( "
                    + "codigo INT(4) NOT NULL AUTO_INCREMENT, "
                    + "hotel INT(4) NOT NULL, "
                    + "fechaEntrada DATE NOT NULL, "
                    + "cantidad INT NOT NULL, "
                    + "PRIMARY KEY (codigo), "
                    + "FOREIGN KEY (hotel) REFERENCES hoteles(idHotel) "
                    + "ON UPDATE CASCADE "
                    + "ON DELETE CASCADE) ");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS clientes_reservas ("
                    + "cliente CHAR(9) NOT NULL, "
                    + "reserva INT(4) NOT NULL, "
                    + "PRIMARY KEY (cliente,reserva), "
                    + "FOREIGN KEY (cliente) REFERENCES clientes (dni) "
                    + "ON UPDATE CASCADE "
                    + "ON DELETE RESTRICT, "
                    + "FOREIGN KEY (reserva) REFERENCES reservas (codigo) "
                    + "ON UPDATE CASCADE "
                    + "ON DELETE CASCADE) "
                    + "");
            
            System.out.println("\n - BASE DE DATOS LISTA - \n");
            
            conexion.close();
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
