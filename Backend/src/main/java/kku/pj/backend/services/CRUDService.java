package kku.pj.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface CRUDService <E,ID>{
    E add(E item);
    E get(ID id);
    E update(E item);
    boolean remove(E item);
    Page<E> gets(int page, int size, Sort sort);
}
