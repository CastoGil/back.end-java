package ar.com.codoacodo.repository;

import java.util.List;

import ar.com.codoacodo.entity.Orador;

public interface OradorRepository {
   public void save(Orador orador);
    Orador getById(int id_orador);
    List<Orador> findAll();
    public void update(Orador orador);
    public void delete(int id);
}
