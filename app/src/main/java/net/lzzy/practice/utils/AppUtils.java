package net.lzzy.practice.utils;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AppUtils extends Application {
    private List<Activity> activities = new ArrayList<>();
    private static AppUtils context;
    public static boolean isLocal;


    public AppUtils() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void allFinishActivity() {
        for (Activity a : activities)
            if (a != null)
                a.finish();
        System.exit(0);
    }



    public static boolean isConnectivity() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        //return info != null && info.isAvailable();
        return info != null && info.isConnected();
    }


    public static void updateNetState() throws IOException {
        try {
            testHost("222.217.36.110");
            isLocal = false;
        } catch (IOException e) {
            testHost("10.88.91.103");
            isLocal = true;
        }
    }

    private static void testHost(String ip) throws IOException {
        URL url = new URL("http://".concat(ip).concat(":8888"));
        HttpURLConnection coon = (HttpURLConnection) url.openConnection();
        coon.setConnectTimeout(5000);

        coon.getContent();
    }
}
