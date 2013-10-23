package lv.lu.newsfeed.impl;

import java.io.InputStream;
import java.util.Map;

import lv.lu.newsfeed.interfaces.HTTPClient;

/**
 * TODO [task] Your task is to implement this HTTP client
 */
public class ApacheHTTPClient implements HTTPClient {

	private Map<String, String> configurationSettings;	// proxy configuration settings
	
	@Override
	public InputStream download(String url){
		throw new RuntimeException("Implement me");
	}

	public Map<String, String> getConfigurationSettings() {
		return configurationSettings;
	}

	public void setConfigurationSettings(Map<String, String> configurationSettings) {
		this.configurationSettings = configurationSettings;
	}
}
