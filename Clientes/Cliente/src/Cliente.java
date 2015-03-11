import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import A3M3.Alumnos;

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Alumnos elAlumno=new Alumnos();
			elAlumno.SetNombreAlumno("PACO");
			elAlumno.SetApellidosAlumno("GARCIA");
			elAlumno.SetEdadAlumno(14);
			Socket cliente = new Socket("localhost", 1234);
			
			PrintWriter out = new PrintWriter(cliente.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			
			System.out.println("E: HOLA");
			out.println("HOLA");
			
			String line = in.readLine();
			
			if(line.equals("NACK")){
				System.out.println("R: NACK");
				System.out.println("Comunicaci�n Finalizada");
			}
			else{
				System.out.println("R: ACK");
				ObjectOutputStream seri = new ObjectOutputStream(cliente.getOutputStream());
				seri.writeObject(elAlumno);
				seri.close();
				System.out.println("E: Alumno enviado. Cerrando cliente.");
				in.close();
				out.close();
				cliente.close();
			}			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}