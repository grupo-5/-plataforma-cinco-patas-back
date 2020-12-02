package br.com.cincopatas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	
//	@Autowired
//	private PersonalidadeRepository personalidadeRepository;
//	
//	@Autowired
//	private CuidadosVeterinariosRepository cuidadosVeterinariosRepository;
	
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
		
// 		final String urlEstados = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
		
// 		RestTemplate rest = new RestTemplate();
		
// 		if(estadoService.listar().size() != 27) {
		
// 			Estado[] estados = rest.getForObject(urlEstados, Estado[].class);
// 			List<Estado> listaEstados = Arrays.asList(estados);
// 			listaEstados
// 				.stream()
// 				.forEach(estado -> {
// 					estadoRepository.save(estado);
// 					Cidade[] cidades = rest.getForObject("https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + estado.getId() + "/municipios", Cidade[].class);
// 					List<Cidade> listaCidades = Arrays.asList(cidades);
// 					listaCidades
// 						.stream()
// 						.forEach(cidade -> {
// 							cidade.setEstado(estado);
// 							cidadeRepository.save(cidade);
// 					});
// 			});
			
// 		}
		
// 		if(permissaoRepository.findAll().size() != 2) {
// 			Permissao p1 = new Permissao();
// 			Permissao p2 = new Permissao();
			
// 			p1.setDescricao("Permissão de pessoas");
// 			p2.setDescricao("Permissão de instituições");
// 			p1.setId(1L);
// 			p2.setId(2L);
// 			p1.setNome("CP01");
// 			p2.setNome("CP02");
			
			
// 			permissaoRepository.saveAll(Arrays.asList(p1,p2));
			
// 			Grupo g1 = new Grupo();
// 			Grupo g2 = new Grupo();
			
// 			g1.setNome("Grupo 1 Pessoa");
// 			g2.setNome("Grupo 2 Instituição");
// 			g1.setId(1L);
// 			g2.setId(2L);
			
// 			Set<Permissao> set1 = new HashSet<Permissao>();
// 			Set<Permissao> set2 = new HashSet<Permissao>();
// 			set1.add(p1);
// 			set2.add(p2);

// 			g1.setPermissoes(set1);
// 			g2.setPermissoes(set2);
			
// 			grupoRepository.saveAll(Arrays.asList(g1,g2));
// 		}
		
// 		if(instituicaoService.listar().size() == 0) {
			
// 			Endereco e6 = Endereco.builder()
// 					.cep("60822480")
// 					.logradouro("Rua Benjamim Moura")
// 					.numero("100")
// 					.complemento("")
// 					.bairro("Cidade dos Funcionários")
// 					.cidade(cidadeRepository.findById(950L).get())
// 			.build();
			
// 			Endereco e7 = Endereco.builder()
// 					.cep("70331700")
// 					.logradouro("SHIGS 703")
// 					.numero("50")
// 					.complemento("")
// 					.bairro("Asa Sul")
// 					.cidade(cidadeRepository.findById(5570L).get())
// 			.build();
			
// 			Endereco e8 = Endereco.builder()
// 					.cep("01021200")
// 					.logradouro("Rua Vinte e Cinco de Março")
// 					.numero("400")
// 					.complemento("")
// 					.bairro("Centro")
// 					.cidade(cidadeRepository.findById(3830L).get())
// 			.build();
			
// 			Endereco e9 = Endereco.builder()
// 					.cep("97105340")
// 					.logradouro("Av. Roraima")
// 					.numero("100")
// 					.complemento("")
// 					.bairro("Camobi")
// 					.cidade(cidadeRepository.findById(4791L).get())
// 			.build();
			
// 			Endereco e10 = Endereco.builder()
// 					.cep("01329000")
// 					.logradouro("Rua dos Ingleses")
// 					.numero("250")
// 					.complemento("")
// 					.bairro("Morro dos Ingleses")
// 					.cidade(cidadeRepository.findById(3830L).get())
// 			.build();
			
