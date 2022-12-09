package com.example.demo.controller;

import com.example.demo.model.Persona;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    @GetMapping("/prima-chiamata")
    //RequestMapping quando la chiamata esula dai canoni standard e si vuol chiamare qualcosa di particolare
    public String demo1() {
        return "ciao da GetMapping";
    }

    // passagio di parametri
    // @RequestParam intercetta parametri nell'url col medesimo nome
    @GetMapping("/seconda-chiamata")    // localhost:8083/seconda-chiamata?nome=Pippo&cognome=Rossi
    //public String demo2(@RequestParam String nome, @RequestParam String cognome){
    public String demo2(@RequestParam(required = true) String nome, @RequestParam(required = false) String cognome) {
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
        Persona persona = new Persona("Dante", "Alighieri", LocalDate.of(1985, 12, 7), 164);
        return persona; // restituisce Json // {"nome":"Dante","cognome":"Alighieri","dob":"1985-12-07","height":164}
    }

    // passagio di Collection
    @GetMapping("persone")      // localhost:8083/persone
    // non sono le url delle pagine web
    // noi metteremo le end-point
    public List<Persona> getPersone() {
        List<Persona> persone = new ArrayList();
        Persona per1 = new Persona("Dante", "Alighieri", LocalDate.of(1985, 12, 7), 164);
        Persona per2 = new Persona("Dantone", "Aligh", LocalDate.of(1725, 4, 2), 157);
        persone.add(per1);
        persone.add(per2);
        return persone; // Json {"nome":"Dante","cognome":"Alighieri","dob":"1985-12-07","height":164}
    }

    // Post
    // come se allegassimo l'oggetto alla richiesta
    @PostMapping("persona")
    public List<Persona> addPersone(@RequestBody Persona persona) {
        List<Persona> persone = new ArrayList();
        Persona per1 = new Persona("Dante", "Alighieri", LocalDate.of(1985, 12, 7), 164);
        Persona per2 = new Persona("Dantone", "Aligh", LocalDate.of(1725, 4, 2), 157);
        persone.add(per1);
        persone.add(per2);

        // autoIstanziato dalla RequestBody
        persone.add(persona);

        // Modalità manuale
        // la regola vuole che non si usino i nostri ... personali
        // ma si basa su classi di appoggio, così dette di request !!!
        Persona p3 = new Persona();
        persona.setNome(persona.getNome().toUpperCase());
        persona.setCognome(persona.getCognome().toUpperCase());
        persona.setDob(persona.getDob());
        persona.setheightr(persona.getheight());
        persone.add(p3);


        return persone; // restituisce Json // {"nome":"Dante","cognome":"Alighieri","dob":"1985-12-07","height":164}
    }

    // PatchMapping
    // fa una modifica parziale della risorsa
    @PatchMapping("persone")      // localhost:8083/persone
    // modifichiamo un parametro della risorsa
    public List<Persona> patchPersone(@RequestParam int incrementaAltezza) {
        List<Persona> persone = new ArrayList();
        Persona per1 = new Persona("Dante", "Alighieri", LocalDate.of(1985, 12, 7), 164);
        Persona per2 = new Persona("Dantone", "Aligh", LocalDate.of(1725, 4, 2), 157);
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
    // dobbiamo definire il metodo che useremo, cosa produce (cio che arriva in input, cosa consuma
    // va ilmime tipe
    @RequestMapping(method = RequestMethod.GET, value ="/chiamata", consumes="text/html", produces = "text/html")
    public String demo2(){
        return "ciao da RequestMapping";
    }
     */
}
