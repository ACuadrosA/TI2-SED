
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
            Archivos a = new Archivos();
            
            try
            {
                a.leer_bc("Entregas");
                a.leer_bc("Calidad");
            }
            catch(IOException e)
            {
                System.out.println(e);
                a.escribir_Arch_Maestro("Calidad");
            }
            
}
    
}
