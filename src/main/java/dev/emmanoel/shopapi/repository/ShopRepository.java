package dev.emmanoel.shopapi.repository;

import dev.emmanoel.shopapi.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {

}
