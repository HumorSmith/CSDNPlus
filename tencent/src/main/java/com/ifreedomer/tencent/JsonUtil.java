package com.ifreedomer.tencent;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
    public static TencentUserInfo parseUserInfo(JSONObject jsonObject) {
        TencentUserInfo tencentUserInfo = new TencentUserInfo();
        try {
            String nickname = jsonObject.getString("nickname");
            tencentUserInfo.setNickName(nickname);
            tencentUserInfo.setAvatarUrl(jsonObject.getString("figureurl_qq_2"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tencentUserInfo;
    }
}
