package it.nadr.EsercizioDebito.repositories;

import it.nadr.EsercizioDebito.domain.Organizzazione;
import it.nadr.EsercizioDebito.domain.Rilevamento;
import org.springframework.data.repository.CrudRepository;

public interface OrganizzazioneRepository extends CrudRepository<Organizzazione,Long> {
}
