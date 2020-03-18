package com.liu.admin.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 钉钉机器人发送消息
 */
public class DingDingMessageUtil {

    public static final String DINGDING_URL ="https://oapi.dingtalk.com/robot/send?access_token=2f1dfe347699c4c31dd41a53bf59beffd6b374587b592d3571fffa21a6f0ba1e";

    public static void sendTextMessage(String msg){

        try {

            Message message = new Message();
            message.setMsgtype("text");
            message.setText(new MessgaeInfo(msg));
            URL url = new URL(DINGDING_URL);
            //建立http连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("charset","UTF-8");
            connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            connection.connect();
            OutputStream out = connection.getOutputStream();
            String textMessage = JsonUtil.toJson(message);
            byte[] data = textMessage.getBytes();
            out.write(data);
            out.flush();
            out.close();
            System.out.println(connection.getResponseCode());
            InputStream in = connection.getInputStream();
            byte[] data1 = new byte[in.available()];
            in.read(data1);
            System.out.println(new String(data1));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        DingDingMessageUtil.sendTextMessage("admin");
    }


}

@Data
class Message{
    private String msgtype;
    private MessgaeInfo text;
}

@Data
@AllArgsConstructor
class MessgaeInfo{
    private String content;
}
