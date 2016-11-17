package net.lzzy.practice.logics;


import net.lzzy.practice.constants.DbConstants;
import net.lzzy.practice.models.Practice;
import net.lzzy.practice.utils.AppUtils;
import net.lzzy.sqlitelib.SqlRepository;

import java.util.List;

public class PracticeFactory {
    private static PracticeFactory instance;
    private SqlRepository<Practice> repository;
    private List<Practice> practices;

    private PracticeFactory() {
        repository = new SqlRepository<>(AppUtils.getContext(), Practice.class, DbConstants.dbPackage);
        practices = repository.get();
    }

    public void replaceAll(List<Practice> list) {
        for (Practice p : list) {
            if (!isExist(p)) {
                Practice practice = new Practice();
                practice.setName(p.getName());
                practice.setOutLines(p.getOutLines());
                practice.setQuestionCount(p.getQuestionCount());
                practice.setApiId(p.getApiId());
                practice.setDownload(p.isDownload());
                practice.setDownloadDate(p.getDownloadDate());
                repository.insert(p);
                practices.add(p);
            }
        }
    }

    public void deletePractice(Practice practice) {
        repository.delete(practice);
    }

    private boolean isExist(Practice practice) {
        for (Practice p : practices) {
            if (p.getApiId() == practice.getApiId())
                return true;
        }
        return false;
    }

    public static PracticeFactory getInstance() {//锁定多线程
        if (instance == null) {
            synchronized (PracticeFactory.class) {
                if (instance == null)
                    instance = new PracticeFactory();
            }
        }
        return instance;
    }

    public List<Practice> getPractices() {
        return practices;
    }

    public void createPractices(Practice practice) {
        repository.insert(practice);
    }
}
