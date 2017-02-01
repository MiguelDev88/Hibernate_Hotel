package funciones;
import POJOS.*;
import hotelmiguel.HibernateUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import org.hibernate.Session;


// @author Miguel

public class Altas {
    
    public static void altas (BufferedReader leer) throws IOException {
        
        byte op=1;
        
        while(op!=0){
            op=Menu.menuAltas(leer);
            switch(op){
                case 1:
                    altaHotel(leer);
                    break;
                case 2:
                    altaCliente(leer);
                    break;
                case 3:
                    altaReserva(leer);
                    break;
            }
            
        }
        
    }
    
    public static void altaHotel (BufferedReader leer) throws IOException {
        
        Session sesion;
        C_Hotel hotel;
        
        
        sesion=HibernateUtil.getSession();
        
        try{
        
            hotel=nuevoHotel(leer);
            
            sesion.beginTransaction();
            sesion.save(hotel);
            sesion.getTransaction().commit();
            
            System.out.println("\n - Hotel Registrado - \n");
            
        }catch(Exception e) {
            System.out.println("\n - Error en el alta del Hotel - \n");
        }
        
        sesion.close();

    }
    
    public static void altaCliente (BufferedReader leer) throws IOException {
        
        Session sesion;
        C_Cliente cliente;
        
        
        sesion=HibernateUtil.getSession();
        
        try{
        
            cliente=nuevoCliente(leer);
            
            sesion.beginTransaction();
            sesion.save(cliente);
            sesion.getTransaction().commit();
            
            System.out.println("\n - Cliente Registrado - \n");
            
        }catch(Exception e) {
            System.out.println("\n - Error en el alta del Cliente - \n");
        }
        
        sesion.close();

    }
    
    public static void altaReserva (BufferedReader leer) throws IOException {
        
        Session sesion;
        C_Reserva reserva;
        C_Hotel hotel;
        int idHotel;
        
        
        sesion=HibernateUtil.getSession();
        
        try{
            System.out.println("Introducir id del Hotel en el que se va a realizar la reserva:");
            idHotel=Integer.parseInt(leer.readLine());
            hotel=(C_Hotel)sesion.get(C_Hotel.class, idHotel);
            
            if(hotel!=null){
                
                reserva=nuevaReserva(leer);
                reserva.setHotel(hotel);
                
                hotel.addListener(reserva);
                hotel.setHabitaciones( hotel.getHabitaciones() - reserva.getCantidad());

                if(reserva.isOK()){

                sesion.beginTransaction();
                sesion.save(reserva);
                sesion.getTransaction().commit();
            
                System.out.println("\n - Reserva Registrada - \n");
                
                Asociar.asociarCliente(leer, reserva, sesion);
                
                
                }else
                    System.out.println("\n - El hotel no dispone de habitaciones suficientes - \n");
            }else
                System.out.println("\n - No se ha encontrado el hotel - \n");
        }catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("\n - Error en el alta de la Reserva - \n");
        }
        
        sesion.close();

    }
    
    public static C_Hotel nuevoHotel (BufferedReader leer) throws IOException {
        
        int idHotel, habitaciones;
        String nombre, telefono;
        float pvp;
        
        
        System.out.println("Introducir id:");
        idHotel=Integer.parseInt(leer.readLine());
        System.out.println("Introducir nombre del Hotel:");
        nombre=leer.readLine();
        System.out.println("Introducir teléfono:");
        telefono=leer.readLine();
        System.out.println("Introducir habitaciones totales:");
        habitaciones=Integer.parseInt(leer.readLine());
        System.out.println("Introducir PVP");
        pvp=Float.parseFloat(leer.readLine());
        
        C_Hotel hotel=new C_Hotel(idHotel,nombre,telefono,habitaciones,pvp);
        
        return hotel;
        
    }
    
    public static C_Cliente nuevoCliente (BufferedReader leer) throws IOException {
        
        String dni, nombre, telefono;
        
        
        System.out.println("Introducir DNI:");
        dni=leer.readLine();
        System.out.println("Introducir nombre:");
        nombre=leer.readLine();
        System.out.println("Introducir teléfono:");
        telefono=leer.readLine();
        
        C_Cliente cliente=new C_Cliente (dni,nombre,telefono);
        
        return cliente;
        
    }
    
    public static C_Reserva nuevaReserva (BufferedReader leer) throws IOException {
        
        int cantidad;
        Date fechaEntrada;

        System.out.println("Introducir Fecha de entrada:(aaaa-mm-dd)");
        fechaEntrada=Date.valueOf(leer.readLine());
        System.out.println("Introducir cantidad a reservar:");
        cantidad=Integer.parseInt(leer.readLine());
        
        C_Reserva reserva=new C_Reserva (fechaEntrada,cantidad);
        
        return reserva;
        
    }

}
