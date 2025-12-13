package com.parthiv.jpaTutorial.jpaTuts;

import com.parthiv.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.parthiv.jpaTutorial.jpaTuts.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaTutorialApplicationTests {

    @Autowired
    ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

    @Test
    void testRepository(){
        ProductEntity productEntity= ProductEntity.builder()
                .sku("nestle234")
                .title("Nestle Chocolate")
                .price(BigDecimal.valueOf(123.45))
                .quantity(12)
                .build();

        ProductEntity savedProductEntity= productRepository.save(productEntity);
        System.out.println(savedProductEntity);
    }

    @Test
    void getRepository(){
        List<ProductEntity> entities= productRepository.findAll();
        System.out.println(entities);
    }

    @Test
    void getRepositoryByTitle(){
        List<ProductEntity> entities= productRepository.findByTitle("Pepsi");
        System.out.println(entities);
    }
    @Test
    void getRepositoryAfterDate(){
        List<ProductEntity> entities= productRepository.findByCreatedAtAfter(LocalDateTime.of(2024, 1 ,1, 0,0,0));
        System.out.println(entities);
    }
    @Test
    void getRepositoryByQuantityAndPrice(){
        List<ProductEntity> entities= productRepository.findByQuantityAndPrice(4, BigDecimal.valueOf(23.45));
        System.out.println(entities);
    }
    @Test
    void getRepositoryByQuantityAndPriceInequality(){
        List<ProductEntity> entities= productRepository.findByQuantityGreaterThanOrPriceLessThan(4, BigDecimal.valueOf(23.45));
        System.out.println(entities);
    }
    @Test
    void getSingleFromRepository(){
        Optional<ProductEntity> productEntity= productRepository.findByTitleIgnoreCaseAndPrice("pepsi", BigDecimal.valueOf(14.4));
        productEntity.ifPresent(System.out::println);
    }
}
