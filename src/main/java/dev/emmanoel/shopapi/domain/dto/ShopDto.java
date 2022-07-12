package dev.emmanoel.shopapi.domain.dto;

import dev.emmanoel.shopapi.domain.Shop;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ShopDto {
    private String identifier;
    private String status;
    private LocalDate dateShop;
    private List<ShopItemDto> items;

    public static ShopDto convert(Shop shop) {
        ShopDto shopDto = new ShopDto();
        shopDto.setIdentifier(shop.getIdentifier());
        shopDto.setDateShop(shop.getDateShop());
        shopDto.setStatus(shop.getStatus());
        shopDto.setItems(shop
            .getItems()
            .stream()
            .map(ShopItemDto::convert)
            .collect(Collectors.toList()));
        return shopDto;
    }
}
