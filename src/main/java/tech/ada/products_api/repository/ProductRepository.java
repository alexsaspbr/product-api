package tech.ada.products_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.products_api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
