package it.nadr.EsercizioDebito.bootstrap;


import it.nadr.EsercizioDebito.domain.Organizzazione;
import it.nadr.EsercizioDebito.domain.Rilevamento;
import it.nadr.EsercizioDebito.repositories.OrganizzazioneRepository;
import it.nadr.EsercizioDebito.repositories.RilevamentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Component
public class BootStrapData implements CommandLineRunner {

    private final OrganizzazioneRepository organizzazioneRepository;
    private final RilevamentoRepository rilevamentoRepository;

    public BootStrapData(OrganizzazioneRepository organizzazioneRepository, RilevamentoRepository rilevamentoRepository) {
        this.organizzazioneRepository = organizzazioneRepository;
        this.rilevamentoRepository = rilevamentoRepository;
    }


    @Override
    public void run(String... args) throws Exception{
        System.out.println("Bootstrap - start");
        LocalDate data = LocalDate.now();
        Organizzazione ger  = new Organizzazione();
        Organizzazione spn  = new Organizzazione();
        Organizzazione fr  = new Organizzazione();
        Organizzazione it  = new Organizzazione();
        Organizzazione neth  = new Organizzazione();
        Organizzazione euro  = new Organizzazione();


        ger.setNazione("Germania");
        spn.setNazione("Spagna");
        fr.setNazione("Francia");
        it.setNazione("Italia");
        neth.setNazione("Paesi Bassi");
        euro.setNazione("Euro");

        Organizzazione gerSaved = organizzazioneRepository.save(ger);
        Organizzazione spnSaved = organizzazioneRepository.save(spn);
        Organizzazione frSaved = organizzazioneRepository.save(fr);
        Organizzazione itSaved = organizzazioneRepository.save(it);
        Organizzazione nethSaved = organizzazioneRepository.save(neth);
        Organizzazione euroSaved  = organizzazioneRepository.save(euro);

        String filePath = "C:\\Users\\NadrAbouMoustafa\\Downloads\\prestiti_organizzazioni.csv";
        File CSVFile = new File(filePath);
        Scanner sc = new Scanner(CSVFile);
        sc.useDelimiter("[;\\n]");

        for(int i=0;sc.hasNext();i++)
        {
            String lettura = sc.next();
            if(i==0){
                data = conversioneDateTime(lettura);

            }
            else{
                Rilevamento r1 = new Rilevamento();
                r1.setData_Rilevamento(data);
                r1.setDebito(Double.parseDouble(lettura));
                switch (i){
                    case 1:
                        r1.setOrganizzazione(ger);
                        break;
                    case 2:
                        r1.setOrganizzazione(spn);
                        break;
                    case 3:
                        r1.setOrganizzazione(fr);
                        break;
                    case 4:
                        r1.setOrganizzazione(it);
                        break;
                    case 5:
                        r1.setOrganizzazione(neth);
                        break;
                    case 6:
                        r1.setOrganizzazione(euro);
                }
                rilevamentoRepository.save(r1);
            }
            if(i==6){
                i=-1;
                sc.nextLine();
            }
        }
        sc.close();
        System.out.println("Bootstrap - End");
    }

    public LocalDate conversioneDateTime(String dataString){
        String[] subStrings = dataString.split("-");
        int giorno = Integer.parseInt(subStrings[0]);
        int anno = Integer.parseInt(subStrings[2]) < 100 ? Integer.parseInt(subStrings[2])+2000: Integer.parseInt(subStrings[2]);
        int mese = switch (subStrings[1].toUpperCase()) {
            case "GEN" -> 1;
            case "FEB" -> 2;
            case "MAR" -> 3;
            case "APR" -> 4;
            case "MAG" -> 5;
            case "GIU" -> 6;
            case "LUG" -> 7;
            case "AGO" -> 8;
            case "SET" -> 9;
            case "OTT" -> 10;
            case "NOV" -> 11;
            case "DIC" -> 12;
            default -> 0;
        };

        return LocalDate.of(anno,mese,giorno);
    }

}
