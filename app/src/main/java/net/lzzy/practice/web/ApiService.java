package net.lzzy.practice.web;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiService {

    public static String get(String address) throws IOException {//使用HttpURLConnection下载data
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
            reader.close();
            inputStream.close();
            return buffer.toString();
        } finally {
            connection.disconnect();
        }
    }

    public static void post(JSONObject json, String address) throws IOException {//上传
        URL url = new URL(address);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("POST");
        urlConnection.setChunkedStreamingMode(0);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        byte[] data = json.toString().getBytes("UTF-8");
        urlConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
        try (OutputStream stream = urlConnection.getOutputStream()) {
            stream.write(data);
            stream.flush();
        } finally {
            urlConnection.disconnect();
        }
    }


    public static String okGet(String address) throws IOException {//使用okHttp下载data
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful())
            return response.body().string();
        else
            throw new IOException("Exception code " + response.code());
    }


    public static void okPost(JSONObject json, String address) throws IOException {//上传
        OkHttpClient client = new OkHttpClient();
        MediaType type = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(type, json.toString());
        Request request = new Request.Builder().url(address).post(body).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Exception code " + response.code());

    }
}
