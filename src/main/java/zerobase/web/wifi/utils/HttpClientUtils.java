package zerobase.web.wifi.utils;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
public class HttpClientUtils {
	public static String doGet(String url) throws IOException {
		String result = null;
		OkHttpClient httpClient = new OkHttpClient();
		Request request = new Request.Builder()
			.url(url)
			//.addHeader("custom-key", "mkyong")  // add request headers
			//.addHeader("User-Agent", "OkHttp Bot")
			.build();
		try (Response response = httpClient.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new IOException("Unexpected code " + response);}
			// Get response headers
			Headers responseHeaders = response.headers();
			for (int i = 0; i < responseHeaders.size(); i++) {
				System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));}
			// Get response body
			if (response.body() != null) {
				result = response.body().string();
				//System.out.println(response.body().string());
			}}return result;}}