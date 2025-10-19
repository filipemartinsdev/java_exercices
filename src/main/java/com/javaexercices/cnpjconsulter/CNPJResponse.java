package com.javaexercices.cnpjconsulter;

import com.google.gson.annotations.SerializedName;

public class CNPJResponse {
    public CNPJResponse() {
    }

    String uf;
    String cep;

    QSA[] qsa;

    String cnpj;

    String pais;

    String email;

    String porte;

    String bairro;

    String numero;

    @SerializedName("ddd_fax")
    String dddFax;

    String municipio;

    String logradouro;

    @SerializedName("cnae_fiscal")
    int cnaeFiscal;

    @SerializedName("codigo_pais")
    String codigoPais;

    String complemento;

    @SerializedName("codigo_porte")
    int codigoPorte;

    @SerializedName("razao_social")
    String razaoSocial;

    @SerializedName("nome_fantasia")
    String nomeFantasia;

    @SerializedName("capital_social")
    int capitalSocial;

    @SerializedName("ddd_telefone_1")
    String dddTelefone1;

    @SerializedName("ddd_telefone_2")
    String dddTelefone2;

    @SerializedName("opcao_pelo_mei")
    String opcaoPeloMei;

    @SerializedName("descricao_porte")
    String descricaoPorte;

    @SerializedName("codigo_municipio")
    int codigoMunicipio;

    @SerializedName("cnaes_secundario")
    QNAE[] qnaesSecundarios;

    @SerializedName("natureza_juridica")
    String naturezaJuridica;

    @SerializedName("regime_tributario")
    RegimeTributario[] regimeTributario;

    @SerializedName("situacao_especial")
    String situacaoEspecial;

    @SerializedName("opcao_pelo_simples")
    String opcaoPeloSimples;

    @SerializedName("situacao_cadastral")
    int situacao_cadastral;

    @SerializedName("data_opcao_pelo_mei")
    String dataOpcaoPeloMei;

    @SerializedName("data_exclusao_do_mei")
    String dataExclusaoDoMei;

    @SerializedName("cnae_fiscal_descricao")
    String cnaeFiscalDescricao;

    @SerializedName("codigo_municipio_ibge")
    int codigoMunicipioIbge;;

    @SerializedName("data_inicio_atividade")
    String dataInicioAtividade;

    @SerializedName("data_situacao_especial")
    String dataSituacaoEspecial;

    @SerializedName("data_situacao_cadastral")
    String dataSituacaoCadastral;

    @SerializedName("nome_cidade_no_exterior")
    String nomeCidadeNoExterior;

    @SerializedName("codigo_natureza_juridica")
    int codigoNaturezaJuridica;

    @SerializedName("data_opcao_pelo_simples")
    String dataOpcaoPeloSimples;

    @SerializedName("data_exclusao_do_simples")
    String dataExclusaoDoSimples;
}

//  "data_exclusao_do_simples": null,
//  "motivo_situacao_cadastral": 0,
//  "ente_federativo_responsavel": "",
//  "identificador_matriz_filial": 1,
//  "qualificacao_do_responsavel": 16,
//  "descricao_situacao_cadastral": "ATIVA",
//  "descricao_tipo_de_logradouro": "AVENIDA",
//  "descricao_motivo_situacao_cadastral": "SEM MOTIVO",
//  "descricao_identificador_matriz_filial": "MATRIZ"
//}


class QSA{
    String pais;

    @SerializedName("nome_socio")
    String nome_socio;

    @SerializedName("codigoPais")
    String codigoPais;

    @SerializedName("faixa_etaria")
    String faixaEtaria;

    @SerializedName("cnpj_cpf_do_socio")
    String CNPJ_CPF_socio;

    @SerializedName("qualificacao_socio")
    String qualificacaoSocio;

    @SerializedName("codigo_faixa_etaria")
    String codigoFaixaEtaria;

    @SerializedName("data_entrada_sociedade")
    String dataEntradaSociedade;

    @SerializedName("identificador_de_socio")
    int identificadorDeSocio;

    @SerializedName("cpf_representante_legal")
    String cpfRepresentanteLegal;

    @SerializedName("nome_representante_legal")
    String nomeRepresentanteLegal;

    @SerializedName("codigo_qualificacao_socio")
    int codigoQualificacaoSocio;

    @SerializedName("qualificacao_representante_legal")
    String qualificacaoRepresentanteLegal;

    @SerializedName("codigo_qualificacao_representante_legal")
    int getCodigoQualificacaoRepresentanteLegal;
}

class QNAE{
    public QNAE(){}

    int codigo;
    String descricao;
}

class RegimeTributario{
    public RegimeTributario(){}

    int ano;

    @SerializedName("cnpj_da_scp")
    String cnpjDaScp;

    @SerializedName("forma_de_tributacao")
    String formaDeTributacao;

    @SerializedName("quantidade_de_escrituracoes")
    int quantidadeDeEscrituracoes;
}