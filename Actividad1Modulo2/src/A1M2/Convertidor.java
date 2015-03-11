package A1M2;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Convertidor extends JApplet implements ActionListener{
	
	private JTextField euros = new JTextField();
	private JTextField pesetas = new JTextField();
	private java.applet.AudioClip clipAudio;
	
	public void init(){

		this.getContentPane().setLayout(new GridLayout(3,1));
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuElem1 = new JMenu("Menú");
		
		JMenuItem jmnAyuda = new JMenuItem("Ayuda");
		jmnAyuda.setName("Ayuda");
		jmnAyuda.addActionListener(this);
		
		menuElem1.add(jmnAyuda);
		
		menuBar.add(menuElem1);
		this.setJMenuBar(menuBar);

		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,2));
		
		
		JLabel lbEuros= new JLabel(getParameter("TEXTO1"));
		JLabel lbPesetas = new JLabel(getParameter("TEXTO2"));
		this.pesetas.setEnabled(false);
		
		
		panel1.add(lbEuros);
		panel1.add(euros);
		panel1.add(lbPesetas);
		panel1.add(pesetas);
				
		add(panel1);
		
		JPanel panel2  = new JPanel();
		panel2.setLayout(new FlowLayout());
		
		JButton BtConvertir = new JButton(getParameter("TEXTO3"));
		BtConvertir.setName("Convertir");
		BtConvertir.addActionListener(this);
		JButton BtBorrar = new JButton(getParameter("TEXTO4"));
		BtBorrar.setName("Borrar");
		BtBorrar.addActionListener(this);
		
		panel2.add(BtConvertir);
		panel2.add(BtBorrar);
		
		add(panel2);
	}
	
	public void start(){
		try{
			//this.clipAudio = this.getAudioClip(new URL("file:Deep_Purple_-_Smoke_On_The_Water.mid"));
			this.clipAudio = this.getAudioClip(this.getDocumentBase(), "Deep_Purple_-_Smoke_On_The_Water.mid");
			this.clipAudio.play();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(this, "Error al cargar el arhivo de audio");
		}
	}
	
	public void stop(){
		this.clipAudio.stop();
	}
	
	public void destroy(){
		this.clipAudio.stop();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() instanceof JButton) {
			if(((JButton)arg0.getSource()).getName().equals("Convertir")){
				try{
					double cantidad =  Double.parseDouble(this.euros.getText());
					
					double resultado =Float.parseFloat(this.euros.getText().toString())*166.386;
					this.pesetas.setText(String.valueOf(resultado));
				}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(this, "Se ha producido un error, el campo euros no es valido."); 
				}
			}
			if(((JButton)arg0.getSource()).getName().equals("Borrar")){
				this.euros.setText("");
				this.pesetas.setText("");
			}
		}
		if(arg0.getSource() instanceof JMenuItem) {
			if(((JMenuItem)arg0.getSource()).getName().equals("Ayuda")){
				try {
					URL url = new URL(getDocumentBase(), "codigo.html");
					getAppletContext().showDocument(url,"_blank");
					} catch (MalformedURLException e) {
				} 
			}
		}
	}	
}












