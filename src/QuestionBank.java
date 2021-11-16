import java.io.Serializable;
import java.util.ArrayList;

public class QuestionBank implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Question> qList;
	private ArrayList<Tag> tagList;
	QuestionBank(){
		qList = new ArrayList<Question>();
		tagList = new ArrayList<Tag>();
	}
	public void addQuestion(Question q) {
		qList.add(q);
	}
	public void addTag(Tag t) {
		tagList.add(t);
	}
	
	public ArrayList<Question> getQuestions() {
		return qList;
	}
	public ArrayList<Tag> getTags() {
		return tagList;
	}
}
