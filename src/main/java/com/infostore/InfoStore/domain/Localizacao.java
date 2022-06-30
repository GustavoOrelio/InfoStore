package com.infostore.InfoStore.domain;

import lombok.Data;

@Data
public class Localizacao {
    private String type;
    private Coordenadas coordinates;
}
