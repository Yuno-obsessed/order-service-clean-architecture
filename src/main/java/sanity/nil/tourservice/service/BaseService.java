package sanity.nil.tourservice.service;

import java.util.List;

public interface BaseService<T, I> {

    void save(T entity);

    T get(I id);

    List<T> getAll();

    void update(T entity);

    boolean delete(I id);
}
