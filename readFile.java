package hw4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class readFile {
	public ArrayList<String> read(String fileName) {
		ArrayList<String> details = new ArrayList<String>();
		try {
			// new file to read
			File file = new File(fileName);
			// read the file 
			FileReader fr = new FileReader(file);
			// creates buffering character input stream
			BufferedReader br = new BufferedReader(fr);
			String line;
			// while there is something to read, add what is read into an array of strings
			while((line=br.readLine())!=null) {
				details.add(line);
			}
			fr.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return details;
	}
}
   