package net.lzzy.practice.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import net.lzzy.practice.fragment.PracticeFragment;
import net.lzzy.practice.utils.AppUtils;

public class PracticeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils appUtils = new AppUtils();
        appUtils.addActivity(this);
    }

    @Override
    protected Fragment getFragment() {
        return new PracticeFragment();
    }


}
