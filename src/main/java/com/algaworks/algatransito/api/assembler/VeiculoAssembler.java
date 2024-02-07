package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.api.model.inpt.VeiculoInput;
import com.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VeiculoAssembler {

    private final ModelMapper modelMapper;

    /*
       Transforma a instancia VeiculoInput em Veiculo
     */
    public Veiculo toEntity(VeiculoInput veiculoInput) {
        return modelMapper.map(veiculoInput, Veiculo.class);
    }

    /*
      Transforma a instancia veiculo em VeiculoModel
     */
    public VeiculoModel toModel(Veiculo veiculo) {
        return modelMapper.map(veiculo, VeiculoModel.class);
    }

    /*
      Transforma uma lista da entidade de veiculos em uma lista da entidade VeiculoModel
     */
    public List<VeiculoModel> toCollectionModel(List<Veiculo> veiculos) {
        return veiculos.stream()
                .map(this::toModel)
                .toList();

    }
}
