package charAlgorithm.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 文件工具类
 * @author Kajelas
 *
 */
public class FileUtil {
	/**
	 * 读取配置文件
	 * @param url 配置文件相对路径
	 * @return Properties 读取到的properties
	 * @throws IOException
	 */
	public static Properties readProps(String url) throws IOException {
		Properties props = new Properties();
		InputStream iStream = FileUtil.class.getResourceAsStream(url);
		props.load(iStream);
		return props;
	}
	
	/**
	 * 读取文件内容
	 * @param url 文件绝对路径
	 * @return String 读取的内容
	 * @throws IOException
	 */
	public static String readContentFromFile(String url) throws IOException {
		File file = new File(url);
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.err.println("输入文件"+url+"未找到");
			throw e;
		}  
        String tempString = null;
        String result = "";
        while ((tempString = reader.readLine()) != null) {
        	result += tempString;
        }  
        reader.close();
        return result;
	}
	
	/**
	 * 输出content到文件
	 * @param url  文件绝对路径
	 * @param content  文件内容
	 * @throws IOException
	 */
	public static void writeContentToFile(String url,String content) throws IOException {
		File file = new File(url);

	      if(!file.exists()){
	    	  try {
	    		  file.createNewFile();
	    	  } catch (IOException e) {
	    		  new File(file.getParent()).mkdirs();
	    		  file.createNewFile();
	    	  }
	      }

	      FileWriter fileWritter = new FileWriter(file);
	      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	      bufferWritter.write(content);
	      bufferWritter.close();
	      fileWritter.close();
	}
}
