package com.example.fazenda;

import com.example.fazenda.entities.Fazenda;
import com.example.fazenda.repositories.FazendaRepository;
import com.example.fazenda.services.FazendaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.NoSuchElementException;
import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FazendaControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FazendaService fazendaService;

  @Autowired
  private FazendaRepository fazendaRepository;

  private ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
    fazendaRepository.deleteAll(); // Limpar o repositório antes de cada teste
  }

  // Cenário 1: Cadastro bem-sucedido de uma nova fazenda
  @Test
  void testCadastroFazendaComSucesso() throws Exception {
    Fazenda fazenda = new Fazenda(null, "Fazenda Teste", "12345", 100.0, 50.0, 50.0, "12345678901", 30.0, -50.0);

    mockMvc.perform(post("/fazendas")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(fazenda)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists());
  }

  // Cenário 2: Cadastro de fazenda com dados incompletos
  @Test
  void testCadastroFazendaComDadosIncompletos() throws Exception {
    Fazenda fazendaIncompleta = new Fazenda();
    fazendaIncompleta.setNome("Fazenda Teste");
    fazendaIncompleta.setCodigo("54321");
    fazendaIncompleta.setAreaTotal(null); // Este é o campo que você quer que falhe
    fazendaIncompleta.setAreaAgricultavel(100.0);
    fazendaIncompleta.setAreaNaoAgricultavel(50.0);
    fazendaIncompleta.setCpfProprietario("12345678901");
    fazendaIncompleta.setLatitude(10.0);
    fazendaIncompleta.setLongitude(20.0);

    mockMvc.perform(post("/fazendas")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(fazendaIncompleta)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Área total deve ser um valor positivo.")); // Altere para a mensagem que
                                                                                           // você espera
  }

  // Cenário 3: Consulta de uma fazenda por ID com sucesso
  @Test
  void testConsultaFazendaPorIdComSucesso() throws Exception {
    Fazenda fazenda = new Fazenda(null, "Fazenda Teste", "12345", 100.0, 50.0, 50.0, "12345678901", 30.0, -50.0);
    Fazenda savedFazenda = fazendaRepository.save(fazenda);

    mockMvc.perform(get("/fazendas/{id}", savedFazenda.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome", is("Fazenda Teste")))
        .andExpect(jsonPath("$.codigo", is("12345")));
  }

  // Cenário 4: Consulta de fazenda por ID inexistente
  @Test
  void testConsultaFazendaPorIdInexistente() throws Exception {
    mockMvc.perform(get("/fazendas/{id}", 99999))
        .andExpect(status().isNotFound())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoSuchElementException));
  }

  // Cenário 5: Atualização bem-sucedida dos dados de uma fazenda
  @Test
  void testAtualizacaoDadosFazendaComSucesso() throws Exception {
    Fazenda fazenda = new Fazenda(null, "Fazenda Teste", "12345", 100.0, 50.0, 50.0, "12345678901", 30.0, -50.0);
    Fazenda savedFazenda = fazendaRepository.save(fazenda);

    Fazenda fazendaAtualizada = new Fazenda(savedFazenda.getId(), "Fazenda Renovada", "12345", 100.0, 60.0, 40.0,
        "12345678901", 30.0, -50.0);

    mockMvc.perform(put("/fazendas")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(fazendaAtualizada)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome", is("Fazenda Renovada")))
        .andExpect(jsonPath("$.areaAgricultavel", is(60.0)));
  }

  // Cenário 6: Atualização de fazenda inexistente
  @Test
  void testAtualizacaoFazendaInexistente() throws Exception {
    Fazenda fazendaAtualizada = new Fazenda(99999L, "Fazenda Renovada", "12345", 100.0, 60.0, 40.0, "12345678901", 30.0,
        -50.0);

    mockMvc.perform(put("/fazendas")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(fazendaAtualizada)))
        .andExpect(status().isNotFound())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoSuchElementException));
  }

  // Cenário 7: Exclusão bem-sucedida de uma fazenda por ID
  @Test
  void testExclusaoFazendaComSucesso() throws Exception {
    Fazenda fazenda = new Fazenda(null, "Fazenda Teste", "12345", 100.0, 50.0, 50.0, "12345678901", 30.0, -50.0);
    Fazenda savedFazenda = fazendaRepository.save(fazenda);

    mockMvc.perform(delete("/fazendas/{id}", savedFazenda.getId()))
        .andExpect(status().isNoContent());

    mockMvc.perform(get("/fazendas/{id}", savedFazenda.getId()))
        .andExpect(status().isNotFound());
  }

  // Cenário 8: Exclusão de fazenda inexistente
  @Test
  void testExclusaoFazendaInexistente() throws Exception {
    // Simula que não existe uma fazenda com o ID fornecido
    doThrow(new NoSuchElementException("Fazenda não encontrada com ID: 99999"))
        .when(fazendaService).delete(99999L); // Passa o ID como Long

    mockMvc.perform(delete("/fazendas/{id}", 99999))
        .andExpect(status().isNotFound()) // Espera o status 404
        .andExpect(result -> assertEquals("Fazenda não encontrada com ID: 99999",
            result.getResolvedException().getMessage())); // Verifica a mensagem
  }
}
