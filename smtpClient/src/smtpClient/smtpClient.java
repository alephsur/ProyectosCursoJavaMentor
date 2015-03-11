package smtpClient;

import java.io.*;
import java.net.*;

import javax.xml.bind.DatatypeConverter;


public class smtpClient {
	
	private DatatypeConverter dc;
	
    public static void main(String[] args) {
        	
    	smtpClient cliente =new smtpClient();
    	cliente.sendMail();
    	
    }
    
    public void sendMail()
    {
    try
    {
    Socket s = new Socket("smtp.terra.com", 587); 
    out = new PrintWriter(s.getOutputStream());
    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    String hostName = InetAddress.getLocalHost().getHostName();
    System.out.println("hostName = " + hostName);
    send(null);
    send("HELO localhost");
    send("AUTH LOGIN\n");
    
    sendAuth(dc.parseBase64Binary("athaloc_65"));
    sendAuth(dc.parseBase64Binary("456mdfg"));
    
    
    send("MAIL FROM: " + "<athaloc_65@terra.com>");
    send("RCPT TO: " + "<athaloc_65@terra.com>");
    send("DATA");
    send("Envio de prueba");
    send(".");
    send("QUIT");
    s.close();
    out.close();
    in.close();
    }
    catch(IOException e)
    { e.printStackTrace();
    }
    }

    public void send(String s) throws IOException
    { 
    	if (s != null){ 
    		out.println(s);
    		out.flush();
    	}
    	String line;
    	if ((line = in.readLine()) != null) //output the response
    		System.out.println(line);
    }
    
    public void sendAuth(byte[] s) throws IOException{
    	if(s != null){
    		out.println(s.toString()  + "\n");
    		out.flush();
    	}
    	String line;
    	if((line = in.readLine())!=null){
    		System.out.println(line);
    	}
    }

    private PrintWriter out;
    private BufferedReader in;
}