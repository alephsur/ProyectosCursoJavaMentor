package A3M3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class Respuesta extends Thread{
	
	private Socket cliente = null;
	
	public Respuesta(Socket cliente){
		this.cliente = cliente;
	}
	
	@Override
	public void run(){
		
		try {
			
			System.out.println("Comunicacion Entrante recibida");
			
			PrintWriter out = new PrintWriter(this.cliente.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(this.cliente.getInputStream()));
			
			String inputLine = in.readLine();
			
			if(inputLine.equals("HOLA")){
				long numero = Math.round(Math.random());
				if(numero == 1){
					System.out.println("E: ACK");
					out.println("ACK");
					
					ObjectInputStream entrada  = new ObjectInputStream(this.cliente.getInputStream());
					try {
						Alumnos elAlumno = (Alumnos)entrada.readObject();
						System.out.println("E: " + elAlumno.GetNombreAlumno());
						System.out.println("E: " + elAlumno.GetApellidosAlumno());
						System.out.println("E: " + elAlumno.GetEdadAlumno());
						entrada.close();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
				}
				else{
					System.out.println("E: NACK");
					out.println("NACK");
				}				
			}
			else{
				System.out.println("E: NACK");
				out.println("NACK");
			}
			
			out.close();
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
	}
}