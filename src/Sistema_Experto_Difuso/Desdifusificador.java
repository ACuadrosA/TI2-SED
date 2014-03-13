package Sistema_Experto_Difuso;

import java.io.File;

public class Desdifusificador 
{
    File salDifusa,salReal;
    
    public Desdifusificador(String rutaIn,String rutaSal)
    {
        salDifusa = new File(rutaIn);
        salReal = new File(rutaSal);
    }
    
    public void Desdifusificar()
    {
        
    }
    
    /*
    Este método genera el archivo que contiene las variables de salida, arch
    es el archivo de la variable de salida que se seleccionará, etiqueta es el
    nombre de la etiqueta y valor el valor de la etiqueta
   */
    
    public static void generarArchivo(String arch,String etiqueta,double valor)
    {
     /*
      El símbolo | indica la separación entre la etiqueta y su valor, y el
      caracter / indica fin de línea
        */   
     try
     {
      RandomAccessFile raf=new RandomAccessFile(arch,"rw");
      raf.seek(raf.length());
      raf.writeChars(etiqueta);
      raf.writeChar('|');
      raf.writeDouble(valor);
      raf.writeChar('/');
     }//fin de try
     catch(FileNotFoundException e)
     {
         System.out.println(e);
     }//fin de catch
     catch(IOException e)
     {
         System.out.println(e);
     }//fin de catch    
    }//Fin de generarArchivo
}
