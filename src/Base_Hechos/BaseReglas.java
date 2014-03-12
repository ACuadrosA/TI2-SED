/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base_Hechos;

import GUI.Consola;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Angel
 */
public class BaseReglas extends Archivo_Secuencial_Indexado
{
    String Ante[], Cons[];
    public BaseReglas(String nombre) 
    {
        super(nombre);
    }

    public void comando(Consola con) 
    {
        Scanner comando = new Scanner(con.getEntrada());
        PrintStream salida = con.getSalida();
        String linea;
        //Por ahora la salida será por consola
        salida.println("Modo de configuración de Reglas");
        salida.println("Ingrese una instruccion y presione ENTER, para salir Escriba SALIR");
        salida.print("Reglas>");
       
        while( ! (linea = comando.nextLine()).equals("SALIR"))  //Mientras no se escriba SALIR el programa continúa
        {
            if(linea.startsWith("antecedentes:"))
            {
                Ante = linea.substring(14).split(",");
                salida.println("Antecedentes");
                for (int i = 0; i < Ante.length; i++) 
                {
                    salida.println("\t"+Ante[i]);
                }
            }
            else
            if(linea.startsWith("consecuentes:"))
            {
                Cons = linea.substring(14).split(",");
                salida.println("Consecuente(s)");
                for (int i = 0; i < Cons.length; i++) 
                {
                    salida.println("\t"+Cons[i]);
                }
            }
            else
            if(linea.startsWith("inicia"))
            {
                try {                
                    escribeIndice();
                } catch (IOException ex) {
                    salida.println(ex.getMessage());
                } 
            }
            else
            if(linea.startsWith("nueva regla:"))
            {
                String[] split = linea.substring(13).split(",");
                
            }
                    
            salida.print("Reglas>");
        }
    }

    @Override
    public void escribeIndice() throws FileNotFoundException, IOException
    {
        RandomAccessFile cabezal = new RandomAccessFile(indice, "rw");
        cabezal.write(Ante.length);
        for (int i = 0; i < Ante.length; i++) 
        {
            StringBuilder sb = new StringBuilder(Ante[i]);
            sb.setLength(15);
            cabezal.writeChars(sb.toString());
        }
        cabezal.write(Cons.length);
        for (int i = 0; i < Cons.length; i++) 
        {
            StringBuilder sb = new StringBuilder(Cons[i]);
            sb.setLength(15);
            cabezal.writeChars(sb.toString());
        }
        puntIndice = cabezal.getFilePointer();
        cabezal.close();
    }    

    @Override
    public void cargar() throws IOException{
        RandomAccessFile cabezal = new RandomAccessFile(indice, "rw");
        char etiqueta[] = new char[15];
        int readInt = cabezal.readInt();
        
        Ante = new String[readInt];        
        for (int i = 0; i < Ante.length; i++) 
        {
            for (int j = 0; j < 15; j++) 
            {
                etiqueta[j] = cabezal.readChar();
            }
            Ante[i] = new String(etiqueta);
        }
        
        readInt = cabezal.readInt();
        
        Cons = new String[readInt];
        for (int i = 0; i < Cons.length; i++) 
        {
            for (int j = 0; j < 15; j++) 
            {
                etiqueta[j] = cabezal.readChar();
            }
            Cons[i] = new String(etiqueta);
        }
        puntIndice = cabezal.getFilePointer();
        cabezal.close();
    }

    public void insertar(int llave,String[] s) throws FileNotFoundException, IOException 
    {
        RandomAccessFile raf = new RandomAccessFile(maestro, "rw");
        super.insertar(llave);
        raf.seek(puntMaestro);
        for (String cad:s) 
        {
            StringBuilder sb = new StringBuilder(cad);
            sb.setLength(15);
            raf.writeChars(sb.toString());
        }
        puntMaestro = raf.getFilePointer();
        raf.close();
    }
    
 
    
}
