package com.oshiel.externals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.oshiel.beans.NewsAPIResponseBean;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NewsApiClient {

    private final String newsApikey = "f1610616944049acb929fe22507350a0";
    ;
    private final String newsApiEndpoint = "https://newsapi.org/v2/everything";

    /**
     * 最新ニュース記事取得
     *
     * @param topic
     * @throws IOException
     */
    public List<NewsAPIResponseBean> getNews(String topic) throws IOException {
        try {
            System.err.println("---------- " + topic + "の記事取得開始 ----------");
            // 最新ニュース記事取得
            RestTemplate restTemplate = new RestTemplate();
            String newsApiUrl = UriComponentsBuilder.fromHttpUrl(this.newsApiEndpoint)
                    .queryParam("q", topic)
                    .queryParam("sortBy", "publishedAt")
                    .queryParam("apiKey", this.newsApikey)
                    .build(false)
                    .toUriString();
            System.err.println("ニュースURL" + newsApiUrl);
            String newsResult = restTemplate.getForObject(newsApiUrl, String.class);
            System.err.println("ニュース記事取得" + newsResult);
            JSONObject root = new JSONObject(newsResult);
            String status = null;
            Integer totalResults = null;
            String title = null;
            String description = null;
            String url = null;
            String urlToImage = null;
            String publishedAt = null;

            List<NewsAPIResponseBean> articleList = new ArrayList<>();
            NewsAPIResponseBean newsBean = new NewsAPIResponseBean();
            status = root.getString("status");
            totalResults = root.getInt("totalResults");
            // ニュース記事取得失敗 または 取得記事が0件
            if (status != "ok" || totalResults == 0) {
            }
            JSONArray articlesObject = root.getJSONArray("articles");
            for (int i = 0; i < articlesObject.length(); i++) {
                JSONObject arrayElement = articlesObject.getJSONObject(i);
                title = arrayElement.getString("title");
                if (!arrayElement.isNull("description")) {
                    description = arrayElement.getString("description");
                } else {
                    description = null;
                }
                url = arrayElement.getString("url");
                if (!arrayElement.isNull("urlToImage")) {
                    urlToImage = arrayElement.getString("urlToImage");
                } else {
                    urlToImage = null;
                }
                publishedAt = arrayElement.getString("publishedAt");
                newsBean.setTitle(title);
                newsBean.setDescription(description);
                newsBean.setUrl(url);
                newsBean.setUrlToImage(urlToImage);
                newsBean.setPublishedAt(publishedAt);
                articleList.add(newsBean);
            }

            System.err.println("---------- " + topic + "の記事取得終了 ----------");
            return articleList;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}