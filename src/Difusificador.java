
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Angel
 */
public class Difusificador 
{
    
    public void difusificar(float ve,Archivos a,String nombre) throws FileNotFoundException, IOException
    {
        long ap_actual, ap_final;
        float punto[] = new float[8],pbase = 0,pbasey=0;
        PruebaHistograma ph;

        RandomAccessFile leer_archi = new RandomAccessFile(nombre, "r");
        while ((ap_actual = leer_archi.getFilePointer()) != (ap_final = leer_archi.length())) {
            
            char etiqueta[] = new char[15], temp;
            for (int c = 0; c < etiqueta.length; c++) {
                temp = leer_archi.readChar();
                etiqueta[c] = temp;
            }
            new String(etiqueta).replace('\0', ' ');
            System.out.println(etiqueta);
             ph = new PruebaHistograma();
            for (int i = 0; i < 8; i++) 
            {
                punto[i] = leer_archi.readFloat();
                if(ve > pbase && ve < punto[i])
                {
                    ph.evaluar(pbase,pbasey,punto[i],1,ve);
                }
                else
                {
                    
                }
            }
//            int j = 0;
//            float v1,v2;
//            while(punto[j] != 0.0)
//            {
//                v1 = punto[j];
//                System.out.println(v1);
//                j ++;
//                v2 =punto[j];
//                System.out.println(v2);
//                j ++;
//                pbase = (float)ph.histograma(v1, v2, pbase);
//                System.out.println("Punto base = " + pbase);
//            }

        }
        leer_archi.close();
    }
}
