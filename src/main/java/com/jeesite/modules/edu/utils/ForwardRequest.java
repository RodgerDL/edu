package com.jeesite.modules.edu.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Roger
 */
public class ForwardRequest {

    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    public static String postMessage(String urlStr) throws IOException {
        final StringBuffer content = new StringBuffer();

        final URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", userAgent);
        conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setConnectTimeout(5000);

        // Send post request
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        final String msg = "<siteName>e-all</siteName><webExID>eall_webex_account1</webExID><password>P@ss12345678</password></securityContext></header><body><bodyContent xsi:type=\"java:com.webex.service.binding.meeting.GethosturlMeeting\"><meetingKey>448248575</meetingKey></bodyContent></body></serv:message>";
        wr.writeBytes(msg);
        // send request
        wr.flush();
        // close
        wr.close();

        // read response
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String str;
        while ((str = in.readLine()) != null) {
            content.append(str);
        }
        in.close();

        return content.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(postMessage("https://e-all.webex.com.cn/WBXService/xml8.0.0/XMLService"));
        } catch(IOException ex) {
            // process the exception
        }
    }

}