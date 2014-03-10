
package Base_Hechos;

import java.io.File;
import java.io.IOException;

public class Archivo_Secuencial_Indexado 
{
    File maestro,indice;

    public Archivo_Secuencial_Indexado(String nombre) 
    {
        maestro = new File(nombre);
        indice = new File("index_"+ nombre);
    }
    
    public void nuevo() throws IOException
    {
        maestro.createNewFile();
        indice.createNewFile();
    }
    
    public void cargar(Object o)
    {
        
    }
    
    public void leerCompleto()
    {
    
    }
    
    public void leerPorLlave()
    {
        
    }
    
    public void insertar(int llave,Object o)
    {
        
    }   
    
}
