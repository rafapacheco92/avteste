package com.example;

public class CadastroFazendaService {
    public boolean validarFazenda(Fazenda fazenda) {
        return validarString(fazenda.getNome()) &&
                validarArea(fazenda.getAreaTotal()) &&
                validarArea(fazenda.getAreaAgricultavel()) &&
                validarArea(fazenda.getAreaNaoAgricultavel()) &&
                validarCpf(fazenda.getCpfProprietario()) &&
                validarAreas(fazenda) &&
                validarLatitude(fazenda.getLatitude()) &&
                validarLongitude(fazenda.getLongitude());
    }

    public boolean validarString(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    public boolean validarAreas(Fazenda fazenda) {
        return fazenda.getAreaAgricultavel() + fazenda.getAreaNaoAgricultavel() <= fazenda.getAreaTotal();
    }

    public boolean validarArea(double area) {
        return area >= 0;
    }

    public boolean validarCpf(String cpf) {
        return cpf != null && cpf.matches("\\d{11}"); // Exemplo de regex para CPF com 11 dígitos
    }

    public boolean validarLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    public boolean validarLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }
}
