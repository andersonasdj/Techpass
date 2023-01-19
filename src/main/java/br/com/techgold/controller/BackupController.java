package br.com.techgold.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techgold.backup.BackupDb;

@RestController
@RequestMapping("backup")
public class BackupController {

	@GetMapping("executar")
	public String gerar() throws InterruptedException, IOException {
		BackupDb bkp = new BackupDb();
		return bkp.execute();
	}
	
}
