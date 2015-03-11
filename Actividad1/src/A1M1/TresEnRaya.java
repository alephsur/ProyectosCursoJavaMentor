package A1M1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

/**
 *
 * @author portatil
 */
public class TresEnRaya implements WindowListener, ActionListener, ItemListener{
    
    private enum TipoJuego{
        HumanoVsHumano,
        HumanoVsMaquina
    }
    
    private enum Jugador{
        jugador1,
        jugador2
    }
    
    private Frame frame;
    private Button boton1;
    private Button boton2;
    private Button boton3;
    private Button boton4;
    private Button boton5;
    private Button boton6;
    private Button boton7;
    private Button boton8;
    private Button boton9;
    private Label LbResultado;
    private TipoJuego selTipoJuego=TipoJuego.HumanoVsMaquina;
    private Jugador jugadorHumano = Jugador.jugador1;
    private boolean victoriaConsequida=false;
    private boolean partidaEnTablas = false;
    
    public TresEnRaya(){}
    
    public void DiseñarPantalla(){
        frame = new Frame();
        frame.setLayout(new GridLayout(1,1));
        frame.setBounds(0,0,400,200);
        frame.setTitle("Ejemplo AWT");
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(3,3));
        panel.setBounds(10,30,250,220);
        panel.setBackground(Color.LIGHT_GRAY);
        boton1 = new Button("");
        boton1.setName("boton1");
        boton1.addActionListener(this);
        panel.add(boton1);
        boton2 = new Button("");
        boton2.setName("boton2");
        boton2.addActionListener(this);
        panel.add(boton2);
        boton3 = new Button("");
        boton3.setName("boton3");
        boton3.addActionListener(this);
        panel.add(boton3);
        boton4 = new Button("");
        boton4.setName("boton4");
        boton4.addActionListener(this);
        panel.add(boton4);
        boton5 = new Button("");
        boton5.setName("boton5");
        boton5.addActionListener(this);
        panel.add(boton5);
        boton6 = new Button("");
        boton6.setName("boton6");
        boton6.addActionListener(this);
        panel.add(boton6);
        boton7 = new Button("");
        boton7.setName("boton7");
        boton7.addActionListener(this);
        panel.add(boton7);
        boton8 = new Button("");
        boton8.setName("boton8");
        boton8.addActionListener(this);
        panel.add(boton8);
        boton9 = new Button("");
        boton9.setName("boton9");
        boton9.addActionListener(this);
        panel.add(boton9);
        
        frame.add(panel);
        
        Panel panel1 = new Panel();
        panel1.setLayout(new GridLayout(4,1));
        panel1.setBounds(100,100,100,100);
        panel1.setBackground(Color.LIGHT_GRAY);
        
        CheckboxGroup ChkGrupoOpciones = new CheckboxGroup();
        Checkbox ChkHvsC = new Checkbox("Humano contra computadora",true,ChkGrupoOpciones);
        ChkHvsC.addItemListener(this);
        Checkbox ChkHvsH = new Checkbox("Humano contra Humano",false,ChkGrupoOpciones);       
        ChkHvsH.addItemListener(this);
        
        LbResultado = new Label("");
        
