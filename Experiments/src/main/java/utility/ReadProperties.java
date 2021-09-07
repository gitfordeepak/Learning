package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	static Properties prop;
	static File file;
	static FileInputStream input;
	
	public String readProp(String key) throws IOException {
		
		prop = new Properties();
		file = new File("C:\\Users\\deepa\\git\\Learning\\Experiments\\src\\main\\resources\\ConfigFiles\\Properties.properties");
		//file = new File(".\\Experiments\\src\\main\\resources\\ConfigFiles\\Properties.properties");
		
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop.load(input);
		String propValue = prop.getProperty(key);
		return propValue;	
		
	}

}
