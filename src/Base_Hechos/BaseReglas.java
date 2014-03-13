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
import java.util.logging.Level;
import java.util.logging.Logger;

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
                    salida.println("Escribiendo en archivo indice");
                    escribeIndice();
                } catch (IOException ex) {
                    salida.println(ex.getMessage());
                } 
            }
            else
            if(linea.startsWith("nueva regla:"))
            {
                int hash = linea.substring(13).hashCode();
                String[] split = linea.substring(13).split(",");
                try {
                    insertar((hash < 0)?-hash:hash,split);
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
                    
            salida.print("Reglas>");
        }
    }

    @Override
    public void escribeIndice() throws FileNotFoundException, IOException
    {
        RandomAccessFile cabezal = new RandomAccessFile(indice, "rw");
        cabezal.writeInt(Ante.length);
        for (int i = 0; i < Ante.length; i++) 
        {
            StringBuilder sb = new StringBuilder(Ante[i]);
            sb.setLength(15);
            cabezal.writeChars(sb.toString());
        }
        cabezal.writeInt(Cons.length);
        for (int i = 0; i < Cons.length; i++) 
        {
            StringBuilder sb = new StringBuilder(Cons[i]);
            sb.setLength(15);
            cabezal.writeChars(sb.toString());
        }
        
        puntIndice = cabezal.getFilePointer();
        System.out.println(puntIndice);
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

    public void depurar() throws IOException 
    {
        RandomAccessFile raf = null;
        char temp[] = new char[15];
        System.out.println("Archivo "+ indice);
        if(indice.exists())
            raf = new RandomAccessFile(indice, "rw");
        else
        {
            System.out.println("El archivo "+indice+" no exitse");
            return;
        }
        System.out.println(indice);
        int a = raf.readInt();
        System.out.println(a + " antecedentes: ");
        for (int i = 0; i < a; i++) 
        {
            for (int j = 0; j < 15; j++) 
            {
                char c = raf.readChar();
                temp[j] = c;
            }
            System.out.println("\t"+new String(temp));
        }
        a = raf.readInt();
        System.out.println(a + " consecuentes: ");
        for (int i = 0; i < a; i++) 
        {
            for (int j = 0; j < 15; j++) 
            {
                char c = raf.readChar();
                temp[j] = c;
            }
            System.out.println("\t"+new String(temp));
        }
        while(raf.getFilePointer() < raf.length())
        {
            System.out.print("\t"+raf.readInt());
            
            System.out.println("\t"+raf.readLong());
        }
        raf.close();
    }
    
}
