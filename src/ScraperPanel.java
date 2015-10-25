/**********************************************************************
Displays the Scraper content in a JPanel.

@author Conner Toney
@version 1.1
 *********************************************************************/

import javax.swing.JPanel;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class ScraperPanel extends JPanel {
	
	/** default serial ID */
	private static final long serialVersionUID = 1L;
	
	/** Scraper object used for panel */
	private Scraper scraper;
	
	/** used to display String information */
	private JTextArea textArea;
	
	/** used to determine actions after buttons are pressed */
	private ButtonListener bListener;
	
	/** button used to refresh content */
	private JButton refreshBtn;
	
	public ScraperPanel() {
		scraper = new Scraper();
		textArea = new JTextArea();
		try {
		Scraper.getContent();
		}
		catch (IOException e) {
			textArea.setText("Error retrieving content");
		}
		textArea.setText(scraper.toString());
		add(textArea);
		bListener = new ButtonListener();
		refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(bListener);
		add(refreshBtn);
	}
	
	/******************************************************************
    ButtonListener used to determine which buttons were selected
	 *****************************************************************/
	private class ButtonListener implements ActionListener {
		
		/**************************************************************
	    Determines the actions to go through with when a button is
	    selected
	    @param e the action event
		 *************************************************************/
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == refreshBtn) {
				try {
					Scraper.getContent();
				}
				catch (IOException e2) {
					textArea.setText("Error retrieving content");
				}
				textArea.setText(scraper.toString());
			}
		}
	}
}
