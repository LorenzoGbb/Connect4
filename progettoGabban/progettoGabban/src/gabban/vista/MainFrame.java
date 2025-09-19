package gabban.vista;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import gabban.vista.Frame;

/***
 * 
 * @author Lorenzo Gabban 20044584
 *
 */

public class MainFrame {
	
	/***
	 * Metodo main
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Frame frame = new Frame();
	        frame.setVisible(true);
		});	
	}
}