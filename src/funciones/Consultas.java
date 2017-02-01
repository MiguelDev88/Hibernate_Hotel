package funciones;
import POJOS.*;
import hotelmiguel.HibernateUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import org.hibernate.Session;


// @author Miguel

public class Consultas {
    
    public static void consultas (BufferedReader leer) throws IOException {
        
        byte op=1;
        
        while(op!=0){
            op=Menu.menuConsultas(leer);
            switch(op){
                case 1:
                    verReservasCliente(leer);
                    break;
            }
            
        }

    }
    
    public static void verReservasCliente (BufferedReader leer) throws IOException {
        
        Session sesion;
        C_Cliente cliente;
        C_Reserva reserva;
        C_Hotel hotel;
        String dni;
        
        sesion=HibernateUtil.getSession();
        
        try{
            
            System.out.println("Introducir DNI del cliente a Consultar:");
            dni=leer.readLine();
            cliente=(C_Cliente)sesion.get(C_Cliente.class, dni);
            
            if(cliente!=null) {
                
                System.out.println("Reservas para el Cliente " + cliente.getNombre()+":");
                Iterator reservas=cliente.getReservas().iterator();
                while(reservas.hasNext()){
                    reserva=(C_Reserva)reservas.next();
                    System.out.println("Reserva: "+reserva.getCodigo()+" para la fecha: "+reserva.getFechaEntrada());
                    hotel=reserva.getHotel();
                    System.out.println("HOTEL:"+hotel.getNombre()
                                    + "\nTELEFONO:"+hotel.getTelefono()
                                    + "\nPVP:"+hotel.getPvp());
                    
                }

            }else
                System.out.println("\n - Cliente no encontrado - \n");
            
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
