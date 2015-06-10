package helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SMSUtil {
	private static final String addr = "http://api.sms.cn/mt/";
	private static final String userId = "55443";
	private static final String pwd = "bcfcd8e4100c47fcd1a90195360461df";
	private static final String encode = "utf8";
	
	public static String send(String phoneNo, String content) throws Exception {
		content = java.net.URLEncoder.encode(content, "UTF-8");
		// 组建请求
		String straddr = addr + "?uid=" + userId + "&pwd=" + pwd + "&mobile="
				+ phoneNo + "&encode=" + encode + "&content=" + content;

		System.out.println(straddr);
		StringBuffer sb = new StringBuffer(straddr);

		// 发送请求
		URL url = new URL(sb.toString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));

		// 返回结果
		String inputline = in.readLine();
		System.out.println("Response:" + inputline);

		return inputline;

	}
}
