package br.com.cincopatas.exception;

public class SolicitacaoNaoEncontradaException extends EntidadeNaoEncontradaException{
	private static final long serialVersionUID = 1L;

	public SolicitacaoNaoEncontradaException(String mensagem) {
		super(mensagem);		
	}

	public SolicitacaoNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de animal com código %d", id));
	}
}
