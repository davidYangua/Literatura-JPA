package com.alura.literatura.Service;

public interface IObtenerDatos {

    <T> T covertirDatos(String json, Class<T> clase);
}
