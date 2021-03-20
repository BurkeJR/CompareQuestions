package CompareQuestions;
import java.util.ArrayList;

public class Test {

	private String testName;
	private ArrayList<Question> questions;
	
	/**
	 * Creates a Test.
	 * ArrayList of questions begins empty.
	 * TestName is set to "unknown" by default.
	 */
	public Test() {
		testName = "unknown";
		questions = new ArrayList<Question>();
	}
	
	/**
	 * Creates a Test.
	 * The file name is set to be the Test's name.
	 * ArrayList of questions begins empty.
	 * @param num
	 */
	public Test(String name) {
		this.testName = name;
		questions = new ArrayList<>();
	}
	
	public void addQuestion(Question q) {
		questions.add(q);
	}
	
	public Question getQuestion(int index) {
		return questions.get(index);
	}
	
	public int getQuestionCount() {
		return questions.size();
	}
	
	public String getName() {
		return testName;
	}
	
}
