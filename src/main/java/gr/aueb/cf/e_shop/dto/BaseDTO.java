package gr.aueb.cf.e_shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BaseDTO {
    @NotNull
    private Long id;
}
