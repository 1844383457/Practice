package net.lzzy.practice.constants;

public final class ApiConstants {
    private ApiConstants() {

    }

    private static final String API_SERVER_LOCAL = "http://10.88.91.103:8888/api/";
    private static final String API_SERVER = "http://222.217.36.110:8888/api/";
    private static final String API_PRACTICES = "practices";
    private static final String API_QUESTIONS = "pquestions?practiceid=";
    public static final String URL_API_PRACTICES_LOCAL = API_SERVER_LOCAL.concat(API_PRACTICES);
    public static final String URL_API_PRACTICES = API_SERVER.concat(API_PRACTICES);

    public static final String URL_API_QUESTION_LOCAL = API_SERVER_LOCAL.concat(API_QUESTIONS);
    public static final String URL_API_QUESTION = API_SERVER.concat(API_QUESTIONS);
    /***
     * practice json
     **/
    public static final String JSON_PRACTICE_API_ID = "Id";
    public static final String JSON_PRACTICE_NAME = "Name";
    public static final String JSON_PRACTICE_OUTLINES = "OutLines";
    public static final String JSON_PRACTICE_QUESTION_COUNT = "QuestionCount";

    /**
     * question json
     */
    public static final String JSON_QUESTION_QUESTION_TYPE = "QuestionType";
    public static final String JSON_QUESTION_CONTENT = "Content";
    public static final String JSON_QUESTION_ANALYSIS = "Analysis";
    public static final String JSON_QUESTION_OPTIONS = "Options";
    public static final String JSON_QUESTION_ANSWERS = "Answers";
    /**
     * option json
     */
    public static final String JSON_OPTION_CONTENT = "Content";
    public static final String JSON_OPTION_LABEL = "Label";
    public static final String JSON_OPTION_API_ID = "Id";
    public static final String JSON_OPTION_ID = "OptionId";


}
