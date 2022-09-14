package classes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleBar extends JPanel{

	TitleBar()
	{
		this.setPreferredSize(new Dimension(400,80));
		
		JLabel titleText = new JLabel("To Do List");
		titleText.setPreferredSize(new Dimension(200,60));
		titleText.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 37));
		titleText.setBackground(new Color(255, 160, 122));
		titleText.setBounds(0, 34, 216, 67);
		titleText.setHorizontalAlignment(JLabel.CENTER);
		  
	       
		this.add(titleText);
	}
}
