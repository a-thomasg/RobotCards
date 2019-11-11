package Managers;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveManager {

	String file;
	String input;
	long line;
	
	public SaveManager(String file, String input, long line) {
		this.file = file;
		this.input = input;
		this.line = line;
	}
	
	public void save() {
//		System.out.println("\n");
         
//		PrintWriter pwFile = null;// write sth in a file (deletes the lines if exist)
//		try{
//			pwFile = new PrintWriter(file);
//			pwFile.println(input);// write a builtIn object
//			pwFile.write("Write something in a line. i = "+line);
//			System.out.println("Write to the file successfully");
//		}catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}catch(SecurityException e) {
//			e.printStackTrace();
//		}finally {// always close the output stream
//			if(pwFile != null){
//				pwFile.close();
//			}
//		}
         
		PrintWriter pwFile1 = null;
		Object obj = System.getProperty("line.separator");// write in a file in a newline (no deletion of previous writing)
		try {
			FileWriter fl = new FileWriter(file, true);
			BufferedWriter br = new BufferedWriter(fl);
			pwFile1 = new PrintWriter(br);
			
//			pwFile1.write("!!!Test!!!",3 ,8);// write the string beginning from the 3rd char until the 8th -- outputs "Test"
			pwFile1.write(input + "\n");// -- outputs "Test" + new line
//			pwFile1.println(obj);// -- adds another blank line
//			System.out.println("Add new lines to the file successfully");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {// for FileWriter
			e.printStackTrace();
		} finally {// no matter what happen, close the output stream
			if(pwFile1 != null) {
				pwFile1.close();
			}
		}
	}
}
