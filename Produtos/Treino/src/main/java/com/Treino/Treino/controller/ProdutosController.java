package com.Treino.Treino.controller;


import com.Treino.Treino.business.ProdutosService;
import com.Treino.Treino.infraestructure.entitys.Produtos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutosController {

    private final ProdutosService produtosService;

    @PostMapping
    public ResponseEntity<Void> salvarProduto(@RequestBody Produtos produtos) {
        produtosService.salvarProduto(produtos);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Produtos> buscarProdutoPorNome(@RequestParam String nome){
        return ResponseEntity.ok(produtosService.burcarProdutoPorNome(nome));
    }
    @DeleteMapping
    public ResponseEntity<Void> deletarProdutoPorNome(@RequestParam String nome){
        produtosService.deletarProdutoPorNome(nome);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<Void> atualizarProdutoPorId(@RequestParam Integer id,
                                                      @RequestBody Produtos produtos) {
        produtosService.atualizarProdutoPorId(id, produtos);
        return ResponseEntity.ok().build();
    }
}
