package A4M3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Chat extends Thread{
	
	public Socket socket;
	private ArrayList<String> mensajes;
	private ArrayList<Chat> clientes;
		
	private PrintWriter out = null;
	private BufferedReader in = null;
	
	public Chat(Socket socket, ArrayList<String> mensajes){
		this.socket = socket;
		this.mensajes = mensajes;
	}
	
	public void SetClientes(ArrayList<Chat> clientes){
		this.clientes = clientes;
	}
	
	
	@Override
	public void run(){
		
		System.out.println("Comunicacion Entrante recibida");
		
		
		try {
			out = new PrintWriter(this.socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			
			while(true){
				String inputLine = in.readLine();
								
				this.mensajes.add(inputLine);
				
				for(int i =0;i<this.clientes.size();i++){
					this.clientes.get(i).Enviar();
				}
			}			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Enviar(){
		out.println(this.mensajes);
	}
}




