// 			URL url1 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/ong1.png");
// 			URL url2 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/ong2.png");
// 			URL url3 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/ong3.jpg");
// 			URL url4 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/ong4.jpg");
// 			URL url5 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/ong5.png");
// 			URL url6 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal1.png");
// 			URL url7 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal2.jpg");
// 			URL url8 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal3.jpg");
// 			URL url9 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal4.jpg");
// 			URL url10 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal5.jpg");
// 			URL url11 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal6.jpg");
// 			URL url12 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal7.jpg");
// 			URL url13 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal8.jpg");
// 			URL url14 = new URL("https://dh-noturno.s3.us-east-2.amazonaws.com/Fotos/animal9.jpg");
			
// 			Imagem imgOng1 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url1)
// 			.build();
			
// 			Imagem imgOng2 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url2)
// 			.build();
			
// 			Imagem imgOng3 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url3)
// 			.build();
			
// 			Imagem imgOng4 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url4)
// 			.build();
			
// 			Imagem imgOng5 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url5)
// 			.build();
			
// 			Imagem imgAni1 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url6)
// 			.build();
			
// 			Imagem imgAni2 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url7)
// 			.build();
			
// 			Imagem imgAni3 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url8)
// 			.build();
			
// 			Imagem imgAni4 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url9)
// 			.build();
			
// 			Imagem imgAni5 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url10)
// 			.build();
			
// 			Imagem imgAni6 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url11)
// 			.build();
			
// 			Imagem imgAni7 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url12)
// 			.build();
			
// 			Imagem imgAni8 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url13)
// 			.build();
			
// 			Imagem imgAni9 = Imagem.builder()
// 					.nomeArquivo("")
// 					.nomeArquivoCompleto("")
// 					.contentType("")
// 					.tamanho(100L)
// 					.url(url14)
// 			.build();
			
// 			imagemRepository.saveAll(Arrays.asList(imgOng1, imgOng2, imgOng3, imgOng4, imgOng5, imgAni1, 
// 					imgAni2, imgAni3, imgAni4, imgAni5, imgAni6, imgAni7, imgAni8, imgAni9));
			
// 			Instituicao inst1 = Instituicao.builder()
// 					.nome("Animal Help")
// 					.numeroDocumento("123456789")
// 					.tipoDocumento("CNPJ")
// 					.contato("2135647896")
// 					.razaoSocial("")
// 					.inscricaoEstadual("")
// 					.capacidade(25L)
// 					.banco("Banco do Brasil")
// 					.agencia("1787-2")
// 					.conta("98358-5")
// 					.email("animalhelp@gmail.com")
// 					.senha("123456")
// 					.endereco(e6)
// 					.imagem(imagemRepository.findById(1L).get())
// 			.build();
			
// 			Instituicao inst2 = Instituicao.builder()
// 					.nome("SOS Animal")
// 					.numeroDocumento("123456789")
// 					.tipoDocumento("CNPJ")
// 					.contato("1135647896")
// 					.razaoSocial("")
// 					.inscricaoEstadual("")
// 					.capacidade(25L)
// 					.banco("Banco do Brasil")
// 					.agencia("1787-2")
// 					.conta("98358-5")
// 					.email("sos@gmail.com")
// 					.senha("123456")
// 					.endereco(e7)
// 					.imagem(imagemRepository.findById(2L).get())
// 			.build();
			
// 			Instituicao inst3 = Instituicao.builder()
// 					.nome("Brigada Animal")
// 					.numeroDocumento("123456789")
// 					.tipoDocumento("CNPJ")
// 					.contato("4535647896")
// 					.razaoSocial("")
// 					.inscricaoEstadual("")
// 					.capacidade(25L)
// 					.banco("Banco do Brasil")
// 					.agencia("1787-2")
// 					.conta("98358-5")
// 					.email("brigada@gmail.com")
// 					.senha("123456")
// 					.endereco(e8)
// 					.imagem(imagemRepository.findById(3L).get())
// 			.build();
			
// 			Instituicao inst4 = Instituicao.builder()
// 					.nome("ONG São Francisco")
// 					.numeroDocumento("123456789")
// 					.tipoDocumento("CNPJ")
// 					.contato("5535647896")
// 					.razaoSocial("")
// 					.inscricaoEstadual("")
// 					.capacidade(25L)
// 					.banco("Banco do Brasil")
// 					.agencia("1787-2")
// 					.conta("98358-5")
// 					.email("sf@gmail.com")
// 					.senha("123456")
// 					.endereco(e9)
// 					.imagem(imagemRepository.findById(4L).get())
// 			.build();
			
