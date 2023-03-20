package az.elvinali.os.api.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RespOrder {
    String name;

    Integer quantity;

    Double price;
}
