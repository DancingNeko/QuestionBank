import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainWindow extends JFrame{
	JFileChooser jfc;
	User user;
	public static class MainPanel extends JPanel {
		static User user;
		public MainPanel(User u) {
			super();
			user = u;
			BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(layout);
			File titleFile =  new File("./Title.png");
			BufferedImage titleImage = null;
				try {
					titleImage = ImageIO.read(titleFile);
				} catch (IOException e) { System.out.println(e); }
			JLabel title = new JLabel();
			title.setIcon(new ImageIcon(titleImage));
			title.setAlignmentX(CENTER_ALIGNMENT);
			this.add(title);
			JTextField searchbar = new JTextField();
			searchbar.setMaximumSize(new Dimension(800,50));
			searchbar.setText("Search for questions by tag/keywords!");
			this.add(searchbar);
			JButton review = new JButton("Review!");
			review.setMinimumSize(new Dimension(40,10));
			review.setMaximumSize(new Dimension(200,30));
			review.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(review);
			JPanel stats = new StatsPanel(user.getQuestions().getQuestions());
			stats.setAlignmentX(CENTER_ALIGNMENT);
			stats.repaint();
			stats.setVisible(true);
			this.add(stats);
			searchbar.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent arg0) {
					searchbar.setText("");
				}
				@Override
				public void focusLost(FocusEvent arg0) {
				}
				
			});
			searchbar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(searchbar.getText());
					searchbar.setText("");
				}
				
			});
			
			review.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					user.getQuestions().getQuestions().sort(null);
					System.out.println(user.getQuestions().getQuestions());
					ReviewWindow reviewWindow = new ReviewWindow(user.getQuestions().getQuestions(),stats);
				}
			});
			this.revalidate();
		}

		
		public static void addQuestion(Object question, Object answer) {
			Question q = new Question(question, answer);
			user.getQuestions().addQuestion(q);
		}
	}
	
	public class LeftSidePanel extends JPanel{
		public LeftSidePanel(){
			super();
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			this.add(new JLabel("Welcome back,\n" + user.getName()),0);
			JButton addQuestion = new JButton("Add questions");
			this.add(addQuestion);
			addQuestion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					FileSelectionWindow selectionWindow = new FileSelectionWindow();
					Thread selection = new Thread();
					selection.run();
				}
			});
		}
	}
	
	public class RightSidePanel extends JPanel{
		public RightSidePanel() {
			super();
			JPanel thisPanel = this;
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			this.add(new JLabel("Recent activity"),0);
			JButton save = new JButton("Save");
			this.add(save);
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(user.saveData())
						JOptionPane.showMessageDialog(thisPanel, "Saved Successfully!");
					else
						JOptionPane.showMessageDialog(thisPanel, "Uh Oh, something went wrong...");
				}
			});
		}
	}
	
	public MainWindow(User u) {
		super();
		JFrame thisFrame = this;
		user = u;
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        int response = JOptionPane.showConfirmDialog(thisFrame, "Do you wanna save the changes?");
		        if(response == JOptionPane.YES_OPTION) {
		        	if(user.saveData()) {
						JOptionPane.showMessageDialog(thisFrame, "Saved Successfully!");
			        	System.exit(0);
		        	}
					else
						JOptionPane.showMessageDialog(thisFrame, "Uh Oh, something went wrong...");
		        }
		        else if(response == JOptionPane.NO_OPTION)
		        	System.exit(0);
		    }
		});
		this.setSize(800, 600);
		this.setResizable(true);
		this.setTitle("QBank");
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(200,200));
		this.getContentPane().add(new MainPanel(user),BorderLayout.CENTER);
		this.getContentPane().add(new LeftSidePanel(),BorderLayout.WEST);
		this.getContentPane().add(new RightSidePanel(),BorderLayout.EAST);
		this.setVisible(true);
		this.revalidate();
	}
	
	public static void main(String args[]) {
		StartUp go = new StartUp();
		new Thread(()->{
			System.out.print("Logging in");
			while(!go.isLoggedIn()) {
				System.out.print(".");
			}
			go.setVisible(false);
			MainWindow window = new MainWindow(go.getUser());
		}).start();
	}

}