// 			Instituicao inst5 = Instituicao.builder()
// 					.nome("Animal Ajuda")
// 					.numeroDocumento("123456789")
// 					.tipoDocumento("CNPJ")
// 					.contato("1935647896")
// 					.razaoSocial("")
// 					.inscricaoEstadual("")
// 					.capacidade(25L)
// 					.banco("Banco do Brasil")
// 					.agencia("1787-2")
// 					.conta("98358-5")
// 					.email("ajuda@gmail.com")
// 					.senha("123456")
// 					.endereco(e10)
// 					.imagem(imagemRepository.findById(5L).get())
// 			.build();
			
// 			instituicaoRepository.saveAll(Arrays.asList(inst1, inst2, inst3, inst4, inst5));
			
// 			Usuario usuario6 = Usuario.builder()
// 					.nome(inst1.getNome())
// 					.email(inst1.getEmail())
// 					.senha(passwordEncoder.encode(inst1.getSenha()))
// 					.grupo(grupoRepository.findById(2L).get())
// 					.tipo(2L)
// 					.codigo(inst1.getId())
// 			.build();
			
// 			Usuario usuario7 = Usuario.builder()
// 					.nome(inst2.getNome())
// 					.email(inst2.getEmail())
// 					.senha(passwordEncoder.encode(inst2.getSenha()))
// 					.grupo(grupoRepository.findById(2L).get())
// 					.tipo(2L)
// 					.codigo(inst2.getId())
// 			.build();
			
// 			Usuario usuario8 = Usuario.builder()
// 					.nome(inst3.getNome())
// 					.email(inst3.getEmail())
// 					.senha(passwordEncoder.encode(inst3.getSenha()))
// 					.grupo(grupoRepository.findById(2L).get())
// 					.tipo(2L)
// 					.codigo(inst3.getId())
// 			.build();
			
// 			Usuario usuario9 = Usuario.builder()
// 					.nome(inst4.getNome())
// 					.email(inst4.getEmail())
// 					.senha(passwordEncoder.encode(inst4.getSenha()))
// 					.grupo(grupoRepository.findById(2L).get())
// 					.tipo(2L)
// 					.codigo(inst4.getId())
// 			.build();
			
// 			Usuario usuario10 = Usuario.builder()
// 					.nome(inst5.getNome())
// 					.email(inst5.getEmail())
// 					.senha(passwordEncoder.encode(inst5.getSenha()))
// 					.grupo(grupoRepository.findById(2L).get())
// 					.tipo(2L)
// 					.codigo(inst5.getId())
// 			.build();
			
// 			usuarioRepository.saveAll(Arrays.asList(usuario6, usuario7, usuario8, usuario9, usuario10));
			
// 			Personalidade pers1 = new Personalidade();
// 			Personalidade pers2 = new Personalidade();
// 			Personalidade pers3 = new Personalidade();
// 			Personalidade pers4 = new Personalidade();
// 			Personalidade pers5 = new Personalidade();

			
// 			pers1.setDescricao("Dócil");
// 			pers2.setDescricao("Brincalhão");
// 			pers3.setDescricao("Sociável");
// 			pers4.setDescricao("Imperativo");
// 			pers5.setDescricao("Carente");
			
// 			CuidadosVeterinarios c1 = new CuidadosVeterinarios();
// 			CuidadosVeterinarios c2 = new CuidadosVeterinarios();
// 			CuidadosVeterinarios c3 = new CuidadosVeterinarios();
// 			CuidadosVeterinarios c4 = new CuidadosVeterinarios();
			
// 			c1.setDescricao("Vermifugado");
// 			c2.setDescricao("Castrado");
// 			c3.setDescricao("Vacinado");
// 			c4.setDescricao("Cuidados especiais");
			
