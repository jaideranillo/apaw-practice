package es.upm.miw.apaw_practice.domain.services.shop;

import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.shop.ArticleCreation;
import es.upm.miw.apaw_practice.domain.models.shop.ArticlePriceUpdating;
import es.upm.miw.apaw_practice.domain.out_ports.shop.ArticlePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ArticleService {

    private ArticlePersistence articlePersistence;

    @Autowired
    public ArticleService(ArticlePersistence articlePersistence) {
        this.articlePersistence = articlePersistence;
    }

    public Article create(ArticleCreation articleCreation) {
        return this.articlePersistence.create(articleCreation.toArticle());
    }

    public void updatePrices(List<ArticlePriceUpdating> articlePriceUpdatingList) {
        articlePriceUpdatingList
                .forEach(articleNewPrice -> {
                    Article article = this.articlePersistence.readByBarcodeAssure(articleNewPrice.getBarcode());
                    article.setPrice(articleNewPrice.getPrice());
                    this.articlePersistence.update(article);
                });
    }

    public Stream<Article> findByProviderAndPriceGreaterThan(String provider, BigDecimal price) {
        return this.articlePersistence.findByProviderAndPriceGreaterThan(provider, price);
    }
}
