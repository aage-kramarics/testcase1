package no.nav.ortok.jetty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class HttpClient { // Tester PUT-metoden i intlayer
	
	private final String url;

	public HttpClient(String url) {
		this.url = url;
	}

	static class Response {
		final int responseCode;
		final String normalResponse;
		final String errorResponse;
		
		public Response(int responseCode, String normalResponse, String errorResponse) {
			this.responseCode = responseCode;
			this.normalResponse = normalResponse;
			this.errorResponse = errorResponse;
		}

		@Override
		public String toString() {
			if (responseCode == 200) {
				return "OK respons: " + normalResponse;
			}
			return "Feilrespons, kode: " + responseCode + ", respons: " + errorResponse;
		}		
	}
	
	public Response putJson(String json) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		try {
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");		
			OutputStream os = conn.getOutputStream();
			os.write(json.getBytes());
			os.flush();
		
			int responseCode = conn.getResponseCode();
			String normalResponse = null;
			if (conn.getInputStream() != null) {
				normalResponse = IOUtils.toString(conn.getInputStream());
			}
			String errorResponse = null;
			if (conn.getErrorStream() != null) {
				errorResponse = IOUtils.toString(conn.getErrorStream());
			}
			conn.disconnect();
			return new Response(responseCode, normalResponse, errorResponse);
			
		} catch (IOException e) {
			try {
				int responseCode = conn.getResponseCode();
				String errorResponse = null;
				if (conn.getErrorStream() != null) {
					errorResponse = IOUtils.toString(conn.getErrorStream());
				}
				return new Response(responseCode, null, errorResponse);
				
			} catch (IOException e2) {
				throw new RuntimeException("Kan ikke hente feilresponskode og feilrespons");
			}
		}
	}
}
