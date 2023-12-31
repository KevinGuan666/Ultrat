package com.yihang.ultrat.common.utils.url;

import cn.hutool.core.util.StrUtil;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class PriorityUrlMatcher extends AbstractUrlMatcher {
    private final List<UrlMatcher> urlMatchers = new ArrayList<>(2);

    public PriorityUrlMatcher() {
        urlMatchers.add(new CommonUrlMatcher());
        urlMatchers.add(new WechatUrlMatcher());
    }

    @Override
    public String getTitle(Document document) {
        for (UrlMatcher urlMatcher : urlMatchers) {
            String urlTitle = urlMatcher.getTitle(document);
            if (StrUtil.isNotBlank(urlTitle)) {
                return urlTitle;
            }
        }
        return null;
    }

    @Override
    public String getDescription(Document document) {
        for (UrlMatcher urlMatcher : urlMatchers) {
            String urlDescription = urlMatcher.getDescription(document);
            if (StrUtil.isNotBlank(urlDescription)) {
                return urlDescription;
            }
        }
        return null;
    }

    @Override
    public String getThumbnail(String url, Document document) {
        for (UrlMatcher urlMatcher : urlMatchers) {
            String urlThumbnail = urlMatcher.getThumbnail(url, document);
            if (StrUtil.isNotBlank(urlThumbnail)) {
                return urlThumbnail;
            }
        }
        return null;
    }
}
