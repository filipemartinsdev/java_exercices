import java.util.ArrayList;

public class App{
    public static void main(String[]args){
        Livro livro1 = new Livro("Java: Aprenda a programar edicao 8", "Paul Deitel");
        Livro livro2 = new Livro("SQL", "Vinicius");
        Livro livro3 = new Livro("Entendento Algoritmos", "Aditya Y. Bhargava");
        Livro livro4 = new Livro("Clean Code", "Robert Cecil Martin");
        Livro livro5 = new Livro("Java: Aprenda a programar edicao 10", "Paul Deitel");

        Biblioteca.adicionar_livro(livro1, livro2, livro3, livro4, livro5);
        Usuario eu = new Usuario("Filipe Martins");

        Biblioteca.exibir_catalogo();

        Biblioteca.fazer_emprestimo(eu, livro1);
        Biblioteca.fazer_emprestimo(eu, livro2);

        Biblioteca.fazer_devolucao(eu, livro1);

        for(String flag:args){
            if (flag.equals("-s")) {
                System.out.printf("%-30s%d\n", "Total de livros na biblioteca:", Livro.total_livros);
                System.out.printf("%-30s%d\n", "Total de livros disponiveis:", Biblioteca.livros_disponiveis);
                System.out.printf("%-30s%d\n", "Total de livros emprestados:", Biblioteca.livros_emprestados + "\n");    
            }
        }
        
    }


    static class Usuario {
        String nome;
        ArrayList<Livro> livros_pegos;
        int quantidade_livros_pegos;

        public Usuario(String nome){
            this.nome = nome;
            livros_pegos = new ArrayList<Livro>();
            quantidade_livros_pegos = 0;
        }

        public void pegar_livro(Livro livro){
            this.livros_pegos.add(livro);            
            this.quantidade_livros_pegos++;            
        }

        public void devolver_livro(Livro livro){
            this.livros_pegos.remove(livro);
            this.quantidade_livros_pegos--;
        }
    }

    static class Biblioteca {
        static ArrayList<Livro> catalogo = new ArrayList<Livro>();
        static int livros_disponiveis = 0;
        static int livros_emprestados = 0;

        public static void adicionar_livro(Livro...livros){
            for(Livro livro:livros){
                Biblioteca.catalogo.add(livro);
                livros_disponiveis++;
            }
        }

        public static void fazer_emprestimo(Usuario usuario, Livro livro){
            usuario.pegar_livro(livro);
            Biblioteca.livros_disponiveis--;
            Biblioteca.livros_emprestados++;
            livro.estaDisponivel = false;
        }

        public static void fazer_devolucao(Usuario usuario, Livro livro){
            usuario.devolver_livro(livro);
            Biblioteca.livros_disponiveis++;   
            Biblioteca.livros_emprestados--;
            livro.estaDisponivel = true;
        }

        public static void exibir_catalogo(){
            System.out.println("-------------CATALOGO-------------");
            for(Livro livro:catalogo){
                System.out.printf("%s - %s | %s\n", livro.titulo, livro.autor, livro.estaDisponivel ? "Disponivel" : "Indisponivel");
            }
            System.out.println("-----------------------------------\n");
        }
    }

    static class Livro {
            public static int total_livros;

            String titulo;
            String autor;
            boolean estaDisponivel;
            public Livro(String titulo, String autor){
                this.titulo = titulo;
                this.autor = autor;
                estaDisponivel = true;

                total_livros++;
            }
        }
}