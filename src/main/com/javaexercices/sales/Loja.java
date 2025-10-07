package sales;

import java.util.HashMap;
import java.util.Map;

public class Loja {
    private static Map<Integer, Venda> vendas = new HashMap<>();

    public static Map<String, Integer> quantidadeVendasPorCategoria = new HashMap<>();
    public static Map<String, Float> vendasPorCategoria = new HashMap<>();

    private static int quantidadeTotalItensVendidos;
    private static float totalGanhoEmVendas;

    public static void printAnalize(){
        System.out.println("++======================++");
        System.out.println("||  ANALIZE DE VENDAS  ||");
        System.out.println("++=====================++");

        for(Venda venda:Loja.vendas.values()){
            System.out.println("\n> VENDA #" + venda.ID);
            System.out.println("+------------------------------------------------------+");


            System.out.printf("%-30s%-15s%-10s%s\n", "| descricao", "| preco uni", "| qnt", "|");
            System.out.println("+------------------------------------------------------+");

            for(Produto item:venda.getProdutos().keySet()){

                System.out.printf("%-30s%-15s%-10s%s\n", "| "+item.getNome(), "| "+ item.getPrecoUnitario(), "| "+ venda.getQuantidadePedida(item), "|");
            }
            System.out.println("+------------------------------------------------------+");
            System.out.printf("| %-28s", "TOTAL = R$" + String.format("%.2f", venda.getValorTotal()));
            System.out.print("| \n");
            System.out.println("+-----------------------------+");

        }

        System.out.println("\n+----------------------+");
        System.out.println("|     ESTATISTICAS     |");
        System.out.println("+----------------------+");

        System.out.println("| Quantidade de vendas = "+ Loja.getQuantidadeVendas());
        System.out.printf("| Valor total = R$%.2f\n", Loja.getTotaGanhoEmVendas());
        System.out.println(
                "| Total de itens vendidos = " + Loja.getQuantidadeTotalItensVendidos()
        );
        System.out.printf(
                "%10s%-30s%5s itens | %s\n",
                "| Vendas: ", "categoria Eletronicos =",
                Loja.getQuantidadeVendasNaCategoria("eletronico"),
                "R$"+Loja.getTotalVendasNaCategoria("eletronico")
        );
        System.out.printf(
                "%-10s%-30s%5s itens | %s\n",
                "|",
                "categoria Alimentos =",
                Loja.getQuantidadeVendasNaCategoria("alimento"),
                "R$"+Loja.getTotalVendasNaCategoria("alimento")
        );
        System.out.printf(
                "%-10s%-30s%5s itens | %s\n",
                "|", "categoria Frios =",
                Loja.getQuantidadeVendasNaCategoria("frios"),
                "R$"+Loja.getTotalVendasNaCategoria("frios")
        );
        System.out.printf(
                "%-10s%-30s%5s itens | %s\n",
                "|", "categoria Roupas =",
                Loja.getQuantidadeVendasNaCategoria("roupa"),
                "R$"+Loja.getTotalVendasNaCategoria("roupa")
        );

        System.out.println("+------->\n");
    }

    public static void incrementarTotalItensVendidos(int valor){
        Loja.quantidadeTotalItensVendidos += valor;
    }
    public static void decrementarTotalItensVendidos(int valor){
        Loja.quantidadeTotalItensVendidos -= valor;
    }

    public static void incrementarQuantidadeVendasPorCategoria(String categoria, int quantidade){
        Loja.quantidadeVendasPorCategoria.put(categoria, quantidade);
    }
    public static void incrementarVendasPorCategoria(String categoria, float preco, int quantidade){
        Loja.vendasPorCategoria.put(categoria, quantidade*preco);
    }

    public static int getQuantidadeVendasNaCategoria(String categoria){
        return Loja.quantidadeVendasPorCategoria.get(categoria);
    }
    public static float getTotalVendasNaCategoria(String categoria){
        return Loja.vendasPorCategoria.get(categoria);
    }
    public static int getQuantidadeVendas(){
        return Loja.vendas.size();
    }
    public static int getQuantidadeTotalItensVendidos() {
        return Loja.quantidadeTotalItensVendidos;
    }
    public static float getTotaGanhoEmVendas(){
        return Loja.totalGanhoEmVendas;
    }
    public static void adicionarVenda(Venda venda){
        Loja.vendas.put(venda.getID(), venda);
    }

    public static void atualizarTotalGanhoEmVendas(){
        totalGanhoEmVendas=0;
        for(Venda venda:Loja.vendas.values()){
            totalGanhoEmVendas+=venda.valorTotal;
        }
    }

    public static class Venda {
        private Map<Produto, Integer> listaProdutos = new HashMap<>();
        private static int ultimoIdVenda = 0;
        private float valorTotal = 0;
        private final int ID;

