package A2M3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.bind.DatatypeConverter;

public class main {

    private PrintWriter out;
    private BufferedReader in;
	
	public static void main(String args[]) throws IOException,   UnknownHostException {
    	main cliente =new main();
    	cliente.sendMail();
	}

    public void sendMail()
    {
	    try{
	    	String usuario = "athaloc_65@terra.com";
	    	String password = "456mdfg";
	    	
	    	byte[] dataUsuario = usuario.getBytes();
	    	byte[] dataPassword = password.getBytes();
	    	
	    	java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
            String FECHA = sdf.format(date);
	    	
	    	Socket s = new Socket("smtp.terra.com", 587); 
		    out = new PrintWriter(s.getOutputStream());
		    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		    String hostName = InetAddress.getLocalHost().getHostName();
		    System.out.println("hostName = " + hostName);
		    send(null);
		    send("HELO " + hostName);    
		    send("AUTH LOGIN");
		    	    
		    send(javax.xml.bind.DatatypeConverter.printBase64Binary(dataUsuario));
		    send(javax.xml.bind.DatatypeConverter.printBase64Binary(dataPassword));	    
	    
		    send("MAIL FROM: " + "<athaloc_65@terra.com>");
		    send("RCPT TO: " + "<athaloc_65@terra.com>");
		    send("DATA ");
		    sendMessage("Envio de prueba");
		    sendMessage("Date: " + FECHA);
		    sendMessage("FROM: athaloc_65@terra.com");
		    sendMessage("To: athaloc_65@terra.com");
		    sendMessage("Este es el mensaje");
	    	send(".");
	    	send("QUIT");
	    	s.close();
	    	out.close();
	    	in.close();
	    }
	    catch(IOException e){ 
	    	e.printStackTrace();
	    }
    }

    public void send(String s) throws IOException
    { 
    	if (s != null){ 
    		System.out.println("E:" + s);
    		out.println(s);
    		out.flush();
    	}
    	String line;
    	if ((line = in.readLine()) != null) //output the response
    		System.out.println("R: " + line);
    }
    
    public void sendMessage(String s) throws IOException
    {    	
    	if (s != null){ 
		System.out.println("E:" + s);
		out.println(s);
		out.flush();
    	}    	
    }
    
}









