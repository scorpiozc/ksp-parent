package cn.com.bjjdsy.data.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractReadDataFile {

	static final Logger logger = LoggerFactory.getLogger(AbstractReadDataFile.class);

	public void read(String filename) {
		int linenum = 0;
		File dataFile = new File(this.getClass().getResource(filename).getFile());
		try (BufferedReader reader = new BufferedReader(new FileReader(dataFile));) {
			reader.readLine();
//			reader.readLine();
			while (true) {
				String line = reader.readLine();
				if (line == null || line.length() == 0) {
					break;
				}
				// parse the data
//				logger.info("linenum {}", ++linenum);
				String[] data = line.split(",");
				this.parseData(data);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public abstract void parseData(String[] data);
}
