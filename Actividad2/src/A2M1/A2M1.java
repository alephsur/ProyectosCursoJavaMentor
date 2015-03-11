package A2M1;

import javax.swing.UIManager;

import A2M1.TresEnRaya;

public class A2M1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try
        {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        }
        catch (Exception ex) { System.out.println("error look and feel"); }
		
        TresEnRaya tresEnRaya = new TresEnRaya();
        tresEnRaya.DiseñarPantalla();
	}

}
