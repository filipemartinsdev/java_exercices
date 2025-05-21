class Venda:
    def __init__(self, produto, categoria, quantidade, valor):
        self.produto = produto
        self.categoria = categoria
        self.quantidade = quantidade
        self.valor = valor

    def calcular_total(self):
        return self.quantidade * self.valor

class AnaliseVendas:
    def __init__(self):
        self.vendas = []

    def adicionar_venda(self, venda):
        self.vendas.append(venda)

    def calcular_total_vendas(self):
        return sum(venda.calcular_total() for venda in self.vendas)

    def calcular_media_por_categoria(self, categoria):
        vendas_categoria = [v for v in self.vendas if v.categoria == categoria]
        total_categoria = sum(v.calcular_total() for v in vendas_categoria)
        return total_categoria / len(vendas_categoria) if vendas_categoria else 0

# Dados de exemplo usando objetos
venda1 = Venda("Laptop", "Eletrônicos", 3, 1500)
venda2 = Venda("Camiseta", "Roupas", 5, 50)
venda3 = Venda("Smartphone", "Eletrônicos", 10, 800)

# Sistema de análise de vendas
sistema = AnaliseVendas()
sistema.adicionar_venda(venda1)
sistema.adicionar_venda(venda2)
sistema.adicionar_venda(venda3)

# Cálculo de total e média usando métodos orientados a objetos
print("Total de Vendas (POO):", sistema.calcular_total_vendas())
print("Média de Vendas para Eletrônicos (POO):", sistema.calcular_media_por_categoria("Eletrônicos"))