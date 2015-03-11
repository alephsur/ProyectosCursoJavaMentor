package A3M2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.tools.JavaCompiler;

public class GestionAlumnos implements ActionListener, KeyListener,ChangeListener, ItemListener {
	
	private final String NOMBRE_FICHERO = "./bin/alumnos.txt";
	
	private JButton BtLimpiar;
	private JButton BtAlta;
	private JButton darBaja;
	private JButton btModificar;
	
	private JTextField txClave;
	private JTextField txNombre;
	private JTextField txApellidos;
	private JTextField txEdad;
	private JTextField txCalle;
	private JTextField txNumero;
	private JTextField txCodigoPostal;
	private JTextField txNombreBaja;
	private JTextField txApellidosBaja;
	private JTextField txEdadBaja;
	private JTextField txCalleBaja;
	private JTextField txNumeroBaja;
	private JTextField txCodigoPostalBaja;
	private JTextField txNombreModif;
	private JTextField txApellidosModif;
	private JTextField txEdadModif;
	private JTextField txCalleModif;
	private JTextField txNumeroModif;
	private JTextField txCodigoPostalModif;
	
	private JComboBox cbClaveBaja;
	private JComboBox cbClaveModif;
	
	private JTabbedPane pestañas;
	
	private JFrame frame;
	
	private ArrayList<Alumnos> listaAlumnos;
	
	private Boolean modificacionesRealizadas=false;
	
	public void GestionAlumnos(){


	}
	
