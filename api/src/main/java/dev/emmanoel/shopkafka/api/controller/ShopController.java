package dev.emmanoel.shopkafka.api.controller;

import dev.emmanoel.shopkafka.api.domain.Shop;
import dev.emmanoel.shopkafka.api.domain.ShopItem;
import dev.emmanoel.shopkafka.api.domain.dto.ShopDto;
import dev.emmanoel.shopkafka.api.kafka.KafkaClient;
import dev.emmanoel.shopkafka.api.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    private final KafkaClient kafkaClient;
    private final ShopRepository shopRepository;

    @GetMapping
    public List<ShopDto> getShop() {
        return shopRepository
            .findAll()
            .stream()
            .map(ShopDto::convert)
            .collect(Collectors.toList());
    }

    @PostMapping
    public ShopDto saveShop(@RequestBody ShopDto shopDto) {
        shopDto.setIdentifier(UUID.randomUUID().toString());
        shopDto.setDateShop(LocalDate.now());
        shopDto.setStatus("PENDING");
        Shop shop = Shop.convert(shopDto);
        for (ShopItem shopItem : shop.getItems()) {
            shopItem.setShop(shop);
        }
        final ShopDto shopDtoSaved = ShopDto.convert(shopRepository.save(shop));
        kafkaClient.sendMessage(shopDto);
        return shopDtoSaved;
    }
}
