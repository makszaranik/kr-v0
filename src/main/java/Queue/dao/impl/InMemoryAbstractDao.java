package Queue.dao.impl;

import Queue.dao.Dao;
import Queue.model.Queue;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class InMemoryAbstractDao<T> implements Dao<T> {
  protected Map<Integer, T> entities;
  protected Function<T, Integer> idGetter;
  protected BiConsumer<T, Integer> idSetter;
  protected InMemoryDatabase database;

  InMemoryAbstractDao(Map<Integer, T> entities, Function<T, Integer> idGetter, BiConsumer<T, Integer> idSetter, InMemoryDatabase database) {
    this.entities = entities;
    this.idGetter = idGetter;
    this.idSetter = idSetter;
    this.database = database;
  }

  @Override
  public T get(Integer id) {
      return this.entities.get(id);
  }

  @Override
  public Collection<T> findAll() {
      return this.entities.values();
  }

  @Override
  public void insert(T entitie, boolean generateId) {
      if(generateId){
          int maxId = this.entities.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
          this.idSetter.accept(entitie, maxId+1);
      }
      this.entities.put(this.idGetter.apply(entitie), entitie);
  }

  @Override
  public void delete(T entitie) {
      this.entities.remove(this.idGetter.apply(entitie));
  }

  @Override
  public void update(T entitie) {
      this.entities.put(this.idGetter.apply(entitie), entitie);
  }
}