	public void DiseñarPantalla(){
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 400);
		frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if(modificacionesRealizadas == true){
					Object[] opciones = {"Aceptar","Cancelar"};
					int n = JOptionPane.showOptionDialog(frame, "No ha guardado las modificaciones realizadas, ¿Salir de todos modos?", "Salir",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
					if(n == JOptionPane.YES_NO_OPTION){
						System.exit(0);
					}
					else{					
					}
				}
				else{
					System.exit(0);
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		
		JMenuBar barraMenu = new JMenuBar();
		JMenu elMenu = new JMenu("Fichero");
		
		JMenuItem elItemGuardar = new JMenuItem("Guardar");
		elItemGuardar.setName("Guardar");
		elItemGuardar.addActionListener(this);
		elMenu.add(elItemGuardar);
		
		JMenuItem elItemSalir =new JMenuItem("Salir");
		elItemSalir.setName("Salir");
		elItemSalir.addActionListener(this);
		elMenu.add(elItemSalir);
		
		barraMenu.add(elMenu);
		
		
		pestañas = new JTabbedPane();
		pestañas.addChangeListener(this);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		
		JPanel datosPersonales = new JPanel();
		datosPersonales.setLayout(new GridLayout(4,2));
		datosPersonales.setBorder(BorderFactory.createTitledBorder("Datos Personales"));
		
		JLabel lbClave = new JLabel("Clave");
		datosPersonales.add(lbClave);
		txClave = new JTextField();		
		datosPersonales.add(txClave);
		
		JLabel lbNombre = new JLabel("Nombre");
		datosPersonales.add(lbNombre);
		txNombre = new JTextField();
		datosPersonales.add(txNombre);
		
		JLabel lbApellidos = new JLabel("Apellidos");
		datosPersonales.add(lbApellidos);
		txApellidos = new JTextField();
		datosPersonales.add(txApellidos);
		
		JLabel lbEdad = new JLabel("Edad");
		datosPersonales.add(lbEdad);
		txEdad = new JTextField();
		txEdad.addKeyListener(this);
		datosPersonales.add(txEdad);
		
		
		panel1.add(datosPersonales,BorderLayout.NORTH);
		
		JPanel direccion = new JPanel();
		direccion.setLayout(new GridLayout(3,2));
		direccion.setBorder(BorderFactory.createTitledBorder("Dirección"));
		
		JLabel lbCalle = new JLabel("Calle");
		direccion.add(lbCalle);
		txCalle = new JTextField();
		direccion.add(txCalle);
		
		JLabel lbNumero = new JLabel("Número");
		direccion.add(lbNumero);
		txNumero = new JTextField();
		txNumero.addKeyListener(this);
		direccion.add(txNumero);
		
		JLabel lbCodioPostal = new JLabel("Código Postal");
		direccion.add(lbCodioPostal);
		txCodigoPostal = new JTextField();
		direccion.add(txCodigoPostal);
		
		panel1.add(direccion);
		
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		BtLimpiar = new JButton("Limpiar");
		BtLimpiar.setName("Limpiar");
		BtLimpiar.addActionListener(this);
		botones.add(BtLimpiar);
		
		BtAlta = new JButton("Dar Alta");
		BtAlta.setName("Alta");
		BtAlta.addActionListener(this);
		botones.add(BtAlta);
		
		panel1.add(botones,BorderLayout.SOUTH);
		
		pestañas.addTab("Altas",panel1);		
		
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(4,2));
		
		JPanel alumno = new JPanel();
		alumno.setLayout(new FlowLayout());
		alumno.setBorder(BorderFactory.createTitledBorder("Alumno"));
		
		JLabel lbClaveBaja= new JLabel("Clave");
		alumno.add(lbClaveBaja);
		cbClaveBaja = new JComboBox();
		cbClaveBaja.setName("cbBaja");
		cbClaveBaja.addItemListener(this);
		alumno.add(cbClaveBaja);
		
		panel2.add(alumno,BorderLayout.NORTH);
		
		JPanel datosPersonales2 = new JPanel();
		datosPersonales2.setBorder(BorderFactory.createTitledBorder("Datos Personales"));
		datosPersonales2.setLayout(new GridLayout(3,2));
		
		JLabel lbNombreBaja = new JLabel("Nombre");
		datosPersonales2.add(lbNombreBaja);
		txNombreBaja = new JTextField();
		txNombreBaja.setEnabled(false);
		datosPersonales2.add(txNombreBaja);
		
		JLabel lbApellidosBaja = new JLabel("Apellidos");
		datosPersonales2.add(lbApellidosBaja);
		txApellidosBaja = new JTextField();
		txApellidosBaja.setEnabled(false);
		datosPersonales2.add(txApellidosBaja);
		
		JLabel lbEdadBaja = new JLabel("Edad");
		datosPersonales2.add(lbEdadBaja);
		txEdadBaja = new  JTextField();
		txEdadBaja.setEnabled(false);
		datosPersonales2.add(txEdadBaja);		
		
		panel2.add(datosPersonales2,BorderLayout.CENTER);
		
		JPanel direccion2 = new JPanel();
		direccion2.setLayout(new GridLayout(3,2));
		direccion2.setBorder(BorderFactory.createTitledBorder("Direccion"));
		
		JLabel lbCalleBaja = new JLabel("Calle");
		direccion2.add(lbCalleBaja);
		txCalleBaja = new JTextField();
		txCalleBaja.setEnabled(false);
		direccion2.add(txCalleBaja);
		
		JLabel lbNumeroBaja = new JLabel("Número");
		direccion2.add(lbNumeroBaja);
		txNumeroBaja = new JTextField();
		txNumeroBaja.setEnabled(false);
		direccion2.add(txNumeroBaja);
		
		JLabel lbCodigoPostalBaja = new JLabel("Código Postal");
		direccion2.add(lbCodigoPostalBaja);
		txCodigoPostalBaja = new JTextField();
		txCodigoPostalBaja.setEnabled(false);
		direccion2.add(txCodigoPostalBaja);
		
		panel2.add(direccion2);
		
		JPanel botonesBaja = new JPanel();
		botonesBaja.setLayout(new FlowLayout());
		
		darBaja = new JButton("Dar de Baja");
		darBaja.setName("Baja");
		darBaja.addActionListener(this);
		botonesBaja.add(darBaja);
		
		panel2.add(botonesBaja,BorderLayout.SOUTH);
		
		pestañas.addTab("Bajas", panel2);		
		
	
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(4,1));
		
