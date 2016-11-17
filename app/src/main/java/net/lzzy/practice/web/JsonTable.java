package net.lzzy.practice.web;

import org.json.JSONException;
import org.json.JSONObject;


public interface JsonTable {
    void fromJson(JSONObject json)throws JSONException;
}
