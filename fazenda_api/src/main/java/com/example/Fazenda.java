package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Fazenda {
    private String nome;
    private String codigo;
    private double areaTotal;
    private double areaAgricultavel;
    private double areaNaoAgricultavel;
    private String cpfProprietario;
    private double latitude;
    private double longitude;
}
