package net.lzzy.practice.models;

import java.util.UUID;


public class BaseEntity {
    protected UUID id;

    protected BaseEntity() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
}
