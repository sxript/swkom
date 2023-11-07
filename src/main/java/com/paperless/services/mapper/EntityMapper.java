package com.paperless.services.mapper;

import org.openapitools.jackson.nullable.JsonNullable;

public interface EntityMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);


    default <T> T map(JsonNullable<T> value) {
        if (value == null || !value.isPresent()) {
            return null;
        }
        return value.get();
    }

    default <T> JsonNullable<T> map(T value) {
        if (value == null) {
            return JsonNullable.undefined();
        }
        return JsonNullable.of(value);
    }

}

