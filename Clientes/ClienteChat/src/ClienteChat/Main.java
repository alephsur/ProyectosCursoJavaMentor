package ClienteChat;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean swP = false;
		boolean swH = false;
		boolean swN = false;
		String ip="";
		int puerto =0;
		String nick="";
		
		if(args.length < 6){
			System.out.println("El numero de parametros es erroneo.\n");
			System.out.println("El formato correcto es\n -h: Dirección IP del Servidor\n -p: Puerto del Servidor.\n-n: Nick del usuario.");
			return;
		}
		for(int i = 0;i<args.length;i++){
			if(args[i].equals("-h")){
				swH = true;
				ip = args[i+1];
			}
			if(args[i].equals("-p")){
				swP = true;
				puerto = Integer.parseInt(args[i+1]);
			}
			if(args[i].equals("-n")){
				swN = true;
				nick = args[i+1];
			}
		}
		if(swH == false || swP == false || swN == false){
			System.out.println("Los parametros no son correctos.\n");
			System.out.println("El formato correcto es\n -h: Dirección IP del Servidor\n -p: Puerto del Servidor.\n-n: Nick del usuario.");
			return;
		}
		
		
		Cliente elCliente = new Cliente(ip,nick,puerto);
		elCliente.DiseñarPantalla();
	}

}
