package com.alura.literatura.Modelo;

public enum Idioma {
    ESPAÑOL("es"),
    INGLES("en"),
    FRANCES("fr"),
    PORTUGES("pt");

    public String abreviatura;

    Idioma(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public static Idioma value(String texto) {
        for(Idioma i: Idioma.values()){
            if(i.abreviatura.equalsIgnoreCase(texto)){
                return i;
            }
        }
        throw new IllegalArgumentException();
    }
}
