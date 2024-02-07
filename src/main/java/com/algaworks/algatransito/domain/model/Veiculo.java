package com.algaworks.algatransito.domain.model;


import com.algaworks.algatransito.domain.exception.NegocioException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    /*
        Essa anotacao serve para informar que existe um relacionamento entre a tabela:
        Proprietario e Veiculo.
        O relacionamento é de muitos para um = (n - 1)
     */

    @ManyToOne
    private Proprietario proprietario;
    private String marca;
    private String modelo;
    private String placa;

    /*  @Enumerated(EnumType.STRING)
       Essa anotacao possibilita a configuracao do que queremos armazenar na coluna de status
       no modelo relacional da tabela
     */
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

    /*
       Essa anotacao serve para informar que existe um relacionamento entre a tabela:
        Veiculo e Autuação.
        É um relacionamento Bidimencional -> mappedBy ira fazer esse Bidimencionamento
    */
    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Autuacao> autuacaos = new ArrayList<>();

    public Autuacao adicionarAutuacao(Autuacao autuacao) {
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacaos().add(autuacao);
        return autuacao;
    }

    public void apreender() {
        if(estaApreendido()) {
            throw new  NegocioException("Veiculo já se encontra aprrendido");
        }

        setStatus(StatusVeiculo.APREENDIDO);
        setDataApreensao(OffsetDateTime.now());
    }
    public void removerApreensao() {
        if(naoEstaApreendido()) {
            throw new  NegocioException("Veiculo não está apreendido");
        }

        setStatus(StatusVeiculo.REGULAR);
        setDataApreensao(null);
    }
    public boolean estaApreendido() {
        return StatusVeiculo.APREENDIDO.equals(getStatus());
    }
    public boolean naoEstaApreendido() {
        return !estaApreendido();
    }


}
