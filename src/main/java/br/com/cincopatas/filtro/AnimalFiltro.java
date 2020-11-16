package br.com.cincopatas.filtro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Informações básicas sobre os Animais", value = "Animal")
public class AnimalFiltro {

    @ApiModelProperty(value = "O código da Cidade a ser buscada", required = false, position = 1, dataType = "Long", example = "1")
    private Long cidade;
    
    @ApiModelProperty(value = "O código do Estado a ser buscado", required = false, position = 1, dataType = "Long", example = "1")
    private Long estado;
    
    @ApiModelProperty(value = "O porte do animal a ser buscado", required = false, position = 1, dataType = "String", example = "P")
    private String porte;
    
    @ApiModelProperty(value = "A espécie do animal a ser buscada", required = false, position = 1, dataType = "String", example = "Gato")
    private String especie;
    
    @ApiModelProperty(value = "O sexo do animal a ser buscado", required = false, position = 1, dataType = "String", example = "Macho")
    private String sexo;

}