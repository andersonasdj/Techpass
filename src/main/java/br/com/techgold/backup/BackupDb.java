package br.com.techgold.backup;

import java.io.IOException;
import java.util.Date;

import br.com.techgold.model.Banco;

public class BackupDb {
	
	public String execute(Banco banco) throws InterruptedException, IOException{
		
		Process p;
		ProcessBuilder pb;
		String data;
		data = new Date().toString();
		String strRetorno = new String("Backup realizado com sucesso em - "+ data +" !");

		/*
		String strUsuarioDB = "techpass_root";
		String strSenhaBD = new String("Senha1234");
		String strNomeBD = new String("techpass_db");
		String strPastaDestinoDB = new String("/home/aas/BKP/bkp");
		String strPastaExecBK = new String("/usr/lib/postgresql/14/bin/pg_dump");
		String strIPServidor = new String("192.168.0.135");
		*/

		pb = new ProcessBuilder (banco.getCaminho(), "-f", "-t", "-U", banco.getUsuario(), "-h", banco.getIp(), "-f", banco.getLocalsave()+data+".backup", "-d", banco.getNome());
		pb.environment().put("PGPASSWORD", banco.getSenha());
		try {
			p = pb.start();
			p.waitFor();
			if(p.exitValue() != 0) {
				strRetorno = "BACKUP ERROR!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strRetorno;
	}
}
