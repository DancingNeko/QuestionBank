
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartUp extends JFrame{
	boolean loggedIn = false;
	public String name;
	public String pwd;
	User usr;
	StartUp(){
		super();
		JFrame thisWindow = this;
		this.setTitle("Startup");
		this.setBounds(0,0,300,175);
		this.setLocationRelativeTo(null);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		JButton newUser = new JButton("New User");
		JButton existingAccount = new JButton("Existing Account");
		newUser.setAlignmentX(CENTER_ALIGNMENT);
		existingAccount.setAlignmentX(CENTER_ALIGNMENT);
		this.add(newUser);
		this.add(existingAccount);
		this.revalidate();
		existingAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newUser.setVisible(false);
				existingAccount.setVisible(false);
				JButton back = new JButton("back");
				JPanel thisPanel = new JPanel();
				thisPanel.setLayout(new BoxLayout(thisPanel, BoxLayout.Y_AXIS));
				JTextField username = new JTextField("username");
				thisPanel.add(username);
				JTextField password = new JTextField("password");
				thisPanel.add(password);
				JButton confirm = new JButton("confirm");
				confirm.setAlignmentX(CENTER_ALIGNMENT);
				back.setAlignmentX(CENTER_ALIGNMENT);
				thisPanel.add(confirm);
				thisPanel.add(back);
				thisWindow.add(thisPanel);
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						newUser.setVisible(true);
						existingAccount.setVisible(true);
						thisPanel.setVisible(false);
					}});
				confirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						File userData = new File(System.getProperty("user.dir") + "/" + username.getText());
						if(userData.exists()) {
							try {
					            FileInputStream fileIn = new FileInputStream(userData);
					            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
					            Object obj = objectIn.readObject();
					            objectIn.close();
					            usr = (User)obj;
					            if(usr.getPassword().equals(password.getText())) {
					            	loggedIn = true;
					            }
					            else
					            	JOptionPane.showMessageDialog(thisPanel,"Wrong password, please try again.\nhint: " + usr.getPassword().charAt(0) + "***" + usr.getPassword().charAt(usr.getPassword().length()-1));
					            	
					        } catch (Exception ex) {
					            System.out.println();
					        	ex.printStackTrace();
					            JOptionPane.showMessageDialog(thisPanel, "Oops, something went wrong...");
					        }
						}
						else {
							JOptionPane.showMessageDialog(thisPanel, "User not found");
						}
					}
				});
			}
		});
		newUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newUser.setVisible(false);
				existingAccount.setVisible(false);
				JPanel thisPanel = new JPanel();
				thisPanel.setLayout(new BoxLayout(thisPanel, BoxLayout.Y_AXIS));
				JTextField username = new JTextField("username");
				thisPanel.add(username);
				JTextField password = new JTextField("password");
				thisPanel.add(password);
				JTextField reenter = new JTextField("re-enter password");
				thisPanel.add(reenter);
				JButton confirm = new JButton("confirm");
				confirm.setAlignmentX(CENTER_ALIGNMENT);
				thisPanel.add(confirm);
				JButton back = new JButton("back");
				back.setAlignmentX(CENTER_ALIGNMENT);
				thisPanel.add(back);
				thisWindow.add(thisPanel);
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						newUser.setVisible(true);
						existingAccount.setVisible(true);
						thisPanel.setVisible(false);
					}});
				confirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!password.getText().equals(reenter.getText())) {
							System.out.println(password.getText()+"\n"+reenter.getText());
							JOptionPane.showMessageDialog(thisWindow, "Password not match, please retry");
						}
						else {
							name = username.getText();
							pwd = password.getText();
							usr = new User(name, pwd);
							loggedIn = true;
						}
					}
				});
			}
		});
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public User getUser() {
		return usr;
	}
}
