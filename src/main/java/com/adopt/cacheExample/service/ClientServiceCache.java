package com.adopt.cacheExample.service;

import com.adopt.cacheExample.model.ClientService;

import java.util.Optional;

public interface ClientServiceCache extends Cache{

    Optional<ClientService> get(String id, String name, String value);

    ClientService getOrderDB(String name);
}
