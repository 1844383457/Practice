package net.lzzy.practice.models;

import android.database.Cursor;

import net.lzzy.practice.constants.ApiConstants;
import net.lzzy.practice.constants.DbConstants;
import net.lzzy.practice.web.JsonTable;
import net.lzzy.sqlitelib.ISqlitable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


public class Practice extends BaseEntity implements ISqlitable, JsonTable {
    private String name;
    private int apiId;
    private boolean isDownload;
    private Date downloadDate;
    private int questionCount;
    private String outLines;

    public Practice() {
        downloadDate = new Date();
    }

    public int getApiId() {
        return apiId;
    }

    public void setApiId(int apiId) {
        this.apiId = apiId;
    }

    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(Date downloadDate) {
        this.downloadDate = downloadDate;
    }

    public boolean isDownload() {
        return isDownload;
    }

    public void setDownload(boolean download) {
        isDownload = download;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutLines() {
        return outLines;
    }

    public void setOutLines(String outLines) {
        this.outLines = outLines;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    @Override
    public String getTableName() {
        return DbConstants.PRACTICE_TABLE_NAME;
    }

    @Override
    public String getPKName() {
        return DbConstants.PRACTICE_ID;
    }


    @Override
    public HashMap<String, Object> getInsertCols() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(DbConstants.PRACTICE_ID, id);
        map.put(DbConstants.PRACTICE_API_ID, apiId);
        map.put(DbConstants.PRACTICE_QUESTION_COUNT, questionCount);
        map.put(DbConstants.PRACTICE_DOWNLOAD_DATE, downloadDate.getTime());
        map.put(DbConstants.PRACTICE_IS_DOWNLOAD, isDownload ? 1 : 0);
        map.put(DbConstants.PRACTICE_OUT_LINES, outLines);
        map.put(DbConstants.PRACTICE_NAME, name);
        return map;
    }

    @Override
    public HashMap<String, Object> getUpdateCols() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(DbConstants.PRACTICE_API_ID, apiId);
        map.put(DbConstants.PRACTICE_QUESTION_COUNT, questionCount);
        map.put(DbConstants.PRACTICE_DOWNLOAD_DATE, downloadDate.getTime());
        map.put(DbConstants.PRACTICE_IS_DOWNLOAD, isDownload ? 1 : 0);
        map.put(DbConstants.PRACTICE_OUT_LINES, outLines);
        map.put(DbConstants.PRACTICE_NAME, name);
        return map;
    }

    @Override
    public void fromCursor(Cursor cursor) {
        id = UUID.fromString(cursor.getString(cursor.getColumnIndex(DbConstants.PRACTICE_ID)));
        name = cursor.getString(cursor.getColumnIndex(DbConstants.PRACTICE_NAME));
        outLines = cursor.getString(cursor.getColumnIndex(DbConstants.PRACTICE_OUT_LINES));
        isDownload = cursor.getInt(cursor.getColumnIndex(DbConstants.PRACTICE_IS_DOWNLOAD)) != 0;
        downloadDate = new Date(cursor.getLong(cursor.getColumnIndex(DbConstants.PRACTICE_DOWNLOAD_DATE)));
        apiId = cursor.getInt(cursor.getColumnIndex(DbConstants.PRACTICE_API_ID));
        questionCount = cursor.getInt(cursor.getColumnIndex(DbConstants.PRACTICE_QUESTION_COUNT));
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        apiId = json.getInt(ApiConstants.JSON_PRACTICE_API_ID);
        name = (json.getString(ApiConstants.JSON_PRACTICE_NAME));
        questionCount = json.getInt(ApiConstants.JSON_PRACTICE_QUESTION_COUNT);
        outLines = json.getString(ApiConstants.JSON_PRACTICE_OUTLINES);
    }
}
