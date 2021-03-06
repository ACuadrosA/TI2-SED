package Sistema_Experto_Difuso;


import Base_Hechos.Archivos;
import java.io.File;
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
    float traslape;
    File varDifusa;
    long puntero;

    public Difusificador(float traslape,String ruta) 
    {
        puntero = 0;
        this.traslape = traslape;
        varDifusa = new File(ruta);
    }
    
    public void difusificar(float ve,Archivos a,String nombre) throws FileNotFoundException, IOException
    {
        long ap_actual, ap_final;
        float punto[] = new float[8],pbase = 0,pbasey=0;
        PruebaHistograma ph;
        
        RandomAccessFile raf = new RandomAccessFile(varDifusa, "rw");
        raf.seek(puntero);
        
        RandomAccessFile leer_archi = new RandomAccessFile(nombre, "r");
            
        char etiqueta[] = new char[15], temp;
            
            for (int c = 0; c < etiqueta.length; c++) {
                temp = leer_archi.readChar();
                etiqueta[c] = temp;
            }
        

        pbase = leer_archi.readFloat();//incio universo
         leer_archi.readFloat();//fin universo
         traslape = leer_archi.readFloat();//traslape
         for (int i = 0; i < 5; i++) {
            leer_archi.readFloat();
        }
             
        while ((ap_actual = leer_archi.getFilePointer()) != (ap_final = leer_archi.length())) 
        {
            
            etiqueta = new char[15];
            for (int c = 0; c < etiqueta.length; c++) {
                temp = leer_archi.readChar();
                etiqueta[c] = temp;
            }
            
            String string = new String(etiqueta);
             ph = new PruebaHistograma();
             float valor = 0;
             float m = 0,t = 0,xf = 0.0f;
             
             
            for (int i = 0; i < 8; i++) 
            {
                punto[i] = leer_archi.readFloat();
                
                //System.out.println("punto "+punto[i]);
                if(punto[i] != 0)
                {                    
                    t = ph.evaluar(pbase, pbasey, punto[i], 1, ve);
//                    System.out.println(string +" = "+ t);
                    valor = (valor < t)?t:valor;
                    t = (1-pbasey)/(punto[i]-pbase);
                    m = (t != 0)?t:m;
                    pbase = punto[i];
                    pbasey = 1;
                }            
                else
                {
                    xf = ph.fin(pbase,pbasey,-m);
                    
                    t = ph.evaluar(pbase, pbasey, xf, 0, ve);
                    valor = (valor < t)?t:valor;
//                    System.out.println(string +" = "+ t);
                    pbasey = 0;
                    pbase = xf - (xf - pbase) * traslape;
                    //System.out.println("("+pbase+","+pbasey+")");
                    for(int j = 0 ; j < (8-i-1);j++)
                    {
                        leer_archi.readFloat();
                    }
                    break;
               
            }
//            
            
            }
            StringBuilder sb = new StringBuilder(nombre);
            sb.setLength(15);
            
            raf.writeChars(sb.toString());
            raf.writeChars(string);
            raf.writeFloat(valor);
            System.out.println(sb.toString()+string+valor);//En lugar de imprimir en pantalla escribimos a un archivo 
                                                    //Nombre de variable (char[15]) etiqueta(char[15]) grado de pertenencia(float)
           
        }
        puntero = raf.getFilePointer();
        raf.close();
        leer_archi.close();
    }
    
}
