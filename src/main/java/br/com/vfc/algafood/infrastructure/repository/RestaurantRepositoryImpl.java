package br.com.vfc.algafood.infrastructure.repository;

import br.com.vfc.algafood.domain.model.Restaurant;
import br.com.vfc.algafood.domain.repository.RestaurantRepository;
import br.com.vfc.algafood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static br.com.vfc.algafood.infrastructure.repository.spec.RestaurantSpecs.freeShippingFee;
import static br.com.vfc.algafood.infrastructure.repository.spec.RestaurantSpecs.similarName;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    private RestaurantRepository restaurantRepository;

    @Autowired
    @Lazy
    public RestaurantRepositoryImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> find(String name, BigDecimal initialValue, BigDecimal finalValue) {

        var jpql = new StringBuilder();
        jpql.append("from Restaurant where 1 = 1 ");

        var parameters = new HashMap<String, Object>();

        if (StringUtils.hasLength(name)) {
            jpql.append("and nome like :nome ");
            parameters.put("name", "%" + name + "%");
        }

        if (initialValue != null) {
            jpql.append("and shippingFee >= :initialValue ");
            parameters.put("initialValue", initialValue);
        }

        if (finalValue != null) {
            jpql.append("and shippingFee <= :finalValue ");
            parameters.put("finalValue", finalValue);
        }

        TypedQuery<Restaurant> query = entityManager.createQuery(jpql.toString(), Restaurant.class);
        parameters.forEach(query::setParameter);

        return query.getResultList();
    }

    @Override
    public List<Restaurant> findUsingCriteria(String name, BigDecimal initialValue, BigDecimal finalValue) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteriaQuery = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteriaQuery.from(Restaurant.class);
        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasLength(name)) {
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        if (initialValue != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("shippingFee"), initialValue));
        }

        if (finalValue != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("shippingFee"), finalValue));
        }
        criteriaQuery.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Restaurant> findByFreeShippingFeeUsingSpecs(String name) {

        return restaurantRepository.findAll(freeShippingFee()
                .and(similarName(name)));
    }
}
