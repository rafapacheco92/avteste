package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CadastroFazendaServiceTest {
  private CadastroFazendaService cadastroFazendaService;
  private Fazenda fazenda;

  @BeforeEach
  public void setUp() {
    cadastroFazendaService = new CadastroFazendaService();
    fazenda = new Fazenda();
  }

  // Feature: Validação do nome da fazenda
  @Test
  public void testNomeValido() {
    fazenda.setNome("Fazenda Teste");
    assertTrue(cadastroFazendaService.validarString(fazenda.getNome()));
  }

  @Test
  public void testNomeVazio() {
    fazenda.setNome("");
    assertFalse(cadastroFazendaService.validarString(fazenda.getNome()));
  }

  @Test
  public void testNomeNulo() {
    fazenda.setNome(null);
    assertFalse(cadastroFazendaService.validarString(fazenda.getNome()));
  }

  // Feature: Validação da área da fazenda
  @Test
  public void testAreaValida() {
    fazenda.setAreaTotal(300);
    assertTrue(cadastroFazendaService.validarArea(fazenda.getAreaTotal()));
  }

  @Test
  public void testAreaNegativa() {
    fazenda.setAreaTotal(-100);
    assertFalse(cadastroFazendaService.validarArea(fazenda.getAreaTotal()));
  }

  // Feature: Validação do CPF do proprietário da fazenda
  @Test
  public void testCpfValido() {
    fazenda.setCpfProprietario("12345678901");
    assertTrue(cadastroFazendaService.validarCpf(fazenda.getCpfProprietario()));
  }

  @Test
  public void testCpfMenosDeOnzeDigitos() {
    fazenda.setCpfProprietario("1234567890");
    assertFalse(cadastroFazendaService.validarCpf(fazenda.getCpfProprietario()));
  }

  @Test
  public void testCpfNulo() {
    fazenda.setCpfProprietario(null);
    assertFalse(cadastroFazendaService.validarCpf(fazenda.getCpfProprietario()));
  }

  @Test
  public void testCpfComCaracteresInvalidos() {
    fazenda.setCpfProprietario("12345A7890B");
    assertFalse(cadastroFazendaService.validarCpf(fazenda.getCpfProprietario()));
  }

  // Feature: Validação da latitude da fazenda
  @Test
  public void testLatitudeValida() {
    fazenda.setLatitude(45.0);
    assertTrue(cadastroFazendaService.validarLatitude(fazenda.getLatitude()));
  }

  @Test
  public void testLatitudeForaDoIntervalo() {
    fazenda.setLatitude(-95.0);
    assertFalse(cadastroFazendaService.validarLatitude(fazenda.getLatitude()));
  }

  // Feature: Validação da longitude da fazenda
  @Test
  public void testLongitudeValida() {
    fazenda.setLongitude(120.0);
    assertTrue(cadastroFazendaService.validarLongitude(fazenda.getLongitude()));
  }

  @Test
  public void testLongitudeForaDoIntervalo() {
    fazenda.setLongitude(190.0);
    assertFalse(cadastroFazendaService.validarLongitude(fazenda.getLongitude()));
  }

  // Feature: Validação das áreas da fazenda
  @Test
  public void testAreasValidas() {
    fazenda.setAreaTotal(200);
    fazenda.setAreaAgricultavel(100);
    fazenda.setAreaNaoAgricultavel(100);
    assertTrue(cadastroFazendaService.validarAreas(fazenda));
  }

  @Test
  public void testAreasInvalidas() {
    fazenda.setAreaTotal(200);
    fazenda.setAreaAgricultavel(150);
    fazenda.setAreaNaoAgricultavel(150);
    assertFalse(cadastroFazendaService.validarAreas(fazenda));
  }
}
