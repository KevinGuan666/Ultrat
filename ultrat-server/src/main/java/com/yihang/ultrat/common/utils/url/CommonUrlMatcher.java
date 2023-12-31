package com.yihang.ultrat.common.utils.url;

import cn.hutool.core.util.StrUtil;
import org.jsoup.nodes.Document;
import org.springframework.lang.Nullable;

public class CommonUrlMatcher extends AbstractUrlMatcher {
    @Nullable
    @Override
    public String getTitle(Document document) {
        return document.title();
    }

    @Nullable
    @Override
    public String getDescription(Document document) {
        String description = document.head().select("meta[name=description]").attr("content");
        String keywords = document.head().select("meta[name=keywords]").attr("content");
        String content = StrUtil.isNotBlank(description) ? description : keywords;
        return StrUtil.isNotBlank(content) ? content.substring(0, content.indexOf("。")) : content;
    }

    @Nullable
    @Override
    public String getThumbnail(String url, Document document) {
        String image = document.select("link[type=image/x-icon]").attr("href");
        //如果没有去匹配含有icon属性的logo
        String href = StrUtil.isEmpty(image) ? document.select("link[rel$=icon]").attr("href") : image;
        if (StrUtil.containsAny(url, "favicon")) {
            return url;
        }
        if (isValidUrl(!StrUtil.startWith(href, "http") ? "http:" + href : href)) {
            return href;
        }
        return StrUtil.format("{}/{}", url, StrUtil.removePrefix(href, "/"));
    }
}
