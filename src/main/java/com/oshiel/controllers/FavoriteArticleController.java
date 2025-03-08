

package com.oshiel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oshiel.beans.FavoriteArticleBean;
import com.oshiel.beans.FavoriteArticleRequestBean;
import com.oshiel.services.FavoriteArticleService;

@RestController
@RequestMapping("/bookmark")
public class FavoriteArticleController {

    private final FavoriteArticleService favoriteArticleService;


    @Autowired
    public FavoriteArticleController(FavoriteArticleService favoriteArticleService) {
        this.favoriteArticleService = favoriteArticleService;
    }

    @GetMapping("/{oshielId}")
    public ResponseEntity<FavoriteArticleBean> getArticleList(@PathVariable String oshielId) {
        FavoriteArticleBean favoriteArticles = this.favoriteArticleService.getByOshielId(oshielId);
        return ResponseEntity.ok(favoriteArticles);
    }

    @PostMapping()
    public ResponseEntity<FavoriteArticleBean> addFavoriteArticle(@RequestBody FavoriteArticleRequestBean favoriteArticleBean) {
        HttpStatus status = HttpStatus.OK;
        System.out.println("favoriteArticleBean===============>" + favoriteArticleBean);
        this.favoriteArticleService.setFavoriteArticle(favoriteArticleBean);
        return new ResponseEntity<>(status);
    }
}
