package net.lzzy.practice.web;

import net.lzzy.practice.constants.ApiConstants;
import net.lzzy.practice.models.Option;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class OptionJson {
    public static List<Option> getOptions(String jsonOptions, UUID questionId, String answers) throws Exception {
        JsonConverter<Option> repository = new JsonConverter<>(Option.class);
        List<Option> options = repository.getT(jsonOptions);
        List<Integer> answerIds = new ArrayList<>();
        JSONArray answerArray = (JSONArray) new JSONTokener(answers).nextValue();
        for (int y = 0; y < answerArray.length(); y++) {
            JSONObject j = answerArray.getJSONObject(y);
            int optionId = j.getInt(ApiConstants.JSON_OPTION_ID);
            answerIds.add(optionId);
        }
        for (Option option:options) {
            option.setQuestionId(questionId);
            if (answerIds.contains(option.getApiId())) {
                option.setAnswer(true);
            }
        }
        return options;
    }

}
