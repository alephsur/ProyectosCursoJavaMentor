package ClienteChat;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Cliente extends Thread implements ActionListener{
	
	private JFrame frame;
	private TextArea comunicaciones;
	private JTextField mensaje;
	private JButton enviar;
	
	private Socket socketCliente;
	
	private String ipServidor, nick;
	private int puertoComunicaciones;
	
	private PrintWriter out;
	private BufferedReader in;
	
	private Thread hiloLectura;
	
	private String[] mensajes;
	
 	public Cliente(String ipServidor, String nick, int puertoComunicaciones){
		this.ipServidor = ipServidor;
		this.nick = nick;
		this.puertoComunicaciones = puertoComunicaciones;

		this.hiloLectura = new Thread(this);
		
		try {
			socketCliente = new Socket(ipServidor,puertoComunicaciones);
			
			out = new PrintWriter(socketCliente.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			out.println("USUARIO: " + nick + " conectado");
			this.hiloLectura.start();
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void DiseņarPantalla(){
		frame = new JFrame();
		frame.setBounds(0, 0, 400, 400);
		
		frame.setLayout(new BorderLayout());
		
		comunicaciones = new TextArea("",20,15);
		
		frame.add(comunicaciones,BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		
		
		frame.add(panel,BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout());
		
		mensaje = new JTextField();
		mensaje.setColumns(25);
		panel.add(mensaje);
		
		enviar = new JButton("Enviar");
		panel.add(enviar);
		enviar.addActionListener(this);
		
		frame.setVisible(true);
	}

	
	@Override
	public void run(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){
			synchronized (hiloLectura) {
				String linea="";
				try {
					linea = in.readLine();
					
					String[] lista = linea.split(",");
					
					if(this.mensajes == null){
						this.comunicaciones.setText("USUARIO: " + nick + " conectado\n");
						this.mensajes = lista;
					}
					else{
						for(int i=this.mensajes.length;i<lista.length;i++){
							this.comunicaciones.setText(this.comunicaciones.getText() + lista[i].replace("]", "") + "\n");
						}
						this.mensajes =  lista;
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!mensaje.getText().equals("")){
			out.println(nick + ": " +  mensaje.getText());	
		}
		
	}
}






























