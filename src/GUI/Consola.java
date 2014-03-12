package GUI;

import Base_Hechos.Archivos;
import Base_Hechos.BaseReglas;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Consola 
{
    InputStream entrada;
    PrintStream salida;
    
    public Consola(InputStream in,PrintStream out)
    {
        entrada = in;
        salida = out;
    }
    
    public static void main(String[] args) 
    {
        Consola c = new Consola(System.in,System.out);
        c.inicia();
        
    }

    private void inicia() 
    {
        Scanner comando = new Scanner(entrada);
        String linea;
        //Por ahora la salida será por consola
        salida.println("Bienvenido al modo de configuración de SISP");
        salida.println("Ingrese una instruccion y presione ENTER, para salir Escriba SALIR");
        salida.print(">");
       
        while( ! (linea = comando.nextLine()).equals("SALIR"))  //Mientras no se escriba SALIR el programa continúa
        {
            if(linea.startsWith("ver"))
            {
                try{
                    new Archivos().leer_bc(linea.substring(4));
                }catch(IOException e){
                    salida.println(e.getMessage());
                }
                    
            }
            else
            if(linea.startsWith("nueva variable"))
            {
                try{
                    new Archivos().escribir_Arch_Maestro(linea.substring(15));
                }catch(IOException e){
                    salida.println(e.getMessage());
                }
            }
            else
            if(linea.startsWith("nueva base de reglas"))
            {
                try{
                    BaseReglas base = new BaseReglas(linea.substring(21));
                    base.nuevo();
                    base.comando(this);
                }catch(IOException e){
                    salida.println(e.getMessage());
                }
            }
                    
            
            
            salida.print(">");
        }
        
    }

    public PrintStream getSalida() {
        return salida;
    }
    
    public InputStream getEntrada()
    {
        return entrada;
    }
}