// 			Animal a1 = Animal.builder()
// 				.id(1L)
// 				.nome("Toddy")
// 				.especie("Cachorro")
// 				.sexo("Masculino")
// 				.sexo("Macho")
// 				.porte("M")
// 				.localizacao("Ong")
// 				.dataNasc(LocalDate.parse("2015-02-20"))
// 				.nomeTitular("João Roberto")
// 				.contato("45988745978")
// 				.status("Disponível")
// 				.endereco(e10)
// 				.imagem(imgAni2)
// 				.instituicao(inst1)
// 			.build();
			
// 			Animal a2 = Animal.builder()
// 					.id(2L)
// 					.nome("Peludo")
// 					.especie("Cachorro")
// 					.sexo("Macho")
// 					.porte("G")
// 					.localizacao("Ong")
// 					.dataNasc(LocalDate.parse("2019-02-27"))
// 					.nomeTitular("João Roberto")
// 					.contato("45988745878")
// 					.status("Disponível")
// 					.endereco(e10)
// 					.imagem(imgAni3)
// 					.instituicao(inst1)
// 				.build();
			
// 			Animal a3 = Animal.builder()
// 					.id(3L)
// 					.nome("Fofucho")
// 					.especie("Gato")
// 					.sexo("Macho")
// 					.porte("P")
// 					.localizacao("Ong")
// 					.dataNasc(LocalDate.parse("2017-02-20"))
// 					.nomeTitular("João Roberto")
// 					.contato("459887459878")
// 					.status("Disponível")
// 					.endereco(e10)
// 					.imagem(imgAni5)
// 					.instituicao(inst5)
// 				.build();
			
			
// 			Animal a4 = Animal.builder()
// 					.id(4L)
// 					.nome("Garfield")
// 					.especie("Gato")
// 					.sexo("Macho")
// 					.porte("P")
// 					.localizacao("Com o dono")
// 					.dataNasc(LocalDate.parse("2020-09-15"))
// 					.nomeTitular("João Roberto")
// 					.contato("45988749878")
// 					.status("Disponível")
// 					.endereco(e10)
// 					.imagem(imgAni7)
// 					.instituicao(inst5)
// 				.build();

			
// 			Animal a5 = Animal.builder()
// 					.id(5L)
// 					.nome("Sensei")
// 					.especie("Gato")
// 					.sexo("Macho")
// 					.porte("M")
// 					.localizacao("Ong")
// 					.dataNasc(LocalDate.parse("2018-03-12"))
// 					.nomeTitular("Rubens Doido")
// 					.contato("21987859878")
// 					.status("Disponível")
// 					.endereco(e9)
// 					.imagem(imgAni6)
// 					.instituicao(inst4)
// 				.build();
			
			
// 			Animal a6 = Animal.builder()
// 					.id(6L)
// 					.nome("Princesa")
// 					.especie("Gato")
// 					.sexo("Fêmea")
// 					.porte("M")
// 					.localizacao("Com o dono")
// 					.dataNasc(LocalDate.parse("2019-01-05"))
// 					.nomeTitular("Roberta Nunes")
// 					.contato("1194599878")
// 					.status("Disponível")
// 					.endereco(e8)
// 					.imagem(imgAni8)
// 					.instituicao(inst3)
// 				.build();
			
			
// 			Animal a7 = Animal.builder()
// 					.id(7L)
// 					.nome("Pru")
// 					.especie("Cachorro")
// 					.sexo("Fêmea")
// 					.porte("P")
// 					.localizacao("Ong")
// 					.dataNasc(LocalDate.parse("2015-11-14"))
// 					.nomeTitular("Juliana Prestes")
// 					.contato("47789888878")
// 					.status("Disponível")
// 					.endereco(e7)
// 					.imagem(imgAni9)
// 					.instituicao(inst2)
// 				.build();
			
			
// 			Animal a8 = Animal.builder()
// 					.id(8L)
// 					.nome("Panda")
// 					.especie("Cachorro")
// 					.sexo("Fêmea")
// 					.porte("P")
// 					.localizacao("Com o dono")
// 					.dataNasc(LocalDate.parse("2014-02-20"))
// 					.nomeTitular("Ronalda Fenômena")
// 					.contato("45987459878")
// 					.status("Disponível")
// 					.endereco(e6)
// 					.imagem(imgAni4)
// 					.instituicao(inst1)
// 				.build();
		
			
// 			Animal a9 = Animal.builder()
// 					.id(9L)
// 					.nome("Briguento")
// 					.especie("Cachorro")
// 					.sexo("Macho")
// 					.porte("P")
// 					.localizacao("Com o dono")
// 					.dataNasc(LocalDate.parse("2017-02-20"))
// 					.nomeTitular("Ronalda Fenômena")
// 					.contato("45987459878")
// 					.status("Disponível")
// 					.endereco(e6)
// 					.imagem(imgAni1)
// 					.instituicao(inst1)
// 				.build();
			
