package com.example.demo.model;

import java.time.LocalDate;

public class Persona {
    private String nome;
    private String cognome;
    // le nuove classi per le date
    // Le Java API Time: LocalDate | LocalDateTime
    private LocalDate dob; // dob = date of birthDay
    private int height;

    public Persona() {
    }

    public Persona(String nome, String cognome, LocalDate dob, int height) {
        this.nome = nome;
        this.cognome = cognome;
        this.dob = dob;
        this.height = height;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getheight() {
        return height;
    }

    public void setheightr(int height) {
        this.height = height;
    }
}
