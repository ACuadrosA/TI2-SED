package Base_Hechos;



import com.sun.org.apache.regexp.internal.RETest;
import com.sun.xml.internal.ws.util.UtilException;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Archivos {
    
    String var_entrada;
    float punto;
    long direccion;
    
     public void escribir_Arch_Maestro(String nombre_archi) throws IOException {

        long lreg, desplaza;
        int n;
        StringBuffer buffer = null;
        System.out.println("Archivo Maestro");
        RandomAccessFile archi = new RandomAccessFile(nombre_archi, "rw");

        try {
            archi.readInt();

            for (int i = 0; i < 8; i++) {
                char etiqueta[] = new char[15], temp;
                for (int c = 0; c < etiqueta.length; c++) {
                    temp = archi.readChar();
                    etiqueta[c] = temp;
                }
            }

            lreg = archi.getFilePointer();
            desplaza = recorrerfin() * lreg;
            archi.seek(desplaza);

        } catch (EOFException e) {
            System.out.println("Archivo BC sin datos aun");
        }

        
        Scanner entrada = new Scanner(System.in);
        /*
         Inserccion de universo de discurso y valor de traslape
         */
        
         System.out.println("Nombre del modelo");
         var_entrada = entrada.next();
         buffer = new StringBuffer(var_entrada);
         buffer.setLength(15);
         archi.writeChars(buffer.toString());
         
         System.out.println("Inicio de universo de discurso: ");
         punto = entrada.nextFloat();
         archi.writeFloat(punto);
         
         System.out.println("Fin de universo de discurso: ");
         punto = entrada.nextFloat();
         archi.writeFloat(punto);
        
         System.out.println("Porcentaje de traslape para el modelo: ");
         punto = entrada.nextFloat();
         archi.writeFloat(punto);
         
         for (int i = 0; i < 5; i++) {
             archi.writeFloat(0);
         }
         
        do {
            //Nombre de etiqueta difusa
            System.out.println("Nombre de la etiqueta difusa:");
            var_entrada = entrada.next();
            buffer = new StringBuffer(var_entrada);
            buffer.setLength(15);
            
            archi.writeChars(buffer.toString());

            n = 1;
            for (int i = 0; i < 8; i++) {
                if (n != 1) {
                    archi.writeInt(0);
                } else {
                    System.out.println("Introduce punto critico: ");
                    //punto = entrada.nextInt();
                    punto = entrada.nextFloat();
                    //archi.writeInt(punto);
                    archi.writeFloat(punto);
                    //Agregar otros puntos criticos
                    System.out.println("¿DESEA AGREGAR OTRO PUNTO CRITICO?: SI=1, NO=2  ");
                    n = entrada.nextInt();
                }

            }

            //Agregar otra etiqueta difusa
            System.out.println("¿OTRA ETIQUETA DIFUSA?: SI=1, NO=2  ");
            n = entrada.nextInt();

        } while (n == 1);
        archi.close();
    }
    
public int recorrerfin() throws FileNotFoundException, IOException {
        long ap_actual, ap_final;
        int contador = 0;
        RandomAccessFile leer_archi = new RandomAccessFile("base_conocimiento", "r");

        while ((ap_actual = leer_archi.getFilePointer()) != (ap_final = leer_archi.length())) {
            char etiqueta[] = new char[15], temp;
            for (int c = 0; c < etiqueta.length; c++) {
                    temp = leer_archi.readChar();
                    etiqueta[c] = temp;
            }
            for (int i = 0; i < 8; i++) {
                //leer_archi.readInt();
                leer_archi.readFloat();
            }
                
            contador++;
        }
        leer_archi.close();
        return contador;
    }

public void leer_bc(String nombre) throws IOException {

        long ap_actual, ap_final;

        RandomAccessFile leer_archi = new RandomAccessFile(nombre, "r");
        while ((ap_actual = leer_archi.getFilePointer()) != (ap_final = leer_archi.length())) {
            
            char etiqueta[] = new char[15], temp;
            for (int c = 0; c < etiqueta.length; c++) {
                temp = leer_archi.readChar();
                etiqueta[c] = temp;
            }
            new String(etiqueta).replace('\0', ' ');
            System.out.println(etiqueta);
            
            for (int i = 0; i < 8; i++) {
                //punto = leer_archi.readInt();
                punto = leer_archi.readFloat();
                System.out.println(punto);
            }

        }
        leer_archi.close();
    }

public float getinicio_universo(String nombre) throws FileNotFoundException, IOException{
    float inicio = 0;

        RandomAccessFile leer_archi = new RandomAccessFile(nombre, "r");
           
            char etiqueta[] = new char[15], temp;
            for (int c = 0; c < etiqueta.length; c++) {
                temp = leer_archi.readChar();
                etiqueta[c] = temp;
            }
            new String(etiqueta).replace('\0', ' ');
            System.out.println(etiqueta);
            
                inicio = leer_archi.readFloat();
                System.out.println(inicio);
            
        leer_archi.close();
    
    return inicio;
}

public float getfin_universo(String nombre) throws FileNotFoundException, IOException{
    float fin = 0;
        RandomAccessFile leer_archi = new RandomAccessFile(nombre, "r");
           
            char etiqueta[] = new char[15], temp;
            for (int c = 0; c < etiqueta.length; c++) {
                temp = leer_archi.readChar();
                etiqueta[c] = temp;
            }
            new String(etiqueta).replace('\0', ' ');
            System.out.println(etiqueta);
            
                leer_archi.readFloat();
                fin = leer_archi.readFloat();
                System.out.println(fin);
            
        leer_archi.close();
    
    return fin;
}

public float get_traslape(String nombre) throws FileNotFoundException, IOException{
    float traslape = 0;

        RandomAccessFile leer_archi = new RandomAccessFile(nombre, "r");
           
            char etiqueta[] = new char[15], temp;
            for (int c = 0; c < etiqueta.length; c++) {
                temp = leer_archi.readChar();
                etiqueta[c] = temp;
            }
            new String(etiqueta).replace('\0', ' ');
            System.out.println(etiqueta);
            
                leer_archi.readFloat();
                leer_archi.readFloat();
                traslape = leer_archi.readFloat();
                System.out.println(traslape);
            
        leer_archi.close();
    
    return traslape;
}

}

