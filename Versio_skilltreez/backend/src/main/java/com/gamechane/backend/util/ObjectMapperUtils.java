package com.gamechane.backend.util;

import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ObjectMapperUtils {
    private static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper = new ModelMapper();
    }

    private ObjectMapperUtils(){}

    public static <D, T> D map(final T entity, Class<D> outClass){
        return modelMapper.map(entity, outClass);
    }

    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outClass){
        return entityList.stream()
                .map(entity -> map(entity, outClass))
                .collect(Collectors.toList());
    }

    public static <D, T> List<D> mapAll(final Iterable<T> entityList, Class<D> outClass){
        return StreamSupport.stream(entityList.spliterator(), false)
                .map(entity -> map(entity, outClass))
                .collect(Collectors.toList());
    }
}
