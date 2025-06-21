package com.alura.literatura.Service;

import com.alura.literatura.Modelo.DatosLibro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObtenerDatos implements IObtenerDatos {

    private ObjectMapper datos = new ObjectMapper();

    @Override
    public <T> T covertirDatos(String json, Class<T> clase) {
        T libro = null;
        try {
            libro = datos.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return libro;
    }
}
