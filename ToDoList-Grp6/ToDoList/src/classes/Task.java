package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 





public class Task extends JPanel{
	
	JLabel index;
	JTextField taskName;
	JButton done;
	JButton update;
	
	private boolean checked;
	
	Task()
	{
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd ");  
		   LocalDateTime now = LocalDateTime.now();  
		  
		
		this.setPreferredSize(new Dimension(400,20));
		this.setBackground(Color.red);
		
		this.setLayout(new BorderLayout());
		
		checked = false;
		
		index = new JLabel("");
		index.setPreferredSize(new Dimension(20,20));
		index.setHorizontalAlignment(JLabel.CENTER);
		index.setBackground(Color.white);
		this.add(index,BorderLayout.WEST);
	
		taskName = new JTextField("TASK                                                                                                                      "+ dtf.format(now));
		taskName.setBorder(BorderFactory.createEmptyBorder());
		taskName.setFont(new Font("Traditional Arabic", Font.PLAIN, 18));
		taskName.setBackground(new Color(240, 248, 255));

		
		this.add(taskName,BorderLayout.CENTER);
		
		done = new JButton("");
		done.setPreferredSize(new Dimension(40,20));
		done.setBorder(BorderFactory.createEmptyBorder());
		done.setBackground(Color.white);
		done.setIcon(new ImageIcon(Task.class.getResource("/images/check.png")));
		done.setFocusPainted(false);
		
		this.add(done,BorderLayout.EAST);
		
		
		update = new JButton("");
		update.setPreferredSize(new Dimension(40,20));
		update.setBorder(BorderFactory.createEmptyBorder());
		update.setFocusPainted(false);
		update.setIcon(new ImageIcon(Task.class.getResource("/images/updating.png")));
		update.setBackground(Color.white);
		this.add(update,BorderLayout.WEST);
		
		
	}
	
	public void changeIndex(int num)
	{
		this.index.setText(num+"");
		this.revalidate();
	}
	
	
	public JButton getDone()
	{
		return done;
	}
	public JButton getUpdate()
	{
		return done;
	}
	
	public boolean getState()
	{
		return checked;
	}
	
	public void changeState()
	{
		this.setBackground(Color.green);
		taskName.setBackground(new Color(255, 218, 185));
		checked = true;
		done.setVisible(false);
		revalidate();
	}
}
