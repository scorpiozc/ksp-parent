package cn.com.bjjdsy.common.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ResourceUtils {

	public static PropertiesConfiguration getConfigProperties() throws ConfigurationException {
		return new PropertiesConfiguration("config.properties");
	}

	public static PropertiesConfiguration getJDBCProperties() throws ConfigurationException {
		return new PropertiesConfiguration("jdbc.properties");
	}

	public static void main(String[] args) {

	}

}
