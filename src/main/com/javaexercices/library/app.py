class Livro:
    # Quantidade total de livros cadastrados
    total_livros = 0

    # CONSTRUTOR para criação de livros
    def __init__(self, titulo, autor):
        self.titulo = titulo
        self.autor = autor
        self.disponivel = True
        Livro.total_livros += 1

    # NAO UTILIZAR | metodo interno
    def descricao(self):
        return f"{self.titulo} - {self.autor}"

    # NAO UTILIZAR | metodo interno
    def emprestar(self):
        if self.disponivel:
            self.disponivel = False
            return True
        return False

    #  NAO UTILIZAR | metodo interno
    def devolver(self):
        self.disponivel = True

    # Retorna total de livros
    @classmethod
    def get_total_livros(cls):
        return cls.total_livros

class Usuario:
    # CONSTRUTOR para criação de usuário
    def __init__(self, nome):
        self.nome = nome
        self.livros_pegos = []

    # NAO UTILIZAR | metodo interno
    def pegar_livro(self, livro):
        if livro.emprestar():
            self.livros_pegos.append(livro)
            return True
        return False

    # NAO UTILIZAR | metodo interno
    def devolver_livro(self, livro):
        if livro in self.livros_emprestados:
            livro.devolver()
            self.livros_pegos.remove(livro)
            return True
        return False

class Biblioteca:
    livros_biblioteca = 0
    livros_disponiveis = 0
    livros_emprestados = 0
    catalogo = []

    # Adiciona livro a biblioteca
    def adicionar_livro(livro):
        Biblioteca.catalogo.append(livro)
        Biblioteca.livros_biblioteca += 1
        Biblioteca.livros_disponiveis += 1

    # Listar todos os livros da biblioteca
    def listar_catalogo():
        print("\n---------------- CATALOGO -------------------\n")
        for livro in Biblioteca.catalogo:
            status = "disponivel" if livro.disponivel else "emprestado"
            print(f"- {livro.descricao()} | {status}")
        print("\n---------------------------------------------\n")
        
    # Checar se livro esta disponivel
    def verificar_disponibilidade(livro):
        return livro.disponivel

    # Pegar livro emprestado
    def fazer_emprestimo(usuario, livro):
        Biblioteca.livros_emprestados += 1
        Biblioteca.livros_disponiveis -= 1
        livro.emprestar()
        usuario.livros_pegos.append(livro)
        return
    
    # Devolver livro
    def fazer_devolucao(usuario, livro):
        Biblioteca.livros_emprestados -= 1
        Biblioteca.livros_disponiveis += 1
        livro.devolver()
        usuario.livros_pegos.remove(livro)
        return

# Criação de objetos Livro
livro1 = Livro("Entendendo Algoritmos", "Aditya Y. Bhargava")
livro2 = Livro("Java: Aprenda a programar edicao 8", "Paul Deitel")
livro3 = Livro("Clean Code", "Robert Cecil Martin")
livro4 = Livro("SQL", "Vinicius")
livro5 = Livro("Java: Aprenda a programar edicao 10", "Paul Deitel")
livro6 = Livro("Desenvolvimento Agil limpo", "Robert C. Martin")

# Criação de objeto Usuario
eu = Usuario("Filipe")

# Adicionando livros ao catálogo da biblioteca
Biblioteca.adicionar_livro(livro1)
Biblioteca.adicionar_livro(livro2)
Biblioteca.adicionar_livro(livro3)
Biblioteca.adicionar_livro(livro4)
Biblioteca.adicionar_livro(livro5)
Biblioteca.adicionar_livro(livro6)

# Listando catálogo da biblioteca
Biblioteca.listar_catalogo()

# Emprestando livros
Biblioteca.fazer_emprestimo(eu, livro1)
Biblioteca.fazer_emprestimo(eu, livro2)
Biblioteca.fazer_emprestimo(eu, livro5)
Biblioteca.fazer_emprestimo(eu, livro6)

# Fazendo devolucao de livros
Biblioteca.fazer_devolucao(eu, livro5)

print("=====Resumo da biblioteca=====")
# Verificação de total de livros
print(f"Total de livros na biblioteca: {Livro.get_total_livros()}")
# Verificação de livros disponiveis
print(f"Total de livros disponiveis: {Biblioteca.livros_disponiveis}")
# Verificação de livros emprestados
print(f"Total de livros emprestados: {Biblioteca.livros_emprestados}\n")