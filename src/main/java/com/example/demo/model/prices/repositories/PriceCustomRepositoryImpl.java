/*
 * Created by Salvador Torres Velasco
 * 2021
 */

package com.example.demo.model.prices.repositories;

import com.example.demo.model.brands.repositories.QBrand;
import com.example.demo.model.products.repositories.QProduct;
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
    public Collection<Price> findByFilter(final PriceFilterDTO filter) {
        final JPAQueryFactory queryFactory = new JPAQueryFactory(this.entityManager);

        final QPrice price = new QPrice("price");
        final QBrand brand = new QBrand("brand");
        final QProduct product = new QProduct("product");

        return queryFactory.selectFrom(price).join(price.brand, brand).join(price.product, product).where(this.generateWhere(price, filter)).fetch();
    }

    private BooleanExpression generateWhere(final QPrice price, final PriceFilterDTO filter) {
        return new FindPriceBuilder(price)
                .date(filter.getDate())
                .productIds(filter.getProductIds())
                .brandIds(filter.getBrandIds())
                .build();
    }

    private class FindPriceBuilder {

        private final QPrice price;

        private BooleanExpression booleanExpression;

        FindPriceBuilder(final QPrice price) {
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

        FindPriceBuilder productIds(final Collection<Integer> productIds) {
            if (!productIds.isEmpty()) {
                this.booleanExpression = this.booleanExpression.and(this.price.product.id.in(productIds));
            }
            return this;
        }

        FindPriceBuilder brandIds(final Collection<Integer> brandIds) {
            if (!brandIds.isEmpty()) {
                this.booleanExpression = this.booleanExpression.and(this.price.brand.id.in(brandIds));
            }
            return this;
        }

        BooleanExpression build() {
            return this.booleanExpression;
        }

    }


}
