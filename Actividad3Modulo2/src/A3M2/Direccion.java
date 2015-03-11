package A3M2;

import java.io.Serializable;

public class Direccion implements Serializable {
	
	private String calle;
	private int numero;
	private String codigoPostal; 
	
	public Direccion(){
	}
	
	public String GetCalle(){
		return this.calle;
	}
	
	public void SetCalle(String calle){
		this.calle = calle;
	}
	
	public int GetNumero(){
		return this.numero;
	}
	
	public void SetNumero(int numero){
		this.numero = numero;
	}
	
	public String GetCodigoPostal(){
		return this.codigoPostal;
	}
	
	public void SetCodigoPostal(String codigoPostal){
		this.codigoPostal = codigoPostal;
	}

}