// 			animalRepository.saveAll(Arrays.asList(a1,a2,a3,a4,a5,a6,a7,a8,a9));
			
// 			pers1.setAnimal(a1);
// 			pers1.setId(1L);
// 			pers5.setAnimal(a1);
// 			pers5.setId(2L);
// 			personalidadeRepository.saveAll(Arrays.asList(pers1, pers5));
// 			c1.setAnimal(a1);
// 			c1.setId(1L);
// 			c2.setAnimal(a1);
// 			c2.setId(2L);
// 			cuidadosVeterinariosRepository.saveAll(Arrays.asList(c1,c2));
// 			a1.setPersonalidades(Arrays.asList(pers1, pers5));
// 			a1.setCuidadosVet(Arrays.asList(c1,c2));
			
// 			pers1.setAnimal(a2);
// 			pers1.setId(3L);
// 			pers3.setAnimal(a2);
// 			pers3.setId(4L);
// 			personalidadeRepository.saveAll(Arrays.asList(pers1, pers3));
// 			c1.setAnimal(a1);
// 			c1.setId(3L);
// 			c2.setAnimal(a1);
// 			c2.setId(4L);
// 			c3.setAnimal(a1);
// 			c3.setId(5L);
// 			cuidadosVeterinariosRepository.saveAll(Arrays.asList(c1,c2,c3));
// 			a2.setPersonalidades(Arrays.asList(pers1, pers3));
// 			a2.setCuidadosVet(Arrays.asList(c1,c2, c3));
			
// 			pers1.setAnimal(a3);
// 			pers1.setId(5L);
// 			pers5.setAnimal(a3);
// 			pers5.setId(6L);
// 			personalidadeRepository.saveAll(Arrays.asList(pers1, pers5));
// 			c3.setAnimal(a3);
// 			c3.setId(6L);
// 			cuidadosVeterinariosRepository.saveAll(Arrays.asList(c3));
// 			a3.setPersonalidades(Arrays.asList(pers1, pers5));
// 			a3.setCuidadosVet(Arrays.asList(c3));
			
// 			pers1.setAnimal(a4);
// 			pers1.setId(7L);
// 			pers2.setAnimal(a4);
// 			pers2.setId(8L);
// 			personalidadeRepository.saveAll(Arrays.asList(pers1, pers2));
// 			c2.setAnimal(a4);
// 			c2.setId(7L);
// 			cuidadosVeterinariosRepository.saveAll(Arrays.asList(c2));
// 			a4.setPersonalidades(Arrays.asList(pers1, pers2));
// 			a4.setCuidadosVet(Arrays.asList(c2));
			
// 			pers1.setAnimal(a5);
// 			pers1.setId(9L);
// 			pers2.setAnimal(a5);
// 			pers2.setId(10L);
// 			pers3.setAnimal(a5);
// 			pers3.setId(11L);
// 			personalidadeRepository.saveAll(Arrays.asList(pers1, pers2, pers3));
// 			c1.setAnimal(a5);
// 			c1.setId(8L);
// 			c2.setAnimal(a5);
// 			c2.setId(9L);
// 			cuidadosVeterinariosRepository.saveAll(Arrays.asList(c1,c2));
// 			a5.setPersonalidades(Arrays.asList(pers1, pers2, pers3));
// 			a5.setCuidadosVet(Arrays.asList(c1,c2));
			
