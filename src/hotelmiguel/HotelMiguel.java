package hotelmiguel;
import funciones.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


// @author Miguel
 
public class HotelMiguel {


    public static void main(String[] args) {
        
        BufferedReader leer=new BufferedReader (new InputStreamReader(System.in));
        byte op=0;
        
        CrearTablas.crearTablas();
        
        do{
            try{
                op=Menu.menuPrincipal(leer);
                
                switch(op){
                    case 1:
                        Altas.altas(leer);
                        break;
                    case 2:
                        Bajas.bajas(leer);
                        break;
                    case 3:
                        Consultas.consultas(leer);
                    case 0:
                        System.out.println("\n - Fin del Programa - \n");
                        System.exit(0);
                }
                
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            
        }while(op!=0);
        
    }
    
}
