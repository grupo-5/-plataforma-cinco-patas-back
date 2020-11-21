package br.com.cincopatas;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import br.com.cincopatas.model.Cidade;
import br.com.cincopatas.model.Estado;
import br.com.cincopatas.model.Grupo;
import br.com.cincopatas.model.Permissao;
import br.com.cincopatas.repository.CidadeRepository;
import br.com.cincopatas.repository.EstadoRepository;
import br.com.cincopatas.repository.GrupoRepository;
import br.com.cincopatas.repository.PermissaoRepository;
import br.com.cincopatas.service.EstadoService;

@SpringBootApplication
public class PlataformaCincoPatasBackApplication implements CommandLineRunner{

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
			
			p1.setNome("Permissão básica");
			p2.setNome("Permissão pessoas");
			p3.setNome("Permissão de instituições");
			p1.setId(1L);
			p2.setId(2L);
			p3.setId(3L);
			p1.setDescricao("DH01");
			p2.setDescricao("DH05");
			p3.setDescricao("DH03");
			
			
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
		
	}
}
