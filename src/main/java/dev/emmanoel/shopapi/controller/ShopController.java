package dev.emmanoel.shopapi.controller;

import dev.emmanoel.shopapi.domain.Shop;
import dev.emmanoel.shopapi.domain.ShopItem;
import dev.emmanoel.shopapi.domain.dto.ShopDto;
import dev.emmanoel.shopapi.repository.ShopRepository;
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
        return ShopDto.convert(shopRepository.save(shop));
    }
}
