import java.io.Serializable;
import java.util.ArrayList;

public class Tag implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Question> questions;
	
	Tag(String n){
		name = n;
		questions = new ArrayList<Question>();
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Question> getQuestions(){
		return questions;
	}
	
	public void addQuestion(Question item) {
		questions.add(item);
	}
	
	public void removeQuesiton(Question item) {
		for(int i = 0; i < questions.size(); i++) {
			if(questions.get(i).equals(item)) {
				questions.remove(i);
				return;
			}
		}
	}
}
