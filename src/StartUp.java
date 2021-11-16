import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StartUp extends JFrame{
	StartUp(){
		super();
		JFrame thisWindow = this;
		this.setTitle("Startup");
		this.setBounds(0,0,800,400);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		JButton newUser = new JButton("New User");
		JButton existingAccount = new JButton("Existing Account");
		this.add(newUser);
		this.add(existingAccount);
		this.revalidate();
		existingAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newUser.setVisible(false);
				existingAccount.setVisible(false);
				thisWindow.setLayout(new BoxLayout(thisWindow.getContentPane(), BoxLayout.Y_AXIS));
				JTextField username = new JTextField("username");
				thisWindow.add(username);
				JTextField password = new JTextField("password");
				thisWindow.add(password);
				JButton confirm = new JButton("confirm");
				confirm.setAlignmentX(CENTER_ALIGNMENT);
				thisWindow.add(confirm);
				confirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						File userData = new File(System.getProperty("user.dir" + "/" + username));
						if(userData.exists()) {
							try {
					            FileInputStream fileIn = new FileInputStream(userData);
					            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
					            Object obj = objectIn.readObject();
					            objectIn.close();
					            MainWindow w = new MainWindow((User)obj);
					        } catch (Exception ex) {
					            ex.printStackTrace();
					            JOptionPane.showMessageDialog(thisWindow, "Oops, something went wrong...");
					        }
						}
						else {
							JOptionPane.showConfirmDialog(thisWindow, "User not found");
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
				thisWindow.setLayout(new BoxLayout(thisWindow.getContentPane(), BoxLayout.Y_AXIS));
				JTextField username = new JTextField("username");
				thisWindow.add(username);
				JTextField password = new JTextField("password");
				thisWindow.add(password);
				JTextField reenter = new JTextField("re-enter password");
				thisWindow.add(reenter);
				JButton confirm = new JButton("confirm");
				confirm.setAlignmentX(CENTER_ALIGNMENT);
				thisWindow.add(confirm);
				confirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!password.getText().equals(reenter.getText())) {
							System.out.println(password.getText()+"\n"+reenter.getText());
							reenter.setText("Password not match, please retry");
						}
						else {
							String name = username.getText();
							String pwd = password.getText();
							MainWindow w = new MainWindow(new User(name,pwd));
						}
					}
				});
			}
		});
	}
	
	public static void main(String args[]) {
		StartUp w = new StartUp();
	}
}
