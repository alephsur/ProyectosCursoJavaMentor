package A6M1;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Ahorcado extends JApplet implements ActionListener{
	
	private JLabel lbPalabra;
	private JLabel lbIntentos;
	private JLabel lbMensajes;
	private JComboBox cbLetras;
	private JButton btAceptar;
	private Lienzo lienzo;
	private int numeroFallos=0;
	private String palabraSecreta;
	private Boolean victoriaConseguida=false;
	
	private String[] alfabeto = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P",
			"Q","R","S","T","U","V","W","X","Y","Z"};
	
	private String[] palabras = {"ORDENADOR","LIBRETA","BAÑADOR","AZULEJO","CEREALES","LLUVIA","AYUNTAMIENTO"};
	
	
	public Ahorcado(){
		
		this.getContentPane().setLayout(new GridLayout(1, 2));
		Image img = Toolkit.getDefaultToolkit().getImage(".\\Imagenes\\icono.gif");
						
		JMenuBar menuBar = new JMenuBar();
		JMenu menuElem1 = new JMenu("Menú");
		
		JMenuItem jmnReiniciar = new JMenuItem("Reiniciar");
		jmnReiniciar.addActionListener(this);
		JMenuItem jmnSalir= new JMenuItem("Salir");
		jmnSalir.addActionListener(this);
		
		menuElem1.add(jmnReiniciar);
		menuElem1.add(jmnSalir);
		
		menuBar.add(menuElem1);
		this.setJMenuBar(menuBar);
		
		
		JPanel panelIzq = new JPanel();
		panelIzq.setLayout(new GridLayout(4,1));
		add(panelIzq);
		JPanel panelDrch=new JPanel();
		//frame.getContentPane().add(panelDrch);
		
		this.DiseñarPanelIzquierdo(panelIzq);
			
		
		lienzo = new Lienzo();
		lienzo.setBackground(Color.white);
		add(lienzo);
				
		this.SeleccionarPalabra();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() instanceof JButton){
			if(((JButton)arg0.getSource()).getText().equals("Aceptar")){
				if(numeroFallos <= 6){
					String letraSeleccionda = (String)this.cbLetras.getSelectedItem();
					if(this.palabraSecreta.contains(letraSeleccionda)){
						this.EscribirLetraEncontrada(letraSeleccionda);
					}
					else{
						numeroFallos ++;
						this.EscribirLetraFallada(letraSeleccionda);
					}
				}
			}
		}
		if(arg0.getSource() instanceof JMenuItem){
			if(((JMenuItem)arg0.getSource()).getText().equals("Reiniciar")){
				this.Reiniciar();
			}
			if(((JMenuItem)arg0.getSource()).getText().equals("Salir")){
				System.exit(0);
			}
		
		}
	}
	
	private void DiseñarPanelIzquierdo(JPanel panel){
		JPanel pnPalabra = new JPanel();
		pnPalabra.setBorder(new TitledBorder("Palabra"));
		pnPalabra.setLayout(new BorderLayout());
		lbPalabra = new JLabel();		
		pnPalabra.add(lbPalabra,BorderLayout.CENTER);
		
		JPanel pnIntentos = new JPanel();
		pnIntentos.setBorder(new TitledBorder("Intentos"));
		pnIntentos.setLayout(new FlowLayout());
		lbIntentos = new JLabel();
		pnIntentos.add(lbIntentos);
		
		
		JPanel pnLetras = new JPanel();
		pnLetras.setBorder(new TitledBorder("Letras"));
		pnLetras.setLayout(new FlowLayout());
		
		this.cbLetras = new JComboBox(this.alfabeto);
		this.btAceptar = new JButton("Aceptar");
		this.btAceptar.addActionListener(this);
		
		pnLetras.add(this.cbLetras);
		pnLetras.add(this.btAceptar);
		
		JPanel pnMensajes = new JPanel();
		pnMensajes.setBorder(new TitledBorder("Mensajes"));
		pnMensajes.setLayout(new FlowLayout());
		lbMensajes = new JLabel("");
		pnMensajes.add(lbMensajes);
		
		
		panel.add(pnPalabra);
		panel.add(pnIntentos);
		panel.add(pnLetras);
		panel.add(pnMensajes);
	}

	private void SeleccionarPalabra(){
        double palabraSeleccionada = Math.floor(Math.random()*6 + 1);   
        
        palabraSecreta = palabras[(int)palabraSeleccionada];
        for(int i = 0;i<palabraSecreta.length(); i++)
        {        	
        	this.lbPalabra.setText(this.lbPalabra.getText() + " _ ");
        }
        
	}

	private void Reiniciar(){
		this.numeroFallos=0;
		victoriaConseguida=false;
		this.lbMensajes.setText("");
		this.lbIntentos.setText("");
		this.lbPalabra.setText("");
		this.btAceptar.setEnabled(true);
		this.lienzo.CambiarImagen(0);
		this.SeleccionarPalabra();
		
	}
	
	private void EscribirLetraEncontrada(String letra){
		if(this.victoriaConseguida==false){
			int indice =0;		
			String palabra =this.lbPalabra.getText();
			String Resultado = "";
			for(int i = 0;i<palabra.length();i++){			
				if(palabra.charAt(i) == '_'){
					if(this.palabraSecreta.charAt(indice) == letra.charAt(0)){
						Resultado = Resultado + letra;
					}
					else{
						Resultado = Resultado + palabra.charAt(i);
					}
					indice++;
				}
				else{
					Resultado = Resultado + palabra.charAt(i);
					if(palabra.charAt(i) != '_' && palabra.charAt(i) != ' '){
						indice++;
					}
				}
			}
			this.lbPalabra.setText(Resultado);
			if(Resultado.contains("_")== false){
				this.lbMensajes.setText("Ha conseguido la victoria");
				this.victoriaConseguida=true;
				this.btAceptar.setEnabled(false);
			}
		}
	}
	
	private void EscribirLetraFallada(String letra){
		this.lbIntentos.setText(this.lbIntentos.getText() + " " + letra);
		this.lienzo.CambiarImagen(numeroFallos);
		if(this.numeroFallos == 7){
			this.lbMensajes.setText("Ha perdido la partida");
			this.btAceptar.setEnabled(false);
		}
	}	
}



	
