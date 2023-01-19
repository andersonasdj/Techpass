package br.com.techgold.backup;

import java.io.IOException;
import java.util.Scanner;

public class BackupDB2 {
	
	public void executa() throws IOException {
		
		Scanner s = new Scanner(Runtime.getRuntime().exec("//usr//lib//postgresql//14//bin//pg_dump -i -h 127.0.0.1 -p 5432 -U postgres -F c -b -v -f //home//aas//BKP//bkp.backup").getInputStream());
		
		System.out.println(s);
	}

}
