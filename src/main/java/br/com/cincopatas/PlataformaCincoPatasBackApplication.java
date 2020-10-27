package br.com.cincopatas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cincopatas.enums.Sexo;
import br.com.cincopatas.enums.TipoPessoa;
import br.com.cincopatas.model.Cidade;
import br.com.cincopatas.model.Endereco;
import br.com.cincopatas.model.Estado;
import br.com.cincopatas.model.Pessoa;
import br.com.cincopatas.repository.CidadeRepository;
import br.com.cincopatas.repository.EstadoRepository;
import br.com.cincopatas.repository.PessoaRepository;

@SpringBootApplication
public class PlataformaCincoPatasBackApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PlataformaCincoPatasBackApplication.class, args);
	}
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
//		Estado est1 = new Estado();
//		Cidade c1 = new Cidade();
//		
//		est1.setNome("Minas Gerais");
//		est1.setId(null);
//		
//		c1.setNome("Uberlandia");
//		c1.setId(null);
//		
//		c1.setEstado(est1);
//		
//		estadoRepository.save(est1);
//		cidadeRepository.save(c1);
//		
//		Pessoa p1 = new Pessoa(null, TipoPessoa.ADOTANTE, "Joao", Sexo.MASCULINO, "00103698132", "3254919", "joao@gmail.com", "55981091715");
//		
//		Endereco e1 = new Endereco();
//		
//		e1.setBairro("Camobi");
//		e1.setCep("97105150");
//		e1.setCidade(c1);
//		e1.setComplemento("Apt 301");
//		e1.setLogradouro("Rua Joao 2");
//		e1.setNumero("555");
//		
//		p1.setEndereco(e1);
//		
//		pessoaRepository.save(p1);
	}

}
