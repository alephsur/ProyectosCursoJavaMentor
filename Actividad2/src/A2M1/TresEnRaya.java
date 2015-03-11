package A2M1;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class TresEnRaya implements WindowListener, ActionListener, ItemListener{

    private enum TipoJuego{
        HumanoVsHumano,
        HumanoVsMaquina
    }
    
    private enum Jugador{
        jugador1,
        jugador2
    }
    
    private JFrame frame;
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JButton boton4;
    private JButton boton5;
    private JButton boton6;
    private JButton boton7;
    private JButton boton8;
    private JButton boton9;
    private JLabel LbResultado;
    private TipoJuego selTipoJuego=TipoJuego.HumanoVsMaquina;
    private Jugador jugadorHumano = Jugador.jugador1;
    private boolean victoriaConsequida=false;
    private boolean partidaEnTablas = false;
    
    public TresEnRaya(){
    	
    }
    
    public void DiseñarPantalla(){
        frame = new JFrame();
        frame.getContentPane().setLayout(new GridLayout(1,1));
        frame.setBounds(0,0,400,200);
        frame.setTitle("Ejemplo AWT");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));
        panel.setBounds(10,30,250,220);        
        panel.setBackground(Color.LIGHT_GRAY);
        boton1 = new JButton("");
        boton1.setName("boton1");
        boton1.addActionListener(this);
        panel.add(boton1);
        boton2 = new JButton("");
        boton2.setName("boton2");
        boton2.addActionListener(this);
        panel.add(boton2);
        boton3 = new JButton("");
        boton3.setName("boton3");
        boton3.addActionListener(this);
        panel.add(boton3);
        boton4 = new JButton("");
        boton4.setName("boton4");
        boton4.addActionListener(this);
        panel.add(boton4);
        boton5 = new JButton("");
        boton5.setName("boton5");
        boton5.addActionListener(this);
        panel.add(boton5);
        boton6 = new JButton("");
        boton6.setName("boton6");
        boton6.addActionListener(this);
        panel.add(boton6);
        boton7 = new JButton("");
        boton7.setName("boton7");
        boton7.addActionListener(this);
        panel.add(boton7);
        boton8 = new JButton("");
        boton8.setName("boton8");
        boton8.addActionListener(this);
        panel.add(boton8);
        boton9 = new JButton("");
        boton9.setName("boton9");
        boton9.addActionListener(this);
        panel.add(boton9);
        
        frame.add(panel);
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4,1));
        panel1.setBounds(100,100,100,100);
        panel1.setBackground(Color.LIGHT_GRAY);
        
        ButtonGroup ChkGrupoOpciones = new ButtonGroup();
        JRadioButton ChkHvsC = new JRadioButton("Humano contra computadora",true);
        ChkGrupoOpciones.add(ChkHvsC);
        ChkHvsC.addItemListener(this);
        JRadioButton ChkHvsH = new JRadioButton("Humano contra Humano",false);
        ChkGrupoOpciones.add(ChkHvsH);
        ChkHvsH.addItemListener(this);
        
        LbResultado = new JLabel("");
        
        panel1.add(ChkHvsC);
        panel1.add(ChkHvsH);
        panel1.add(LbResultado);
        
        
        Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout());
        panel2.setBounds(100, 100, 100, 100);
        panel2.setBackground(Color.LIGHT_GRAY);
        
        panel1.add(panel2);
        
        JButton BtReiniciar = new JButton("Reiniciar");
        BtReiniciar.addActionListener(this);
        panel2.add(BtReiniciar,BorderLayout.CENTER);
        
        frame.add(panel1);

        frame.setVisible(true); 
        frame.addWindowListener(this);
        
    }
	
    
    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(((JButton)e.getSource()).getText()=="Reiniciar")
        {	
        	this.ReinicarJuego();
            return;
        }        
        else{
            if(((JButton)e.getSource()).getText().equals(""))
            {
                if(this.jugadorHumano == Jugador.jugador1){
                    ((JButton)e.getSource()).setText("X");
                    if(this.ComprobarVictoria("X")){
                        return;
                    }
                    this.ComprobarTablas();
                    if(this.selTipoJuego== TipoJuego.HumanoVsHumano)
                        this.jugadorHumano = Jugador.jugador2;
                }
                else{
                    ((JButton)e.getSource()).setText("0");
                    if(this.ComprobarVictoria("0")){
                        return;
                    }
                    this.ComprobarTablas();
                    this.jugadorHumano = Jugador.jugador1;
                }
            }
        }
        if(selTipoJuego == TipoJuego.HumanoVsMaquina)
        {
            this.MovimientoComputador();
            if(this.ComprobarVictoria("0")){
                return;
            }
            this.ComprobarTablas();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(((Checkbox)e.getSource()).getLabel().equals("Humano contra computadora"))
        {
            this.selTipoJuego = TipoJuego.HumanoVsMaquina;
        }
        if(((Checkbox)e.getSource()).getLabel().equals("Humano contra Humano"))
        {
            this.selTipoJuego = TipoJuego.HumanoVsHumano;
        }
        this.ReinicarJuego();
    }  
    
    private void ReinicarJuego(){
		this.boton1.setText("");
		this.boton2.setText("");
		this.boton3.setText("");
		this.boton4.setText("");
		this.boton5.setText("");
		this.boton6.setText("");
		this.boton7.setText("");
		this.boton8.setText("");
		this.boton9.setText("");
        this.jugadorHumano = Jugador.jugador1;
        this.victoriaConsequida = false;
        this.LbResultado.setText("");
    }
    
    //Este metodo 
    private void MovimientoComputador()
    {
        JButton boton =  this.ComprobarMovimiento();
        if(boton != null){
            boton.setText("0");
            return;
        }
        
        double casilla1 = Math.floor(Math.random()*9 + 1);   
        int casilla = (int)casilla1;
        boton = this.BotonSeleccionado(casilla);
        
        if(boton.getText().equals(""))
        {
            boton.setText("0");
        }
        else
        {
            int sw =0;
            for(int i=casilla;i<9;i++){  
                if(sw==0){
                    boton = this.BotonSeleccionado(i);   
                    if(boton.getText().equals("")){
                        boton.setText("0");
                        sw=1;
                    }
                }
            }
            if(sw==0){
                for(int i=0;i<casilla;i++){
                    if(sw==0){
                        boton = this.BotonSeleccionado(i);   
                        if(boton.getText().equals("")){
                            boton.setText("0");
                            sw=1;
                        }
                    }
                }
            }
        }
    }
    
    private JButton BotonSeleccionado(int casilla)
    {
        switch(casilla){
            case 1: return this.boton1;
            case 2: return this.boton2;
            case 3: return this.boton3;
            case 4: return this.boton4;
            case 5: return this.boton5;
            case 6: return this.boton6;
            case 7: return this.boton7;
            case 8: return this.boton8;
            case 9: return this.boton9;
            default: return this.boton1;
        }
    }
    
    //retornara el boton a pulsar para impedir la vistoria del jugador
    private JButton ComprobarMovimiento(){
        if(this.boton1.getText().equals("X") && this.boton2.getText().equals("X")){
            if(this.boton3.getText().equals("")){
                return this.boton3;
            }
        }
        if(this.boton2.getText().equals("X") && this.boton3.getText().equals("X")){
            if(this.boton3.getText().equals("")){
                return this.boton1;
            }
        }
        if(this.boton1.getText().equals("X") && this.boton3.getText().equals("X")){
            if(this.boton2.getText().equals("")){
                return this.boton2;
            }
        }
        if(this.boton1.getText().equals("X") && this.boton4.getText().equals("X")){
            if(this.boton7.getText().equals("")){
                return this.boton7;
            }
        }
        if(this.boton4.getText().equals("X") && this.boton7.getText().equals("X")){
            if(this.boton1.getText().equals("")){
                return this.boton1;
            }
        }
        if(this.boton1.getText().equals("X") && this.boton7.getText().equals("X")){
            if(this.boton4.getText().equals("")){
                return this.boton4;
            }
        }
        if(this.boton2.getText().equals("X") && this.boton5.getText().equals("X")){
            if(this.boton8.getText().equals("")){
                return this.boton8;
            }
        }
        if(this.boton5.getText().equals("X") && this.boton8.getText().equals("X")){
            if(this.boton2.getText().equals("")){
                return this.boton2;
            }
        }
        if(this.boton2.getText().equals("X") && this.boton8.getText().equals("X")){
            if(this.boton5.getText().equals("")){
                return this.boton5;
            }
        }
        if(this.boton3.getText().equals("X") && this.boton6.getText().equals("X")){
            if(this.boton9.getText().equals("")){
                return this.boton9;
            }
        }
        if(this.boton6.getText().equals("X") && this.boton9.getText().equals("X")){
            if(this.boton3.getText().equals("")){
                return this.boton3;
            }
        }
        if(this.boton3.getText().equals("X") && this.boton9.getText().equals("X")){
            if(this.boton6.getText().equals("")){
                return this.boton6;
            }
        }
        if(this.boton4.getText().equals("X") && this.boton5.getText().equals("X")){
            if(this.boton6.getText().equals("")){
                return this.boton6;
            }
        }
        if(this.boton5.getText().equals("X") && this.boton6.getText().equals("X")){
            if(this.boton4.getText().equals("")){
                return this.boton4;
            }
        }
        if(this.boton4.getText().equals("X") && this.boton6.getText().equals("X")){
            if(this.boton5.getText().equals("")){
                return this.boton5;
            }
        }
        if(this.boton7.getText().equals("X") && this.boton8.getText().equals("X")){
            if(this.boton9.getText().equals("")){
                return this.boton9;
            }
        }
        if(this.boton8.getText().equals("X") && this.boton9.getText().equals("X")){
            if(this.boton7.getText().equals("")){
                return this.boton7;
            }
        }
        if(this.boton7.getText().equals("X") && this.boton9.getText().equals("X")){
            if(this.boton8.getText().equals("")){
                return this.boton8;
            }
        }
        if(this.boton1.getText().equals("X") && this.boton5.getText().equals("X")){
            if(this.boton9.getText().equals("")){
                return this.boton9;
            }
        }
        if(this.boton5.getText().equals("X") && this.boton9.getText().equals("X")){
            if(this.boton1.getText().equals("")){
                return this.boton1;
            }
        }
        if(this.boton1.getText().equals("X") && this.boton9.getText().equals("X")){
            if(this.boton5.getText().equals("")){
                return this.boton5;
            }
        }
        if(this.boton3.getText().equals("X") && this.boton5.getText().equals("X")){
            if(this.boton7.getText().equals("")){
                return this.boton7;
            }
        }
        if(this.boton5.getText().equals("X") && this.boton7.getText().equals("X")){
            if(this.boton3.getText().equals("")){
                return this.boton3;
            }
        }
        if(this.boton3.getText().equals("X") && this.boton7.getText().equals("X")){
            if(this.boton5.getText().equals("")){
                return this.boton5;
            }
        }
        return null;
    }
    
    private boolean ComprobarVictoria(String simbolo){
        
        if(this.boton1.getText().equals(simbolo) && this.boton2.getText().equals(simbolo) &&
                this.boton3.getText().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton4.getText().equals(simbolo) && this.boton5.getText().equals(simbolo) &&
                this.boton6.getText().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton7.getText().equals(simbolo) && this.boton8.getText().equals(simbolo) &&
                this.boton9.getText().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton1.getText().equals(simbolo) && this.boton4.getText().equals(simbolo) &&
                this.boton7.getText().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton2.getText().equals(simbolo) && this.boton5.getText().equals(simbolo) &&
                this.boton8.getText().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton3.getText().equals(simbolo) && this.boton6.getText().equals(simbolo) &&
                this.boton9.getText().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton1.getText().equals(simbolo) && this.boton5.getText().equals(simbolo) &&
                this.boton9.getText().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton3.getText().equals(simbolo) && this.boton5.getText().equals(simbolo) &&
                this.boton7.getText().equals(simbolo)){
            victoriaConsequida=true;
        }
        
        if(this.victoriaConsequida==true){
            if(simbolo.equals("X")){
                LbResultado.setText("Victoria del jugador 1");
            }
            else{
                if(this.selTipoJuego == TipoJuego.HumanoVsHumano){
                    LbResultado.setText("Victoria del jugador 2");
                }
                else
                {
                    LbResultado.setText("Victoria de la computadora");
                }
            }
        }
        return this.victoriaConsequida;
    }
    
    private void ComprobarTablas(){
        
        boolean casillasLibres = false;
        
        if(this.boton1.getText().equals("") || this.boton2.getText().equals("") || this.boton3.getText().equals("")
		||this.boton4.getText().equals("")||this.boton5.getText().equals("")||this.boton6.getText().equals("")
		||this.boton7.getText().equals("") || this.boton8.getText().equals("") || this.boton9.getText().equals("")){
        	casillasLibres=true;
        }
        
        if(this.victoriaConsequida == false)
        {
            if(casillasLibres == false){
                this.LbResultado.setText("Partida en tablas");
            }
        }
    }
}