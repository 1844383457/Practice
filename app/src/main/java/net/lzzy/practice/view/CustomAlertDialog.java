package net.lzzy.practice.view;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.lzzy.practice.R;


public class CustomAlertDialog extends AlertDialog implements View.OnClickListener {
    private TextView tv_title;
    private TextView tv_content;
    private LinearLayout content_layout;
    private Context context;
    private LinearLayout btn_layout;
    private boolean isHaveButton;

    public CustomAlertDialog(Context context, boolean isHaveButton) {
        super(context);
        this.context = context;
        this.isHaveButton = isHaveButton;
        initView();
    }


    private void initView() {
        this.show();
        int width = context.getResources().getDisplayMetrics().widthPixels;
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (width * 0.85);
        lp.gravity = Gravity.CENTER_VERTICAL;
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = View.inflate(context, R.layout.custom_alert_dialog, null);
        setContentView(view);
        tv_title = (TextView) view.findViewById(R.id.custom_alert_dialog_tv_title);
        tv_content = (TextView) view.findViewById(R.id.custom_alert_dialog_tv_content);
        content_layout = (LinearLayout) view.findViewById(R.id.custom_alert_dialog_layout);
        btn_layout = (LinearLayout) view.findViewById(R.id.custom_alert_dialog_btn_layout);
        if (!isHaveButton)
            content_layout.setBackgroundResource(R.drawable.custom_alert_dialog_bottom);
        setContentView(view);

    }

    @Override
    public void setTitle(CharSequence title) {
        tv_title.setText(title);
    }


    public void addBottomView(View view, LinearLayout.LayoutParams layoutParams) {
        content_layout.addView(view, layoutParams);

    }

    @Override
    public void setContentView(@NonNull View view, ViewGroup.LayoutParams params) {
        tv_content.setGravity(View.GONE);
        content_layout.addView(view, params);
    }

    @Override
    public void setMessage(CharSequence message) {
        tv_content.setText(message);
    }


    public void setNegativeButton(CharSequence text, View.OnClickListener listener, int resIdLeft) {
        Button btn_negative = new Button(context);
        btn_negative.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        btn_negative.setText(text);
        btn_negative.setBackgroundResource(resIdLeft);
        btn_negative.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));
        btn_layout.addView(btn_negative);
        if (listener == null)
            btn_negative.setOnClickListener(this);
        else
            btn_negative.setOnClickListener(listener);
    }


    public void setPositiveButton(CharSequence text, View.OnClickListener listener, int resIdRight) {
        Button btn_positive = new Button(context);
        btn_positive.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        btn_positive.setText(text);
        btn_positive.setBackgroundResource(resIdRight);
        btn_positive.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));
        btn_layout.addView(btn_positive);
        if (listener == null)
            btn_positive.setOnClickListener(this);
        else
            btn_positive.setOnClickListener(listener);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
