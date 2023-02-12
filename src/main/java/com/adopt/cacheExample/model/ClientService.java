package com.adopt.cacheExample.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClientService {

    private Integer id;
    private String name;
    private String value;
    private Integer mvnoId;

    public ClientService(Integer id, String name, String value, Integer mvnoId) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.mvnoId = mvnoId;
    }
}