		JPanel alumno2 = new JPanel();
		alumno2.setBorder(BorderFactory.createTitledBorder("Alumno"));
		
		JLabel lbClaveModif= new JLabel("Clave");
		alumno2.add(lbClaveModif);
		cbClaveModif = new JComboBox();
		cbClaveModif.setName("cbModif");
		cbClaveModif.addItemListener(this);
		alumno2.add(cbClaveModif);
		
		panel3.add(alumno2);
		
		JPanel datosPersonales3 = new JPanel();
		datosPersonales3.setBorder(BorderFactory.createTitledBorder("Datos Personales"));
		datosPersonales3.setLayout(new GridLayout(3,2));
		
		JLabel lbNombreModif = new JLabel("Nombre");
		datosPersonales3.add(lbNombreModif);
		txNombreModif = new JTextField();
		datosPersonales3.add(txNombreModif);
		
		JLabel lbApellidosModif = new JLabel("Apellidos");
		datosPersonales3.add(lbApellidosModif);
		txApellidosModif = new JTextField();
		datosPersonales3.add(txApellidosModif);
		
		JLabel lbEdadModif = new JLabel("Edad");
		datosPersonales3.add(lbEdadModif);
		txEdadModif = new  JTextField();
		datosPersonales3.add(txEdadModif);		
		
		panel3.add(datosPersonales3);
		
		JPanel direccion3 = new JPanel();
		direccion3.setBorder(BorderFactory.createTitledBorder("Direccion"));
		direccion3.setLayout(new GridLayout(3,2));
		
		JLabel lbCalleModif = new JLabel("Calle");
		direccion3.add(lbCalleModif);
		txCalleModif = new JTextField();
		direccion3.add(txCalleModif);
		
		JLabel lbNumeroModif = new JLabel("Número");
		direccion3.add(lbNumeroModif);
		txNumeroModif = new JTextField();
		direccion3.add(txNumeroModif);
		
		JLabel lbCodigoPostalModif = new JLabel("Código Postal");
		direccion3.add(lbCodigoPostalModif);
		txCodigoPostalModif = new JTextField();
		direccion3.add(txCodigoPostalModif);
		
		panel3.add(direccion3);
		
		JPanel BotonesModif = new JPanel();
		BotonesModif.setLayout(new FlowLayout());
		
		btModificar = new JButton("Modificar");
		btModificar.setName("Modificar");
		btModificar.addActionListener(this);
		BotonesModif.add(btModificar);
		
		panel3.add(BotonesModif);
		
		pestañas.addTab("Modificaciones", panel3);		
		
