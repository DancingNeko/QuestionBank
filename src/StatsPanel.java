import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsPanel extends JPanel{
	public StatsPanel(ArrayList<Question>q) {
		super();
		JPanel thisPanel = this;
		this.setLayout(new BoxLayout(thisPanel, BoxLayout.Y_AXIS));
		JLabel title = new JLabel("Top Missed Questions");
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setFont(new Font("Monospaced",Font.BOLD,20));
		this.add(title);
		this.add(new StatsDisplay(q));
	}
}
