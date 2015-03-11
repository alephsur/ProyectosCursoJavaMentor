package A3M2;

import java.io.Serializable;

public class Alumnos implements Serializable {
	
	private String clave;
	private String nombreAlumno;
	private String apellidosAlumno;
	private int edadAlumno;
	private Direccion direccion;
	
	public Alumnos(){
		this.direccion = new Direccion();
	}
	
	public String GetClave(){
		return this.clave;
	}
	
	public void SetClave(String clave){
		this.clave = clave;
	}
	
	public String GetNombreAlumno(){
		return this.nombreAlumno;
	}
	
	public void SetNombreAlumno(String nombre){
		this.nombreAlumno = nombre;
	}
	
	public String GetApellidosAlumno(){
		return this.apellidosAlumno;
	}
	
	public void SetApellidosAlumno(String nombre){
		this.apellidosAlumno = nombre;
	}
	
	public int GetEdadAlumno(){
		return this.edadAlumno;
	}
	
	public void SetEdadAlumno(int edad){
		this.edadAlumno = edad;
	}
	
	public Direccion GetDireccion(){
		return this.direccion;
	}
	
	public void SetDireccion(Direccion direccion){
		this.direccion = direccion;
	}
	


}










