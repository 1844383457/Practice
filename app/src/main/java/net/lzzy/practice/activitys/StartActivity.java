package net.lzzy.practice.activitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import net.lzzy.practice.R;
import net.lzzy.practice.utils.AppUtils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class StartActivity extends AppCompatActivity {
    private static final long MIN_TIME = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if (AppUtils.isConnectivity()) {
            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    long startTime = System.currentTimeMillis();
                    boolean results;
                    try {
                        AppUtils.updateNetState();
                        results = true;
                    } catch (IOException e) {
                        results = false;
                    }
                    long time = System.currentTimeMillis() - startTime;
                    if (time < MIN_TIME) {
                        try {
                            Thread.sleep(MIN_TIME - time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return results;
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    if (aBoolean) {
                        jump();
                    } else {
                        Toast.makeText(StartActivity.this, "服务器未响应", Toast.LENGTH_SHORT).show();
                        jump();
                    }
                }
            }.execute();
        } else {
            Toast.makeText(StartActivity.this, "网络未开启，请检查网络设置", Toast.LENGTH_SHORT).show();
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    jump();
                }
            };
            timer.schedule(task, 3000);
        }
    }

    private void jump() {
        Intent intent = new Intent(StartActivity.this, PracticeActivity.class);
        startActivity(intent);
        finish();
    }

}
