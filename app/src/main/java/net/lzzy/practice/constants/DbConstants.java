package net.lzzy.practice.constants;

import net.lzzy.sqlitelib.DbPackage;

import java.util.ArrayList;
import java.util.List;


public final class DbConstants {

    private DbConstants() {
    }

    public static final String DB_NAME = "practice.db";
    /**
     * 数据库版本
     */
    public static final int DB_VERSION = 1;
    /**
     * 创建表头语句
     **/
    public static final String CREATE_TABLE_HEAD = "create table ";
    /**
     * 数据库字段类型
     */
    private static final String TYPE_INT = " integer";
    private static final String TYPE_STRING = " text";
    private static final String TYPE_REAL = " real";


    /**
     * table practice
     **/
    public static final String PRACTICE_TABLE_NAME = "practice";
    public static final String PRACTICE_ID = "id";
    public static final String PRACTICE_NAME = "name";
    public static final String PRACTICE_API_ID = "apiId";
    public static final String PRACTICE_DOWNLOAD_DATE = "downloadDate";
    public static final String PRACTICE_QUESTION_COUNT = "questionCount";
    public static final String PRACTICE_OUT_LINES = "outLines";
    public static final String PRACTICE_IS_DOWNLOAD = "isDownload";
    public static final StringBuilder PRACTICE_TABLE_SQL = new StringBuilder();

    /**
     * table question
     **/
    public static final String QUESTION_TABLE_NAME = "question";
    public static final String QUESTION_ID = "id";
    public static final String QUESTION_ANALYSIS = "analysis";
    public static final String QUESTION_CONTENT = "content";
    public static final String QUESTION_PRACTICE_ID = "practiceId";
    public static final String QUESTION_TYPE = "type";
    public static final StringBuilder QUESTION_TABLE_SQL = new StringBuilder();

    /**
     * table option
     **/
    public static final String OPTION_TABLE_NAME = "option";
    public static final String OPTION_ID = "id";
    public static final String OPTION_CONTENT = "content";
    public static final String OPTION_LABEL = "label";
    public static final String OPTION_IS_ANSWER = "is_answer";
    public static final String OPTION_QUESTION_ID = "questionId";
    public static final String OPTION_API_ID = "apiId";
    public static final StringBuilder OPTION_TABLE_SQL = new StringBuilder();
    /**
     * table favorite
     */
    public static final String FAVORITE_TABLE_NAME = "favorite";
    public static final String FAVORITE_ID = "id";
    public static final String FAVORITE_QUESTION_ID = "questionId";
    public static final String FAVORITE_TIMES = "times";
    public static final String FAVORITE_IS_DONE = "is_done";
    public static final StringBuilder FAVORITE_TABLE_SQL = new StringBuilder();


    public static final List<String> CREATE_TABLE_SQLS = new ArrayList<>();
    public static final List<String> UPDATE_TABLE_SQLS = new ArrayList<>();
    public static DbPackage dbPackage;

    static {
        PRACTICE_TABLE_SQL.append(CREATE_TABLE_HEAD)
                .append(PRACTICE_TABLE_NAME).append("(")
                .append(PRACTICE_ID).append(TYPE_STRING).append(",")
                .append(PRACTICE_NAME).append(TYPE_STRING).append(",")
                .append(PRACTICE_API_ID).append(TYPE_INT).append(",")
                .append(PRACTICE_DOWNLOAD_DATE).append(TYPE_REAL).append(",")
                .append(PRACTICE_IS_DOWNLOAD).append(TYPE_INT).append(",")
                .append(PRACTICE_QUESTION_COUNT).append(TYPE_INT).append(",")
                .append(PRACTICE_OUT_LINES).append(TYPE_STRING).append(")");
        QUESTION_TABLE_SQL.append(CREATE_TABLE_HEAD)
                .append(QUESTION_TABLE_NAME).append("(")
                .append(QUESTION_ID).append(TYPE_STRING).append(",")
                .append(QUESTION_ANALYSIS).append(TYPE_STRING).append(",")
                .append(QUESTION_CONTENT).append(TYPE_STRING).append(",")
                .append(QUESTION_PRACTICE_ID).append(TYPE_STRING).append(")");
        OPTION_TABLE_SQL.append(CREATE_TABLE_HEAD)
                .append(OPTION_TABLE_NAME).append("(")
                .append(OPTION_ID).append(TYPE_STRING).append(",")
                .append(OPTION_CONTENT).append(TYPE_STRING).append(",")
                .append(OPTION_LABEL).append(TYPE_STRING).append(",")
                .append(OPTION_IS_ANSWER).append(TYPE_INT).append(",")
                .append(OPTION_QUESTION_ID).append(TYPE_STRING).append(",")
                .append(PRACTICE_API_ID).append(TYPE_INT).append(")");
        FAVORITE_TABLE_SQL.append(CREATE_TABLE_HEAD)
                .append(FAVORITE_TABLE_NAME).append("(")
                .append(FAVORITE_ID).append(TYPE_STRING).append(",")
                .append(FAVORITE_QUESTION_ID).append(TYPE_STRING).append(",")
                .append(FAVORITE_IS_DONE).append(TYPE_INT).append(",")
                .append(FAVORITE_TIMES).append(TYPE_INT).append(")");

        CREATE_TABLE_SQLS.add(PRACTICE_TABLE_SQL.toString());
        dbPackage = DbPackage.getInstance(DB_NAME, DB_VERSION, CREATE_TABLE_SQLS, UPDATE_TABLE_SQLS);
    }
}
