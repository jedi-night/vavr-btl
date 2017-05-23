package btl.services;

import btl.models.Personne;
import btl.enums.Agence;
import javaslang.collection.HashMap;
import javaslang.collection.Map;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonneService {

    private static final Map<Integer, Personne> ALL_PERSONNES = HashMap.of(
            1, Personne.builder().agence(Agence.NETAPSYS_BRETAGNE.getCode()).nom("Bathelemy").prenom("Manon").sexe("F").build(),
            2, Personne.builder().agence(Agence.NETAPSYS_BRETAGNE.getCode()).nom("Le Vot").prenom("Jean-Baptiste").sexe("M").build(),
            3, Personne.builder().agence(Agence.NETAPSYS_BRETAGNE.getCode()).nom("Lescalier").prenom("Vincent").sexe("M").build(),
            4, Personne.builder().agence(Agence.NETAPSYS_BRETAGNE.getCode()).nom("Bisson").prenom("Sébastien").sexe("M").build(),
            5, Personne.builder().agence(Agence.NETAPSYS_PARIS.getCode()).nom("Garandel").prenom("Cyril").sexe("M").build()
    );

    public javaslang.collection.List<Personne> getAllPersonnesByAgence(Integer idAgence) {
        return ALL_PERSONNES.filter(integerPersonneTuple2 -> integerPersonneTuple2._2.getAgence().equals(idAgence)).values().toList();
    }

    public List<Personne> getJavaAllPersonnesByAgence(Integer idAgence) {
        return ALL_PERSONNES.toJavaMap()
                .values()
                .stream()
                .filter(personne -> personne.getAgence().equals(idAgence))
                .collect(Collectors.toList());
    }
}
