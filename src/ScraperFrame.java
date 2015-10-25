/**********************************************************************
Displays the Scraper and ScraperPanel content in a JFrame.

@author Conner Toney
@version 1.1
 *********************************************************************/

import javax.swing.*;

public class ScraperFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Hip Hop Scraper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ScraperPanel panel = new ScraperPanel();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
