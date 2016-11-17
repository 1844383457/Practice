package net.lzzy.practice.logics;


import net.lzzy.practice.constants.DbConstants;
import net.lzzy.practice.models.Question;
import net.lzzy.practice.utils.AppUtils;
import net.lzzy.sqlitelib.SqlRepository;

import java.util.List;

public class QuestionFactory {
    private static QuestionFactory instance;
    private SqlRepository<Question> repository;
    private List<Question> questions;

    private QuestionFactory() {
        repository = new SqlRepository<>(AppUtils.getContext(), Question.class, DbConstants.dbPackage);
        questions = repository.get();
    }

    public static QuestionFactory getInstance() {//锁定多线程
        if (instance == null) {
            synchronized (QuestionFactory.class) {
                if (instance == null)
                    instance = new QuestionFactory();
            }
        }
        return instance;
    }

    public List<Question> getQuestions() {
        return questions;
    }


}
