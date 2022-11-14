package com.conectdoe.api.service.impl;

import com.conectdoe.api.domain.Ong;
import com.conectdoe.api.exception.DuplicatedNameExcpetion;
import com.conectdoe.api.exception.ResourceNotFoundException;
import com.conectdoe.api.repository.OngRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class OngServiceImplTest {

    private final OngRepository ongRepository = mock(OngRepository.class);

    @InjectMocks
    private final OngServiceImpl ongService = spy(new OngServiceImpl(ongRepository));

    private Ong ongExpected;
    private List<Ong> ongsExpected;

    @BeforeEach
    void setUp() {
        ongExpected = new Ong();
        ongExpected.setNome("ONG Teste");
        ongExpected.setCidade("Juiz de Fora");
        ongExpected.setUf("MG");
        ongExpected.setEmail("ongteste@email.com");
        ongExpected.setOngId("8499fc63-dcf9-4b5f-8779-20e39a57f504");

        ongsExpected = Collections.singletonList(ongExpected);
    }

    @Test
    void testAdicionarSucesso() {
        when(ongRepository.buscarPorNome(ongExpected.getNome())).thenReturn(null);
        when(ongRepository.salvar(ongExpected)).thenReturn(ongExpected);
        assertThatCode(() -> ongService.adicionar(ongExpected)).doesNotThrowAnyException();
        Ong response = ongService.adicionar(ongExpected);
        assertEquals(response, ongExpected);
    }

    @Test
    void testAdicionarFalha() {
        when(ongRepository.buscarPorNome(ongExpected.getNome())).thenReturn(ongExpected);
        assertThrows(DuplicatedNameExcpetion.class, () -> ongService.adicionar(ongExpected));
    }

    @Test
    void testBuscarPorIdSucesso() {
        when(ongRepository.buscarPorId(ongExpected.getOngId())).thenReturn(ongExpected);
        assertThatCode(() -> ongService.buscarPorId(ongExpected.getOngId())).doesNotThrowAnyException();
        assertEquals(ongExpected, ongService.buscarPorId(ongExpected.getOngId()));
    }

    @Test
    void testBuscarPorIdFalha() {
        when(ongRepository.buscarPorId(ongExpected.getOngId())).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> ongService.buscarPorId(ongExpected.getOngId()));
    }

    @Test
    void buscarPorNome() {
        when(ongRepository.buscarPorNome(ongExpected.getNome())).thenReturn(ongExpected);
        assertThatCode(() -> ongService.buscarPorNome(ongExpected.getNome())).doesNotThrowAnyException();
        assertEquals(ongExpected, ongService.buscarPorNome(ongExpected.getNome()));
    }

    @Test
    void testBuscarPorNomeFalha() {
        when(ongRepository.buscarPorNome(ongExpected.getNome())).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> ongService.buscarPorNome(ongExpected.getNome()));
    }

    @Test
    void testBuscarTodos() {
        when(ongRepository.listarTodos()).thenReturn(ongsExpected);
        assertThatCode(ongService::buscarTodos).doesNotThrowAnyException();
        assertEquals(ongsExpected, ongService.buscarTodos());
    }

    @Test
    void testDeletarSucesso() {
        ongService.deletar(ongExpected.getOngId());
        verify(ongRepository).deletar(ongExpected.getOngId());
    }
}