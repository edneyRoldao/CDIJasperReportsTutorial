
package sistemaDeVendas.services;

import java.io.Serializable;

import javax.inject.Inject;

import sistemaDeVendas.exceptions.BusinessException;
import sistemaDeVendas.jpa.Transactional;
import sistemaDeVendas.model.Produto;
import sistemaDeVendas.repositories.ProdutoRepository;

public class CadastroProdutoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	ProdutoRepository produtoRepository;

	@Transactional
	public Produto salvar(Produto value) {
		
		Produto produto = produtoRepository.buscarPorSKU(value.getSku());
		
		if(produto != null) {
			throw new BusinessException("Já existe um produto com o SKU informado !");
		}
		
		return produtoRepository.guardar(value);
	}
	

}
