package com.algaworks.algatransito.common;

import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    /*
       Essa anotação serve para instaciar, inicializar, configurar um Bean
       que sera gerenciado pelo Spring, e disponibilizado para injeção em outras classes
     */
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

      /*
         Modelo de conficuração de mapeamento de tipos
       */
        modelMapper.createTypeMap(Veiculo.class, VeiculoModel.class)
                .addMappings(mapper -> mapper.map(Veiculo::getPlaca, VeiculoModel::setNumeroPlaca));

        return new ModelMapper();
    }


}
