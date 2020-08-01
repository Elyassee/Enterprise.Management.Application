/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Tools{

public static void msgBox(String message){ //get message and printn it
	JOptionPane.showMessageDialog(null,message);
}

public static boolean confimationOfMessage(String message){
    int msg = JOptionPane.showConfirmDialog(null, message);
    if(msg == JOptionPane.YES_OPTION){
    return true;
    }
    else{
        return false;
    }
}


public static void openForm(JFrame form) { //shows emo.png
	try{
		form.setLocationRelativeTo(null);
		Image img = ImageIO.read(Tools.class.getResource("icone.png"));
		form.setIconImage(img);
		form.setDefaultCloseOperation(2);
                form.getContentPane().setBackground(Color.white);
		form.setVisible(true);
	}catch (IOException ex){
		msgBox(ex.getMessage());
	}
}


public static void clearText(Container form){ 
	for (Component c : form.getComponents()){
		if (c instanceof JTextField){
			JTextField txt = (JTextField) c;
			txt.setText("");
		}else if (c instanceof Container){
			clearText((Container) c);
		}
	}
}


public static Object InputBox(String title){ 
	Object myObj = JOptionPane.showInputDialog(title);
	return myObj;
}



public class Table {

	public int columns;
	public Object[][] Items;

	public Table(int columns){
		this.columns = columns;
		Items = new Object[0][columns];
	}
// add data to the table
	public void addNewRow(Object row[]){
		//save the old data in a temporary object
		Object TempItems[][] = Items;
		//full the old data in the new object
		Items = new Object[Items.length + 1][columns];
		for (int x = 0; x<TempItems.length; x++){
		Items[x] = TempItems[x];
	}
	Items[Items.length - 1] = row;
}


public void printItems(){
	for (Object objs[] : Items) {
		for (Object obj : objs) {
			System.out.print(obj + " ; ");
		}
	}
}



}
}