package az.elvinali.os.api.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqOrder {
    @NotBlank
    String name;

    @NotBlank
    Integer quantity;

    @NotBlank
    Double price;

}
