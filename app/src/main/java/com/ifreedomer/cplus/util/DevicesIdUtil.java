package com.ifreedomer.cplus.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.ifreedomer.cplus.activity.CPApplication;
import com.ifreedomer.cplus.constant.SocializeConstants;
import com.ifreedomer.cplus.constant.SocializeProtocolConstants;

import java.util.UUID;

import androidx.core.content.ContextCompat;

public class DevicesIdUtil {
    public static String getDeviceIdV2(Context context) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == 0) {
            return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        }
        return null;
    }

    public static String getDeviceId(Context context) {
        StringBuilder deviceId = new StringBuilder();
        deviceId.append("a");
        try {
            if (ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == 0) {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                String imei = tm.getDeviceId();
                if (TextUtils.isEmpty(imei)) {
                    String sn = tm.getSimSerialNumber();
                    if (!TextUtils.isEmpty(sn)) {
                        deviceId.append(SocializeProtocolConstants.PROTOCOL_KEY_SHARE_NUM);
                        deviceId.append(sn);
                        return deviceId.toString();
                    }
                }
                deviceId.append(SocializeProtocolConstants.PROTOCOL_KEY_IMEI);
                deviceId.append(imei);
                return deviceId.toString();
            }
            String uuid = getUUID();
            if (!TextUtils.isEmpty(uuid)) {
                deviceId.append("id");
                deviceId.append(uuid);
                return deviceId.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            deviceId.append("id").append(getUUID());
        }
        return deviceId.toString();
    }

    private static String getUUID() {
        String uuid = (String) SPUtil.get(CPApplication.INSTANCE, "uuid", "");
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            SPUtil.put(CPApplication.INSTANCE, "uuid", uuid);
        }

        return uuid.replace(SocializeConstants.OP_DIVIDER_MINUS, "");
    }
}