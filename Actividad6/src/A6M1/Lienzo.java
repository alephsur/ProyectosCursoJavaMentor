package A6M1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;

public class Lienzo extends Canvas {
    
	private int imagen;
	
	public Lienzo(){
		
	}
	
	public void CambiarImagen(int i){
		this.imagen = i;
		this.repaint();
	}
	
	public void paint(Graphics g){
		Image img = Toolkit.getDefaultToolkit().getImage(this.imagen +".gif");
		g.drawImage(img,45,25,this);        
    }
}
