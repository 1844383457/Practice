package net.lzzy.practice.models;

import android.database.Cursor;

import net.lzzy.practice.constants.ApiConstants;
import net.lzzy.practice.constants.DbConstants;
import net.lzzy.practice.web.JsonTable;
import net.lzzy.practice.web.OptionJson;
import net.lzzy.sqlitelib.ISqlitable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class Question extends BaseEntity implements ISqlitable ,JsonTable {
    private String content;
    private QuestionType type;
    private String analysis;
    private List<Option> options;
    private UUID practiceId;

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(UUID practiceId) {
        this.practiceId = practiceId;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    @Override
    public String getTableName() {
        return DbConstants.QUESTION_TABLE_NAME;
    }

    @Override
    public String getPKName() {
        return DbConstants.QUESTION_ID;
    }

    @Override
    public HashMap<String, Object> getInsertCols() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(DbConstants.QUESTION_ID, id);
        map.put(DbConstants.QUESTION_CONTENT, content);
        map.put(DbConstants.QUESTION_PRACTICE_ID, practiceId);
        map.put(DbConstants.QUESTION_TYPE, type);
        map.put(DbConstants.QUESTION_ANALYSIS, analysis);
        return map;
    }

    @Override
    public HashMap<String, Object> getUpdateCols() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(DbConstants.QUESTION_CONTENT, content);
        map.put(DbConstants.QUESTION_PRACTICE_ID, practiceId);
        map.put(DbConstants.QUESTION_TYPE, type);
        map.put(DbConstants.QUESTION_ANALYSIS, analysis);
        return map;
    }

    @Override
    public void fromCursor(Cursor cursor) {
        id = UUID.fromString(cursor.getString(cursor.getColumnIndex(DbConstants.QUESTION_ID)));
        content = cursor.getString(cursor.getColumnIndex(DbConstants.QUESTION_CONTENT));
        practiceId = UUID.fromString(cursor.getString(cursor.getColumnIndex(DbConstants.QUESTION_PRACTICE_ID)));
        type = QuestionType.getQuestionType(cursor.getInt(
                cursor.getColumnIndex(DbConstants.QUESTION_TYPE)));
        analysis = cursor.getString(cursor.getColumnIndex(DbConstants.QUESTION_ANALYSIS));
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        analysis = json.getString(ApiConstants.JSON_QUESTION_ANALYSIS);
        content = json.getString(ApiConstants.JSON_QUESTION_CONTENT);
        type = QuestionType.getQuestionType(
                json.getInt(ApiConstants.JSON_QUESTION_QUESTION_TYPE));
        String jsonOption = json.getString(ApiConstants.JSON_QUESTION_OPTIONS);
        String jsonAnswers = json.getString(ApiConstants.JSON_QUESTION_ANSWERS);

        try {
            List<Option> options = OptionJson.getOptions(jsonOption, id, jsonAnswers);
            this.options.addAll(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
