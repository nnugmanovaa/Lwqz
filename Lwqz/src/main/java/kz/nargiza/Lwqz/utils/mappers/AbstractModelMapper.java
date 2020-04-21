package kz.nargiza.Lwqz.utils.mappers;

import java.util.List;

public abstract class AbstractModelMapper<E,D> {

    public abstract D toDto (E e);
    public abstract E toEntity(D d);
    public abstract List<D> toDtoList(List<E> eList);
    public abstract List<E> toEntityList(List<D> dList);

}
