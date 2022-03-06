
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private QuestionBank qBank;
	private HashMap<String, Tag> tagList;
	public User(String name, String pwd) {
		username = name;
		password = pwd;
		qBank = new QuestionBank();
		tagList = new HashMap<String, Tag>();
	}
	
	public static User createUser(String name, String pwd) {
		User client = new User(name, pwd);
		return client;
	}

	public String getName() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public QuestionBank getQuestions() {
		return qBank;
	}
	
	public HashMap<String,Tag> getTags(){
		return tagList;
	}
	
	public void bindTag(String tagName, Question q) {
		if(tagList == null)
			tagList = new HashMap<String, Tag>(); //for different version of data file, some doesn't have tagList
		if(tagList.containsKey(tagName.toLowerCase())) {
			tagList.get(tagName.toLowerCase()).addQuestion(q);
			q.addTag(tagList.get(tagName.toLowerCase()));
		}
		else {
			Tag newTag = new Tag(tagName.toLowerCase());
			tagList.put(tagName.toLowerCase(), newTag);
			newTag.addQuestion(q);
			q.addTag(newTag);
		}
	}
	
	public void removeTag(String tagName, Question q) {
		if(tagList.containsKey(tagName.toLowerCase())) {
			Tag tag = tagList.get(tagName);
			tag.removeQuesiton(q);
			q.removeTag(tagName);
		}
	}
	
	public String encryptData() {
		return password;
	}
	
	public boolean saveData() {
		try { 
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir")+"/" + username);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
		return true;	
	}
}
