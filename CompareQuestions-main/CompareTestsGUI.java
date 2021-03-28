//package CompareQuestions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

/**
 * Gets the names of the files for the tests from the test folder.
 * Checks each file name the to see if it is the correct file type.
 * Adds all compatible files to an ArrayList.
 * Shows the user the compatible files it found and asks if they want to remove any.
 * Creates an array of Test objects the size of the number of compatible files.
 * Each Test is filled with the questions from a file.
 	* Assuming the test has a header, skip everything until we find question 1.
 	* We will skip anything that is not a question (e.g. multiple choice answers)
 	* the punctuation is removed from the question
 * Ask the user for a percentage.
 * The Test array and percentage is sent to CompareTests.
 * CompareTests returns a String of the results that can be directly printed into a csv file.
 * @author BURKEJR19
 *
 */
public class CompareTestsGUI extends JFrame implements ActionListener{

	/**
	 * default ID
	 */
	private static final long serialVersionUID = 1L;

	private static ArrayList<File> files;
	
	private JComboBox<String> fileList;
	private JButton remove, start;
	private JLabel output;
	
	public CompareTestsGUI() {
		//setup
		super("Compare Tests");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FlowLayout flow = new FlowLayout();
		
		//displays all of the files in a combo box and allows the user to choose a specific file
		fileList = new JComboBox<>();
		for(int i = 0; i < files.size(); i++) {
			fileList.addItem(files.get(i).getName());
		}
		
		//removes the currently selected file in the combo box from the list
		remove = new JButton("Remove");
		remove.addActionListener(this);
		
		//runs the program
		start = new JButton("Start");
		start.addActionListener(this);
		
		//label that begins as empty and says done when the tests have been compared
		output = new JLabel("");
		
		//set layout
		setLayout(flow);
		
		//add stuff
		add(fileList);
		add(remove);
		add(start);
		add(output);
		
		//make everything visible 
		setVisible(true);
	}
	
	@Override
	/**
	 * Gets the actions performed by the user in the GUI and performs functions based on those actions.
	 */
	public void actionPerformed(ActionEvent e) {
		//removes a file from the files ArrayList and the fileList ComboBox
		if(e.getSource() == remove) {
			String name = (String)fileList.getSelectedItem();
			//remove from ComboBox
			fileList.removeItem(name);
			
			//find and remove file from list
			for(int i = 0; i < files.size(); i++) {
				if(files.get(i).getName().equals(name)) {
					files.remove(i);
				}
			}
		}
		//starts the program
		else if(e.getSource() == start) {
			try {
				this.run();
				//no problems, tell the user we are done
				output.setText("All done. The results are in a file name \"results.csv\".");
			}catch (FileNotFoundException ex) {
				//something wrong with the files
				output.setText("Problem with a file: " + ex.getMessage());
			}catch (Exception ex) {
				//something bad happened
				output.setText("Unexpected Error: " + ex.getMessage());
			}
			
		}
	}

	/**
	 * Calls MakeTests() to create an array of the Tests.
	 * Creates a CompareTests() object and sends it the array.
	 * CompareTests() compares the tests and returns the results in a String.
	 * The String is formatted to be directly output to a csv file.
	 * The String is output to the output file.
	 * @throws FileNotFoundException
	 */
	private void run() throws FileNotFoundException {
		//make the tests
		Test[] tests = makeTests();
		//send the tests to CompareTests
		CompareTests ct = new CompareTests(tests);

		//run the comparison and store the result
		String results = ct.run();
		
		//make the output file
		File out = new File("results.csv");
		
		//print the result
		PrintWriter pw = new PrintWriter(out);
		pw.print(results);
		
		//close
		pw.flush();
		pw.close();
	}

	/**
	 * Creates an array of Test objects and returns it. 
	 * The array is filled with Tests that contain the questions from each test file.
	 * @return
	 * @throws FileNotFoundException
	 */
	private Test[] makeTests() throws FileNotFoundException {
		Test[] tests = new Test[files.size()];

		for(int i = 0; i < tests.length; i++) {
			Test currentT = new Test(files.get(i).getName());
			Scanner in = new Scanner(files.get(i));
			
			//this is the current question we are looking to add
			int currentQ = 1;
			in.useDelimiter(currentQ + "\\.");
			
			//get everything before the first question
			if(in.hasNext()) {	//checks if the file is empty
				in.next();
			}
			
			//update delimiter
			currentQ++;
			in.useDelimiter(currentQ + "\\.");
			
			//go through every line of the file
			while(in.hasNextLine()) {
				//get the question and put it in a scanner
				String s = in.next();
				Scanner read = new Scanner(s);

				//remove the number at the start
				read.next();
				
				//set delimiter to "A." to get the question before the first answer
				char curChar = 'A';
				read.useDelimiter(curChar++ + "\\.");
				
				//get the question
				Question q = new Question(read.next());
				
				//get the answers by incrementing the delimiter by letter
				read.useDelimiter(curChar++ + "\\.");
				
				while(read.hasNext()) {
					//puts the next answer into a string
					String a = read.next();
					
					//removes the first 2 chars from the beginning of the string which is the identifier
					//e.g. "A." or "B."
					q.addAnswer(a.substring(2));
					
					//updates delimiter
					read.useDelimiter(curChar++ + "\\.");

				}
				
				//add question to test
				currentT.addQuestion(q);
				
				
				//update delimiter
				currentQ++;
				in.useDelimiter(currentQ + "\\.");

				//close our scanners
				read.close();
			}
			
			//add test to the array
			tests[i] = currentT;
			
			//close scanner
			in.close();
		}

		return tests;
	}
	
	private void toCSV(Test[] tests) {
		//TODO: make method that converts each test into its own csv file
		//add a button that lets u turn this on or off
		//call this method in run() if the button sets this to be true
	}
	
	/**
	 * Gets the list of files from the folder. 
	 * Only adds files to the list if they are compatible.
	 * Starts the GUI.
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
	
		//contains all of the compatible files
		files = new ArrayList<>();
		
		//the program will have a folder in the same directory as it called "Files"
		//the test files will be retrieved from this folder
		File folder = new File("./Tests");
		
		//get all of the files in the folder and check if they are .txt
		//if they are .txt, then add the file to the files ArrayList
		for(File entry: folder.listFiles()) {
			String name = entry.getName();
			int i = name.lastIndexOf('.');
			if(i > 0) {
				if(name.substring(i + 1).equals("txt")) {
					files.add(entry);
				}
			}
			
		}
		
		//sort the files into alphabetical order
		Collections.sort(files);
		
		//starts the GUI
		new CompareTestsGUI();
		
	}
	
}
