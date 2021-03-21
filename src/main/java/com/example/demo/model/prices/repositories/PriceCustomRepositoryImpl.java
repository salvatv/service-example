/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.model.prices.repositories;

import com.example.demo.ws.prices.dto.PriceFilterDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Date;

@Repository
public class PriceCustomRepositoryImpl implements PriceCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<Prices> findByFilter(final PriceFilterDTO filter) {
        final JPAQueryFactory queryFactory = new JPAQueryFactory(this.entityManager);

        final QPrices prices = new QPrices("prices");

        return queryFactory.selectFrom(prices).where(this.generateWhere(prices, filter)).fetch();
    }

    private BooleanExpression generateWhere(final QPrices price, final PriceFilterDTO filter) {
        return new FindPriceBuilder(price)
                .date(filter.getDate())
                .productId(filter.getProductId())
                .brandId(filter.getBrandId())
                .build();
    }

    private class FindPriceBuilder {

        private final QPrices price;

        private BooleanExpression booleanExpression;

        FindPriceBuilder(final QPrices price) {
            this.booleanExpression = Expressions.asBoolean(true).isTrue();
            this.price = price;
        }

        FindPriceBuilder date(final Date date) {
            if (date != null) {
                this.booleanExpression = this.booleanExpression.and(this.price.startDate.before(date)
                        .and(this.price.endDate.after(date)));
            }
            return this;
        }

        FindPriceBuilder productId(final Integer productId) {
            if (productId != null) {
                this.booleanExpression = this.booleanExpression.and(this.price.productId.eq(productId));
            }
            return this;
        }

        FindPriceBuilder brandId(final Integer brandId) {
            if (brandId != null) {
                this.booleanExpression = this.booleanExpression.and(this.price.brandId.eq(brandId));
            }
            return this;
        }

        BooleanExpression build() {
            return this.booleanExpression;
        }

    }


}
