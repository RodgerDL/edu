package com.jeesite.modules.edu.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Roger
 */
public class requestUtils {

    private static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    private static String requestURL= "https://e-all.webex.com.cn/WBXService/XMLService";

    // 创建会议Request Body
    private static String createMeetingMsg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><serv:message xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><header><securityContext><siteName>e-all</siteName><webExID>webexID</webExID><password>webexPwd</password></securityContext></header><body><bodyContent xsi:type=\"java:com.webex.service.binding.meeting.CreateMeeting\"><accessControl><meetingPassword>meetingPwd</meetingPassword></accessControl><metaData><confName>meetingTitle</confName><agenda></agenda></metaData><schedule><startDate>planDate</startDate><duration>60</duration></schedule></bodyContent></body></serv:message>";

    // 获得主持人开会地址Request Body
    private static String gethosturlMeetingMsg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><serv:message xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><header><securityContext><siteName>e-all</siteName><webExID>webexID</webExID><password>webexPwd</password></securityContext></header><body><bodyContent xsi:type=\"java:com.webex.service.binding.meeting.GethosturlMeeting\"><meetingKey>meetingId</meetingKey></bodyContent></body></serv:message>";

    // 获得加会地址Request Body
    private static String getjoinurlMeetingMsg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><serv:message xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><header><securityContext><siteName>e-all</siteName><webExID>webexID</webExID><password>webexPwd</password></securityContext></header><body><bodyContent xsi:type=\"java:com.webex.service.binding.meeting.GetjoinurlMeeting\"><meetingKey>meetingId</meetingKey></bodyContent></body></serv:message>";

    /**
     * 创建会议
     * @param webexID 账户用户
     * @param webexPwd 账户密码
     * @param meetingPwd 会议密码
     * @param meetingTitle 会议标题
     * @param planDate 计划开始时间
     * @param duration 会议时长
     * @return meetingId 会议Key
     */
    public static String creatMeeting (String webexID, String webexPwd, String meetingPwd, String meetingTitle, String planDate, String duration) throws IOException, DocumentException {

        String msg = createMeetingMsg.replace("webexID", webexID).replace("webexPwd", webexPwd).replace("meetingPwd", meetingPwd).replace("meetingTitle", meetingTitle).replace("planDate", planDate);
        String res = postMessage(requestURL, msg);

        Document doc = DocumentHelper.parseText(res);
        Element root = doc.getRootElement();
        res = root.element("body").element("bodyContent").elementText("meetingkey");

        return res;
    }

    /**
     * 获得主持人开会地址
     * @param webexID 账户用户
     * @param webexPwd 账户密码
     * @param meetingId 会议Key
     * @return hostURL 主持人开会URL
     */
    public static String gethosturlMeeting (String webexID, String webexPwd, String meetingId) throws IOException, DocumentException {

        String msg = gethosturlMeetingMsg.replace("webexID", webexID).replace("webexPwd", webexPwd).replace("meetingId", meetingId);
        String res = postMessage(requestURL, msg);

        Document doc = DocumentHelper.parseText(res);
        Element root = doc.getRootElement();
        res = root.element("body").element("bodyContent").elementText("hostMeetingURL");

        return res;
    }

    /**
     * 获得加会地址
     * @param webexID 账户用户
     * @param webexPwd 账户密码
     * @param meetingId 会议Key
     * @return hostURL 主持人URL
     */
    public static String getjoinurlMeeting (String webexID, String webexPwd, String meetingId) throws IOException, DocumentException {

        String msg = getjoinurlMeetingMsg.replace("webexID", webexID).replace("webexPwd", webexPwd).replace("meetingId", meetingId);
        String res = postMessage(requestURL, msg);

        Document doc = DocumentHelper.parseText(res);
        Element root = doc.getRootElement();
        res = root.element("body").element("bodyContent").elementText("joinMeetingURL");

        return res;
    }

    /**
     * 发送请求
     * @param urlStr 请求URL
     * @param msg 请求Body
     * @return res
     */
    private static String postMessage(String urlStr, String msg) throws IOException {
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
            System.out.println(getjoinurlMeeting("eall_webex_account1", "P@ss12345678", "601553473"));
        } catch (IOException ex) {
            // process the exception
        } catch (DocumentException dx) {

        }
    }

}