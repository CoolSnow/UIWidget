package com.aries.ui.widget.demo.module.alert;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aries.ui.view.radius.RadiusEditText;
import com.aries.ui.view.radius.RadiusTextView;
import com.aries.ui.widget.alert.UIAlertView;
import com.aries.ui.widget.demo.R;
import com.aries.ui.widget.demo.base.BaseActivity;
import com.aries.ui.widget.demo.util.SizeUtil;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_NEUTRAL;
import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * Created: AriesHoo on 2017/7/18 14:50
 * Function: 颜色UIAlertView部分用法
 * Desc:
 */
public class AlertActivity extends BaseActivity {

    @BindView(R.id.sBar_num) SeekBar sBarNum;
    @BindView(R.id.tv_statusNum) TextView tvNum;
    @BindView(R.id.sBtn_titleAlert) SwitchCompat sBtnTitle;
    @BindView(R.id.sBtn_titleColorAlert) SwitchCompat sBtnTitleColor;
    @BindView(R.id.sBtn_msgColorAlert) SwitchCompat sBtnMsgColor;
    @BindView(R.id.sBtn_buttonColorAlert) SwitchCompat sBtnButtonColor;
    @BindView(R.id.sBtn_backAlert) SwitchCompat sBtnBack;
    @BindView(R.id.rtv_showAlert) RadiusTextView rtvShow;

    private boolean isShowTitle = true;
    private boolean isDefaultTitleColor = false;
    private boolean isDefaultMsgColor = false;
    private boolean isDefaultButtonColor = false;
    private boolean isBackDim = true;

    private int num = 2;

    @Override
    protected void setTitleBar() {
        titleBar.setTitleMainText("UIAlertView");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_alert;
    }

    @Override
    protected void initView(Bundle var1) {

        sBarNum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                num = progress + 1;
                tvNum.setText(num + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sBtnTitle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isShowTitle = isChecked;
                sBtnTitle.setText(isShowTitle ? "显示Title" : "隐藏Title");
                sBtnTitleColor.setVisibility(isShowTitle ? View.VISIBLE : View.GONE);
            }
        });

        sBtnTitleColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDefaultTitleColor = isChecked;
                sBtnTitleColor.setText(isDefaultTitleColor ? "默认Title文本颜色" : "自定义Title文本颜色");
            }
        });
        sBtnMsgColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDefaultMsgColor = isChecked;
                sBtnMsgColor.setText(isDefaultMsgColor ? "默认Message文本颜色" : "自定义Message文本颜色");
            }
        });

        sBtnButtonColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDefaultButtonColor = isChecked;
                sBtnButtonColor.setText(isDefaultButtonColor ? "默认Button文本颜色" : "自定义Button文本颜色");
            }
        });

        sBtnBack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isBackDim = isChecked;
                sBtnBack.setText(isBackDim ? "背景半透明" : "背景全透明");
            }
        });
        sBtnTitle.setChecked(true);
        sBtnTitleColor.setChecked(true);
        sBtnMsgColor.setChecked(true);
        sBtnButtonColor.setChecked(true);
        sBtnBack.setChecked(true);
        sBarNum.setProgress(1);
    }

    @OnClick({R.id.rtv_showAlert, R.id.rtv_editAlert})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rtv_editAlert:

                UIAlertView alertEdit = new UIAlertView(mContext);
                final RadiusEditText editText = new RadiusEditText(AlertActivity.this);
                editText.getDelegate()
                        .setTextColor(Color.GRAY)
                        .setRadius(6f)
                        .setBackgroundColor(Color.WHITE)
                        .setStrokeColor(Color.GRAY)
                        .setStrokeWidth(SizeUtil.dp2px(0.5f));
                editText.setMinHeight(SizeUtil.dp2px(40));
                editText.setGravity(Gravity.CENTER_VERTICAL);
                editText.setPadding(SizeUtil.dp2px(12), 0, SizeUtil.dp2px(12), 0);
                editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                editText.setHint("请输入内容");
                alertEdit.setTitle("Alert添加输入框示例")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String text = editText.getText().toString().trim();
                                if (TextUtils.isEmpty(text)) {
                                    return;
                                }
                                Toast.makeText(mContext, "你输入的是:" + text, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .setView(editText)
                        .show();
                editText.requestFocus();
                break;
            case R.id.rtv_showAlert:
                UIAlertView alert = new UIAlertView(mContext);
                alert.setMessage("1、本次更新修复多个重大BUG\n2、新增用户反馈接口", Gravity.LEFT | Gravity.CENTER_VERTICAL)
                        .setMinHeight(SizeUtil.dp2px(80));
                if (isShowTitle) {
                    alert.setTitle("UIAlertView");
                }
                if (!isDefaultTitleColor) {
                    alert.setTitleTextColor(Color.BLUE);
                }
                if (!isDefaultMsgColor) {
                    alert.setMessageTextColor(Color.RED);
                }
                if (!isDefaultButtonColor) {
                    alert.setNegativeButtonTextColor(Color.RED)
                            .setNeutralButtonTextColor(Color.BLUE)
                            .setPositiveButtonTextColor(Color.BLACK);
                }
                if (num == 1) {
                    alert.setPositiveButton("确定", onAlertClick);
                } else if (num == 2) {
                    alert.setNegativeButton("否定", onAlertClick)
                            .setPositiveButton("肯定", onAlertClick);
                } else {
                    alert.setNegativeButton("否定", onAlertClick)
                            .setPositiveButton("肯定", onAlertClick)
                            .setNeutralButton("中性", onAlertClick);
                }
                if (!isBackDim) {
                    alert.setDimAmount(0f);
                }
                alert.show();
                break;
        }
    }

    DialogInterface.OnClickListener onAlertClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String msg = "";
            switch (which) {
                case BUTTON_NEGATIVE:
                    msg = "否定";
                    break;
                case BUTTON_POSITIVE:
                    msg = "肯定";
                    break;
                case BUTTON_NEUTRAL:
                    msg = "中性";
                    break;
            }
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    };
}
