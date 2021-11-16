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
		JButton image = new JButton("Image input");
		JButton file = new JButton("File input");
		text.setAlignmentX(CENTER_ALIGNMENT);
		image.setAlignmentX(CENTER_ALIGNMENT);
		file.setAlignmentX(CENTER_ALIGNMENT);
		this.add(text);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);;
		this.setVisible(true);
		
		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				text.setVisible(false);
				image.setVisible(false);
				file.setVisible(false);
				JTextField jtf = new JTextField("Input your question here");
				add(jtf);
				jtf.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(question == null) {
							question = jtf.getText();
							jtf.setText("Input your answer");
						}
						else if(answer == null){
							answer = jtf.getText();
							MainWindow.MainPanel.addQuestion(question,answer);
							jtf.setText("Question created, close window to continue");
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
