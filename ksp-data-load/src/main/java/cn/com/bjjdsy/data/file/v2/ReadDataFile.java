package cn.com.bjjdsy.data.file.v2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public abstract class ReadDataFile {

	static final Logger logger = LoggerFactory.getLogger(ReadDataFile.class);

	public static List<String[]> read(String filename) throws IOException {
		List<String[]> list = Lists.newArrayList();
		int linenum = 0;
		File dataFile = new File(filename);
		try (BufferedReader reader = new BufferedReader(new FileReader(dataFile));) {
//			reader.readLine();
//			reader.readLine();
			while (true) {
				String line = reader.readLine();
				if (line == null || line.length() == 0) {
					break;
				}
				// parse the data
//				logger.info("linenum {}", ++linenum);
				String[] data = line.split(",");
				list.add(data);
			}
		}
		return list;
//		catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
	}

	public abstract void parseData(String[] data);
}
