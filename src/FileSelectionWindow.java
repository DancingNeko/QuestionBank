import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class FileSelectionWindow extends JFrame{
	Object question = null;
	Object answer = null;
	Question q = null;
	boolean tagSet = false;
	
	FileSelectionWindow(){
		super();
		JFrame window = this;
		this.setLocationRelativeTo(null);
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
				jtf.setLineWrap(true);
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
							q = MainWindow.MainPanel.addQuestion(question,answer);
							jtf.setText("Add a list of tags for your question below:\n[Example Tag]\n[ExampleTag]\nThe Square bracket is required");
						}
						else if(!tagSet){
							String textInput = jtf.getText();
							while(textInput.contains("[") && textInput.contains("]")){
								int front = textInput.indexOf('[');
								int back = textInput.indexOf(']');
								MainWindow.MainPanel.updateTag(textInput.substring(front+1,back), q, true);
								if(back == textInput.length()-1)
									textInput = "";
								else
									textInput = textInput.substring(back+1);
							}
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
