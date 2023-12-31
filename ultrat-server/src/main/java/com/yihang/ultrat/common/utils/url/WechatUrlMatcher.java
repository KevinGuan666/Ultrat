package com.yihang.ultrat.common.utils.url;


import org.jsoup.nodes.Document;

public class WechatUrlMatcher extends AbstractUrlMatcher {
    @Override
    public String getTitle(Document document) {
        return document.getElementsByAttributeValue("property", "og:title").attr("content");
    }

    @Override
    public String getDescription(Document document) {
        return document.getElementsByAttributeValue("property", "og:description").attr("content");
    }

    @Override
    public String getThumbnail(String url, Document document) {
        String href = document.getElementsByAttributeValue("property", "og:image").attr("content");
        return isValidUrl(href) ? href : null;
    }
}
