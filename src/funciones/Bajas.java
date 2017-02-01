package funciones;
import POJOS.*;
import hotelmiguel.HibernateUtil;
import java.io.BufferedReader;
import java.io.IOException;
import org.hibernate.Session;


// @author Miguel

public class Bajas {
    
    public static void bajas (BufferedReader leer) throws IOException {
        
        byte op=1;
        
        while(op!=0){
            op=Menu.menuBajas(leer);
            switch(op){
                case 1:
                    bajaHotel(leer);
                    break;
                case 2:
                    bajaCliente(leer);
                    break;
                case 3:
                    bajaReserva(leer);
                    break;
            }
            
        }
        
    }
    
    public static void bajaHotel (BufferedReader leer) throws IOException {
        
        C_Hotel hotel;
        Session sesion;
        int idHotel;
        
        
        sesion=HibernateUtil.getSession();
        
        try{
            System.out.println("Introduzca el id del Hotel a eliminar");
            idHotel=Integer.parseInt(leer.readLine());
            
            hotel=(C_Hotel)sesion.get(C_Hotel.class, idHotel);
            
            if(hotel!=null){

                if(Menu.menuConfirmar(leer)==1)
                {
                    sesion.beginTransaction();
                    sesion.delete(hotel);
                    sesion.getTransaction().commit();
                    System.out.println("\n Hotel eliminado \n");
                }
            }else
                System.out.println("\n Hotel no encontrado \n");

            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        sesion.close();
        
    }
    
    public static void bajaCliente (BufferedReader leer) throws IOException {
        
        C_Cliente cliente;
        Session sesion;
        String dni;
        
        
        sesion=HibernateUtil.getSession();
        
        try{
            System.out.println("Introduzca el DNI del Cliente a eliminar");
            dni=leer.readLine();
            
            cliente=(C_Cliente)sesion.get(C_Cliente.class, dni);
            
            if(cliente!=null){

                if(Menu.menuConfirmar(leer)==1)
                {
                    sesion.beginTransaction();
                    sesion.delete(cliente);
                    sesion.getTransaction().commit();
                    System.out.println("\n Cliente eliminado \n");
                }
            }else
                System.out.println("\n Cliente no encontrado \n");

            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        sesion.close();
        
    }
    
    public static void bajaReserva (BufferedReader leer) throws IOException {
        
        C_Reserva reserva;
        Session sesion;
        int id;
        
        
        sesion=HibernateUtil.getSession();
        
        try{
            System.out.println("Introduzca el id de la reserva a eliminar");
            id=Integer.parseInt(leer.readLine());
            
            reserva=(C_Reserva)sesion.get(C_Reserva.class, id);
            
            if(reserva!=null){

                if(Menu.menuConfirmar(leer)==1)
                {
                    sesion.beginTransaction();
                    reserva.getHotel().setHabitaciones( reserva.getHotel().getHabitaciones() + reserva.getCantidad() );
                    sesion.delete(reserva);
                    sesion.getTransaction().commit();
                    System.out.println("\n Reserva eliminada \n");
                }
            }else
                System.out.println("\n Reserva no encontrada \n");

            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        sesion.close();
        
    }

}
