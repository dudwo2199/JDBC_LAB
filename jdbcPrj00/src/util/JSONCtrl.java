package util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONCtrl {
	private static final String storagePath = "res";
	private static final String ext = ".json";
	
	public static JSONObject load(String fileName) {
		try {
			File filePath = new File(String.format("%s/%s%s", storagePath, fileName, ext));
			File rootPath = filePath.getAbsoluteFile();    
	        
	        JSONParser parser = new JSONParser();
	        
	        Reader reader = new FileReader(rootPath);
	        JSONObject jsonObject = (JSONObject) parser.parse(reader);
	        
	        return jsonObject;
		}catch(IOException ioException) {
			System.err.println(ioException.getMessage());
		}catch(ParseException parseException) {
			System.err.println(parseException.getMessage());
		}
		
		return null;
	}
	
	public static void save(String fileName, JSONObject obj) {
		try {
			File filePath = new File(String.format("%s/%s%s", storagePath, fileName, ext));
			File rootPath = filePath.getAbsoluteFile();   
			
			FileWriter fw = new FileWriter(rootPath);
			
			fw.write(obj.toJSONString());
			fw.flush();
			fw.close();
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static String get(JSONObject obj, String key) {
		return (String)obj.get(key);
	}
	
	public static int getINT(JSONObject obj, String key) {
		return (int)(long)obj.get(key);
	}
	
	public static long getLONG(JSONObject obj, String key) {
		return (long)obj.get(key);
	}
	
	public static float getFLOAT(JSONObject obj, String key) {
		return (float)(double)obj.get(key);
	}
	
	public static Date getDATE(JSONObject obj, String key) {
		try {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date result = transFormat.parse((String)obj.get(key));
			
			return result;
		}catch(java.text.ParseException e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}
}