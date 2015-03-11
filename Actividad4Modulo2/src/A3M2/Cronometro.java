package A3M2;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.text.IconView;

public class Cronometro extends Thread implements ActionListener {
	
	private JFrame frame;
	private JButton btArrancar;
	private JButton btReinicar;
	
	private JLabel Img1;
	private JLabel Img2;
	private JLabel Img3;
	private JLabel Img4;
	private JLabel Img5;
	private JLabel Img6;
	private JLabel Img7;
	private JLabel Img8;
	
	private boolean arrancado = false;
	private boolean parado = false;
		
	private int segundos = 0;
	private int minutos = 0;
	private int horas = 0;
	
	private Thread hilo;
	
	public Cronometro(){
		this.DiseñarPantalla();
		hilo = new Thread(this);
	}
	
	public void DiseñarPantalla(){
		
		frame = new JFrame();
		frame.setBounds(0,0,300,200);
		frame.setLayout(new FlowLayout());
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(new GridLayout(2, 1));
		frame.add(panel);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new FlowLayout());
		panel.add(panelSuperior);
		JPanel panelInferior = new JPanel();
		panel.add(panelInferior);
		
		btArrancar= new JButton("Arrancar/Parar");
		btArrancar.setName("Arrancar");
		btArrancar.addActionListener(this);
		btReinicar = new JButton("Reinicar");
		btReinicar.setName("Reiniciar");
		btReinicar.addActionListener(this);
		panelInferior.add(btArrancar);
		panelInferior.add(btReinicar);
		
		JPanel panelInterior = new JPanel();
		panelInterior.setBorder(BorderFactory.createBevelBorder(1));
		panelSuperior.add(panelInterior);
		
		
		Img1= new JLabel();
		panelInterior.add(Img1);
		this.CambiarImagen(Img1, 0);
		
		Img2= new JLabel();
		panelInterior.add(Img2);
		this.CambiarImagen(Img2, 0);
		
		Img3= new JLabel();
		panelInterior.add(Img3);
		this.CambiarImagen(Img3, 10);
		
		Img4= new JLabel();
		panelInterior.add(Img4);
		this.CambiarImagen(Img4, 0);
		
		Img5= new JLabel();
		panelInterior.add(Img5);
		this.CambiarImagen(Img5, 0);
		
		Img6= new JLabel();
		panelInterior.add(Img6);
		this.CambiarImagen(Img6, 10);
		
		Img7= new JLabel();
		panelInterior.add(Img7);
		this.CambiarImagen(Img7, 0);
		
		Img8= new JLabel();
		panelInterior.add(Img8);
		this.CambiarImagen(Img8, 0);
		
		
		frame.setVisible(true);
	}

	@Override
	public void run(){
		synchronized (this.hilo) {
			while(true){			
				try{
					Thread.sleep(1000);
					this.calcularTiempo();
					this.PintarImagenes();
					if(this.parado == true){
						this.hilo.wait();
					}
				}
				catch(InterruptedException e){				
				}		
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JButton){
			if(((JButton)e.getSource()).getName().equals("Arrancar")){
				if(this.arrancado == false){
					this.hilo.start();
					this.arrancado = true;
					return;
				}
				if(this.arrancado == true && this.parado == false)
				{
					this.parado = true;
				}
				else{
					synchronized (this.hilo) {
						this.hilo.notify();
						this.parado = false;	
					}
				}
			}
			if(((JButton)e.getSource()).getName().equals("Reiniciar")){
				this.segundos =0;
				this.minutos =0;
				this.horas = 0;
				this.PintarImagenes();
			}
		}
	}
		
	private void CambiarImagen(JLabel img, int numeroImagen){
		if(numeroImagen == 10){
			Icon Imagen = new ImageIcon("./Imagenes/separador.gif");
			img.setIcon(Imagen);
		}
		else{
			Icon Imagen = new ImageIcon("./Imagenes/" + numeroImagen + ".gif");
			img.setIcon(Imagen);
		}
	}

	private void calcularTiempo(){
		if(this.segundos == 59){
			this.segundos = 0;
			this.minutos ++;
		}
		else{
			this.segundos ++;
		}
		if(this.minutos == 59){
			this.minutos = 0;
			this.horas ++;
		}		
	}
	
	private void PintarImagenes(){
		
		String cadenaSegundos = Integer.toString(segundos);
		String cadenaMinutos = Integer.toString(minutos);
		String cadenaHoras = Integer.toString(horas);
				
		if(cadenaSegundos.length() == 2){
			this.CambiarImagen(Img8, Integer.parseInt(cadenaSegundos.substring(1,2)));
			this.CambiarImagen(Img7, Integer.parseInt(cadenaSegundos.substring(0,1)));			
		}
		else{
			this.CambiarImagen(Img8, Integer.parseInt(cadenaSegundos));
		}
		
		if(cadenaMinutos.length() == 2){
			this.CambiarImagen(Img5, Integer.parseInt(cadenaMinutos.substring(1,2)));
			this.CambiarImagen(Img4, Integer.parseInt(cadenaMinutos.substring(0,1)));			
		}
		else{
			this.CambiarImagen(Img5, Integer.parseInt(cadenaMinutos));
		}
		
		if(cadenaHoras.length() == 2){
			this.CambiarImagen(Img2, Integer.parseInt(cadenaHoras.substring(1,2)));
			this.CambiarImagen(Img1, Integer.parseInt(cadenaHoras.substring(0,1)));			
		}
		else{
			this.CambiarImagen(Img2, Integer.parseInt(cadenaHoras));
		}
	}
}




















