package br.com.cincopatas;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import br.com.cincopatas.model.Animal;
import br.com.cincopatas.model.Cidade;
import br.com.cincopatas.model.Endereco;
import br.com.cincopatas.model.Estado;
import br.com.cincopatas.model.Grupo;
import br.com.cincopatas.model.Imagem;
import br.com.cincopatas.model.Instituicao;
import br.com.cincopatas.model.Permissao;
import br.com.cincopatas.model.Pessoa;
import br.com.cincopatas.model.Usuario;
import br.com.cincopatas.repository.AnimalRepository;
import br.com.cincopatas.repository.CidadeRepository;
import br.com.cincopatas.repository.EstadoRepository;
import br.com.cincopatas.repository.GrupoRepository;
import br.com.cincopatas.repository.ImagemRepository;
import br.com.cincopatas.repository.InstituicaoRepository;
import br.com.cincopatas.repository.PermissaoRepository;
import br.com.cincopatas.repository.PessoaRepository;
import br.com.cincopatas.repository.UsuarioRepository;
import br.com.cincopatas.service.EstadoService;
import br.com.cincopatas.service.InstituicaoService;
import br.com.cincopatas.service.PessoaService;

@SpringBootApplication
public class PlataformaCincoPatasBackApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private InstituicaoService instituicaoService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private ImagemRepository imagemRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PlataformaCincoPatasBackApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		final String urlEstados = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
		
		RestTemplate rest = new RestTemplate();
		
		if(estadoService.listar().size() != 27) {
		
			Estado[] estados = rest.getForObject(urlEstados, Estado[].class);
			List<Estado> listaEstados = Arrays.asList(estados);
			listaEstados
				.stream()
				.forEach(estado -> {
					estadoRepository.save(estado);
					Cidade[] cidades = rest.getForObject("https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + estado.getId() + "/municipios", Cidade[].class);
					List<Cidade> listaCidades = Arrays.asList(cidades);
					listaCidades
						.stream()
						.forEach(cidade -> {
							cidade.setEstado(estado);
							cidadeRepository.save(cidade);
					});
			});
			
		}
		
		if(permissaoRepository.findAll().size() != 3) {
			Permissao p1 = new Permissao();
			Permissao p2 = new Permissao();
			Permissao p3 = new Permissao();
			
			p1.setDescricao("Permissão básica");
			p2.setDescricao("Permissão pessoas");
			p3.setDescricao("Permissão de instituições");
			p1.setId(1L);
			p2.setId(2L);
			p3.setId(3L);
			p1.setNome("DH01");
			p2.setNome("DH05");
			p3.setNome("DH03");
			
			
			permissaoRepository.saveAll(Arrays.asList(p1,p2,p3));
			
			Grupo g1 = new Grupo();
			Grupo g2 = new Grupo();
			
			g1.setNome("Grupo 1 Pessoa");
			g2.setNome("Grupo 2 Instituição");
			g1.setId(1L);
			g2.setId(2L);
			
			Set<Permissao> set1 = new HashSet<Permissao>();
			Set<Permissao> set2 = new HashSet<Permissao>();
			set1.add(p1);
			set1.add(p2);
			set2.add(p1);
			set2.add(p2);
			set2.add(p3);

			g1.setPermissoes(set1);
			g2.setPermissoes(set2);
			
			grupoRepository.saveAll(Arrays.asList(g1,g2));
		}
		
		if(instituicaoService.listar().size() == 0) {
			
			Endereco e6 = Endereco.builder()
					.cep("60822480")
					.logradouro("Rua Benjamim Moura")
					.numero("100")
					.complemento("")
					.bairro("Cidade dos Funcionários")
					.cidade(cidadeRepository.findById(950L).get())
			.build();
			
			Endereco e7 = Endereco.builder()
					.cep("70331700")
					.logradouro("SHIGS 703")
					.numero("50")
					.complemento("")
					.bairro("Asa Sul")
					.cidade(cidadeRepository.findById(5570L).get())
			.build();
			
			Endereco e8 = Endereco.builder()
					.cep("01021200")
					.logradouro("Rua Vinte e Cinco de Março")
					.numero("400")
					.complemento("")
					.bairro("Centro")
					.cidade(cidadeRepository.findById(3830L).get())
			.build();
			
			Endereco e9 = Endereco.builder()
					.cep("97105340")
					.logradouro("Av. Roraima")
					.numero("100")
					.complemento("")
					.bairro("Camobi")
					.cidade(cidadeRepository.findById(4791L).get())
			.build();
			
			Endereco e10 = Endereco.builder()
					.cep("01329000")
					.logradouro("Rua dos Ingleses")
					.numero("250")
					.complemento("")
					.bairro("Morro dos Ingleses")
					.cidade(cidadeRepository.findById(3830L).get())
			.build();
			
			URL url1 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/ong1.png");
			URL url2 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/ong2.png");
			URL url3 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/ong3.png");
			URL url4 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/ong4.png");
			URL url5 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/ong5.png");
			URL url6 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal1.png");
			URL url7 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal2.png");
			URL url8 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal3.png");
			URL url9 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal4.png");
			URL url10 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal5.png");
			URL url11 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal6.png");
			URL url12 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal7.png");
			URL url13 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal8.png");
			URL url14 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal9.png");
			
			Imagem imgOng1 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url1)
			.build();
			
			Imagem imgOng2 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url2)
			.build();
			
			Imagem imgOng3 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url3)
			.build();
			
			Imagem imgOng4 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url4)
			.build();
			
			Imagem imgOng5 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url5)
			.build();
			
			Imagem imgAni1 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url6)
			.build();
			
			Imagem imgAni2 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url7)
			.build();
			
			Imagem imgAni3 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url8)
			.build();
			
			Imagem imgAni4 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url9)
			.build();
			
			Imagem imgAni5 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url10)
			.build();
			
			Imagem imgAni6 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url11)
			.build();
			
			Imagem imgAni7 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url12)
			.build();
			
			Imagem imgAni8 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url13)
			.build();
			
			Imagem imgAni9 = Imagem.builder()
					.nomeArquivo("")
					.nomeArquivoCompleto("")
					.contentType("")
					.tamanho(100L)
					.url(url14)
			.build();
			
			imagemRepository.saveAll(Arrays.asList(imgOng1, imgOng2, imgOng3, imgOng4, imgOng5, imgAni1, 
					imgAni2, imgAni3, imgAni4, imgAni5, imgAni6, imgAni7, imgAni8, imgAni9));
			
			Instituicao inst1 = Instituicao.builder()
					.nome("Animal Help")
					.numeroDocumento("123456789")
					.tipoDocumento("CNPJ")
					.contato("2135647896")
					.razaoSocial("")
					.inscricaoEstadual("")
					.capacidade(25L)
					.banco("Banco do Brasil")
					.agencia("1787-2")
					.conta("98358-5")
					.email("animalhelp@gmail.com")
					.senha("123456")
					.endereco(e6)
					.imagem(imagemRepository.findById(1L).get())
			.build();
			
			Instituicao inst2 = Instituicao.builder()
					.nome("SOS Animal")
					.numeroDocumento("123456789")
					.tipoDocumento("CNPJ")
					.contato("1135647896")
					.razaoSocial("")
					.inscricaoEstadual("")
					.capacidade(25L)
					.banco("Banco do Brasil")
					.agencia("1787-2")
					.conta("98358-5")
					.email("sos@gmail.com")
					.senha("123456")
					.endereco(e7)
					.imagem(imagemRepository.findById(2L).get())
			.build();
			
			Instituicao inst3 = Instituicao.builder()
					.nome("Brigada Animal")
					.numeroDocumento("123456789")
					.tipoDocumento("CNPJ")
					.contato("4535647896")
					.razaoSocial("")
					.inscricaoEstadual("")
					.capacidade(25L)
					.banco("Banco do Brasil")
					.agencia("1787-2")
					.conta("98358-5")
					.email("brigada@gmail.com")
					.senha("123456")
					.endereco(e8)
					.imagem(imagemRepository.findById(3L).get())
			.build();
			
			Instituicao inst4 = Instituicao.builder()
					.nome("ONG São Francisco")
					.numeroDocumento("123456789")
					.tipoDocumento("CNPJ")
					.contato("5535647896")
					.razaoSocial("")
					.inscricaoEstadual("")
					.capacidade(25L)
					.banco("Banco do Brasil")
					.agencia("1787-2")
					.conta("98358-5")
					.email("sf@gmail.com")
					.senha("123456")
					.endereco(e9)
					.imagem(imagemRepository.findById(4L).get())
			.build();
			
			Instituicao inst5 = Instituicao.builder()
					.nome("Animal Ajuda")
					.numeroDocumento("123456789")
					.tipoDocumento("CNPJ")
					.contato("1935647896")
					.razaoSocial("")
					.inscricaoEstadual("")
					.capacidade(25L)
					.banco("Banco do Brasil")
					.agencia("1787-2")
					.conta("98358-5")
					.email("ajuda@gmail.com")
					.senha("123456")
					.endereco(e10)
					.imagem(imagemRepository.findById(5L).get())
			.build();
			
			instituicaoRepository.saveAll(Arrays.asList(inst1, inst2, inst3, inst4, inst5));
			
			Usuario usuario6 = Usuario.builder()
					.nome(inst1.getNome())
					.email(inst1.getEmail())
					.senha(passwordEncoder.encode(inst1.getSenha()))
					.grupo(grupoRepository.findById(2L).get())
					.tipo(2L)
					.codigo(inst1.getId())
			.build();
			
			Usuario usuario7 = Usuario.builder()
					.nome(inst2.getNome())
					.email(inst2.getEmail())
					.senha(passwordEncoder.encode(inst2.getSenha()))
					.grupo(grupoRepository.findById(2L).get())
					.tipo(2L)
					.codigo(inst2.getId())
			.build();
			
			Usuario usuario8 = Usuario.builder()
					.nome(inst3.getNome())
					.email(inst3.getEmail())
					.senha(passwordEncoder.encode(inst3.getSenha()))
					.grupo(grupoRepository.findById(2L).get())
					.tipo(2L)
					.codigo(inst3.getId())
			.build();
			
			Usuario usuario9 = Usuario.builder()
					.nome(inst4.getNome())
					.email(inst4.getEmail())
					.senha(passwordEncoder.encode(inst4.getSenha()))
					.grupo(grupoRepository.findById(2L).get())
					.tipo(2L)
					.codigo(inst4.getId())
			.build();
			
			Usuario usuario10 = Usuario.builder()
					.nome(inst5.getNome())
					.email(inst5.getEmail())
					.senha(passwordEncoder.encode(inst5.getSenha()))
					.grupo(grupoRepository.findById(2L).get())
					.tipo(2L)
					.codigo(inst5.getId())
			.build();
			
			usuarioRepository.saveAll(Arrays.asList(usuario6, usuario7, usuario8, usuario9, usuario10));
			
			Animal a1 = Animal.builder()
				.nome("Toddy")
				.especie("Cachorro")
				.sexo("MASCULINO")
				.porte("M")
				.localizacao("ONG")
				.dataNasc(LocalDate.parse("2015-02-20"))
				.nomeTitular("João Roberto")
				.contato("459887459878")
				.status("DISPONÍVEL")
				.endereco(e10)
				.imagem(imgAni2)
				.instituicao(inst5)
			.build();
			
			Animal a2 = Animal.builder()
					.nome("Peludo")
					.especie("Cachorro")
					.sexo("MACHO")
					.porte("G")
					.localizacao("ONG")
					.dataNasc(LocalDate.parse("2019-02-27"))
					.nomeTitular("João Roberto")
					.contato("459887459878")
					.status("DISPONÍVEL")
					.endereco(e10)
					.imagem(imgAni3)
					.instituicao(inst5)
				.build();
			
			Animal a3 = Animal.builder()
					.nome("Fofucho")
					.especie("Gato")
					.sexo("MACHO")
					.porte("P")
					.localizacao("ONG")
					.dataNasc(LocalDate.parse("2017-02-20"))
					.nomeTitular("João Roberto")
					.contato("459887459878")
					.status("DISPONÍVEL")
					.endereco(e10)
					.imagem(imgAni5)
					.instituicao(inst5)
				.build();
			
			Animal a4 = Animal.builder()
					.nome("Garfield")
					.especie("Gato")
					.sexo("MACHO")
					.porte("P")
					.localizacao("ONG")
					.dataNasc(LocalDate.parse("2020-09-15"))
					.nomeTitular("João Roberto")
					.contato("459887459878")
					.status("DISPONÍVEL")
					.endereco(e10)
					.imagem(imgAni7)
					.instituicao(inst5)
				.build();
			
			Animal a5 = Animal.builder()
					.nome("Sensei")
					.especie("Gato")
					.sexo("MACHO")
					.porte("M")
					.localizacao("ONG")
					.dataNasc(LocalDate.parse("2018-03-12"))
					.nomeTitular("Rubens Doido")
					.contato("219887859878")
					.status("DISPONÍVEL")
					.endereco(e9)
					.imagem(imgAni6)
					.instituicao(inst4)
				.build();
			Animal a6 = Animal.builder()
					.nome("Princesa")
					.especie("Gato")
					.sexo("FÊMEA")
					.porte("M")
					.localizacao("ONG")
					.dataNasc(LocalDate.parse("2019-01-05"))
					.nomeTitular("Roberta Nunes")
					.contato("11945899878")
					.status("DISPONÍVEL")
					.endereco(e8)
					.imagem(imgAni8)
					.instituicao(inst3)
				.build();
			
			Animal a7 = Animal.builder()
					.nome("Pru")
					.especie("Cachorro")
					.sexo("FÊMEA")
					.porte("P")
					.localizacao("ONG")
					.dataNasc(LocalDate.parse("2015-11-14"))
					.nomeTitular("Juliana Prestes")
					.contato("47789888878")
					.status("DISPONÍVEL")
					.endereco(e7)
					.imagem(imgAni9)
					.instituicao(inst2)
				.build();
			
			Animal a8 = Animal.builder()
					.nome("Panda")
					.especie("Cachorro")
					.sexo("FÊMEA")
					.porte("P")
					.localizacao("ONG")
					.dataNasc(LocalDate.parse("2014-02-20"))
					.nomeTitular("Ronalda Fenômena")
					.contato("459887459878")
					.status("DISPONÍVEL")
					.endereco(e6)
					.imagem(imgAni4)
					.instituicao(inst1)
				.build();
			
			Animal a9 = Animal.builder()
					.nome("Briguento")
					.especie("Cachorro")
					.sexo("MACHO")
					.porte("P")
					.localizacao("ONG")
					.dataNasc(LocalDate.parse("2017-02-20"))
					.nomeTitular("Ronalda Fenômena")
					.contato("459887459878")
					.status("DISPONÍVEL")
					.endereco(e6)
					.imagem(imgAni1)
					.instituicao(inst1)
				.build();
			
			animalRepository.saveAll(Arrays.asList(a1,a2,a3,a4,a5,a6,a7,a8,a9));
		
		}
		
		if(pessoaService.listar().size() == 0) {
			
			Endereco e1 = Endereco.builder()
					.cep("97105150")
					.logradouro("Rua Franklin Bittencourt Filho")
					.numero("269")
					.complemento("")
					.bairro("Camobi")
					.cidade(cidadeRepository.findById(4791L).get())
			.build();
			
			Endereco e2 = Endereco.builder()
					.cep("01001000")
					.logradouro("Praça da Sé")
					.numero("100")
					.complemento("")
					.bairro("Sé")
					.cidade(cidadeRepository.findById(3830L).get())
			.build();
			
			Endereco e3 = Endereco.builder()
					.cep("01411011")
					.logradouro("Rua Barão de Capanema")
					.numero("650")
					.complemento("")
					.bairro("Jardins")
					.cidade(cidadeRepository.findById(3830L).get())
			.build();
			
			Endereco e4 = Endereco.builder()
					.cep("01304000")
					.logradouro("Rua Augusta")
					.numero("698")
					.complemento("")
					.bairro("Consolação")
					.cidade(cidadeRepository.findById(3830L).get())
			.build();
			
			Endereco e5 = Endereco.builder()
					.cep("06803440")
					.logradouro("Rua Belo Horizonte")
					.numero("123")
					.complemento("")
					.bairro("Centro")
					.cidade(cidadeRepository.findById(3435L).get())
			.build();
			
			
			Pessoa p1 = Pessoa.builder()
					.nome("João Pessoa")
					.dataNasc("13-10-1995")
					.sexo("MASCULINO")
					.tipo("ADOTANTE")
					.cpf("00202587458")
					.rg("8759874")
					.email("joao@gmail.com")
					.senha("123456")
					.contato("55985478574")
					.endereco(e1)
					.build();
			
			Pessoa p2 = Pessoa.builder()
					.nome("Tathiane Souza")
					.dataNasc("15-02-1994")
					.sexo("FEMININO")
					.tipo("TUTOR")
					.cpf("88747584789")
					.rg("8745987")
					.email("t@gmail.com")
					.senha("123456")
					.contato("41548796547")
					.endereco(e2)
					.build();
			
			Pessoa p3 = Pessoa.builder()
					.nome("Rosana Damasceno")
					.dataNasc("01-05-1992")
					.sexo("FEMININO")
					.tipo("ADOTANTE")
					.cpf("22154856984")
					.rg("8759874")
					.email("rosana@gmail.com")
					.senha("123456")
					.contato("11987458798")
					.endereco(e3)
					.build();
			
			Pessoa p4 = Pessoa.builder()
					.nome("Marcelo Dantas")
					.dataNasc("15-12-1990")
					.sexo("MASCULINO")
					.tipo("ADOTANTE")
					.cpf("25489687585")
					.rg("5689784")
					.email("marcelo@gmail.com")
					.senha("123456")
					.contato("55898745689")
					.endereco(e4)
					.build();
			
			Pessoa p5 = Pessoa.builder()
					.nome("Fernanda Kirschner")
					.dataNasc("08-09-1999")
					.sexo("FEMININO")
					.tipo("ADOTANTE")
					.cpf("55989745689")
					.rg("8798569")
					.email("fernanda@gmail.com")
					.senha("123456")
					.contato("45887986958")
					.endereco(e5)
					.build();
			
			pessoaRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
			
			Usuario usuario1 = Usuario.builder()
					.nome(p1.getNome())
					.email(p1.getEmail())
					.senha(passwordEncoder.encode(p1.getSenha()))
					.grupo(grupoRepository.findById(1L).get())
					.tipo(1L)
					.codigo(p1.getId())
			.build();
			
			Usuario usuario2 = Usuario.builder()
					.nome(p2.getNome())
					.email(p2.getEmail())
					.senha(passwordEncoder.encode(p2.getSenha()))
					.grupo(grupoRepository.findById(1L).get())
					.tipo(1L)
					.codigo(p2.getId())
			.build();
			
			Usuario usuario3 = Usuario.builder()
					.nome(p3.getNome())
					.email(p3.getEmail())
					.senha(passwordEncoder.encode(p3.getSenha()))
					.grupo(grupoRepository.findById(1L).get())
					.tipo(1L)
					.codigo(p3.getId())
			.build();
			
			Usuario usuario4 = Usuario.builder()
					.nome(p4.getNome())
					.email(p4.getEmail())
					.senha(passwordEncoder.encode(p4.getSenha()))
					.grupo(grupoRepository.findById(1L).get())
					.tipo(1L)
					.codigo(p4.getId())
			.build();
			
			Usuario usuario5 = Usuario.builder()
					.nome(p5.getNome())
					.email(p5.getEmail())
					.senha(passwordEncoder.encode(p5.getSenha()))
					.grupo(grupoRepository.findById(1L).get())
					.tipo(1L)
					.codigo(p5.getId())
			.build();
				
			usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5));
		
		}
		
	
	}
}
