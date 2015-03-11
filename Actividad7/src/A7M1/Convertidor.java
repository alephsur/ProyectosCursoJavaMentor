package A7M1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.PageAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Convertidor extends JApplet  implements ActionListener,KeyListener{
	
	private JTextField euros = new JTextField();
	private JTextField pesetas = new JTextField();
	
	public void init(){
		
		this.getContentPane().setLayout(new GridLayout(2,1));
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,2));
		
		
		JLabel lbEuros= new JLabel(getParameter("TEXTO1"));
		JLabel lbPesetas = new JLabel(getParameter("TEXTO2"));
		this.euros.addKeyListener(this);
		this.pesetas.setEnabled(false);
		
		
		panel1.add(lbEuros);
		panel1.add(euros);
		panel1.add(lbPesetas);
		panel1.add(pesetas);
				
		add(panel1);
		
		JPanel panel2  = new JPanel();
		panel2.setLayout(new FlowLayout());
		
		JButton BtConvertir = new JButton(getParameter("TEXTO3"));
		BtConvertir.setName("Convertir");
		BtConvertir.addActionListener(this);
		JButton BtBorrar = new JButton(getParameter("TEXTO4"));
		BtBorrar.setName("Borrar");
		BtBorrar.addActionListener(this);
		
		panel2.add(BtConvertir);
		panel2.add(BtBorrar);
		
		add(panel2);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(((JButton)arg0.getSource()).getName().equals("Convertir")){
			if(!this.euros.getText().equals("")){
				double resultado =Float.parseFloat(this.euros.getText().toString())*166.386;
				this.pesetas.setText(String.valueOf(resultado));
			}
		}
		if(((JButton)arg0.getSource()).getName().equals("Borrar")){
			this.euros.setText("");
			this.pesetas.setText("");
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
		int k = (int)e.getKeyChar();
		if (k <= 47 || k >=  58) {

			e.consume();
		}
	}
}



















