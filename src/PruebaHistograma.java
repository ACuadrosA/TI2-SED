/**
 *
 * @author AngelAlfredo
 */
public class PruebaHistograma 
{
 //Las siguientes variables se establecen para generar variables de la función
 //donde las que tienen la letra máyuscula A pretenecen a la función de la
 //primer recta y las que tienen B a la de la 2da recta.
 //xA y xB serán las variables introducidas por el usuario.  
 //m y m2 son las pendientes de cada recta
 double xA,xA2,xB,xB2,m,m2;
    public static void main(String[] args) 
    {
        
    }//fin de main
    
    double histograma(float v1, float v2, float ptoBase)
    {
     //ptoBase2 es el segundo punto base a encontrar
     float ptoBase2=0;
     double resta=0,traslape=0;
     //m es la variante que fungirá como pendiente   
     m=1/(v1-ptoBase);
     //m2 es la variante que dará la pendiente invertida
     m2=-m;
     //dist es la variable para obtener el otro punto base
     float dist=v1-ptoBase;
     
      xA2=ptoBase;
     
     if(v2==0)
     {
       ptoBase2=v1+dist;
         System.out.println(ptoBase2);
       //resta es una variable que definirá la cantidad de traslape
       resta=dist*0.35;
       //traslape será el punto base de la siguiente gráfica
       traslape=ptoBase2-resta;
         System.out.println(traslape);
         
       xB2=v1;
     }//fin de if 
     else
     {
       ptoBase2=v2+dist;
         System.out.println(ptoBase2);
       resta=dist*0.35;
       traslape=ptoBase2-resta;
         System.out.println(traslape);
       xB2=v2;
     }//fin de else  
     
     /*
      Tomando como base la ecuación de la recta de forma punto pendiente:
      y=m(x-x1)+y1
      Se tienen todos los valores para calcular las funciones de las rectas
      del modo que sigue:
      1er recta: y=m*(xA-xA2)
      2da recta: y=-m*(xB-xB2)
      NOTA: y1 siempre es 0 pues siempre es el origen.
     */
     
     return traslape;
    }//fin de histograma        

    float evaluar(float x1, float y1, float x2, float y2, float x) 
    {
        return  ((y2-y1)/(x2-x1))*(x - x2) + y2;
    }
    
}//fin de clase
