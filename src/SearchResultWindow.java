import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SearchResultWindow extends JFrame{
	JFrame thisWindow;
	JScrollPane mainPanel;
	JPanel questionPanel;
	Question selectedQ;
	SearchResultWindow(ArrayList<Question> results){
		super();
		thisWindow = this;
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		if(results.size() == 0) {
			this.add(new JLabel("No result found, please verify your search keywords"));
		}
		JPanel inScrollPane = new JPanel();
		JScrollPane displayList = new JScrollPane(inScrollPane);
		mainPanel = displayList;
		this.add(displayList);
		inScrollPane.setLayout(new BoxLayout(inScrollPane,BoxLayout.Y_AXIS));
		ArrayList<JButton> buttonList = new ArrayList<JButton>();
		ActionListener buttonToggled = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedQuestion = arg0.getActionCommand();
				for(Question question:results) {
					String qText = (String)question.getQuestion();
					if(qText.equals(selectedQuestion))
						selectedQ = question;
				}
				QuestionPanel qPane = new QuestionPanel(selectedQ);
				mainPanel.setVisible(false);
				thisWindow.add(qPane);
				thisWindow.revalidate();
			}
		};
		for(Question q:results) {
			JButton butt = new JButton((String)q.getQuestion());
			butt.setSize(500,40);
			butt.addActionListener(buttonToggled);
			buttonList.add(butt);
			inScrollPane.add(butt);
		}
		inScrollPane.setSize(getPreferredSize());
		displayList.setSize(getPreferredSize());
		this.setVisible(true);
	}
	
	public class QuestionPanel extends JPanel{
		QuestionPanel(Question q){
			super();
			questionPanel = this;
			JTextArea displayProblem;
			String question = (String)q.getQuestion();
			displayProblem = new JTextArea(question);
			this.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
			this.add(displayProblem);
			JTextArea displayAnswer;
			String answer = (String)q.getAnswer();
			displayAnswer = new JTextArea(answer);
			displayAnswer.setBackground(new Color(156, 255, 165));
			JButton showAnswer = new JButton("show answer");
			JButton back = new JButton("back");
			JButton saveChanges = new JButton("save changes");
			saveChanges.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int response = JOptionPane.showConfirmDialog(thisWindow, "Save all the changes to your question/answer?");
					if(response == JOptionPane.YES_OPTION) {
						q.setQuestion(displayProblem.getText());
						q.setAnswer(displayAnswer.getText());
						JOptionPane.showMessageDialog(thisWindow, "Question saved!");
					}
				}
			});
			showAnswer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					showAnswer.setVisible(false);
					back.setVisible(false);
					displayAnswer.setVisible(true);
					displayAnswer.setSize(getPreferredSize());
					questionPanel.add(displayAnswer);
					back.setVisible(true);
					questionPanel.add(saveChanges);
					questionPanel.add(back);
					questionPanel.revalidate();
					thisWindow.revalidate();
				}
			});
			back.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					questionPanel.setVisible(false);
					mainPanel.setVisible(true);
				}
			});
			this.add(showAnswer);
			this.add(back);
		}
	}
}
