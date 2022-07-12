package dev.emmanoel.shopkafka.validator;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ShopDto {
    private String identifier;
    private LocalDate dateShop;
    private String status;
    private List<ShopItemDto> items = new ArrayList<>();
}
