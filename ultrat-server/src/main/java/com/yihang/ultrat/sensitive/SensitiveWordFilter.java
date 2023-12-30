package com.yihang.ultrat.sensitive;

import java.util.List;

public interface SensitiveWordFilter {

    boolean containSensitiveWord(String text);

    String filter(String text);

    void loadSensitiveWord(List<String> words);
}
