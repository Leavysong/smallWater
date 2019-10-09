package com.df.smallwater.smallwater;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.df.smallwater.smallwater.api.Api;
import com.df.smallwater.smallwater.base.BaseActivity;
import com.df.smallwater.smallwater.baserx.RxSchedulers;
import com.df.smallwater.smallwater.baserx.RxSubscriber;
import com.df.smallwater.smallwater.bean.Result;
import com.df.smallwater.smallwater.utils.PreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QingjiaActivity extends BaseActivity {


    @Bind(R.id.tv_base_text)
    TextView tvBaseText;
    @Bind(R.id.iv_base_back)
    ImageView ivBaseBack;
    @Bind(R.id.textView21)
    EditText textView21;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.textView25)
    TextView textView25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qingjia);
        ButterKnife.bind(this);
        SetStatusBarColor();
        tvBaseText.setText("请假");
        textView25.setText(PreferencesUtils.getString(this,"yname"));
    }

    @OnClick({R.id.iv_base_back, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_base_back:
                this.finish();
                break;
            case R.id.button:

                if (textView21.getText().toString().length() > 5 && textView21.getText().toString().length() < 50) {


                    Api.getDefault().qingjia(PreferencesUtils.getString(this, "username"), textView21.getText().toString() + "", PreferencesUtils.getInt(this, "banjiid"), PreferencesUtils.getString(this, "name"))
                            .compose(RxSchedulers.<Result>io_main()).subscribe(new RxSubscriber<Result>(this, false) {
                        @Override
                        protected void _onNext(Result result) {

                            result.getResult();
                            result.getMsg();
                            showLongToast(result.getMsg());
                            readyGoThenKill(QingjiaListActivity.class, null);
                        }

                        @Override
                        protected void _onError(String message) {

                        }
                    });
                } else {
                    showLongToast("请输入5-50字内的请假信息");
                }


                break;
        }
    }
}
