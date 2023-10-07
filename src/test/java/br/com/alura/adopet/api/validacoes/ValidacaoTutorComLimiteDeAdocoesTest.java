package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComLimiteDeAdocoesTest {


    @InjectMocks
    private ValidacaoTutorComLimiteDeAdocoes validador;
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    @DisplayName("Não deveria aprovar a adoção pelo limite de adoções aprovadas de 5")
    void cenarioNegado(){
        //ARRANGE
        given(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(),StatusAdocao.APROVADO)).willReturn(5);

        //ACT + ASSERT
        assertThrows(ValidacaoException.class, () -> validador.validar(dto));
    }
    @Test
    @DisplayName("Deveria permitir adoção, pelo fato que a adoção não passou chegou ao limite")
    void cenarioAceito(){
        //ARRANGE
        given(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(),StatusAdocao.APROVADO)).willReturn(4);

        //ACT + ASSERT
        assertDoesNotThrow(()-> validador.validar(dto));
    }




}