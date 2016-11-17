package net.lzzy.practice.web;


import net.lzzy.practice.constants.ApiConstants;
import net.lzzy.practice.models.Practice;
import net.lzzy.practice.utils.AppUtils;

import java.io.IOException;
import java.util.List;


public class PracticeJson {
    public static String getPracticeFromJson() throws IOException {
        if (AppUtils.isLocal) {
            return ApiService.okGet(ApiConstants.URL_API_PRACTICES_LOCAL);
        } else {
            return ApiService.okGet(ApiConstants.URL_API_PRACTICES);
        }
    }

    public static List<Practice> getPractices(String json) throws Exception {
        JsonConverter<Practice> repository=new JsonConverter<>(Practice.class);
        return repository.getT(json);

    }


}
