package A5M2;

public class clase2 extends Thread {
	
	private Contenedor contenedor;
	
	public clase2(Contenedor contenedor){
		this.contenedor = contenedor;
	}
		
	public void run(){
		int i=1;
		while(i<=10){
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(this.contenedor.BorrarNumero("Clase2")==true){
				i++;
			}
			if(this.contenedor.SetNumero(i,"Clase2")== true){
				i++;
			}

		}
	}
}
