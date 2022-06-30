package com.infostore.InfoStore.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Endereco implements Serializable {

    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private Localizacao location;
}
