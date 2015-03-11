package A5M2;

public class clase1 extends Thread {
	
	private Contenedor contenedor;
	
	public clase1(Contenedor contenedor){
		this.contenedor = contenedor;
	}
	
	public void run(){
		int i=1;
		while(i<=10){
			try{
				sleep(1000);
			}
			catch(InterruptedException ex){				
			}
			if(this.contenedor.BorrarNumero("Clase1")==true){
				i++;
			}
			if(this.contenedor.SetNumero(i,"Clase1") == true){
				i++;
			}

		}
	}
}