// 			pers4.setAnimal(a6);
// 			pers4.setId(12L);
// 			personalidadeRepository.saveAll(Arrays.asList(pers4));
// 			c1.setAnimal(a6);
// 			c1.setId(10L);
// 			cuidadosVeterinariosRepository.saveAll(Arrays.asList(c1));
// 			a6.setPersonalidades(Arrays.asList(pers4));
// 			a6.setCuidadosVet(Arrays.asList(c1));
			
// 			pers5.setAnimal(a7);
// 			pers5.setId(13L);
// 			personalidadeRepository.saveAll(Arrays.asList(pers5));
// 			c2.setAnimal(a7);
// 			c2.setId(11L);
// 			cuidadosVeterinariosRepository.saveAll(Arrays.asList(c2));
// 			a7.setPersonalidades(Arrays.asList(pers5));
// 			a7.setCuidadosVet(Arrays.asList(c2));
			
// 			pers3.setAnimal(a8);
// 			pers3.setId(14L);
// 			pers4.setAnimal(a8);
// 			pers4.setId(15L);
// 			personalidadeRepository.saveAll(Arrays.asList(pers3, pers4));
// 			c2.setAnimal(a8);
// 			c2.setId(12L);
// 			c3.setAnimal(a8);
// 			c3.setId(13L);
// 			cuidadosVeterinariosRepository.saveAll(Arrays.asList(c2,c3));
// 			a8.setPersonalidades(Arrays.asList(pers3, pers4));
// 			a8.setCuidadosVet(Arrays.asList(c2, c3));
			
// 			pers1.setAnimal(a9);
// 			pers1.setId(16L);
// 			pers2.setAnimal(a9);
// 			pers2.setId(17L);
// 			pers3.setAnimal(a9);
// 			pers3.setId(18L);
// 			personalidadeRepository.saveAll(Arrays.asList(pers1, pers2, pers3));
// 			c4.setAnimal(a9);
// 			c4.setId(14L);
// 			cuidadosVeterinariosRepository.saveAll(Arrays.asList(c4));
// 			a9.setPersonalidades(Arrays.asList(pers1, pers2, pers3));
// 			a9.setCuidadosVet(Arrays.asList(c4));
			
// 			animalRepository.saveAll(Arrays.asList(a1,a2,a3,a4,a5,a6,a7,a8,a9));
		
// 		}
		
// 		if(pessoaService.listar().size() == 0) {
			
// 			Endereco e1 = Endereco.builder()
// 					.cep("97105150")
// 					.logradouro("Rua Franklin Bittencourt Filho")
// 					.numero("269")
// 					.complemento("")
// 					.bairro("Camobi")
// 					.cidade(cidadeRepository.findById(4791L).get())
// 			.build();
			
// 			Endereco e2 = Endereco.builder()
// 					.cep("01001000")
// 					.logradouro("Praça da Sé")
// 					.numero("100")
// 					.complemento("")
// 					.bairro("Sé")
// 					.cidade(cidadeRepository.findById(3830L).get())
// 			.build();
			
// 			Endereco e3 = Endereco.builder()
// 					.cep("01411011")
// 					.logradouro("Rua Barão de Capanema")
// 					.numero("650")
// 					.complemento("")
// 					.bairro("Jardins")
// 					.cidade(cidadeRepository.findById(3830L).get())
// 			.build();
			
// 			Endereco e4 = Endereco.builder()
// 					.cep("01304000")
// 					.logradouro("Rua Augusta")
// 					.numero("698")
// 					.complemento("")
// 					.bairro("Consolação")
// 					.cidade(cidadeRepository.findById(3830L).get())
// 			.build();
			
// 			Endereco e5 = Endereco.builder()
// 					.cep("06803440")
// 					.logradouro("Rua Belo Horizonte")
// 					.numero("123")
// 					.complemento("")
// 					.bairro("Centro")
// 					.cidade(cidadeRepository.findById(3435L).get())
// 			.build();
			
			
// 			Pessoa p1 = Pessoa.builder()
// 					.nome("João Pessoa")
// 					.dataNasc("13-10-1995")
// 					.sexo("Masculino")
// 					.tipo("Adotante")
// 					.cpf("00202587458")
// 					.rg("8759874")
// 					.email("joao@gmail.com")
// 					.senha("123456")
// 					.contato("55985478574")
// 					.endereco(e1)
//					.imagem(imagemRepository.findById(2L).get())
// 					.build();
			
