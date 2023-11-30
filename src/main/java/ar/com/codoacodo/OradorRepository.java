package ar.com.codoacodo;

import java.util.List;

public interface OradorRepository {
   public void save(Orador orador);
    Orador getById(int id_orador);
    List<Orador> findAll();
    public void update(Orador orador);
    public void delete(int id);
}
