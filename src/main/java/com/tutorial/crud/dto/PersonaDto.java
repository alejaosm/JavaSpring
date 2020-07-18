package com.tutorial.crud.dto;

public class PersonaDto {


    private String nombre;
    private String birth;

    public PersonaDto() {
    }

    public PersonaDto(String nombre, String birth) {
        this.nombre = nombre;
        this.birth = birth;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
