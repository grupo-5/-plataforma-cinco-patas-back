package br.com.cincopatas.request;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import br.com.cincopatas.validation.FileSize;
import lombok.Data;

@Data
public class ImagemRequest {

	@NotNull
	@FileSize(max="2000KB")
	private MultipartFile imagem;
//	
//	@NotBlank
//	private String descricao;
	
}
