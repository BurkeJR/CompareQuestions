package CompareQuestions;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Takes an array of Tests and compares the questions in each of them.
 * Compares every question a test with all of the questions in every other test.
 * Does not compare questions within one Test.
 * Records questions that are similar at a given percentage.
 * 
 * @author BURKEJR19
 *
 */
public class CompareTests {

	
	private Test[] tests;
	private double percentage;
	
	/**
	 * Creates a new CompareTests with the given array of Tests.
	 * Percentage defaults to 0.7 (70%)
	 * @param tests
	 */
	public CompareTests(Test[] tests) {
		this.tests = tests;
		this.percentage = .7;
	}
	
	/**
	 * Creates a new CompareTests with the given array of Tests and percentage.
	 * @param tests
	 * @param percentage
	 */
	public CompareTests(Test[] tests, double percentage) {
		this.tests = tests;
		this.percentage = percentage;
	}
	
	public String run() {
		StringBuilder sb = new StringBuilder("");
		
		//each test is compared with the all of the other tests
		for(int first = 0; first < tests.length; first++) {
			//t1 is the current test and is the first one put into the compareTests method
			//me in the following comments refers to t1
			Test t1 = tests[first];
			
			//add my name for the header for my section
			sb.append(t1.getName() + "\n");
			
			//add headers for all of my questions
			sb.append(" , ");  //add extra space for formatting
			for(int q = 1; q <= t1.getQuestionCount(); q++) {
				sb.append("Question " + q + ", ");
			}
			sb.append("\n");  //go to the next line so the comparisons can start being printed
			
			//compare with all tests in the indexes before me
			for(int prev = first - 1; prev >= 0; prev--) {
				//add the name of the test I am comparing with 
				sb.append(tests[prev].getName() + ", ");
				//add result of comparison
				sb.append(compareTests(t1, tests[prev]));
				//add new line to separate the tests
				sb.append("\n");
			}
			
			//only compares tests before and after me, so doesn't compare me to myself
			
			//compare with all tests in the indexes after me
			for(int next = first + 1; next < tests.length; next++) {
				//add the name of the test I am comparing with 
				sb.append(tests[next].getName() + ", ");
				//add result of comparison
				sb.append(compareTests(t1, tests[next]));
				//add new line to separate the tests
				sb.append("\n");
			}
			
			//add extra line to separate me from the next test header
			sb.append("\n");
		}
		
		
		return sb.toString();
	}
	
	private String compareTests(Test t1, Test t2) {
		StringBuilder result = new StringBuilder("");
		int size1 = t1.getQuestionCount();
		int size2 = t2.getQuestionCount();
		ArrayList<Integer> related;
		
		for(int one = 0; one < size1; one++) {
			Question temp = t1.getQuestion(one);
			related = new ArrayList<>();
			
			for(int two = 0; two < size2; two++) {
				if(compareQuestions(temp, t2.getQuestion(two))) {
					related.add(two + 1);
				}
			}
			
			for(int i = 0; i < related.size(); i++) {
				result.append(related.get(i));
				if(i != related.size() - 1) {
					result.append("; ");
				}
			}
			
			
			if(one != size1 - 1) {
				result.append(", ");
			}
		}
		
		
		return result.toString();
	}
	
	private boolean compareQuestions(Question q1, Question q2) {
		double q2TotalWords = 0;
		double related = 0;
		
		//create ArrayList to store all words in question 1's question and answers
		ArrayList<String> words1 = new ArrayList<>();
		Scanner read1 = new Scanner(q1.getQuestion());
		
		//add all of q1's question's words
		while(read1.hasNext()) {
			words1.add(read1.next());
		}
		
		//add all of q1's answers' words
		for(int a = 0; a < q1.answers.size(); a++) {
			read1 = new Scanner(q1.answers.get(a));
			while(read1.hasNext()) {
				words1.add(read1.next());
			}
		}
		
		Scanner read2 = new Scanner(q2.getQuestion());
		
		//compare all words in q2's question
		while(read2.hasNext()) {
			if(words1.contains(read2.next())) {
				related++;
			}
			q2TotalWords++;
		}
		
		//compare all words in q2's answers
		for(int a = 0; a < q2.answers.size(); a++) {
			read2 = new Scanner(q2.answers.get(a));
			while(read2.hasNext()) {
				if(words1.contains(read2.next())) {
					related++;
				}
				q2TotalWords++;
			}
		}
		
		read1.close();
		read2.close();
		
		q2TotalWords = (q2TotalWords + words1.size()) / 2;
		
		return (related/q2TotalWords) >= percentage;
		
	}
}
