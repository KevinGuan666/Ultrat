package com.yihang.ultrat.common.utils.url;

import com.yihang.ultrat.domain.UrlInfo;
import org.jsoup.nodes.Document;

import java.util.Map;

public interface UrlMatcher {
    Map<String, UrlInfo> getUrlContentMap(String content);

    UrlInfo getContent(String url);

    String getTitle(Document document);

    String getDescription(Document document);

    String getThumbnail(String url, Document document);
}
