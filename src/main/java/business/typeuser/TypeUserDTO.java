package business.typeuser;

import lombok.Data;

@Data
public class TypeUserDTO {
    private long id;
    private String name;
    private Boolean isActive;
}
