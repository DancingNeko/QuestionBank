import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileSelectionWindow extends JFrame{
	Object question = null;
	Object answer = null;
	
	FileSelectionWindow(){
		super();
		JFrame window = this;
		this.setSize(300, 150);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		JButton text = new JButton("Text input");
		text.setAlignmentX(CENTER_ALIGNMENT);
		this.add(text);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);;
		this.setVisible(true);
		
		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				text.setVisible(false);
				JTextArea jtf = new JTextArea("Input your question here");
				add(jtf);
				JButton confirm = new JButton("confirm");
				window.add(confirm);
				confirm.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(question == null) {
							question = jtf.getText();
							jtf.setText("Input your answer");
						}
						else if(answer == null){
							answer = jtf.getText();
							MainWindow.MainPanel.addQuestion(question,answer);
							jtf.setText("Question created!\nClose window to continue or add another");
							JButton addAnother = new JButton("Add another");
							window.add(addAnother);
							confirm.setVisible(false);
							window.revalidate();
							addAnother.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									addAnother.setVisible(false);
									window.add(jtf);
									confirm.setVisible(true);
									window.add(confirm);
									window.revalidate();
									question = null;
									answer = null;
									jtf.setText("Input your question here");
								}
							});
						}
						else {}
					}
				});
			}
		});
	}
	
	
	
	public Object getQuestion() {
		return question;
	}
	
	public Object getAnswer() {
		return answer;
	}
}
