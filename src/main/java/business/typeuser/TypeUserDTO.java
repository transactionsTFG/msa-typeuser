package business.typeuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeUserDTO {
    private long id;
    private String name;
    private Boolean isActive;
}
