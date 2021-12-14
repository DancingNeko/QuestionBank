import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class ReviewWindow extends JFrame{
	JLabel question;
	JTextArea answer;
	JButton confirm;
	JFrame thisWindow;
	int count = 0;
	public ReviewWindow(ArrayList<Question>questions, JPanel stats) {
		super();
		thisWindow = this;
		question = new JLabel();
		question.setAlignmentX(LEFT_ALIGNMENT);
		answer = new JTextArea();
		confirm = new JButton("confirm");
		confirm.setAlignmentX(CENTER_ALIGNMENT);
		this.setSize(getPreferredSize());
		this.setLayout(new BoxLayout(thisWindow.getContentPane(),BoxLayout.Y_AXIS));
		questions.sort(null);
		this.getContentPane().add(question);
		this.getContentPane().add(answer);
		this.getContentPane().add(confirm);
		this.setQuestion(questions.get(count));
		this.revalidate();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String actualAnswer = (String)(questions.get(count).getAnswer());
				if(compareStrings(actualAnswer,answer.getText()) < 0.1*actualAnswer.length()) { //90% accurate
					JOptionPane.showMessageDialog(thisWindow, "Correct!");
					answer.setText("");
					questions.get(count).answerCorrect(questions.get(count), true);
				}
				else if(compareStrings(actualAnswer,answer.getText()) <= 0.3*actualAnswer.length()) {
					JOptionPane.showMessageDialog(thisWindow, "Seems a bit off?\nCorrect Answer is: " + actualAnswer);
					answer.setText("");
					questions.get(count).answerCorrect(questions.get(count), true);
				}
				else if(compareStrings(actualAnswer,answer.getText()) <= 0.5*actualAnswer.length()) {
					JOptionPane.showMessageDialog(thisWindow, "It's close, but nah-ah...\nCorrect Answer is: " + actualAnswer);
					answer.setText("");
				}
				else{
					JOptionPane.showMessageDialog(thisWindow, "Wrong!\nCorrect Answer is: " + actualAnswer);
					answer.setText("");
					questions.get(count).answerCorrect(questions.get(count), false);
				}
				count++;
				if(count >= questions.size()) {
					JOptionPane.showMessageDialog(thisWindow, "Congrats on finish reviewing all the questions! Good job!");
					questions.sort(null);
					thisWindow.setVisible(false);
					stats.repaint();
				}
				else
				{
					setQuestion(questions.get(count));
				}
			}
			
		});
		}
	
	public int compareStrings(String x, String y) {
		int diff = LevenshteinDistance.compute_Levenshtein_distanceDP(x, y);
	    return diff;
	}

	
	public void setQuestion(Question q) {
		question.setText((String)q.getQuestion());
		question.setSize(getPreferredSize());
		answer.setSize(getPreferredSize());
		confirm.setSize(getPreferredSize());
		thisWindow.setSize(400,400);
	}
	
	public boolean verifyAnswer() {
		return false;
	}
}
