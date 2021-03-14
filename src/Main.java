import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gets the names of the files for the tests from the test folder.
 * Checks each file name the to see if it is the correct file type.
 * Adds all compatible files to an ArrayList.
 * Creates an array of Test objects the size of the number of compatible files.
 * Each Test is filled with the questions from a file.
 	* Assuming the test has a header, skip everything until we find question 1.
 	* We will skip anything that is not a question (e.g. multiple choice answers)
 	* the punctuation is removed from the question
 * Ask the user for a percentage.
 * The Test array and percentage is sent to CompareTests.
 * @author BURKEJR19
 *
 */
public class Main {

	public static void main(String[] args) throws FileNotFoundException {
	
		Question q1_1 = new Question("one two three four five six Seven EIGHT nine ten");
		Question q1_2 = new Question("one two three four five uh uh some some");
		Question q1_3 = new Question("one two three four five six thing some");
		
		Question q2_1 = new Question("one two three four five six seven some some");
		Question q2_2 = new Question("one two three four five k thing that");
		Question q2_3 = new Question("");


		Test t1 = new Test("Test1");
		t1.addQuestion(q1_1);
		t1.addQuestion(q1_2);
		t1.addQuestion(q1_3);
		
		Test t2 = new Test("Test2");
		t2.addQuestion(q2_1);
		t2.addQuestion(q2_2);
		t2.addQuestion(q2_3);
		
		Test[] tests = new Test[2];
		tests[0] = t1;
		tests[1] = t2;
		
		CompareTests ct = new CompareTests(tests);
		
//		System.out.println(ct.compareTests(t1, t2));
//		System.out.println(ct.compareTests(t2, t1));
//		
//		ct.run();
		
		/*
		FileInputStream fis = new FileInputStream("qs.txt");
		Scanner in = new Scanner(fis);
		
		FileOutputStream fos = new FileOutputStream("result.csv");
		PrintWriter pw = new PrintWriter(fos);
		
		ArrayList<String> questions = new ArrayList<>();
		while(in.hasNextLine()) {
			questions.add(in.nextLine());
		}
		
		for(int i = 0; i < questions.size(); i++) {
			pw.print(questions.get(i) + "\n");
		}
		pw.print("one ");
		pw.print("two, three");
		pw.print(",four; five, six; seven");
		
		pw.flush();
		pw.close();
		in.close();
		
		*/
		
	}
	
	
}
