package defaultPackage;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		TestConexion prueba= new TestConexion();
		Integer num = 0;
		while (num !=7) {
			System.out.println("1-Mostrar datos de BD");
			
			System.out.println("2-Añadir 1 a BD");
			
			System.out.println("3-Mostrar datos de Fichero ");
			System.out.println("4-Añadir 1 Fichero");
			System.out.println("5-Pasar los datos que haya en la BD al fichero ");
			System.out.println("6-Pasar los datos que haya en el Fichero a la BD ");
			System.out.println("7-Cerrar programa");
			Scanner s=new Scanner(System.in);
			switch (s.nextInt()) {
			case 1:
				prueba.Consulta("SELECT * FROM juegos",1);
				num=1;
				break;
			case 2:
				prueba.Inserta();
				num=2;
				break;
			case 3:
				prueba.ConsultaFichero();
				num=3;
				break;
			case 4:
				prueba.EscribirFichero();
				num=4;
				break;
			case 5:
				prueba.DeBaseDeDatosAFichero("SELECT * FROM juegos",1);
				num=5;
				break;
			case 6:
				prueba.DeFicheroABaseDeDatos();
				num=6;
				break;
			case 7:
				num=7;
				
				break;
			default:
				System.out.println("Introduce un número!");
			}
	    	
		}
	
		}
}
