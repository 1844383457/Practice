package net.lzzy.practice.web;


import android.os.AsyncTask;

import java.io.IOException;

public class QuestionAsyncTask extends AsyncTask<Void, Integer, String> {
    private int apiId;

    public QuestionAsyncTask(int apiId) {
        this.apiId = apiId;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            return QuestionJson.getQuestinsFromserver(apiId);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
