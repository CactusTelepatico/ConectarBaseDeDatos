package defaultPackage;
import java.sql.*;
import java.util.Scanner;
import java.io.*;
public class TestConexion {
	private String bd = "juegos";
	private String login = "root";
	private String pwd = "";
	private String url = "jdbc:mysql://localhost/" + bd
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private Connection conexion;
//antes que nada comprobar que nos conectamos a la base de datos
	public TestConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, login, pwd);
			System.out.println(" -Conexión con MySQL establecida -");

		} catch (ClassNotFoundException cnfe) {
			System.out.println(" –Error de Conexión con MySQL-");
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			System.out.println(" –Error de Conexión con MySQL-");
			sqle.printStackTrace();
		} catch (Exception e) {
			System.out.println(" –Error de Conexión con MySQL-");
			e.printStackTrace();
		}
	}
	//consulta básica para mostrar los datos de la tabla juegos, la query se encuentra en Test
	public void Consulta(String query, int columna) {
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			while (rset.next()) {

				for (int i = 0; i <= 2; i++) {
					System.out.print(rset.getString(i + 1) + "\t");

				}
				System.out.println();
			}

			rset.close();
			stmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
//Inserción de datos en la base de datos mediante scanner
	public void Inserta() {

		Scanner s = new Scanner(System.in);
		try {
			System.out.println("Enter id:");
			String id = s.next();
			System.out.println("Enter name:");
			String nombre = s.next();
			System.out.println("Enter gender:");
			String genero = s.next();

			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, login, pwd);
			PreparedStatement pst = conexion.prepareStatement("insert into juegos(Id,Nombre,Genero) values(?,?,?)");

			pst.setString(1, id);
			pst.setString(2, nombre);
			pst.setString(3, genero);
			int i = pst.executeUpdate();
			if (i != 0) {
				System.out.println("added");
			} else {
				System.out.println("failed to add");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}
//Consulta de los datos del fichero
	public void ConsultaFichero() {
		 File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;

	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File ("archivo.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null)
	            System.out.println(linea);
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	}
	//Añadir dato al fichero
	public void EscribirFichero() {
		Scanner s = new Scanner(System.in);
		System.out.println("Introduce el dato:");
		String dato = s.next();
		

		FileWriter fichero = null;
		try {

			fichero = new FileWriter("archivo.txt", true);

			// Escribimos en el fichero
				fichero.write(dato+"\n");
			

			fichero.close();

		} catch (Exception ex) {
			System.out.println("Mensaje de la excepción: " + ex.getMessage());
		}
	}
	public void DeBaseDeDatosAFichero(String query, int columna) {
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			FileWriter fichero = null;
			int count = 0;
			while (rset.next()) {

				for (int i = 0; i <= 2; i++) {
					try {

						fichero = new FileWriter("archivo.txt", true);

						// Escribimos en el fichero
							fichero.write(rset.getString(i + 1) + "\t");
							count++;
							if(count==3) {
								fichero.write("\n");
								count=0;
							}

							fichero.close();

					} catch (Exception ex) {
						System.out.println("Mensaje de la excepción: " + ex.getMessage());
					}
					

				}
				
				
			}

			rset.close();
			stmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
public void DeFicheroABaseDeDatos() {
	
	File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    String palabras[] = new String[3];
	try {
		

		Class.forName("com.mysql.cj.jdbc.Driver");
		conexion = DriverManager.getConnection(url, login, pwd);
		PreparedStatement pst = conexion.prepareStatement("insert into juegos(Id,Nombre,Genero) values(?,?,?)");
		archivo = new File ("archivo.txt");
        fr = new FileReader (archivo);
        br = new BufferedReader(fr);

        // Lectura del fichero
        String linea;
        while((linea=br.readLine())!=null) {
        	
        	palabras = linea.split("\t");
        	
    		pst.setString(1, palabras[0]);
    		pst.setString(2, palabras[1]);
    		pst.setString(3, palabras[2]);
    		int i = pst.executeUpdate();
    		if (i != 0) {
    			//se ha añadido bien
    		} else {
    			//avisamos de que no se ha añadido bien
    			System.out.println("failed to add");
    		}
    		
    		
        }
           
		
		

	} catch (Exception e) {
		System.out.println(e);
	}
	
	}
}
	


