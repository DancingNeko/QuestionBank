import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class StatsDisplay extends JPanel{
	JPanel thisPanel;
	ArrayList<Question> qSet;
	public StatsDisplay(ArrayList<Question> q) {
		super();
		qSet = q;
		thisPanel = this;
		this.setSize(thisPanel.getPreferredSize());
		this.setVisible(true);
		qSet.sort(null);
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.red);
		if(qSet.size() == 0) {
			g2d.drawString("No displayable questions for now... Why not add some?", 10, 10);
			return;
		}
		int panelLength = thisPanel.getWidth();
		int panelHeight = thisPanel.getHeight();
		int maxBarLength = panelLength - 20;
		int displayNum = (panelHeight/2) / 20;
		if(displayNum > qSet.size())
			displayNum = qSet.size();
		int minFamilarity = qSet.get(0).getFamiliarity();
		for(int i = 0; i < displayNum; i++) {
			int thisFamiliarity = qSet.get(i).getFamiliarity();
			int barLength = (int)((double)maxBarLength*((double)minFamilarity/thisFamiliarity));
			g2d.fillRect(10, 10 + 40*i, barLength, 20);
			g2d.drawString((String)qSet.get(i).getQuestion(), 10, 10 + 40*i);
		}
	}
}
