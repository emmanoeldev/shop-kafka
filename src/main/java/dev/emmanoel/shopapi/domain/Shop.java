package dev.emmanoel.shopapi.domain;

import dev.emmanoel.shopapi.domain.dto.ShopDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;
    private String status;

    @Column(name = "date_shop")
    private LocalDate dateShop;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shop")
    private List<ShopItem> items;

    public static Shop convert(ShopDto shopDto) {
        Shop shop = new Shop();
        shop.setIdentifier(shopDto.getIdentifier());
        shop.setStatus(shopDto.getStatus());
        shop.setDateShop(shopDto.getDateShop());
        shop.setItems(shopDto
            .getItems()
            .stream()
            .map(ShopItem::convert)
            .collect(Collectors.toList()));
        return shop;
    }
}