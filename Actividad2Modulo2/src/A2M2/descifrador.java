package A2M2;

import java.io.*;

import javax.annotation.processing.FilerException;

public class descifrador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int aux;
		int contadorLongChar=0;
		int contadorLongGrup=1;
		int grupoPalabras = 0;
		try{
			FileReader entrada = new FileReader("./bin/original.dic");
			FileOutputStream salida = new FileOutputStream("./bin/nuevo.dic");
			if(grupoPalabras !=0){
				contadorLongGrup = contadorLongGrup+1;
			}
			System.out.println("Empezando a descifrar el fichero.");
			while((aux = entrada.read()) != -1){
				//System.out.println((char)aux); 	
				//System.out.println((char)(aux + (contadorLongChar + 1) + contadorLongGrup));
				if(contadorLongChar == 20){
					contadorLongChar = 0;
					contadorLongGrup ++;
				}
				if(contadorLongGrup == 8)
					contadorLongGrup = 0;
				
				if((char)aux != ' '){
					salida.write(aux -( (contadorLongChar + 1) + contadorLongGrup) );
					//System.out.println((char)(aux -( (contadorLongChar + 1) + contadorLongGrup)));
				}
				else{
					salida.write(aux);
				}
				contadorLongChar ++;
			}
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		System.out.println("Descifrado finalizado. El fichero esta en " + System.getProperty("user.dir") + "\bin\nuevo.dic" );
	}
}