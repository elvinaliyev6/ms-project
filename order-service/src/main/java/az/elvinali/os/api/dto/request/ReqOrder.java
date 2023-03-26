package az.elvinali.os.api.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqOrder {
    @NotBlank
    String name;

    @Min(value = 1)
    @Max(value = 100)
    Integer quantity;

    @Min(value = 1)
    @Max(value = 100)
    Double price;

}
