package net.lzzy.practice.web;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class JsonConverter<T extends JsonTable> {

    private Class<T> classType;

    public JsonConverter(Class<T> classType) {
        this.classType = classType;
    }

    public List<T> getT(String str) throws IllegalAccessException, InstantiationException, JSONException {
        List<T> list = new ArrayList<>();
        JSONArray array = (JSONArray) new JSONTokener(str).nextValue();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            T t = classType.newInstance();
            t.fromJson(object);
            list.add(t);
        }
        return list;
    }
}
