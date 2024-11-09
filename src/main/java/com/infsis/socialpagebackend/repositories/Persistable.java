package com.infsis.socialpagebackend.repositories;

import io.micrometer.common.lang.Nullable;

public interface Persistable<ID> {
    @Nullable
    ID getId();
    boolean isNew();
}