        public Venda(){
            this.ID = ++Venda.ultimoIdVenda;
            Loja.vendas.put(this.ID, this);
        }

        public void adicionarProduto(Produto produto, int quantidade){
            this.listaProdutos.put(produto, quantidade);
            this.valorTotal += produto.getPrecoUnitario() * quantidade;
            Loja.totalGanhoEmVendas += this.valorTotal;

            int vendasPorCategoriaAtual= Loja.getQuantidadeVendasNaCategoria(produto.getCategoria());
            Loja.incrementarQuantidadeVendasPorCategoria(produto.getCategoria(), vendasPorCategoriaAtual+quantidade);
            Loja.incrementarVendasPorCategoria(produto.getCategoria(), produto.getPrecoUnitario(), quantidade);

            Loja.adicionarVenda(this);
            Loja.incrementarTotalItensVendidos(quantidade);
            Loja.atualizarTotalGanhoEmVendas();
        }

        public int getID(){
            return this.ID;
        }
        public int getQuantidadeProdutos(){
            return this.listaProdutos.size();
        }
        public int getQuantidadePedida(Produto produto){
            return this.listaProdutos.get(produto);
        }
        public float getValorTotal() {
            return this.valorTotal;
        }
        public Map<Produto, Integer> getProdutos(){
            return this.listaProdutos;
        }
    }

    public static abstract class Produto {
        private String nome;
        private float precoUnitario;

        public Produto(String nome, float preco){
            this.nome = nome;
            this.precoUnitario = preco;
        }

        public String getNome() {
            return this.nome;
        }
        public float getPrecoUnitario(){
            return this.precoUnitario;
        }

//        ABSTRACT METHOD
        public abstract String getCategoria();

//        ELETRONICOS ---------------------------------
        static class ProdutoEletronico extends Produto {
            private static final String CATEGORIA = "eletronico";
            private int quantidadeVendida = 0;

            public ProdutoEletronico(String nome, float preco){
                super(nome, preco);
                Loja.incrementarQuantidadeVendasPorCategoria(CATEGORIA, quantidadeVendida);
            }

            @Override
            public String getCategoria(){
                return ProdutoEletronico.CATEGORIA;
            }
            public int getQuantidadeVendida(){
                return quantidadeVendida;
            }
            public void incrimentarQuantidadeVendida(int valor){
                this.quantidadeVendida+=valor;
            }
        }
//        ALIMENTOS -------------------------------
        static class ProdutoAlimento extends Produto {
            private static final String CATEGORIA = "alimento";
            private final String DATA_VALIDADE;
            private int quantidadeVendida = 0;

            public ProdutoAlimento(String nome, float preco, String validade) {
                super(nome, preco);
                this.DATA_VALIDADE = validade;
                Loja.incrementarQuantidadeVendasPorCategoria(CATEGORIA, quantidadeVendida);
            }

            @Override
            public String getCategoria() {
                return ProdutoAlimento.CATEGORIA;
            }
            public int getQuantidadeVendida(){
                return this.quantidadeVendida;
            }
            public String getDataValidade() {
                return this.DATA_VALIDADE;
            }
            public void incrimentarQuantidadeVendida(int valor){
                this.quantidadeVendida+=valor;
            }
        }

//        FRIOS ---------------------------------------------
        static class ProdutoFrios extends ProdutoAlimento {
            private static final String CATEGORIA = "frios";
            private int quantidadeVendida = 0;

            public ProdutoFrios(String nome, float preco, String validade){
                super(nome, preco, validade);
                Loja.incrementarQuantidadeVendasPorCategoria(CATEGORIA, quantidadeVendida);
            }

            @Override
            public String getCategoria(){
                return ProdutoFrios.CATEGORIA;
            }
            public int getQuantidadeVendida(){
                return this.quantidadeVendida;
            }
            public void incrimentarQuantidadeVendida(int valor){
                this.quantidadeVendida+=valor;
            }
        }

//        ROUPAS ------------------------------------------------------
        static class ProdutoRoupa extends Produto {
            private static final String CATEGORIA = "roupa";
            private String tamanho;
            private int quantidadeVendida = 0;

            public ProdutoRoupa(String nome, float preco, String tamanho){
                super(nome, preco);
                this.tamanho = tamanho;
                Loja.incrementarQuantidadeVendasPorCategoria(CATEGORIA, quantidadeVendida);
            }

            @Override
            public String getCategoria(){
                return ProdutoRoupa.CATEGORIA;
            }
            public String getTamanho(){
                return this.tamanho;
            }
            public int getQuantidadeVendida(){
                return this.quantidadeVendida;
            }
            public void incrimentarQuantidadeVendida(int valor){
                this.quantidadeVendida+=valor;
            }
        }
    }
}
