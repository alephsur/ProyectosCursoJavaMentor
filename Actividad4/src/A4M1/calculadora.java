package A4M1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class calculadora extends JApplet implements ActionListener,KeyListener {
	
	private enum Operacion{
		nada,
		suma,
		resta
	}

	private JTextField texto;
	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
	private JButton boton5;
	private JButton boton6;
	private JButton boton7;
	private JButton boton8;
	private JButton boton9;
	private JButton boton10;
	private JButton boton11;
	private JButton boton12;
	private int valor1;
	private Operacion operacion = Operacion.nada;
	
	
    public calculadora(){
    	this.getContentPane().setLayout(
    			new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
    	    	
    	JPanel panel1 = new JPanel();
    	panel1.setLayout(new GridLayout(1,1));
    	add(panel1);

    	texto =  new JTextField();
    	texto.setHorizontalAlignment(SwingConstants.RIGHT);    	
    	texto.addKeyListener(this);
    	panel1.add(texto);
    	
    	JPanel panel2 = new JPanel();
    	panel2.setLayout(new GridLayout(3,4));
    	add(panel2);
    	
    	boton1 = new JButton("1");
    	boton1.addActionListener(this);
    	panel2.add(boton1);
    	
    	boton2 = new JButton("2");
    	boton2.addActionListener(this);
    	panel2.add(boton2);
    	
    	boton3 = new JButton("3");
    	boton3.addActionListener(this);
    	panel2.add(boton3);
    	
    	boton4 = new JButton("+");
    	boton4.addActionListener(this);
    	panel2.add(boton4);
    	
    	boton5 = new JButton("4");
    	boton5.addActionListener(this);
    	panel2.add(boton5);
    	
		boton6 = new JButton("5");
    	boton6.addActionListener(this);
    	panel2.add(boton6);
    	
    	boton7 =new JButton("6");
    	boton7.addActionListener(this);
    	panel2.add(boton7);
    	
    	boton8 = new JButton("-");
    	boton8.addActionListener(this);
    	panel2.add(boton8);
    	
    	boton9 = new JButton("7");
    	boton9.addActionListener(this);
    	panel2.add(boton9);
    	
    	boton10 = new JButton("8");
    	boton10.addActionListener(this);
    	panel2.add(boton10);
    	
    	boton11 = new JButton("9");
    	boton9.addActionListener(this);
    	panel2.add(boton11);
    	
    	boton12 = new JButton("=");
    	boton12.addActionListener(this);
    	panel2.add(boton12);
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!((JButton)e.getSource()).getText().equals("+") && !((JButton)e.getSource()).getText().equals("-") &&
				!((JButton)e.getSource()).getText().equals("="))
		{
			this.texto.setText(this.texto.getText() + ((JButton)e.getSource()).getText());
		}
		else{
			if(((JButton)e.getSource()).getText().equals("+")){
				this.OperacionSuma();
			}
			if(((JButton)e.getSource()).getText().equals("-")){
				this.OperacionResta();
			}
			if(((JButton)e.getSource()).getText().equals("=")){
				this.OperacionResultado();
			}
		}
		this.texto.requestFocus();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar()=='+'){
			this.OperacionSuma();
		}
		if(e.getKeyChar()=='-'){
			this.OperacionResta();
		}
		if(e.getKeyChar()=='=' || e.getKeyChar()=='\n'){
			this.OperacionResultado();
		}
		
		int k = (int)e.getKeyChar();
		if (k <= 47 || k >=  58) {

			e.consume();
		}

	}

	
	private void OperacionSuma(){
		if(this.texto.getText().equals("")){
			return;
		}
		if(this.operacion == Operacion.nada){
			this.valor1 = Integer.parseInt(this.texto.getText());
			this.texto.setText("");
			this.operacion = Operacion.suma;
		}
	}
	
	private void OperacionResta(){
		if(this.texto.getText().equals("")){
			return;
		}
		if(this.operacion == Operacion.nada){
			this.valor1 = Integer.parseInt(this.texto.getText());
			this.texto.setText("");
			this.operacion = Operacion.resta;
		}
	}
	
	private void OperacionResultado(){
		if(this.operacion == Operacion.suma){
			Integer resultado = Integer.parseInt(this.texto.getText()) + valor1;
			this.texto.setText(resultado.toString());
		}
		if(this.operacion == Operacion.resta){
			Integer resultado = valor1 - Integer.parseInt(this.texto.getText());
			this.texto.setText(resultado.toString());					
		}
		this.operacion = Operacion.nada;
	}
}













