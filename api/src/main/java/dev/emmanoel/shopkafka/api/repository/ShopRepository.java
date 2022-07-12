package dev.emmanoel.shopkafka.api.repository;

import dev.emmanoel.shopkafka.api.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {

}
