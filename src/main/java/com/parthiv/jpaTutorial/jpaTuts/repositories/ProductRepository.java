package com.parthiv.jpaTutorial.jpaTuts.repositories;

import com.parthiv.jpaTutorial.jpaTuts.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository <ProductEntity, Long> {

    List<ProductEntity> findByTitle(String pepsi);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);

    List<ProductEntity> findByQuantityAndPrice(int i, BigDecimal bigDecimal);

    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(int i, BigDecimal bigDecimal);

    Optional<ProductEntity> findByTitleIgnoreCaseAndPrice(String title, BigDecimal bigDecimal);

//    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal bigDecimal);

    @Query("select e from ProductEntity e where e.title=?1 and e.price=?2") //JPQL Custom Query
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);
}
