package com.ifreedomer.cplus.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.activity.MainActivity;
import com.ifreedomer.cplus.activity.common.WebViewActivity;
import com.ifreedomer.cplus.activity.forgetpassword.ForgetPasswordGetCodeActivity;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.LoginAppV1TokenResp;
import com.ifreedomer.cplus.http.protocol.resp.V2ProfileResp;
import com.ifreedomer.cplus.util.LogUtil;
import com.ifreedomer.cplus.util.SPUtil;
import com.ifreedomer.cplus.util.WidgetUtil;
import com.ifreedomer.tencent.TencentLoginResult;
import com.ifreedomer.tencent.TencentQQ;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static com.ifreedomer.cplus.ui.login.LoginViewModel.ACCOUNT_KEY;
import static com.ifreedomer.cplus.ui.login.LoginViewModel.LOGINED;
import static com.ifreedomer.cplus.ui.login.LoginViewModel.PASSWORD_KEY;


public class LoginFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = LoginFragment.class.getSimpleName();
    @BindView(R.id.accountEt)
    EditText accountEt;
    @BindView(R.id.relayout_account)
    RelativeLayout relayoutAccount;
    @BindView(R.id.passwordTv)
    TextView passwordTv;
    @BindView(R.id.passwordEt)
    EditText passwordEt;
    @BindView(R.id.passwordRelayout)
    RelativeLayout passwordRelayout;
    @BindView(R.id.registerTv)
    TextView registerTv;
    @BindView(R.id.forgetPasswordTv)
    TextView forgetPasswordTv;
    @BindView(R.id.qqIv)
    ImageView qqIv;
    @BindView(R.id.sinaIv)
    ImageView sinaIv;
    @BindView(R.id.wechatIv)
    ImageView wechatIv;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.loadingview)
    ProgressBar loadingview;

    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, view);
        initList();
        return view;
    }

    private void initList() {
        loginBtn.setOnClickListener(this);
        registerTv.setOnClickListener(this);
        forgetPasswordTv.setOnClickListener(this);
        wechatIv.setOnClickListener(this);
        qqIv.setOnClickListener(this);
        passwordEt.addTextChangedListener(mTextWatcher);
        accountEt.addTextChangedListener(mTextWatcher);
        loginBtn.setEnabled(!TextUtils.isEmpty(accountEt.getText().toString()) && !TextUtils.isEmpty(passwordEt.getText().toString()));

    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            loginBtn.setEnabled(!TextUtils.isEmpty(accountEt.getText().toString()) && !TextUtils.isEmpty(passwordEt.getText().toString()));
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        if (mViewModel.canFastLogin(getContext())) {
            String userName = (String) SPUtil.get(getActivity(), ACCOUNT_KEY, "");
            String password = (String) SPUtil.get(getActivity(), PASSWORD_KEY, "");
            accountEt.setText(userName);
            passwordEt.setText(password);
            login();
        }
    }


    private TencentQQ.LoginCallback mQQLoginCallback = new TencentQQ.LoginCallback() {
        @Override
        public void onComplete(TencentLoginResult tecentLoginResult) {
            Log.d(TAG, "onComplete tecentLoginResult = " + tecentLoginResult.toString());
        }

        @Override
        public void onError(int errorCode, String errorMsg) {
            Log.d(TAG, "onError  errorCode = " + errorCode + "   errorMsg = " + errorMsg);
        }

        @Override
        public void onCancel() {
            Log.d(TAG, "onCancel");
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.wechatIv:
//
////                String url = "https://graph.qq.com/oauth2.0/show?which=Login&display=pc&response_type=code&client_id=100270989&redirect_uri=https%3A%2F%2Fpassport.csdn.net%2Faccount%2Flogin%3Foauth_provider%3DQQProvider&state=test"
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra(WebViewActivity.URL, "https://passport.csdn.net/account/login");
                intent.putExtra(WebViewActivity.SHOW_TITLE, false);
                intent.putExtra(WebViewActivity.TITLE_KEY, getString(R.string.thirdlogin));
                startActivity(intent);
//
////                https://passport.csdn.net/account/login
//
////                intent.put
////                TencentManager.getInstance().login(getActivity(), "1106956819", mQQLoginCallback);
//                Intent intent = new Intent(getActivity(), WechatLoginActivity.class);
                break;
            case R.id.loginBtn:
                if (getActivity() == null) {
                    return;
                }
                if (TextUtils.isEmpty(accountEt.getText().toString())) {
                    WidgetUtil.showSnackBar(getActivity(), getString(R.string.account_cannot_null));
                    return;
                }
                if (TextUtils.isEmpty(passwordEt.getText().toString())) {
                    WidgetUtil.showSnackBar(getActivity(), getString(R.string.password_cannot_null));
                    return;
                }
                login();
                break;
            case R.id.registerTv:
                intent = new Intent(getActivity(), WebViewActivity.class);
                Uri uri = Uri.parse("https://passport.csdn.net/account/register");
                intent.putExtra(WebViewActivity.SHOW_TITLE, false);
                intent.putExtra(WebViewActivity.TITLE_KEY, getString(R.string.thirdlogin));
//                startActivity(intent);
                break;
            case R.id.forgetPasswordTv:

                intent = new Intent(getActivity(), ForgetPasswordGetCodeActivity.class);
                startActivity(intent);


//                intent = new Intent(getActivity(), WebViewActivity.class);
//                intent.putExtra(WebViewActivity.TITLE_KEY, getString(R.string.forget_password));
//                intent.putExtra(WebViewActivity.SHOW_TITLE, false);
//                intent.putExtra(WebViewActivity.URL, "https://passport.csdn.net/passport_fe/forget.html");
                break;
        }
    }

    private void login() {

        loadingview.setVisibility(View.VISIBLE);
        WidgetUtil.showSnackBar(getActivity(), getString(R.string.loging));
        Observable<PayLoad<LoginAppV1TokenResp>> loginAppV1Observer = mViewModel.loginAppV1(accountEt.getText().toString(), passwordEt.getText().toString());
        loginAppV1Observer.subscribe(loginAppV1TokenRespPayLoad -> {
            if (loginAppV1TokenRespPayLoad.getCode() == PayLoad.APP_SUCCESS) {
                String token = loginAppV1TokenRespPayLoad.getData().getToken();
                mViewModel.saveAappV1Token(token);
                getUserToken();

            } else {
                loadingview.setVisibility(View.GONE);
                WidgetUtil.showSnackBar(getActivity(), loginAppV1TokenRespPayLoad.getMessage());
            }
        }, throwable -> {
            loadingview.setVisibility(View.GONE);
            WidgetUtil.showSnackBar(getActivity(), throwable.getMessage());

        });
    }

    private void getUserToken() {
        Disposable subscribe = mViewModel.getUserToken().subscribe(getUserTokenRespPayLoad -> {
            if (getUserTokenRespPayLoad.getCode() == PayLoad.APP_SUCCESS) {
                WidgetUtil.showSnackBar(getActivity(), getString(R.string.get_profile));
                getUserProfile(getUserTokenRespPayLoad.getData().getUserName());
            } else {
                loadingview.setVisibility(View.GONE);
                WidgetUtil.showSnackBar(getActivity(), getUserTokenRespPayLoad.getMessage());
            }
        }, throwable -> {
            loadingview.setVisibility(View.GONE);
            WidgetUtil.showSnackBar(getActivity(), throwable.getMessage());
        });
    }


    public void getUserProfile(String userName) {
        Observable<PayLoad<V2ProfileResp>> v2ProfileObservable = HttpManager.getInstance().getV2Profile(userName);
        Disposable subscribe = v2ProfileObservable.subscribe(v2ProfileRespPayLoad -> {
            if (v2ProfileRespPayLoad.getCode() == PayLoad.SUCCESS) {

                SPUtil.put(getActivity(), LOGINED, true);
                loadingview.setVisibility(View.GONE);

                SPUtil.put(getActivity(), ACCOUNT_KEY, accountEt.getText().toString());
                SPUtil.put(getActivity(), PASSWORD_KEY, passwordEt.getText().toString());
                mViewModel.saveLoginInfo(getContext(), v2ProfileRespPayLoad, userName);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                if (getActivity() != null) {
                    getActivity().finish();
                }
            } else {
                SPUtil.put(getActivity(), LOGINED, true);
                SPUtil.put(getActivity(), ACCOUNT_KEY, "");
                SPUtil.put(getActivity(), PASSWORD_KEY, "");
                loadingview.setVisibility(View.GONE);
                WidgetUtil.showSnackBar(getActivity(), v2ProfileRespPayLoad.getMessage());
            }
        }, throwable -> WidgetUtil.showSnackBar(getActivity(), throwable.getMessage()));
    }
}
