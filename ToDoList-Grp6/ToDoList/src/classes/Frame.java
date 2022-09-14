package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;








public class Frame extends JFrame{
	
	private TitleBar title;
	private Footer footer;
	private List list;
	
	private JButton newTask;
	private JButton clear;
	Connecter conn = new Connecter();
	JLabel background;
	
	Frame()
	{
		 this.setSize(1000,1000);
	     Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\asus\\Documents\\ToDoList-main\\ToDoList\\src\\images\\tasks.png"); 
	      this.setIconImage(icon);  
	      this.setMinimumSize(new Dimension(300,300));
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      this.setBackground(Color.white);
	      this.setTitle("Task");
		this.setVisible(true);
		
		 background = new JLabel("");
	     background.setBounds(0, 0, 654, 800);
	     background.setIcon(new ImageIcon(Frame.class.getResource("/images/back.png")));
	     background.setBackground(Color.white);
	     this.getContentPane().add(background);
	     
		
		title = new TitleBar();
		footer = new Footer();
		list = new List();
		title.setBackground(new Color(255, 255, 255));
		list.setBackground(new Color(255, 255, 255));
		footer.setBackground(new Color(255, 255, 255));
		
		this.add(title,BorderLayout.NORTH);
		this.add(footer,BorderLayout.SOUTH);
		this.add(list,BorderLayout.CENTER);
		
		newTask = footer.getNewTask();
		clear = footer.getClear();
		
		addListeners();
	}
	
	
	public void addListeners()
	{
		newTask.addMouseListener(new MouseAdapter()
		{
			@override
			public void mousePressed(MouseEvent e)
			{
				Task task = new Task();
				if (task.taskName.getText().length() > 0  ) {
					
					
						int stc = 0;
						list.add(task);
				        list.updateNumbers();
						stc = stocheEtudiant(task.taskName.getText());
						
						
			
				}
				
					
						
					   
						
						
				       
				
				
				
				task.getDone().addMouseListener(new MouseAdapter()
				{
					@override
					public void mousePressed(MouseEvent e)
					{
						
						
						
						try {
						 
				            Statement st = conn.obtenirconnexion().createStatement();
				            st.executeUpdate("update `task` set `state` = '0' where `nom` = '"+task.taskName.getText()+"'");
				            JOptionPane.showMessageDialog(null,"Successfully Updated");    
				        }
				        catch(Exception e1){
				            JOptionPane.showMessageDialog(null,"Please enter data in correct format!");
				            //JOptionPane.showMessageDialog(null, e);
				        }
						task.changeState();
						list.updateNumbers();
						revalidate();
					
						
					}
				});
				task.getUpdate().addMouseListener(new MouseAdapter()
				{
					@override
					public void mousePressed(MouseEvent e)
					{
						int input = JOptionPane.showConfirmDialog(null, "Are sure ");
						if (input == 0) {
						try {
						 
				            Statement st = conn.obtenirconnexion().createStatement();
				            st.executeUpdate("update `task` set `nom` = '"+task.taskName.getText()+"'");
				            JOptionPane.showMessageDialog(null,"Successfully Updated");    
				        }
				        catch(Exception e1){
				            JOptionPane.showMessageDialog(null,"Please enter data in correct format!");
				            //JOptionPane.showMessageDialog(null, e);
				        }
						
						
					}
					}
				});
			}
			
		});
		
		
		clear.addMouseListener(new MouseAdapter()
		{
			@override
			public void mousePressed(MouseEvent e)
			{
				list.removeCompletedTasks();
				repaint();
			}
		});
	}	
	private int stocheEtudiant(String nom) {
	
		try{
            
            Statement st = conn.obtenirconnexion().createStatement();
            st.executeUpdate("INSERT INTO `task`(`nom`, `state`) VALUES ('"+nom+"', '1');");
            JOptionPane.showMessageDialog(null,"Successfully created");    
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Please enter data in correct format!");
            //JOptionPane.showMessageDialog(null, e);
        }

		return 0;
		
	}
	
}
