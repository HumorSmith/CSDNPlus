package com.ifreedomer.cplus.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifreedomer.cplus.MainActivity;
import com.ifreedomer.cplus.R;
import com.ifreedomer.cplus.entity.UserInfo;
import com.ifreedomer.cplus.http.center.HttpManager;
import com.ifreedomer.cplus.http.protocol.PayLoad;
import com.ifreedomer.cplus.http.protocol.resp.UserInfoResp;
import com.ifreedomer.cplus.http.protocol.resp.V2ProfileResp;
import com.ifreedomer.cplus.manager.GlobalDataManager;
import com.ifreedomer.cplus.util.WidgetUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class LoginFragment extends Fragment implements View.OnClickListener {


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
        passwordEt.addTextChangedListener(mTextWatcher);
        accountEt.addTextChangedListener(mTextWatcher);

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
            loginBtn.setEnabled(TextUtils.isEmpty(accountEt.getText().toString()) || TextUtils.isEmpty(passwordEt.getText().toString()));
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        if (mViewModel.canFastLogin(getContext())) {
            UserInfo userInfo = mViewModel.fastLogin(getContext());
            startActivity(new Intent(getActivity(), MainActivity.class));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                Observable<PayLoad<UserInfoResp>> loginObserver = mViewModel.login(accountEt.getText().toString(), passwordEt.getText().toString());
                loginObserver.subscribe(userInfoRespPayLoad -> {
                    if (userInfoRespPayLoad.getCode() == 2000) {
                        GlobalDataManager.getInstance().setSessionId(userInfoRespPayLoad.getSessionId());
                        getUserProfile(userInfoRespPayLoad.getData().getUserName());
                    } else {
                        WidgetUtil.showSnackBar(getActivity(), userInfoRespPayLoad.getMessage());
                    }
                }, throwable -> WidgetUtil.showSnackBar(getActivity(), throwable.getMessage()));
                break;
        }
    }


    public void getUserProfile(String userName) {
        Observable<PayLoad<V2ProfileResp>> v2ProfileObservable = HttpManager.getInstance().getV2Profile(userName);
        Disposable subscribe = v2ProfileObservable.subscribe(v2ProfileRespPayLoad -> {
            if (v2ProfileRespPayLoad.getCode() == PayLoad.SUCCESS) {
                mViewModel.saveLoginInfo(getContext(), v2ProfileRespPayLoad);
                startActivity(new Intent(getActivity(),MainActivity.class
                ));
            } else {
                WidgetUtil.showSnackBar(getActivity(), v2ProfileRespPayLoad.getMessage());
            }
        }, throwable -> WidgetUtil.showSnackBar(getActivity(), throwable.getMessage()));
    }
}
