package net.lzzy.practice.models;

public enum QuestionType {
    SINGLE_CHOICE("单项选择", 0), MULTI_CHOICE("多项选择", 1), JUDGE("判断", 2);
    private final String name;
    private final int index;

    QuestionType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public static String getTypeName(QuestionType type) {
        return type.getName();
    }

    public static QuestionType getQuestionType(int index) {
        for (QuestionType q : QuestionType.values()) {
            if (q.getIndex() == index)
                return q;
        }
        return null;
    }
}
