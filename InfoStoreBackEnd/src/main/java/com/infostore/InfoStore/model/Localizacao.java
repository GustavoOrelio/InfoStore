package com.infostore.InfoStore.model;

import lombok.Data;

@Data
public class Localizacao {
    private String type;
    private Coordenadas coordinates;
}
