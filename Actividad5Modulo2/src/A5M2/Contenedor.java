package A5M2;

public class Contenedor {
	
	private int numero=0;
	
	
	public Contenedor(){
		
	}
	
	public synchronized boolean SetNumero(int numero, String Clase){
		if(this.numero == 0){
			System.out.println("Escrito valor " + numero + " Por la clase " + Clase);
			this.numero = numero;
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	public synchronized boolean BorrarNumero(String Clase){
		if(this.numero != 0){
			System.out.println("Borrado valor por la clase " + Clase);
			this.numero = 0;
			notify();
			return true;
		}
		return false;
	}	
}
