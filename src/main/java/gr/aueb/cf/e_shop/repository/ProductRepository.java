package gr.aueb.cf.e_shop.repository;

import gr.aueb.cf.e_shop.Model.Product;

import gr.aueb.cf.e_shop.dto.ProductReadOnlyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);

    // to do
    /*Page<Product> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Product> findAllByOrderByPriceDesc(Pageable pageable);*/
}
