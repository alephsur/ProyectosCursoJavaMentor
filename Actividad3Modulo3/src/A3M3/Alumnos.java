package A3M3;

import java.io.Serializable;


public class Alumnos implements Serializable{
	
	private String nombreAlumno;
	private String apellidosAlumno;
	private int edadAlumno;
	
	public Alumnos(){
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
		
}









