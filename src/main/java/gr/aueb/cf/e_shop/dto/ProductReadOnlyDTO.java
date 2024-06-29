package gr.aueb.cf.e_shop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReadOnlyDTO extends BaseDTO{

    private String productName;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String imageUrl;

    public ProductReadOnlyDTO(Long id, String productName, String description, Double price, Integer stockQuantity, String imageUrl) {
        setId(id);
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }
}
