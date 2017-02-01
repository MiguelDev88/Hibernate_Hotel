package funciones;
import java.io.BufferedReader;
import java.io.IOException;


// @author Miguel
 
public class Menu {
    
    public static byte menuPrincipal(BufferedReader leer) throws IOException {
        
        byte op;
        
        System.out.println("\nIntroduzca opción:"
                + "\n1.Altas"
                + "\n2.Bajas"
                + "\n3.Consultas"
                + "\n0.Finalizar");
        
        op=Byte.parseByte(leer.readLine());
        
        return op;
        
    }
    
    public static byte menuAltas(BufferedReader leer) throws IOException {
        
        byte op;
        
        System.out.println("\n¿Qué desea dar de Alta?"
                + "\n1.Hotel"
                + "\n2.Cliente"
                + "\n3.Reserva"
                + "\n0.Finalizar");
        
        op=Byte.parseByte(leer.readLine());
        
        return op;
        
    }
    
    public static byte menuBajas(BufferedReader leer) throws IOException {
        
        byte op;
        
        System.out.println("\n¿Qué desea dar de Baja?"
                + "\n1.Hotel"
                + "\n2.Cliente"
                + "\n3.Reserva"
                + "\n0.Finalizar");
        
        op=Byte.parseByte(leer.readLine());
        
        return op;
        
    }
    
    public static byte menuConsultas (BufferedReader leer) throws IOException {
        
        byte op;
        
        System.out.println("\n¿Qué desea Consultar?"
                + "\n1.Reservas de un Cliente"
                + "\n0.Finalizar");
        
        op=Byte.parseByte(leer.readLine());
        
        return op;
        
    }
    
    public static byte menuConfirmar (BufferedReader leer) throws IOException {
        
        byte op;
        System.out.println("\n¿Seguro que desea eliminar este registro?"
                + "\n1.SI"
                + "\n2.NO");
        
        op=Byte.parseByte(leer.readLine());
        
        return op;
    }
    
}
