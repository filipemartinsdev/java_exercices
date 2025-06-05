public class Main {
    public static void main(String[] args) {
        Loja.Produto ps5 = new Loja.Produto.ProdutoEletronico("play station 5", 4500F);
        Loja.Produto camiseta = new Loja.Produto.ProdutoRoupa("camiseta SOAD", 150F, "G");
        Loja.Produto queijo = new Loja.Produto.ProdutoFrios("queijo minas padrao 500g", 34.99F, "2025-12-01");
        Loja.Produto arroz = new Loja.Produto.ProdutoAlimento("arroz cristal 5kg", 24.99F, "2026-01-01");

        Loja.Venda venda1 = new Loja.Venda();
        venda1.adicionarProduto(ps5, 1);
        venda1.adicionarProduto(camiseta, 10);

        Loja.Venda venda2 = new Loja.Venda();
        venda2.adicionarProduto(queijo, 100);
        venda2.adicionarProduto(arroz, 50);

        Loja.Venda venda3 = new Loja.Venda();
        venda3.adicionarProduto(queijo, 500);
        venda3.adicionarProduto(camiseta, 199);


        Loja.printAnalize();
    }
}