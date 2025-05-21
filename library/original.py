class Livro:
    # Atributo de classe
    total_livros = 0

    def __init__(self, titulo, autor):
        # Atributos de instância
        self.titulo = titulo
        self.autor = autor
        self.disponivel = True
        Livro.total_livros += 1

    def descricao(self):
        return f"'{self.titulo}' por {self.autor}"

    def emprestar(self):
        if self.disponivel:
            self.disponivel = False
            return True
        return False

    def devolver(self):
        self.disponivel = True

    @classmethod
    def get_total_livros(cls):
        return cls.total_livros



#Classe Usuario

class Usuario:
    def __init__(self, nome):
        self.nome = nome
        self.livros_emprestados = []

    def emprestar_livro(self, livro):
        if livro.emprestar():
            self.livros_emprestados.append(livro)
            return True
        return False

    def devolver_livro(self, livro):
        if livro in self.livros_emprestados:
            livro.devolver()
            self.livros_emprestados.remove(livro)
            return True
        return False


#Classe Biblioteca


class Biblioteca:
    def __init__(self, nome):
        self.nome = nome
        self.catalogo = []

    def adicionar_livro(self, livro):
        self.catalogo.append(livro)

    def listar_catalogo(self):
        for livro in self.catalogo:
            status = "disponível" if livro.disponivel else "emprestado"
            print(f"{livro.descricao()} - {status}")

    @staticmethod
    def verificar_disponibilidade(livro):
        return livro.disponivel




#Classe Emprestimo (usando Herança)



class Emprestimo:
    def __init__(self, usuario, livro):
        self.usuario = usuario
        self.livro = livro

    def realizar_emprestimo(self):
        if self.usuario.emprestar_livro(self.livro):
            print(f"Emprestimo realizado: {self.livro.descricao()} para {self.usuario.nome}")
        else:
            print(f"Falha no emprestimo: {self.livro.descricao()} não está disponível")

    def realizar_devolucao(self):
        if self.usuario.devolver_livro(self.livro):
            print(f"Devolução realizada: {self.livro.descricao()} de {self.usuario.nome}")
        else:
            print(f"Falha na devolução: {self.livro.descricao()} não foi emprestado para {self.usuario.nome}")


#Uso dos Conceitos de POO
#Instanciação e Uso dos Objetos

# Criação de objetos Livro
livro1 = Livro("Python para Iniciantes", "João Silva")
livro2 = Livro("Programação Avançada em Python", "Maria Souza")
livro3 = Livro("Paradigma de programação", "Autor 1")

# Criação de objetos Usuario
usuario1 = Usuario("Alice")
usuario2 = Usuario("Bob")

# Criação de objeto Biblioteca
biblioteca = Biblioteca("Biblioteca Central")

# Adicionando livros ao catálogo da biblioteca
biblioteca.adicionar_livro(livro1)
biblioteca.adicionar_livro(livro2)

# Listando catálogo da biblioteca
print("Catálogo da Biblioteca:")
biblioteca.listar_catalogo()

# Emprestando livros
emprestimo1 = Emprestimo(usuario1, livro1)
emprestimo1.realizar_emprestimo()

emprestimo2 = Emprestimo(usuario2, livro1)
emprestimo2.realizar_emprestimo()

# Devolvendo livros
emprestimo1.realizar_devolucao()

# Listando catálogo após empréstimos e devoluções
print("\nCatálogo da Biblioteca após empréstimos e devoluções:")
biblioteca.listar_catalogo()

# Verificação de total de livros
print(f"\nTotal de livros na biblioteca: {Livro.get_total_livros()}")