package com.yihang.ultrat.common.utils.url;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.yihang.ultrat.common.utils.FutureUtils;
import com.yihang.ultrat.domain.UrlInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractUrlMatcher implements UrlMatcher {
    //链接识别的正则
    private static final Pattern PATTERN = Pattern.compile("((http|https)://)?(www.)?([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?");

    @Override
    public Map<String, UrlInfo> getUrlContentMap(String content) {
        if (StrUtil.isBlank(content)) {
            return new HashMap<>();
        }
        List<String> matchList = ReUtil.findAll(PATTERN, content, 0);

        List<CompletableFuture<Pair<String, UrlInfo>>> futures = matchList.stream().map(url -> CompletableFuture.supplyAsync(() -> {
            UrlInfo urlInfo = getContent(url);
            return Objects.isNull(urlInfo) ? null : Pair.of(url, urlInfo);
        })).collect(Collectors.toList());
        CompletableFuture<List<Pair<String, UrlInfo>>> future = FutureUtils.sequenceNonNull(futures);
        return future.join().stream().collect(Collectors.toMap(Pair::getKey, Pair::getValue, (a, b) -> a));
    }

    @Override
    public UrlInfo getContent(String url) {
        Document document = getUrlDocument(url);
        if (Objects.isNull(document)) {
            return null;
        }
        return UrlInfo.builder()
            .title(getTitle(document))
            .description(getDescription(document))
            .thumbnail(getThumbnail(assemble(url), document)).build();
    }

    protected Document getUrlDocument(String url) {
        try {
            Connection connect = Jsoup.connect(url);
            connect.timeout(2000);
            return connect.get();
        } catch (IOException e) {
            log.error("find error:url:{}", url, e);
        }
        return null;
    }

    private String assemble(String url) {
        if (!StrUtil.startWith(url, "http")) {
            return "http://" + url;
        }
        return url;
    }

    public static boolean isValidUrl(String href) {
        URL url;
        int state;
        String fileType;
        try {
            url = new URL(href);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            state = httpURLConnection.getResponseCode();
            fileType = httpURLConnection.getHeaderField("Content-Disposition");
            //如果成功200，缓存304，移动302都算有效链接，并且不是下载链接
            if ((state == 200 || state == 302 || state == 304) && fileType == null) {
                return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}
