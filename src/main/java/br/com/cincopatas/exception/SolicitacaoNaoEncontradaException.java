package br.com.cincopatas.exception;

public class SolicitacaoNaoEncontradaException extends EntidadeNaoEncontradaException{

	public SolicitacaoNaoEncontradaException(String mensagem) {
		super(mensagem);		
	}

	public SolicitacaoNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de animal com código %d", id));
	}
}
