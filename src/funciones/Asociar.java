package funciones;
import POJOS.C_Cliente;
import POJOS.C_Reserva;
import java.io.BufferedReader;
import java.io.IOException;
import org.hibernate.Session;


// @author Miguel

public class Asociar {
    
    public static void asociarCliente (BufferedReader leer, C_Reserva reserva, Session sesion) throws IOException {
        
        byte op=1;
        C_Cliente cliente;
        String dni;
        
        
        while(op!=0){
            try{
                System.out.println("¿Desea asociar un Cliente nuevo o uno ya existente?"
                        + "\n1.Nuevo"
                        + "\n2.Existente"
                        + "\n0.Finalizar");
                op=Byte.parseByte(leer.readLine());
                switch(op){
                    case 1:
                        cliente=Altas.nuevoCliente(leer);
                        sesion.beginTransaction();
                        reserva.getClientes().add(cliente);
                        sesion.getTransaction().commit();
                        System.out.println("\n - Cliente Asociado - \n");
                        break;
                    case 2:
                        System.out.println("Introducir DNI del cliente a asociar:");
                        dni=leer.readLine();
                        cliente=(C_Cliente)sesion.get(C_Cliente.class, dni);

                        if(cliente!=null){
                            sesion.beginTransaction();
                            reserva.getClientes().add(cliente);
                            sesion.getTransaction().commit();
                            System.out.println("\n - Cliente Asociado - \n");
                        }
                        else
                            System.out.println("\n No se ha encontrado al Cliente \n");
                        break;
                }
            }catch(Exception e) {
                System.out.println("\n - Cliente Duplicado - \n");
                sesion.getTransaction().rollback();
            }
        } 
    }
}
