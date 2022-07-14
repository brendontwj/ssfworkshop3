package com.vttp2022.ssfworkshop3;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.vttp2022.ssfworkshop3.util.IOUtil.*;

@SpringBootApplication
public class Ssfworkshop3Application {
	private static Logger logger = LoggerFactory.getLogger(Ssfworkshop3Application.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Ssfworkshop3Application.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> opsVal = appArgs.getOptionValues("dataDir");
		if(opsVal != null) {
			logger.info("data dir > ", (String) opsVal.get(0));
			createDir(opsVal.get(0));
		} else {
			logger.warn("No data directory was provided");
			System.exit(1);
		}

		app.run(args);
	}

}
