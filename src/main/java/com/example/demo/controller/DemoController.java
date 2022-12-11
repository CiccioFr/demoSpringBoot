package com.example.demo.controller;

import com.example.demo.model.Persona;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
// @RestController definiamo scopo dela classe:
// gestire e configurare gli end-point
// ci permette di generare end-point richiamabili via HTTP
public class DemoController {

    // fa una mappatura di tipo Get
    @GetMapping("/prima-chiamata")
    public String demo1() {
        return "ciao da GetMapping";
    }

    // passagio di parametri
    // localhost:8083/seconda-chiamata?nome=Pippo&cognome=Rossi
    @GetMapping("/seconda-chiamata")
    // @RequestParam intercetta parametri nell'url col medesimo nome
    // parametri annotati con @RequestParam sono obbligatori di default `required = true`
    // defaultValue permette d’impostare un valore di default
    // nota Valorizzando defaultValue, l’elemento required dell’annotazione verrà implicitamente impostato a false.
    public String demo2(@RequestParam(required = true) String nome,
                        @RequestParam(required = false, defaultValue = "Ciccio") String cognome) {
        return "ciao " + nome + " " + cognome;
    }

    // localhost:8083/terza-chiamata/3/8
    // 192.168.1.16:8083/terza-chiamata/3/77
    @GetMapping("/terza-chiamata/{a}/{b}")
    public int demo3(@PathVariable int a, @PathVariable int b) {
        return a + b;
    }

    // passaggio di oggetti
    @GetMapping("persona")      // localhost:8083/persona
    public Persona getPersona() {
        Persona persona = new Persona("Dante", "Alighieri",
                LocalDate.of(1985, 12, 7), 164);
        return persona; // Json: {"nome":"Dante","cognome":"Alighieri","dob":"1985-12-07","height":164}
    }

    // passagio di Collection
    @GetMapping("persone")      // localhost:8083/persone
    // non sono le url delle pagine web
    // noi metteremo le end-point
    public List<Persona> getPersone() {
        List<Persona> persone = new ArrayList();
        Persona per1 = new Persona("Dante", "Alighieri",
                LocalDate.of(1985, 12, 7), 164);
        Persona per2 = new Persona("Dantone", "Aligh",
                LocalDate.of(1725, 4, 2), 157);
        persone.add(per1);
        persone.add(per2);
        return persone; /* Json
                [ { "nome" : "Dante", "cognome" : "Alighieri", "dob" : "1985-12-07", "height" : 164 },
                { "nome" : "Dantone", "cognome" : "Aligh", "dob" : "1725-04-02", "height" : 157 } ] */
    }

    // Post
    // come se allegassimo l'oggetto alla richiesta
    @PostMapping("persona")
    public List<Persona> addPersone(@RequestBody Persona persona) {
        List<Persona> persone = new ArrayList();
        Persona per1 = new Persona("Dante", "Alighieri",
                LocalDate.of(1985, 12, 7), 164);
        Persona per2 = new Persona("Dantone", "Light",
                LocalDate.of(1725, 4, 2), 157);
        persone.add(per1);
        persone.add(per2);

        // autoIstanziato dalla RequestBody
        persone.add(persona);

        // Modalità manuale
        // la regola vuole che non si usino i nostri ... personali
        // ma si basa su classi di appoggio, così dette di request !!!
        Persona p3 = new Persona();
        p3.setNome(persona.getNome().toUpperCase());
        p3.setCognome(persona.getCognome().toUpperCase());
        p3.setDob(persona.getDob());
        p3.setheightr(persona.getheight());
        persone.add(p3);


        return persone; // restituisce Json // {"nome":"Dante","cognome":"Alighieri","dob":"1985-12-07","height":164}
    }

    // PatchMapping
    // fa una modifica parziale della risorsa
    @PatchMapping("persone")      // localhost:8083/persone
    // modifichiamo un parametro della risorsa
    public List<Persona> patchPersone(@RequestParam int incrementaAltezza) {
        List<Persona> persone = new ArrayList();
        Persona per1 = new Persona("Dante", "Alighieri",
                LocalDate.of(1985, 12, 7), 164);
        Persona per2 = new Persona("Dantone", "Aligh",
                LocalDate.of(1725, 4, 2), 157);
        persone.add(per1);
        persone.add(per2);

        List<Persona> personaNew = new ArrayList();
        for (Persona p : persone) {
            p.setheightr((p.getheight()) + incrementaAltezza);
            personaNew.add(p);
        }
        return personaNew;
    }

    //@DeleteMapping
    // delite non si fa quasi mai..
    // per le tabelle referenziate all'oggetto di cancellazione

    /*
    @RequestMapping quando la chiamata esula dai canoni standard e si vuol chiamare qualcosa di particolare
    , cosa consuma
    - Sintassi diversa, bisogna specificare manualmente:
      definire il metodo che useremo (Get/Post/..), in @GetMapping è già definito nel nome
      la uri (es. "/chiamata")
      cosa produce (ciò che arriva in input: il tipo di dato che si aspetta)
      cosa consuma (il tipo di dato che produce)
      // va ilmime tipe
    @RequestMapping(method = RequestMethod.GET, value ="/chiamata", consumes="text/html", produces = "text/html")
    public String demo2(){
        return "ciao da RequestMapping";
    }
     */
}
