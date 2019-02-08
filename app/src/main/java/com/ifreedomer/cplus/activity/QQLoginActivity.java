package com.ifreedomer.cplus.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ifreedomer.cplus.R;

import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class QQLoginActivity extends AppCompatActivity {

    String url = "wtloginmqq://ptlogin/qlogin?p=https%3A%2F%2Fssl.ptlogin2.qq.com%2Fjump%3Fu1%3Dhttp%253A%252F%252Fconnect.qq.com%26pt_report%3D1%26daid%3D383%26style%3D35%26pt_ua%3D1DDF5834F32D1A965F57EB5A26A6D27A%26pt_browser%3DMQQBrowser%26pt_3rd_aid%3D100270989%26pt_openlogin_data%3Dwhich%253D%2526refer_cgi%253Dauthorize%2526response_type%253Dcode%2526client_id%253D100270989%2526state%253D%2526display%253D%2526openapi%253D1010%2526switch%253D0%2526src%253D1%2526sdkv%253D%2526sdkp%253Da%2526tid%253D1549557854%2526pf%253D%2526need_pay%253D0%2526browser%253D0%2526browser_error%253D%2526serial%253D%2526token_key%253D%2526redirect_uri%253Dhttps%25253A%25252F%25252Fpassport.csdn.net%25252Faccount%25252Flogin%25253FwapAuthType%25253Dqq%252526state%25253Dtest%2526sign%253D%2526time%253D%2526status_version%253D%2526status_os%253D%2526status_machine%253D%2526page_type%253D1%2526has_auth%253D1%2526update_auth%253D1%2526auth_time%253D1549557854599";
    WebView web;
    TextView zt,pvid,key1,did,sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_login);

        web = findViewById(R.id.main_web);
        Button bt = findViewById(R.id.main_bt);
        zt = findViewById(R.id.main_zt);
        pvid = findViewById(R.id.main_pvid);
        key1 = findViewById(R.id.main_key);
        did = findViewById(R.id.main_did);
        sid = findViewById(R.id.main_sid);
        zt.setText("未登录");

        bt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View v) {
                try {
                    Intent dl = Intent.parseUri(url, Intent.URI_ANDROID_APP_SCHEME);
                    startActivity(dl);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

            }
        });

        Intent MoreInt = getIntent();
        if (MoreInt.getData() != null)
        {
            removeAllCookie();
            String data = MoreInt.getData().toString();
            // Log.d("测试",data);
            // https://ssl.ptlogin2.qq.com/jump?u1=http%3A%2F%2Fconnect.qq.com&pt_report=1&daid=383&style=35&pt_ua=1DDF5834F32D1A965F57EB5A26A6D27A&pt_browser=MQQBrowser&pt_3rd_aid=100270989&pt_openlogin_data=which%3D%26refer_cgi%3Dauthorize%26response_type%3Dcode%26client_id%3D100270989%26state%3D%26display%3D%26openapi%3D1010%26switch%3D0%26src%3D1%26sdkv%3D%26sdkp%3Da%26tid%3D1549557854%26pf%3D%26need_pay%3D0%26browser%3D0%26browser_error%3D%26serial%3D%26token_key%3D%26redirect_uri%3Dhttps%253A%252F%252Fpassport.csdn.net%252Faccount%252Flogin%253FwapAuthType%253Dqq%2526state%253Dtest%26sign%3D%26time%3D%26status_version%3D%26status_os%3D%26status_machine%3D%26page_type%3D1%26has_auth%3D1%26update_auth%3D1%26auth_time%3D1549557854599&keyindex=19&clientuin=2404316351&clientkey=2222a5ad5599fd632c518c6969f3b812b2f4dd4e9c8ddd513310d6580122612c6a20967ca6e7e50741b84487c56916c2355c3f08f156bc262769041ec8bd4cd0
            set_web(web,data);
        }

    }

    private void set_web(final WebView web, final String url)
    {
        web.loadUrl(url);
        web.getSettings().setMediaPlaybackRequiresUserGesture(false);
        // 启用WebView对JavaScript的支持
        web.getSettings().setJavaScriptEnabled(true);
        // 允许网页缩放
        web.getSettings().setSupportZoom(true);
        web.setHorizontalScrollBarEnabled(false);//水平不显示
        web.setVerticalScrollBarEnabled(false); //垂直不显示

        web.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                //web_title = view.getTitle();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {

                //打开第三方协议
                if (url.startsWith("http") || url.startsWith("https") || url.startsWith("ftp")) {
                    //不处理http, https, ftp的请求
                    return false;
                }
                else if("bilibili".equalsIgnoreCase(Uri.parse(url).getScheme()))
                {
                    return true;
                }

                return true;

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(final WebView view, WebResourceRequest request) {

                final String url = request.getUrl().toString();

                // Log.d("测试",url);

                if (url.contains("passport.csdn.net/v1/login/wap/authorization/status"))
                {
                    // Log.d("测试No",url);
                    set_data_no();
                }
                else if(url.contains("beacon.tingyun.com/xhr1?"))
                {
                    // Log.d("测试Ok",url);
                    // beacon.tingyun.com/pf?pvid=1a547943-adc3-46a9-a9ca-c2c572171002&ref=https%3A%2F%2Fwww.csdn.net%2F&referrer=https%3A%2F%2Fpassport.csdn.net%2Fsignwap&key=eTNv5SLX9JU&v=1.7.5&av=1.7.5&did=4bc8da7e-9897-41b2-9d4b-0037f27e9645&sid=f1909f20-60a2-4beb-85be-c263b2333ab0&f=2&qs=174&rs=248&re=252&os=1105&oe=1121&oi=1105&oc=2362&ls=2363&le=2367&tus=0&tue=0&cs=41&ce=172&ds=3&de=41&fp=512&sl=100&je=0&sh=640&sw=360&dr=1106&fs=1438&trflag=0000&__r=1549559635474
                    String reg = "pvid=(.*)&ref=";
                    String pvid = regular(url, reg);
                    //获取pvid

                    reg = "&key=(.*)&v";
                    String key = regular(url, reg);
                    //获取key

                    reg = "&did=(.*)&sid";
                    String did = regular(url, reg);
                    //获取did

                    reg = "&sid=(.*)&";
                    String sid = regular(url, reg);
                    //获取sid

                    set_data(pvid,key,did,sid);
                }

                return null;
            }

        });

    }

    private void set_data(final String pvid_s, final String key_s, final String did_s, final String sid_s)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        zt.setText("登录成功！");
                        pvid.setText("pvid："+pvid_s);
                        key1.setText("key："+key_s);
                        did.setText("did："+did_s);
                        sid.setText("sid："+sid_s);
                    }
                });
            }
        }.start();
    }

    private void set_data_no()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        zt.setText("登录失败，未绑定手机号！");
                    }
                });
            }
        }.start();
    }

//    private void ts(final String text)
//    {
//        new Thread()
//        {
//            @Override
//            public void run()
//            {
//                runOnUiThread(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        }.start();
//    }

    // 正则表达工具方法
    public static String regular(String datas,String regular)
    {
        // 按指定模式在字符串查找
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(regular);
        // 创建 matcher 对象
        Matcher m = r.matcher(datas);

        if (m.find())
        {
            return m.group(1);
        }
        else
        {
            return "null";
        }
    }

    // 清除所有cookie
    private void removeAllCookie()
    {
        CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(web.getContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();

        // String testcookie1 = cookieManager.getCookie(urlpath);

        cookieManager.removeAllCookie();
        cookieSyncManager.sync();

        // String testcookie2 = cookieManager.getCookie(urlpath);
    }

}
