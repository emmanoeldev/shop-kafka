package dev.emmanoel.shopkafka.api.domain.dto;

import dev.emmanoel.shopkafka.api.domain.ShopItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopItemDto {
    private String productIdentifier;
    private Integer amount;
    private Float price;

    public static ShopItemDto convert(ShopItem shopItem) {
        ShopItemDto shopItemDto = new ShopItemDto();
        shopItemDto.setProductIdentifier(
            shopItem.getProductIdentifier());
        shopItemDto.setAmount(shopItem.getAmount());
        shopItemDto.setPrice(shopItem.getPrice());
        return shopItemDto;
    }
}
