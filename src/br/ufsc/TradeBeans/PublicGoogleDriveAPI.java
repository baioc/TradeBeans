package br.ufsc.TradeBeans;

import java.io.BufferedReader;
import java.io.IOException;


/**
 * Connector for publicly-shared Google Drive files.
 */
public class PublicGoogleDriveAPI extends API {

	public PublicGoogleDriveAPI() {
		super(""); // @todo base URL
	}


	// @todo docs
	public BufferedReader get(final String publicFileID) throws IOException {
		// @todo
		return null;
	}

}
