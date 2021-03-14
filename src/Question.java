
public class Question {
	private String question;
	private int num;
	
	/**
	 * Creates a question with the given string. The number is set to the default of -1.
	 * @param q
	 */
	public Question(String q) {
		setQuestion(q);
		this.num = -1;
	}
	
	/**
	 * Creates a question with the given string and assigns it the given number.
	 * @param q
	 * @param num
	 */
	public Question(String q, int num) {
		setQuestion(q);
		this.num = num;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String q) {
		this.question = q.toLowerCase();
	}
	
	public int getNum() {
		return num;
	}
}
