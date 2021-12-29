package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarUmProdutoComValorIgualA(double valor) {

        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Cocada");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("branco");
        cores.add("amarelo");
        produto.setProdutoCores(cores);

        List<ComponentePojo> componentes = new ArrayList<>();
        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Embalagem");
        componente.setComponenteQuantidade(1);
        componentes.add(componente);
        produto.setComponentes(componentes);

        ComponentePojo componente2 = new ComponentePojo();
        componente2.setComponenteNome("Embalagem");
        componente2.setComponenteQuantidade(1);
        componentes.add(componente2);
        produto.setComponentes(componentes);

        return produto;

    }
}
