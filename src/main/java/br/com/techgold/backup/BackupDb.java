package br.com.techgold.backup;

import java.io.IOException;
import java.util.Date;

public class BackupDb {
	
	public String execute() throws InterruptedException, IOException{
		
		Process p;
		ProcessBuilder pb;
		String data;
		String strRetorno = new String("SUCESSO!");
		String strSenhaBD = new String("Senha1234");
		String strNomeBD = new String("techpass_db");
		String strPastaDestinoDB = new String("/home/aas/BKP/bkp");
		String strPastaExecBK = new String("/usr/lib/postgresql/14/bin/pg_dump");
		String strIPServidor = new String("192.168.0.135");
		System.out.println("aqui");
		data = new Date().toString();
		pb = new ProcessBuilder (strPastaExecBK, "-f", "-t", "-U", "techpass_root", "-h", strIPServidor, "-f", strPastaDestinoDB+data+".backup", "-d", strNomeBD);
		pb.environment().put("PGPASSWORD", strSenhaBD);
		try {
			p = pb.start();
			p.waitFor();
			if(p.exitValue() != 0) {
				strRetorno = "BACKUP ERROR";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strRetorno;
	}
}