		frame.getContentPane().add(pestañas);
		frame.setJMenuBar(barraMenu);
		frame.setVisible(true);
		
		
		this.CargarDatos();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JButton){
			if(((JButton)e.getSource()).getName().equals("Alta")){
				this.Alta();
			}
			if(((JButton)e.getSource()).getName().equals("Limpiar")){
				this.Limpiar();
			}
			if(((JButton)e.getSource()).getName().equals("Baja")){
				this.Baja();
			}
			if(((JButton)e.getSource()).getName().equals("Modificar")){
				this.Modificar();
			}
		}
		if(e.getSource() instanceof JMenuItem){
			if(((JMenuItem)e.getSource()).getName().equals("Guardar") ){
				this.Serializar();
				this.modificacionesRealizadas = false;
			}
			if(((JMenuItem)e.getSource()).getName().equals("Salir") ){
				if(this.modificacionesRealizadas == true){
					Object[] opciones = {"Aceptar","Cancelar"};
					int n = JOptionPane.showOptionDialog(frame, "No ha guardado las modificaciones realizadas, ¿Salir de todos modos?", "Salir",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
					if(n == JOptionPane.NO_OPTION){
						return;
					}
				}
				System.exit(0);
			}
		}
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
		int k = (int)e.getKeyChar();
		if (k <= 47 || k >=  58) {

			e.consume();
		}
	}
	
	private void Alta(){
		if(this.txNombre.getText().equals("") || this.txApellidos.getText().equals("")
				|| this.txClave.equals("") || this.txEdad.getText().equals("") 
				|| this.txCalle.equals("") || this.txCodigoPostal.getText().equals("")
				|| this.txNumero.equals("")){
			JOptionPane.showMessageDialog(frame, "Tiene que introducir todos los datos del alumno, quedan campos en blanco");
			return;
		}
		if(this.listaAlumnos == null){
			this.listaAlumnos = new ArrayList<Alumnos>();
		}
		
		if(this.ClaveValida(this.txClave.getText()) == false){
			JOptionPane.showMessageDialog(frame, "La clave introducida ya existe");
			return;
		}
		
		Alumnos alumno = new Alumnos();
		
		alumno.SetClave(this.txClave.getText());
		alumno.SetNombreAlumno(this.txNombre.getText());
		alumno.SetApellidosAlumno(this.txApellidos.getText());
		alumno.SetEdadAlumno(Integer.parseInt(this.txEdad.getText()));
		alumno.GetDireccion().SetCalle(this.txCalle.getText());
		alumno.GetDireccion().SetCodigoPostal(this.txCodigoPostal.getText());
		alumno.GetDireccion().SetNumero(Integer.parseInt(this.txNumero.getText()));
		
		this.cbClaveBaja.addItem(alumno.GetClave());
		this.cbClaveModif.addItem(alumno.GetClave());
		
		
		this.listaAlumnos.add(alumno);
		this.modificacionesRealizadas=true;
	}
	
	private void Baja(){
		String clave = (String)this.cbClaveBaja.getSelectedItem();
		int indice = this.MostrarAlumnoBaja(clave);
		this.listaAlumnos.remove(indice);
		this.cbClaveBaja.removeItemAt(indice);
		this.modificacionesRealizadas=true;
	}
	
	private void Modificar(){
		String clave = (String)this.cbClaveModif.getSelectedItem();
		int indice= this.GetIndiceModificar(clave);
		this.listaAlumnos.get(indice).SetNombreAlumno(this.txNombreModif.getText());
		this.listaAlumnos.get(indice).SetApellidosAlumno(this.txApellidosModif.getText());
		this.listaAlumnos.get(indice).SetEdadAlumno(Integer.parseInt(this.txEdadModif.getText()));
		this.listaAlumnos.get(indice).GetDireccion().SetCalle(this.txCalleModif.getText());
		this.listaAlumnos.get(indice).GetDireccion().SetNumero(Integer.parseInt(this.txNumeroModif.getText()));
		this.listaAlumnos.get(indice).GetDireccion().SetCodigoPostal(this.txCodigoPostalModif.getText());
		this.modificacionesRealizadas=true;
	}
	
	private void Limpiar(){
		this.txClave.setText("");
		this.txNombre.setText("");
		this.txApellidos.setText("");
		this.txEdad.setText("");
		this.txCalle.setText("");
		this.txNumero.setText("");
		this.txCodigoPostal.setText("");
	}

	private void Serializar(){
		
		FileOutputStream fichero;
		try {
			fichero = new FileOutputStream(this.NOMBRE_FICHERO);
			ObjectOutputStream seri = new ObjectOutputStream(fichero);
			seri.writeObject(this.listaAlumnos);			
			seri.close();
			fichero.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void CargarDatos(){
		try{
			FileInputStream ficheroAlumnos = new FileInputStream(this.NOMBRE_FICHERO);
			ObjectInputStream seri = new ObjectInputStream(ficheroAlumnos);
			//Object objeto = seri.readObject();
			listaAlumnos = (ArrayList<Alumnos>) seri.readObject();
			//Alumnos alumno = (Alumnos)seri.readObject();
			ficheroAlumnos.close();
			seri.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		for(int i = 0;i<this.listaAlumnos.size();i++){
			this.cbClaveBaja.addItem(this.listaAlumnos.get(i).GetClave());
			this.cbClaveModif.addItem(this.listaAlumnos.get(i).GetClave());
		}
	}

	private Boolean ClaveValida(String clave){
		for(int i=0;i<this.listaAlumnos.size();i++){
			if(this.listaAlumnos.get(i).GetClave().equals(clave)){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if(this.pestañas.getSelectedIndex() == 1){
			if(this.cbClaveBaja.getSelectedItem()!=null){
				String claveSeleccionada = (String)this.cbClaveBaja.getSelectedItem();
				this.MostrarAlumnoBaja(claveSeleccionada);
			}
		}
		if(this.pestañas.getSelectedIndex() == 2){
			if(this.cbClaveBaja.getSelectedItem()!=null){
				String claveSeleccionada = (String)this.cbClaveBaja.getSelectedItem();
				this.MostrarAlumnoModificar(claveSeleccionada);
			}			
		}
	}
	
	private int MostrarAlumnoBaja(String clave){
		int indice = 0;
		Alumnos alumnoSeleccionado = null;
		for(int i=0;i<this.listaAlumnos.size();i++){
			if(this.listaAlumnos.get(i).GetClave().equals(clave)){
				alumnoSeleccionado = this.listaAlumnos.get(i);
				this.txNombreBaja.setText(alumnoSeleccionado.GetNombreAlumno());
				this.txApellidosBaja.setText(alumnoSeleccionado.GetApellidosAlumno());
				this.txEdadBaja.setText(String.valueOf(alumnoSeleccionado.GetEdadAlumno()));
				this.txCalleBaja.setText(alumnoSeleccionado.GetDireccion().GetCalle());
				this.txNumeroBaja.setText(String.valueOf(alumnoSeleccionado.GetDireccion().GetNumero()));
				this.txCodigoPostalBaja.setText(alumnoSeleccionado.GetDireccion().GetCodigoPostal());
				indice = i;
				break;
			}
		}
		return indice;
	}
	
	private int MostrarAlumnoModificar(String clave){
		int indice = 0;
		Alumnos alumnoSeleccionado;
		for(int i=0;i<this.listaAlumnos.size();i++){
			if(this.listaAlumnos.get(i).GetClave().equals(clave)){
				alumnoSeleccionado = this.listaAlumnos.get(i);
				this.txNombreModif.setText(alumnoSeleccionado.GetNombreAlumno());
				this.txApellidosModif.setText(alumnoSeleccionado.GetApellidosAlumno());
				this.txEdadModif.setText(String.valueOf(alumnoSeleccionado.GetEdadAlumno()));
				this.txCalleModif.setText(alumnoSeleccionado.GetDireccion().GetCalle());
				this.txNumeroModif.setText(String.valueOf(alumnoSeleccionado.GetDireccion().GetNumero()));
				this.txCodigoPostalModif.setText(alumnoSeleccionado.GetDireccion().GetCodigoPostal());
				indice =i;
				break;
			}
		}
		return indice;
	}
	
	private int GetIndiceModificar(String clave){
		int indice = 0;
		Alumnos alumnoSeleccionado;
		for(int i=0;i<this.listaAlumnos.size();i++){
			if(this.listaAlumnos.get(i).GetClave().equals(clave)){
				indice =i;
				break;
			}
		}
		return indice;
	}

	
	@Override	
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(((JComboBox)e.getSource()).getName().equals("cbBaja")){
			this.MostrarAlumnoBaja((String)this.cbClaveBaja.getSelectedItem());
		}
		if(((JComboBox)e.getSource()).getName().equals("cbModif")){
			this.MostrarAlumnoModificar((String)this.cbClaveModif.getSelectedItem());
		}
	}
}





















