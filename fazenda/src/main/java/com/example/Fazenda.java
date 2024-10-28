package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Fazenda {
    private String nome;
    private double areaTotal;
    private String cpfProprietario;
    private double areaAgricultavel;
    private double areaNaoAgricultavel;
    private double latitude;
    private double longitude;
}
