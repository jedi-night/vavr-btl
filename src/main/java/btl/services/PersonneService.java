package btl.services;

import btl.Personne;
import btl.enums.Agence;
import javaslang.Tuple2;
import javaslang.collection.HashMap;
import javaslang.collection.Map;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonneService {

    private static final Map<Integer, Personne> ALL_PERSONNES = HashMap.empty();

    private PersonneService() {
//        ALL_PERSONNES
//                .put(1, Personne.builder().agence(Agence.NETAPSYS_BRETAGNE.getCode()).nom("Bathelemy").prenom("Manon").sexe("F").build())
//                .put(2, Personne.builder().agence(Agence.NETAPSYS_BRETAGNE.getCode()).nom("Le Vot").prenom("Jean-Baptiste").sexe("M").build())
//                .put(3, Personne.builder().agence(Agence.NETAPSYS_BRETAGNE.getCode()).nom("Lescalier").prenom("Vincent").sexe("M").build())
//                .put(4, Personne.builder().agence(Agence.NETAPSYS_BRETAGNE.getCode()).nom("Bisson").prenom("Sébastien").sexe("M").build())
//                .put(5, Personne.builder().agence(Agence.NETAPSYS_PARIS.getCode()).nom("Garandel").prenom("Cyril").sexe("M").build());
    }

    public javaslang.collection.List<Tuple2<Integer, Personne>> getAllPersonnesByAgence(Integer idAgence) {
        return ALL_PERSONNES
                .filter(integerPersonneTuple2 -> integerPersonneTuple2._1.equals(idAgence))
                .toList();
    }

    public List<Personne> getJavaAllPersonnesByAgence(Integer idAgence) {
        return null;
//        return ALL_PERSONNES.toJavaMap()
//                .values()
//                .stream()
//                .filter(p -> p.getAgence().equals(idAgence))
//                .collect(Collectors.toList());
    }
}
