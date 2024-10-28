package com.example;

public class CadastroFazendaService {
    private FazendaApiService fazendaApiService;

    public CadastroFazendaService(FazendaApiService fazendaApiService) {
        this.fazendaApiService = fazendaApiService;
    }

    public Fazenda cadastrarFazenda(String codigo, String nome) throws RuntimeException {
        Double latitude = fazendaApiService.getLatitude(codigo);
        Double longitude = fazendaApiService.getLongitude(codigo);
        Double areaAgricultavel = fazendaApiService.getAreaAgricultavel(codigo);
        Double areaNaoAgricultavel = fazendaApiService.getAreaNaoAgricultavel(codigo);
        String cpf = fazendaApiService.getCpfProprietario(codigo);
        return new Fazenda(
            nome, codigo, areaAgricultavel + areaNaoAgricultavel, 
            areaAgricultavel, areaNaoAgricultavel,
            cpf, latitude, longitude
        );
    }
}