        panel1.add(ChkHvsC);
        panel1.add(ChkHvsH);
        panel1.add(LbResultado);
        
        
        Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout());
        panel2.setBounds(100, 100, 100, 100);
        panel2.setBackground(Color.LIGHT_GRAY);
        
        panel1.add(panel2);
        
        Button BtReiniciar = new Button("Reiniciar");
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
        if(((Button)e.getSource()).getLabel()=="Reiniciar")
        {
            this.ReinicarJuego();
            return;
        }        
        else{
            if(((Button)e.getSource()).getLabel().equals(""))
            {
                if(this.jugadorHumano == Jugador.jugador1){
                    ((Button)e.getSource()).setLabel("X");
                    if(this.ComprobarVictoria("X")){
                        return;
                    }
                    this.ComprobarTablas();
                    if(this.selTipoJuego== TipoJuego.HumanoVsHumano)
                        this.jugadorHumano = Jugador.jugador2;
                }
                else{
                    ((Button)e.getSource()).setLabel("0");
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
        for(int i=0;i<frame.getComponents().length;i++){
            if(frame.getComponents()[i] instanceof Panel){
                for(int j =0;j<((Panel)frame.getComponents()[i]).getComponents().length;j++)
                {
                    if(((Panel)frame.getComponents()[i]).getComponents()[j] instanceof Button)
                    {
                        ((Button)((Panel)frame.getComponents()[i]).getComponents()[j]).setLabel("");
                    }
                }
            }
        }
        this.jugadorHumano = Jugador.jugador1;
        this.victoriaConsequida = false;
        this.LbResultado.setText("");
    }
    
    //Este metodo 
    private void MovimientoComputador()
    {
        Button boton =  this.ComprobarMovimiento();
        if(boton != null){
            boton.setLabel("0");
            return;
        }
        
        double casilla1 = Math.floor(Math.random()*9 + 1);   
        int casilla = (int)casilla1;
        boton = this.BotonSeleccionado(casilla);
        
        if(boton.getLabel().equals(""))
        {
            boton.setLabel("0");
        }
        else
        {
            int sw =0;
            for(int i=casilla;i<9;i++){  
                if(sw==0){
                    boton = this.BotonSeleccionado(i);   
                    if(boton.getLabel().equals("")){
                        boton.setLabel("0");
                        sw=1;
                    }
                }
            }
            if(sw==0){
                for(int i=0;i<casilla;i++){
                    if(sw==0){
                        boton = this.BotonSeleccionado(i);   
                        if(boton.getLabel().equals("")){
                            boton.setLabel("0");
                            sw=1;
                        }
                    }
                }
            }
        }
    }
    
    private Button BotonSeleccionado(int casilla)
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
    private Button ComprobarMovimiento(){
        if(this.boton1.getLabel().equals("X") && this.boton2.getLabel().equals("X")){
            if(this.boton3.getLabel().equals("")){
                return this.boton3;
            }
        }
        if(this.boton2.getLabel().equals("X") && this.boton3.getLabel().equals("X")){
            if(this.boton3.getLabel().equals("")){
                return this.boton1;
            }
        }
        if(this.boton1.getLabel().equals("X") && this.boton3.getLabel().equals("X")){
            if(this.boton2.getLabel().equals("")){
                return this.boton2;
            }
        }
        if(this.boton1.getLabel().equals("X") && this.boton4.getLabel().equals("X")){
            if(this.boton7.getLabel().equals("")){
                return this.boton7;
            }
        }
        if(this.boton4.getLabel().equals("X") && this.boton7.getLabel().equals("X")){
            if(this.boton1.getLabel().equals("")){
                return this.boton1;
            }
        }
        if(this.boton1.getLabel().equals("X") && this.boton7.getLabel().equals("X")){
            if(this.boton4.getLabel().equals("")){
                return this.boton4;
            }
        }
        if(this.boton2.getLabel().equals("X") && this.boton5.getLabel().equals("X")){
            if(this.boton8.getLabel().equals("")){
                return this.boton8;
            }
        }
        if(this.boton5.getLabel().equals("X") && this.boton8.getLabel().equals("X")){
            if(this.boton2.getLabel().equals("")){
                return this.boton2;
            }
        }
        if(this.boton2.getLabel().equals("X") && this.boton8.getLabel().equals("X")){
            if(this.boton5.getLabel().equals("")){
                return this.boton5;
            }
        }
        if(this.boton3.getLabel().equals("X") && this.boton6.getLabel().equals("X")){
            if(this.boton9.getLabel().equals("")){
                return this.boton9;
            }
        }
        if(this.boton6.getLabel().equals("X") && this.boton9.getLabel().equals("X")){
            if(this.boton3.getLabel().equals("")){
                return this.boton3;
            }
        }
        if(this.boton3.getLabel().equals("X") && this.boton9.getLabel().equals("X")){
            if(this.boton6.getLabel().equals("")){
                return this.boton6;
            }
        }
        if(this.boton4.getLabel().equals("X") && this.boton5.getLabel().equals("X")){
            if(this.boton6.getLabel().equals("")){
                return this.boton6;
            }
        }
        if(this.boton5.getLabel().equals("X") && this.boton6.getLabel().equals("X")){
            if(this.boton4.getLabel().equals("")){
                return this.boton4;
            }
        }
        if(this.boton4.getLabel().equals("X") && this.boton6.getLabel().equals("X")){
            if(this.boton5.getLabel().equals("")){
                return this.boton5;
            }
        }
        if(this.boton7.getLabel().equals("X") && this.boton8.getLabel().equals("X")){
            if(this.boton9.getLabel().equals("")){
                return this.boton9;
            }
        }
        if(this.boton8.getLabel().equals("X") && this.boton9.getLabel().equals("X")){
            if(this.boton7.getLabel().equals("")){
                return this.boton7;
            }
        }
        if(this.boton7.getLabel().equals("X") && this.boton9.getLabel().equals("X")){
            if(this.boton8.getLabel().equals("")){
                return this.boton8;
            }
        }
        if(this.boton1.getLabel().equals("X") && this.boton5.getLabel().equals("X")){
            if(this.boton9.getLabel().equals("")){
                return this.boton9;
            }
        }
        if(this.boton5.getLabel().equals("X") && this.boton9.getLabel().equals("X")){
            if(this.boton1.getLabel().equals("")){
                return this.boton1;
            }
        }
        if(this.boton1.getLabel().equals("X") && this.boton9.getLabel().equals("X")){
            if(this.boton5.getLabel().equals("")){
                return this.boton5;
            }
        }
        if(this.boton3.getLabel().equals("X") && this.boton5.getLabel().equals("X")){
            if(this.boton7.getLabel().equals("")){
                return this.boton7;
            }
        }
        if(this.boton5.getLabel().equals("X") && this.boton7.getLabel().equals("X")){
            if(this.boton3.getLabel().equals("")){
                return this.boton3;
            }
        }
        if(this.boton3.getLabel().equals("X") && this.boton7.getLabel().equals("X")){
            if(this.boton5.getLabel().equals("")){
                return this.boton5;
            }
        }
        return null;
    }
    
    private boolean ComprobarVictoria(String simbolo){
        
        if(this.boton1.getLabel().equals(simbolo) && this.boton2.getLabel().equals(simbolo) &&
                this.boton3.getLabel().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton4.getLabel().equals(simbolo) && this.boton5.getLabel().equals(simbolo) &&
                this.boton6.getLabel().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton7.getLabel().equals(simbolo) && this.boton8.getLabel().equals(simbolo) &&
                this.boton9.getLabel().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton1.getLabel().equals(simbolo) && this.boton4.getLabel().equals(simbolo) &&
                this.boton7.getLabel().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton2.getLabel().equals(simbolo) && this.boton5.getLabel().equals(simbolo) &&
                this.boton8.getLabel().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton3.getLabel().equals(simbolo) && this.boton6.getLabel().equals(simbolo) &&
                this.boton9.getLabel().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton1.getLabel().equals(simbolo) && this.boton5.getLabel().equals(simbolo) &&
                this.boton9.getLabel().equals(simbolo)){
            victoriaConsequida=true;
        }
        if(this.boton3.getLabel().equals(simbolo) && this.boton5.getLabel().equals(simbolo) &&
                this.boton7.getLabel().equals(simbolo)){
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
        
        if(this.victoriaConsequida == false)
        {
            for(int i=0;i<frame.getComponents().length;i++){
                if(frame.getComponents()[i] instanceof Panel){
                    for(int j =0;j<((Panel)frame.getComponents()[i]).getComponents().length;j++)
                    {
                        if(((Panel)frame.getComponents()[i]).getComponents()[j] instanceof Button)
                        {
                            if(((Button)((Panel)frame.getComponents()[i]).getComponents()[j]).getLabel().equals(""))
                            {
                                casillasLibres = true;
                            }
                        }
                    }
                }
            }
        }
        if(casillasLibres == false){
            this.LbResultado.setText("Partida en tablas");
        }
    }
}
