package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastroFazendaServiceTest {

  private FazendaApiService fazendaApiService;
  private CadastroFazendaService cadastroFazendaService;

  @BeforeEach
  void setUp() {
    fazendaApiService = mock(FazendaApiService.class);
    cadastroFazendaService = new CadastroFazendaService(fazendaApiService);
  }

  @Test
  public void testCadastroBemSucedido() {

    String codigo = "12345";
    String nome = "Fazenda Teste";

    double areaAgricultavel = 100.0;
    double areaNaoAgricultavel = 200.0;
    String cpf = "12345678901";
    double latitude = -23.5505;
    double longitude = -46.6333;

    when(fazendaApiService.getLatitude(codigo)).thenReturn(latitude);
    when(fazendaApiService.getLongitude(codigo)).thenReturn(longitude);
    when(fazendaApiService.getAreaAgricultavel(codigo)).thenReturn(areaAgricultavel);
    when(fazendaApiService.getAreaNaoAgricultavel(codigo)).thenReturn(areaNaoAgricultavel);
    when(fazendaApiService.getCpfProprietario(codigo)).thenReturn(cpf);

    Fazenda fazenda = cadastroFazendaService.cadastrarFazenda(codigo, nome);

    assertNotNull(fazenda);
    assertEquals(nome, fazenda.getNome());
    assertEquals(codigo, fazenda.getCodigo());
    assertEquals(areaAgricultavel + areaNaoAgricultavel, fazenda.getAreaTotal());
    assertEquals(areaAgricultavel, fazenda.getAreaAgricultavel());
    assertEquals(areaNaoAgricultavel, fazenda.getAreaNaoAgricultavel());
    assertEquals(cpf, fazenda.getCpfProprietario());
    assertEquals(latitude, fazenda.getLatitude());
    assertEquals(longitude, fazenda.getLongitude());
  }

  @Test
  void testCadastroFalhaRecuperarLatitude() {
    // Given
    String codigo = "12345";
    String nome = "Fazenda Teste";

    when(fazendaApiService.getLatitude(codigo)).thenThrow(new RuntimeException("Erro ao recuperar latitude"));

    // When & Then
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      cadastroFazendaService.cadastrarFazenda(codigo, nome);
    });
    assertEquals("Erro ao recuperar latitude", exception.getMessage());
  }

  @Test
  void testCadastroFalhaRecuperarLongitude() {
    // Given
    String codigo = "12345";
    String nome = "Fazenda Teste";

    when(fazendaApiService.getLongitude(codigo)).thenThrow(new RuntimeException("Erro ao recuperar longitude"));

    // When & Then
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      cadastroFazendaService.cadastrarFazenda(codigo, nome);
    });
    assertEquals("Erro ao recuperar longitude", exception.getMessage());
  }

  @Test
  void testCadastroFalhaRecuperarAreaAgricultavel() {
    // Given
    String codigo = "12345";
    String nome = "Fazenda Teste";

    when(fazendaApiService.getAreaAgricultavel(codigo))
        .thenThrow(new RuntimeException("Erro ao recuperar área agricultável"));

    // When & Then
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      cadastroFazendaService.cadastrarFazenda(codigo, nome);
    });
    assertEquals("Erro ao recuperar área agricultável", exception.getMessage());
  }

  @Test
  void testCadastroFalhaRecuperarAreaNaoAgricultavel() {
    // Given
    String codigo = "12345";
    String nome = "Fazenda Teste";

    when(fazendaApiService.getAreaNaoAgricultavel(codigo))
        .thenThrow(new RuntimeException("Erro ao recuperar área não agricultável"));

    // When & Then
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      cadastroFazendaService.cadastrarFazenda(codigo, nome);
    });
    assertEquals("Erro ao recuperar área não agricultável", exception.getMessage());
  }

  @Test
  void testCadastroFalhaRecuperarCpfProprietario() {
    // Given
    String codigo = "12345";
    String nome = "Fazenda Teste";

    when(fazendaApiService.getCpfProprietario(codigo))
        .thenThrow(new RuntimeException("Erro ao recuperar CPF do proprietário"));

    // When & Then
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      cadastroFazendaService.cadastrarFazenda(codigo, nome);
    });
    assertEquals("Erro ao recuperar CPF do proprietário", exception.getMessage());
  }

}
