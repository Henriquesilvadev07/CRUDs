package com.Treino.Treino.business;


import com.Treino.Treino.infraestructure.entitys.Produtos;
import com.Treino.Treino.infraestructure.repository.ProdutosRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutosService {

    private final ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public void salvarProduto(Produtos produtos) {
        produtosRepository.saveAndFlush(produtos);
    }

    public Produtos burcarProdutoPorNome(String nome){
        return produtosRepository.findByNome(nome).orElseThrow(
                ()-> new RuntimeException("Produto não encontrado!")
        );
    }

    public void deletarProdutoPorNome(String nome) {
        produtosRepository.deleteByNome(nome);
    }

    public void atualizarProdutoPorId(Integer id, Produtos produtos) {
        Produtos produtosEntity = produtosRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("ID não encontrado!"));
        Produtos produtosAtualizado = Produtos.builder()
                .nome(produtos.getNome() != null ? produtos.getNome() :
                        produtosEntity.getNome())
                .tipo(produtos.getTipo() != null ? produtos.getTipo() :
                        produtosEntity.getTipo())
                .preco(produtos.getPreco() != 0.0 ? produtos.getPreco() :
                        produtosEntity.getPreco())
                .id(produtosEntity.getId())
                .build();
                produtosRepository.saveAndFlush(produtosAtualizado);
    }
}
