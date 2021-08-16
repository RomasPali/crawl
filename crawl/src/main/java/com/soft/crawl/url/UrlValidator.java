package com.soft.crawl.url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.stereotype.Component;

/**
* Validator, contains helper methods for url
* 
* @author Romas
* 
*/
@Component
public class UrlValidator {

	public String getLinkTextURL(String url, String linkText) throws IOException {

		if (isValidURL(linkText)) {
			return linkText;
		}

		if (linkText.startsWith("#")) {
			return null;
		}

		URL seedURL = new URL(url);
		return String.format("%s://%s%s", seedURL.getProtocol(), seedURL.getAuthority(), linkText);
	}

	private boolean isValidURL(String url) {

		try {
			new URL(url).toURI();
		} catch (MalformedURLException | URISyntaxException e) {
			return false;
		}

		return true;
	}
}
