package com.lhz.android.baseUtils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lhz.android.baseUtils.R;


/**
 * 短信验证码输入框
 */
public class SmsEditText extends RelativeLayout {

    private String digits = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
    private LinearLayout mRootLayout;
    private SendButton mSendButton;
    private ImageView mClearBtn;
    private EditText mEditText;
    private TextView mLabel;
    private Boolean mClear;
    private View mDivider;

    public SmsEditText(Context context) {
        super(context, null);
    }

    public SmsEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate();
        initView();
        setAttribute(context, attrs);
    }

    private void inflate() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.widget_sms_edit_text, this, true);
        }
    }

    private void initView() {
        mDivider = findViewById(R.id.divider);
        mLabel = (TextView) findViewById(R.id.label);
        mEditText = (EditText) findViewById(R.id.editText);
        mClearBtn = (ImageView) findViewById(R.id.clearBtn);
        mSendButton = (SendButton) findViewById(R.id.sms_button);
        mRootLayout = (LinearLayout) findViewById(R.id.root_layout);

        mClearBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View paramView) {
                getEditText().setText("");
                getEditText().requestFocus();
            }
        });

        initWatch();
    }

    private void setAttribute(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.customEdit);
        try {
            String txt = typedArray.getString(R.styleable.customEdit_label);
            if (!TextUtils.isEmpty(txt)) {
                mLabel.setVisibility(VISIBLE);
                mLabel.setText(txt);
            }
            mClear = typedArray.getBoolean(R.styleable.customEdit_clear, true);
            Boolean sendBtnEnable = typedArray.getBoolean(R.styleable.customEdit_send_btn_enable, true);
            mSendButton.setVisibility(sendBtnEnable ? VISIBLE : GONE);
            Boolean dividerEnable = typedArray.getBoolean(R.styleable.customEdit_divider_enable, true);
            mDivider.setVisibility(dividerEnable ? VISIBLE : GONE);
            int leftPadding = (int) typedArray.getDimension(R.styleable.customEdit_padding_left, 0);
            int rightPadding = (int) typedArray.getDimension(R.styleable.customEdit_padding_right, 0);
            mRootLayout.setPadding(leftPadding, 0, rightPadding, 0);
            String hit = typedArray.getString(R.styleable.customEdit_hit);
            if (!TextUtils.isEmpty(hit)) {
                getEditText().setHint(hit);
            }
            int length = typedArray.getInteger(R.styleable.customEdit_length, -1);
            if (length > 0) {
                getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
            }
            boolean editable = typedArray.getBoolean(R.styleable.customEdit_editable, true);
            mEditText.setEnabled(editable);
            String type = typedArray.getString(R.styleable.customEdit_input_typed);
            if (!TextUtils.isEmpty(type)) {
                getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
                if ("number_letter".equals(type)) {
                    getEditText().setKeyListener(DigitsKeyListener.getInstance(digits));
                }
            }

        } finally {
            typedArray.recycle();
        }
    }

    private void initWatch() {
        getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mClear && getEditText().isEnabled() && getEditText().getText().length() > 0) {
                    mClearBtn.setVisibility(View.VISIBLE);
                } else {
                    mClearBtn.setVisibility(View.GONE);
                }
            }
        });
    }

    public EditText getEditText() {
        return mEditText;
    }

    public SendButton getSendButton() {
        return mSendButton;
    }

}
