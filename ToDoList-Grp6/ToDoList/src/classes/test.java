package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class test extends JFrame {

	private JPanel contentPane;
	private TitleBar title;
	private Footer footer;
	private List list;
	
	private JButton newTask;
	private JButton clear;
	Connecter conn = new Connecter();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 contentPane.setLayout(null);
		
		title = new TitleBar();
		title.setBackground(new Color(255, 255, 255));
		title.setBounds(5, 5, 864, 80);
		footer = new Footer();
		footer.setBackground(new Color(255, 255, 255));
		footer.getClear().setText("Archived finished tasks");
		footer.getClear().setBounds(643, 58, 211, 27);
		footer.getNewTask().setBounds(676, 20, 151, 27);
		footer.setBounds(5, 361, 864, 96);
		list = new List();
		list.setBackground(new Color(255, 255, 255));
		list.setBounds(5, 85, 864, 274);
		
		getContentPane().add(title);
		getContentPane().add(footer);
		getContentPane().add(list);
		list.setLayout(null);
		
	
		
		newTask = footer.getNewTask();
		clear = footer.getClear();
		footer.setLayout(null);
		
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
