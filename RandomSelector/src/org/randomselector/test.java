package org.randomselector;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
	public static void main(String[] args) throws IOException {
		Pattern p = Pattern.compile("^*.xlsx$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher("c:\\User\\kyy\\Desktop\\통합문서1.xlsx");
		boolean b = m.matches();
		System.out.println(b);
	  }
}
