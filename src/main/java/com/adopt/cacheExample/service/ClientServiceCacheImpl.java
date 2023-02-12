package com.adopt.cacheExample.service;

import com.adopt.cacheExample.model.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class ClientServiceCacheImpl implements ClientServiceCache {

    private static final Logger LOG = LoggerFactory.getLogger(ClientServiceCacheImpl.class);

    private List<ClientService> clientServices;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ClientServiceCacheImpl() {
        clientServices = new ArrayList<>();
    }

    @Override
    public Optional<ClientService> get(String id, String name, String value) {
        LOG.info("Searching for id: {} name: {} value: {}", id, name, value);

        Stream<ClientService> orderDetailsStream = this.clientServices.stream()
                .filter(orderDetails -> orderDetails.getId().equals(id))
                .filter(orderDetails -> orderDetails.getName().equals(name))
                .filter(orderDetails -> orderDetails.getValue().equals(value));
        Optional<ClientService> result = orderDetailsStream.findFirst();
        return result;
    }


    @Override
    public void load() {
        LOG.info("Loading Client Service Cache ...");
        String sql = "select * from tblclientservice";
        List<ClientService> result = jdbcTemplate.query(sql, (rs, rowNum) ->
                new ClientService(
                        rs.getInt("SERVICEID"),
                        rs.getString("name"),
                        rs.getString("value"),
                        rs.getInt("mvnoId")
                        ));
        result.forEach(row -> clientServices.add(row));
    }

    @Override
    public ClientService getOrderDB(String name){
        String sql = "select * from tblclientservice WHERE NAME=?";
        List<ClientService> result = jdbcTemplate.query(sql, new Object[]{name}, (rs, rowNum) ->
                new ClientService(
                        rs.getInt("SERVICEID"),
                        rs.getString("name"),
                        rs.getString("value"),
                        rs.getInt("mvnoId")
                ));
        return result.get(0);
    }
}
