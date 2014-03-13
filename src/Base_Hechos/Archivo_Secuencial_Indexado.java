
package Base_Hechos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Archivo_Secuencial_Indexado 
{
    File maestro,indice;
    long puntIndice,puntMaestro;
    Arbol directorio;

    public Archivo_Secuencial_Indexado(String nombre) 
    {
        maestro = new File(nombre);
        indice = new File("index_"+ nombre);
        directorio  = new Arbol();
    }
    
    public void nuevo() throws IOException
    {
        maestro.createNewFile();
        indice.createNewFile();
    }
    
    public void cargar() throws IOException
    {
        
    }
    
    public void leerCompleto()
    {
    
    }
    
    public void leerPorLlave()
    {
        
    }
    
    public void insertar(int llave,long valor) throws FileNotFoundException, IOException
    {
        RandomAccessFile raf = new RandomAccessFile(indice, "rw");
        raf.seek(puntIndice);
        raf.writeInt(llave);
        raf.writeLong(valor);
        puntIndice = raf.getFilePointer();
        raf.close();
    }   
    
    public void escribeIndice() throws FileNotFoundException, IOException
    {
        
    }

    void insertar(int llave) throws FileNotFoundException, IOException 
    {
        directorio.insertar(puntMaestro,llave);
        insertar(llave,puntMaestro);
        
    }

    private static class Arbol {
        Nodo raiz;              

        public Arbol() 
        {
            raiz = null;
        }
        
        public void insertar(long valor, int llave)
        {
            Nodo temp = new Nodo(valor, llave);
            if(raiz == null)
            {
                raiz = temp;
            }
            else
            {
                raiz.inserta(temp);
            }
        }
    }

    private static class Nodo {
        long valor;
        int llave;
        Nodo der,izq;

        public Nodo(long valor, int llave) 
        {
            this.valor = valor;
            this.llave = llave;
        }
        
        public void inserta(Nodo m)
        {
            if(llave < m.llave)            
                if(der == null)
                    der = m;
                else
                    der.inserta(m);            
            else
                if(izq == null)
                    izq = m;
                else
                    izq.inserta(m);            
        }
    }
    
}
