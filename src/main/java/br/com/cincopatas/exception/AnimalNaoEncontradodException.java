package br.com.cincopatas.exception;

public class AnimalNaoEncontradodException extends EntidadeNaoEncontradaException{

	public AnimalNaoEncontradodException(String mensagem) {
		super(mensagem);		
	}

	public AnimalNaoEncontradodException(Long id) {
		this(String.format("Não existe um cadastro de animal com código %d", id));
	}
}
