package net.lzzy.practice.web;

import net.lzzy.practice.constants.ApiConstants;
import net.lzzy.practice.models.Question;
import net.lzzy.practice.utils.AppUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public class QuestionJson {
    public static String getQuestinsFromserver(int apiId) throws IOException {
        if (AppUtils.isLocal) {
            try {
                return ApiService.okGet(ApiConstants.URL_API_QUESTION_LOCAL) + apiId;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return ApiService.okGet(ApiConstants.URL_API_QUESTION) + apiId;
        }
    }

    public static List<Question> getQuestions(String json, UUID practiceId) throws Exception {
        JsonConverter<Question> repository = new JsonConverter<>(Question.class);
        List<Question> questions = repository.getT(json);
        for (Question question : questions) {
            question.setPracticeId(practiceId);
        }
        return questions;
    }


}
