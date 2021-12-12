import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Comparable,Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Tag> tags;
	private int familiarity;
	private Object content;
	private Object answer;
	
	Question(){
		familiarity = 200;
		tags = new ArrayList<Tag>();
	}
	
	Question(Object c, Object a){
		content = c;
		answer = a;
		familiarity = 200;
		tags = new ArrayList<Tag>();
	}
	
	public ArrayList<Tag> getTags(){
		return tags;
	}
	
	public void addTag(Tag newTag) {
		tags.add(newTag);
	}
	
	public int getFamiliarity() {
		return familiarity;
	}
	
	public void setFamiliarity(int f) {
		familiarity = f;
	}
	
	public static void answerCorrect(Question q, boolean correct) {
		if(correct)
			q.setFamiliarity((int)(q.getFamiliarity() * 1.1));
		else
			q.setFamiliarity((int)(q.getFamiliarity()*0.8));
	}

	@Override
	public int compareTo(Object arg0) {
		Question q = (Question)arg0;
		if(this.familiarity > q.getFamiliarity()) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	public Object getQuestion() {
		return content;
	}
	public Object getAnswer() {
		return answer;
	}
	
	public String toString() {
		return "Question: " + content + "\tAnswer:" + answer + "\n";
	}
}
