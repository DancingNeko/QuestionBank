import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private QuestionBank qBank;
	public User(String name, String pwd) {
		username = name;
		password = pwd;
		qBank = new QuestionBank();
	}
	
	public static User createUser(String name, String pwd) {
		User client = new User(name, pwd);
		return client;
	}

	public String getName() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public QuestionBank getQuestions() {
		return qBank;
	}
	
	public String encryptData() {
		return password;
		
	}
	
	public boolean saveData() {
		try { 
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir")+"/" + username);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
		return true;	
	}
}
