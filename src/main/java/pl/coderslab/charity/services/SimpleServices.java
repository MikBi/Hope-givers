package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SimpleServices <T> {

    T add(T t);

    void delete(int id);

    void update(T t);

    List<T> all();

    T single(int id);
}