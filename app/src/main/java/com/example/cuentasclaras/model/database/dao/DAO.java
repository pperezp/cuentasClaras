package com.example.cuentasclaras.model.database.dao;

import java.util.List;

public interface DAO<T> {
    void create(T object);
    List<T> read();
    void update(T object);
    void delete(int id);

    T readByID(int id);
}
