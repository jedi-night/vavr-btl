package btl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
@JsonDeserialize(builder = Personne.PersonneBuilder.class)
public class Personne {

    private String nom;
    private String prenom;
    private String sexe;
    private Integer agence;

    // INNER CLASSES ***************************************************************************************************

    @JsonPOJOBuilder(withPrefix = "")
    public static final class PersonneBuilder {}

}
