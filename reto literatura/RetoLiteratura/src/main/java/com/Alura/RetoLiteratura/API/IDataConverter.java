package com.Alura.RetoLiteratura.API;

public interface IDataConverter {
    <T> T getData(String json, Class<T> tClassType);
}