// 			Pessoa p2 = Pessoa.builder()
// 					.nome("Tathiane Souza")
// 					.dataNasc("15-02-1994")
// 					.sexo("Feminino")
// 					.tipo("Tutor")
// 					.cpf("88747584789")
// 					.rg("8745987")
// 					.email("t@gmail.com")
// 					.senha("123456")
// 					.contato("41548796547")
// 					.endereco(e2)
//					.imagem(imagemRepository.findById(2L).get())
// 					.build();
			
// 			Pessoa p3 = Pessoa.builder()
// 					.nome("Rosana Damasceno")
// 					.dataNasc("01-05-1992")
// 					.sexo("Feminino")
// 					.tipo("Adotante")
// 					.cpf("22154856984")
// 					.rg("8759874")
// 					.email("rosana@gmail.com")
// 					.senha("123456")
// 					.contato("11987458798")
// 					.endereco(e3)
//					.imagem(imagemRepository.findById(2L).get())
// 					.build();
			
// 			Pessoa p4 = Pessoa.builder()
// 					.nome("Marcelo Dantas")
// 					.dataNasc("15-12-1990")
// 					.sexo("Masculino")
// 					.tipo("Adotante")
// 					.cpf("25489687585")
// 					.rg("5689784")
// 					.email("marcelo@gmail.com")
// 					.senha("123456")
// 					.contato("55898745689")
// 					.endereco(e4)
//					.imagem(imagemRepository.findById(2L).get())
// 					.build();
			
// 			Pessoa p5 = Pessoa.builder()
// 					.nome("Fernanda Kirschner")
// 					.dataNasc("08-09-1999")
// 					.sexo("Feminino")
// 					.tipo("Adotante")
// 					.cpf("55989745689")
// 					.rg("8798569")
// 					.email("fernanda@gmail.com")
// 					.senha("123456")
// 					.contato("45887986958")
// 					.endereco(e5)
//					.imagem(imagemRepository.findById(2L).get())		
// 					.build();
			
// 			pessoaRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
			
// 			Usuario usuario1 = Usuario.builder()
// 					.nome(p1.getNome())
// 					.email(p1.getEmail())
// 					.senha(passwordEncoder.encode(p1.getSenha()))
// 					.grupo(grupoRepository.findById(1L).get())
// 					.tipo(1L)
// 					.codigo(p1.getId())
// 			.build();
			
// 			Usuario usuario2 = Usuario.builder()
// 					.nome(p2.getNome())
// 					.email(p2.getEmail())
// 					.senha(passwordEncoder.encode(p2.getSenha()))
// 					.grupo(grupoRepository.findById(1L).get())
// 					.tipo(1L)
// 					.codigo(p2.getId())
// 			.build();
			
// 			Usuario usuario3 = Usuario.builder()
// 					.nome(p3.getNome())
// 					.email(p3.getEmail())
// 					.senha(passwordEncoder.encode(p3.getSenha()))
// 					.grupo(grupoRepository.findById(1L).get())
// 					.tipo(1L)
// 					.codigo(p3.getId())
// 			.build();
			
// 			Usuario usuario4 = Usuario.builder()
// 					.nome(p4.getNome())
// 					.email(p4.getEmail())
// 					.senha(passwordEncoder.encode(p4.getSenha()))
// 					.grupo(grupoRepository.findById(1L).get())
// 					.tipo(1L)
// 					.codigo(p4.getId())
// 			.build();
			
// 			Usuario usuario5 = Usuario.builder()
// 					.nome(p5.getNome())
// 					.email(p5.getEmail())
// 					.senha(passwordEncoder.encode(p5.getSenha()))
// 					.grupo(grupoRepository.findById(1L).get())
// 					.tipo(1L)
// 					.codigo(p5.getId())
// 			.build();
				
// 			usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5));
		
// 		}
		
	
  }
}
