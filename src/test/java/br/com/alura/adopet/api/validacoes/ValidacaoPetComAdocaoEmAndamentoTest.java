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
class ValidacaoPetComAdocaoEmAndamentoTest {

    @InjectMocks
    private ValidacaoPetComAdocaoEmAndamento validacao;
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private SolicitacaoAdocaoDto dto;

    @Mock
    private StatusAdocao statusAdocao;


    @DisplayName("Verificar se existe alguma adoção no banco de dados em andamento")
    @Test
    void cenario01(){
        //ARRANGE

        given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(),statusAdocao.AGUARDANDO_AVALIACAO)).willReturn(true);

        //ASSERT + ACT
        assertThrows(ValidacaoException.class,()-> validacao.validar(dto));

    }

    @DisplayName("Quando não encontra uma adoção em andamento para esse pet")
    @Test
    void cenario02(){
        //ARRANGE

        given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(),statusAdocao.AGUARDANDO_AVALIACAO)).willReturn(false);

        //ASSERT + ACT
        assertDoesNotThrow(() -> validacao.validar(dto));

    }



}