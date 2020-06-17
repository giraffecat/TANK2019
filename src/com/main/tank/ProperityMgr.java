package com.main.tank;

import java.io.IOException;
import java.util.Properties;

public class ProperityMgr {
	private static Properties props;
	
	static {
		try {
			props = new Properties();
			props.load(ProperityMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static String get(String key) {
		return (String)props.get(key);
	}

}
