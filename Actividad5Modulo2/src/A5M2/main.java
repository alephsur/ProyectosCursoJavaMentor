package A5M2;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Contenedor elContenedor = new Contenedor();
		clase1 hilo1 = new clase1(elContenedor);
		clase2 hilo2 = new clase2(elContenedor);
		hilo1.start();
		hilo2.start();
	}
}
