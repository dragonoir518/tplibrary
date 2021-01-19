package fr.training.spring.Library.infrastructure;

import javax.persistence.Embeddable;

@Embeddable
public class LibraryAddressJPA {
        private int numero;
        private String rue;
        private int codePostal;
        private String ville;
}
