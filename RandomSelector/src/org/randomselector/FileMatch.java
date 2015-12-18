package org.randomselector;

public class FileMatch {
	public String fileMatch(String openFilePath){
		String result = null;
		
		int index = openFilePath.lastIndexOf(".");
		result = openFilePath.substring(index+1);		
		return result;
	}
}
