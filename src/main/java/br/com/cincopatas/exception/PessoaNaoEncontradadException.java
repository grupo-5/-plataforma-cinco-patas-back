package br.com.cincopatas.exception;

public class PessoaNaoEncontradadException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public PessoaNaoEncontradadException(String mensagem) {
		super(mensagem);		
	}

	public PessoaNaoEncontradadException(Long id) {
		this(String.format("Não existe um cadastro de pessoa com código %d", id));
	}
}
