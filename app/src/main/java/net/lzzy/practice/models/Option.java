package net.lzzy.practice.models;

import android.database.Cursor;

import net.lzzy.practice.constants.ApiConstants;
import net.lzzy.practice.constants.DbConstants;
import net.lzzy.practice.web.JsonTable;
import net.lzzy.sqlitelib.ISqlitable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.UUID;


public class Option extends BaseEntity implements ISqlitable, JsonTable {
    private String content;
    private int apiId;
    private String label;
    private boolean isAnswer;
    private UUID questionId;

    public Option() {

    }

    public int getApiId() {
        return apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }


    @Override
    public String getTableName() {
        return DbConstants.OPTION_TABLE_NAME;
    }

    @Override
    public String getPKName() {
        return DbConstants.OPTION_ID;
    }

    @Override
    public HashMap<String, Object> getInsertCols() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(DbConstants.OPTION_ID, id);
        map.put(DbConstants.OPTION_CONTENT, content);
        map.put(DbConstants.OPTION_LABEL, label);
        map.put(DbConstants.OPTION_IS_ANSWER, isAnswer ? 1 : 0);
        map.put(DbConstants.OPTION_QUESTION_ID, questionId);
        map.put(DbConstants.OPTION_API_ID, apiId);
        return map;
    }

    @Override
    public HashMap<String, Object> getUpdateCols() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(DbConstants.OPTION_CONTENT, content);
        map.put(DbConstants.OPTION_LABEL, label);
        map.put(DbConstants.OPTION_IS_ANSWER, isAnswer ? 1 : 0);
        map.put(DbConstants.OPTION_QUESTION_ID, questionId);
        map.put(DbConstants.OPTION_API_ID, apiId);
        return map;
    }

    @Override
    public void fromCursor(Cursor cursor) {
        id = UUID.fromString(cursor.getString(cursor.getColumnIndex(DbConstants.OPTION_ID)));
        content = cursor.getString(cursor.getColumnIndex(DbConstants.OPTION_CONTENT));
        label = cursor.getString(cursor.getColumnIndex(DbConstants.OPTION_LABEL));
        isAnswer = cursor.getInt(cursor.getColumnIndex(DbConstants.OPTION_IS_ANSWER)) != 0;
        questionId = UUID.fromString(cursor.getString(cursor.getColumnIndex(DbConstants.OPTION_QUESTION_ID)));
        apiId = cursor.getInt(cursor.getColumnIndex(DbConstants.OPTION_API_ID));
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        content = json.getString(ApiConstants.JSON_OPTION_CONTENT);
        label = json.getString(ApiConstants.JSON_OPTION_LABEL);
        apiId = json.getInt(ApiConstants.JSON_PRACTICE_API_ID);
    }
}
