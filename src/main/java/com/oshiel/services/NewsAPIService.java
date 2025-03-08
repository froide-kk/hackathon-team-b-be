package com.oshiel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.oshiel.beans.NewsAPIResponseBean;
import com.oshiel.externals.NewsApiClient;

@Service
public class NewsAPIService {

    private final NewsApiClient newsApiClient;

    /**
     * ニュース記事クライアント
     *
     * @param newsApiClient
     */
    @Autowired
    public NewsAPIService(NewsApiClient newsApiClient) {
        this.newsApiClient = newsApiClient;
    }

    @Transactional
    public List<NewsAPIResponseBean> getArticlesByNewsApi(String topic) {
        List<NewsAPIResponseBean> response = null;
        try {
            return this.newsApiClient.getNews(topic);
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        }
    }
}
