package gr.aueb.cf.e_shop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductInsertDTO {

    @NotBlank(message = "Το όνομα του προϊόντος δεν μπορεί να είναι κενό")
    @Size(max = 255, message = "Το όνομα του προϊόντος δεν μπορεί να υπερβαίνει τους 255 χαρακτήρες")
    private String productName;

    @Size(max = 1000, message = "Η περιγραφή του προϊόντος δεν μπορεί να υπερβαίνει τους 1000 χαρακτήρες")
    private String description;

    @NotNull(message = "Η τιμή του προϊόντος δεν μπορεί να είναι κενή")
    @Min(value = 0, message = "Η τιμή του προϊόντος δεν μπορεί να είναι αρνητική")
    private Double price;

    @NotNull(message = "Η ποσότητα του προϊόντος δεν μπορεί να είναι κενή")
    @Min(value = 0, message = "Η ποσότητα στοκ δεν μπορεί να είναι αρνητική")
    private Integer stockQuantity;

    @Size(max = 512, message = "Το URL της εικόνας δεν μπορεί να υπερβαίνει τους 255 χαρακτήρες")
    private String imageUrl;
}
