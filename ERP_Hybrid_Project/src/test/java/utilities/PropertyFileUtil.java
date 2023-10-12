package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {
public static Properties conpro;
public static String getValueForKey(String key) throws Throwable {
	conpro = new Properties();
	//Load the file
	conpro.load(new FileInputStream("./PropertyFile/Environment.properties"));
	return conpro.getProperty(key);
	
}

}
