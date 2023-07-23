package com.simplesolutions.courseservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * General mapper class to be extended for mapper implementation
 *
 * @param <A>
 * @param <B>
 */
public abstract class Mapper<A, B> {

    public abstract B map(A entity);

    public List<B> map(List<A> entitylist) {
        return entitylist.stream().map(e -> this.map(e)).collect(Collectors.toList());
    }
}
