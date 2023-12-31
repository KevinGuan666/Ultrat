package com.yihang.ultrat.sensitive;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yihang.ultrat.common.algorithm.ac.ACTrie;
import com.yihang.ultrat.common.algorithm.ac.MatchResult;


import java.util.List;
import java.util.Objects;

public class ACFilter implements SensitiveWordFilter {
    private final static char mask_char = '*';

    private static ACTrie acTrie = null;

    @Override
    public boolean containSensitiveWord(String text) {
        if (StringUtils.isBlank(text)) return false;
        return !Objects.equals(filter(text), text);
    }

    @Override
    public String filter(String text) {
        if (StringUtils.isBlank(text)) return text;
        List<MatchResult> matchResults = acTrie.matches(text);
        StringBuffer result = new StringBuffer(text);
        int endIndex = 0;
        for (MatchResult matchResult : matchResults) {
            endIndex = Math.max(endIndex, matchResult.getEndIndex());
            maskBetween(result, matchResult.getStartIndex(),endIndex);
        }
        return result.toString();
    }

    @Override
    public void loadSensitiveWord(List<String> words) {
        if (words == null) return;
        acTrie = new ACTrie(words);
    }

    private static void maskBetween(StringBuffer buffer, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            buffer.setCharAt(i, mask_char);
        }
    }
}
