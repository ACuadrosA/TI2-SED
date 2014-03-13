package Sistema_Experto_Difuso;

import Base_Hechos.Archivos;
import Base_Hechos.BaseReglas;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Motor_Inferencia 
{
    File varDifusa,salDifusa;
    
    public Motor_Inferencia(String vardif,String salDifusa)
    {
        varDifusa = new File(vardif);
        this.salDifusa = new File(salDifusa);
    }
    
    public void inferir(BaseReglas baseCon) throws IOException
    {
        baseCon.cargar();
        Archivos a = new Archivos();
        float min = 1,temp;
        int i;
        while(baseCon.tieneRegistros())
        {
        //Obtener regla
            baseCon.siguienteRegla();
            
            for (i = 0; i < baseCon.noAnt(); i++) 
            {
                //Obtener grados de pertenencia de cada uno
                    temp = buscar(baseCon.nombreAntecedente(i),baseCon.etiquetaRegla(i),varDifusa);
                //Calcular el mínimo
                    min = Math.min(min,temp);                
            }
            
        //Asignar al(a los) consecuente(s) el máximo entre su valor anterior y el minimo
            
            for (int j = 0; j < baseCon.noCon(); j++) 
            {
                    temp = buscar(baseCon.nombreConsecuente(i),baseCon.etiquetaConsec(i),salDifusa);
                    if(temp < min || temp == 0.0f)
                        inserta(baseCon.nombreConsecuente(i),baseCon.etiquetaConsec(i),min);
            }
        //Fin de reglas        
        //Salida difusa
        }
    }

    private float buscar(String nombreAntecedente, String etiquetaRegla,File archivo) throws FileNotFoundException, IOException 
    {
        RandomAccessFile raf = new RandomAccessFile(archivo, "r");
        
        char temp[] = new char[15];
        
        String variable = null;
        String etiqueta = null;
        float valor = 0.0f;
        do
        {
            if(raf.getFilePointer() == raf.length())
            {
                return 0.0f;
            }
            
            for (int i = 0; i < 15; i++) 
                temp[i] = raf.readChar();
            variable = new String(temp);
            
            for (int i = 0; i < 15; i++) 
                temp[i] = raf.readChar();
            etiqueta = new String(temp);
            
            valor = raf.readFloat();            
            
        }while(!(variable.equals(nombreAntecedente) && etiqueta.equals(etiquetaRegla)));
        
        raf.close();
        return valor;
    }

    private void inserta(String nombreConsecuente, String etiquetaConsec, float min) throws IOException 
    {
        RandomAccessFile raf = new RandomAccessFile(salDifusa, "rw");
        
        char temp[] = new char[15];
        
        String variable = null;
        String etiqueta = null;
        float valor = 0.0f;
        
        while(raf.getFilePointer() < raf.length())
        {            
            for (int i = 0; i < 15; i++) 
                temp[i] = raf.readChar();
            variable = new String(temp);
            
            for (int i = 0; i < 15; i++) 
                temp[i] = raf.readChar();
            etiqueta = new String(temp);
            
            if(variable.equals(nombreConsecuente) && etiqueta.equals(etiquetaConsec))
            {
                raf.writeFloat(min);
                return;
            }
            
            valor = raf.readFloat();                        
        }
        
        raf.writeChars(nombreConsecuente);
        raf.writeChars(etiquetaConsec);
        raf.writeFloat(min);
        
        raf.close();
    }

    
}
