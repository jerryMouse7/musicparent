package com.liu.passport.provider;

import com.alibaba.fastjson.JSON;
import com.liu.passport.dto.AccessTokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public String getGithubAccessToken(AccessTokenDTO accessTokenDTO) throws IOException {
        String json = com.alibaba.fastjson.JSON.toJSONString(accessTokenDTO);
        return post("https://github.com/login/oauth/access_token",json);
    }

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
