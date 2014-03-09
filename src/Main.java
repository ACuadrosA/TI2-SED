
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
            Archivos a = new Archivos();
            Difusificador d = new Difusificador();
            d.difusificar(4.8f,a, "A");
            d.difusificar(45, a, "B");
            //a.escribir_Arch_Maestro("A");
            //a.leer_bc("A");
            //a.escribir_Arch_Maestro("B");
            //a.leer_bc("B");
            //a.escribir_Arch_Maestro("C");
            //a.leer_bc("C");
}
    
}
