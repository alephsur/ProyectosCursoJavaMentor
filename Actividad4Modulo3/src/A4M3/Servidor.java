package A4M3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;

public class Servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> mensajes = new ArrayList<String>();
		ArrayList<Chat> clientes = new ArrayList<Chat>();
		
		try {
			ServerSocket socketServidor = new ServerSocket(Integer.parseInt(args[0]));
			
			while(true){
				Chat elChat = new Chat(socketServidor.accept(),mensajes);
				clientes.add(elChat);
				elChat.SetClientes(clientes);
				elChat.start();				
			}			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("El parametro debe ser un entero indicando el puerto de comunicaciones.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}






















