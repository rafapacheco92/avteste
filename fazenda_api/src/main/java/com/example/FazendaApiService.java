package com.example;

public interface FazendaApiService {
    
    /**
     * Recupera a cultura agrícola da fazenda com base no ID da fazenda.
     * @param fazendaCode Identificador único da fazenda.
     * @return A cultura agrícola da fazenda.
     */
    String getCulturaAgricola(String fazendaCode) throws RuntimeException;

    /**
     * Recupera o CPF do proprietário da fazenda com base no ID da fazenda.
     * @param fazendaCode Identificador único da fazenda.
     * @return O CPF do proprietário.
     */
    String getCpfProprietario(String fazendaCode) throws RuntimeException;

    /**
     * Recupera a área agricultável da fazenda em hectares.
     * @param fazendaCode Identificador único da fazenda.
     * @return A área agricultável em hectares.
     */
    double getAreaAgricultavel(String fazendaCode) throws RuntimeException;

    /**
     * Recupera a área não agricultável da fazenda em hectares.
     * @param fazendaCode Identificador único da fazenda.
     * @return A área não agricultável em hectares.
     */
    double getAreaNaoAgricultavel(String fazendaCode) throws RuntimeException;

    /**
     * Recupera a latitude da localização da fazenda.
     * @param fazendaCode Identificador único da fazenda.
     * @return A latitude da fazenda.
     */
    double getLatitude(String fazendaCode) throws RuntimeException;

    /**
     * Recupera a longitude da localização da fazenda.
     * @param fazendaCode Identificador único da fazenda.
     * @return A longitude da fazenda.
     */
    double getLongitude(String fazendaCode) throws RuntimeException;
}

