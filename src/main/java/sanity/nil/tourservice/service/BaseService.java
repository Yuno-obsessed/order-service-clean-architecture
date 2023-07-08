package sanity.nil.tourservice.service;

import java.util.List;

public interface BaseService<T, I> {

    void save(T model);

    T get(I id);

    List<T> getAll();

    void update(T model);

    void delete(I id);
}
