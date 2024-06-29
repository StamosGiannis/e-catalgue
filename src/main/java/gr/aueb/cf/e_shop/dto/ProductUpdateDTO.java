package gr.aueb.cf.e_shop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ProductUpdateDTO extends BaseDTO {



    @Size(max = 255, message = "Το όνομα του προϊόντος δεν μπορεί να υπερβαίνει τους 255 χαρακτήρες")
    private String productName;

    @Size(max = 1000, message = "Η περιγραφή του προϊόντος δεν μπορεί να υπερβαίνει τους 1000 χαρακτήρες")
    private String description;


    @Min(value = 0, message = "Η τιμή του προϊόντος δεν μπορεί να είναι αρνητική")
    private Double price;


    @Min(value = 0, message = "Η ποσότητα του προϊόντος δεν μπορεί να είναι αρνητική")
    private Integer stockQuantity;

    @Size(max = 512, message = "Το URL της εικόνας δεν μπορεί να υπερβαίνει τους 512 χαρακτήρες")
    private String imageUrl;


    public ProductUpdateDTO(Long id, String productName, String description, Double price, Integer stockQuantity, String imageUrl) {
        this.setId(id);
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }
}
