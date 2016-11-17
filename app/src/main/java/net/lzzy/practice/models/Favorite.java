package net.lzzy.practice.models;

import android.database.Cursor;

import net.lzzy.practice.constants.DbConstants;
import net.lzzy.sqlitelib.ISqlitable;

import java.util.HashMap;
import java.util.UUID;


public class Favorite extends BaseEntity implements ISqlitable {
    private UUID questionId;
    private int times;
    private boolean isDone;

    public Favorite() {
        times = 1;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }


    @Override
    public String getTableName() {
        return DbConstants.FAVORITE_TABLE_NAME;
    }

    @Override
    public String getPKName() {
        return DbConstants.FAVORITE_ID;
    }

    @Override
    public HashMap<String, Object> getInsertCols() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(DbConstants.FAVORITE_ID, id);
        map.put(DbConstants.FAVORITE_QUESTION_ID, questionId);
        map.put(DbConstants.FAVORITE_TIMES, times);
        map.put(DbConstants.FAVORITE_IS_DONE, isDone ? 1 : 0);
        return map;
    }

    @Override
    public HashMap<String, Object> getUpdateCols() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(DbConstants.FAVORITE_QUESTION_ID, questionId);
        map.put(DbConstants.FAVORITE_TIMES, times);
        map.put(DbConstants.FAVORITE_IS_DONE, isDone ? 1 : 0);
        return map;
    }

    @Override
    public void fromCursor(Cursor cursor) {
        id = UUID.fromString(cursor.getString(cursor.getColumnIndex(DbConstants.FAVORITE_ID)));
        questionId = UUID.fromString(cursor.getString(cursor.getColumnIndex(DbConstants.FAVORITE_QUESTION_ID)));
        times = cursor.getInt(cursor.getColumnIndex(DbConstants.FAVORITE_TIMES));
        isDone = cursor.getInt(cursor.getColumnIndex(DbConstants.FAVORITE_IS_DONE)) != 0;
    }
}
