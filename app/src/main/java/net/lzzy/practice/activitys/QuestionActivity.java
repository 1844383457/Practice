package net.lzzy.practice.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import net.lzzy.practice.fragment.QuestionFragment;
import net.lzzy.practice.models.Practice;
import net.lzzy.practice.utils.AppUtils;
import net.lzzy.sqlitelib.GenericAdapter;
import net.lzzy.sqlitelib.ViewHolder;


public class QuestionActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils appUtils = new AppUtils();
        appUtils.addActivity(this);

    }

    @Override
    protected Fragment getFragment() {
        return new QuestionFragment();
    }
}
