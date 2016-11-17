package net.lzzy.practice.web;


import android.os.AsyncTask;

import net.lzzy.practice.logics.PracticeFactory;

import java.io.IOException;

public class DowPractice extends AsyncTask<Void, Integer, String> {


    @Override
    protected String doInBackground(Void... voids) {
        try {
            return PracticeJson.getPracticeFromJson();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (!s.equals("")) {
            try {
                PracticeFactory.getInstance().replaceAll(PracticeJson.getPractices(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
