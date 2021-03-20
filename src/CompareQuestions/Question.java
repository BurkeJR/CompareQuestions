package CompareQuestions;
import java.util.ArrayList;

public class Question {
	private String question;
	private int num;
	public ArrayList<String> answers;
	
	/**
	 * Creates a question with the given string. The number is set to the default of -1.
	 * @param q
	 */
	public Question(String q) {
		setQuestion(q);
		this.num = -1;
		answers = new ArrayList<>();
	}
	
	/**
	 * Creates a question with the given string and assigns it the given number.
	 * @param q
	 * @param num
	 */
	public Question(String q, int num) {
		setQuestion(q);
		this.num = num;
		answers = new ArrayList<>();
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String q) {
		//make the question all lowercase
		this.question = q.toLowerCase();
		//remove all punctuation from the question
		this.question = question.replaceAll("\\p{Punct}", "");
	}
	
	public int getNum() {
		return num;
	}
	
	public void addAnswer(String a) {
		//make the answer all lowercase
		a = a.toLowerCase();
		//remove all punctuation from the answer
		a = a.replaceAll("\\p{Punct}", "");
		//add the answer to the list of answers 
		answers.add(a);
	}
}
