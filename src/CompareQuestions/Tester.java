package CompareQuestions;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException {

		Question q = new Question("some. punc, ua'tion");
		System.out.println(q.getQuestion());

			/*
		Question q1_1 = new Question(" one two three four five six Seven EIGHT nine ten");
		Question q1_2 = new Question("one two three four five uh uh some some");
		Question q1_3 = new Question("one two three four five six thing some");

		Question q2_1 = new Question("one two three four five six seven some some");
		Question q2_2 = new Question("one two three four five k thing that");


		Test t1 = new Test("Test1");
		t1.addQuestion(q1_1);
		t1.addQuestion(q1_2);
		t1.addQuestion(q1_3);

		Test t2 = new Test("Test2");
		t2.addQuestion(q2_1);
		t2.addQuestion(q2_2);

		Test[] tests = new Test[2];
		tests[0] = t1;
		tests[1] = t2;

		CompareTests ct = new CompareTests(tests);

		System.out.println(ct.run());
			 */
		}

	}
