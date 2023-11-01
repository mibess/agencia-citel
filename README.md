# Citel API

## _Banco de Sangue - Sistema de Processamento de Dados_

Este é um sistema web desenvolvido para processar dados de candidatos a doadores fornecidos por uma agência de banco de sangue. Ele extrai informações importantes e exibe resultados como a contagem de candidatos em cada estado, o IMC médio por faixa etária, o percentual de obesos por gênero, a média de idade por tipo sanguíneo e a quantidade de possíveis doadores para cada tipo sanguíneo receptor.

## API com os Recursos

http://ec2-34-226-208-198.compute-1.amazonaws.com/

## Endpoints da API

### _GET /candidatos/importar_

Importa os dados enviados por um JSON para nossa aplicação poder processar as informações contidas nele.

### _GET /candidatos/listar-todos_

Lista todos os candidatos doadores

### _POST /candidatos/salvar_

Salva um candidato novo no sistema. Ex.

```
  @Body exemplo -> salvar um novo candidato
	{
		"nome": "Milena Analu Pires",
		"cpf": "775.256.099-50",
		"rg": "44.084.541-5",
		"data_nasc": "23/05/1964",
		"sexo": "Feminino",
		"mae": "Isadora Marli",
		"pai": "Noah Severino César Pires",
		"email": "mmilenaanalupires@keffin.com.br",
		"cep": "39801-678",
		"endereco": "Rua Kurt W. Rothe",
		"numero": 675,
		"bairro": "Castro Pires",
		"cidade": "Teófilo Otoni",
		"estado": "MG",
		"telefone_fixo": "(33) 3611-4613",
		"celular": "(33) 98481-0191",
		"altura": 1.53,
		"peso": 80,
		"tipo_sanguineo": "O-"
	}
```

### _GET /candidatos/por-estado_

Lista a quantidade de candidatos que tem em cada estado do Brasil

### _GET /candidatos/imc-por-faixa-idade_

Lista o IMC médio dos candidatos entre uma faixa de 10 em 10 anos

### _GET /candidatos/percentual-obesidade-por-sexo_

Lista o percentual de obesidade dos candidatos entre gêneros masculino e feminino

### _GET /candidatos/idade-media-tipo-sanguineo_

Lista a média de idade de cada candidato por tipo sanguíneo

### _GET /candidatos/possiveis-doadores-por-tipo-sanguineo_

Lista os possíveis doadores para cada tipo sanguíneo receptor

# Instalação Local

1. **Clonar o repositório:**
   ```
   git clone https://github.com/mibess/agencia-citel.git
   ```
2. **Configurar variáveis de ambiente:**
   Na pasta root da aplicação renomeie o arquivo `.env.example` para `.env` e configure as variáveis de ambiente necessárias, como as credenciais de banco de dados.

   **Atenção: o arquivo `.env` já vem pré configurado para usar uma base de dados `POSTGRES` dentro da sua máquina local. Caso queira utilizar outra base de dados, então você deve alterar o arquivo `.env` com as configurações necessárias.**

3. **Instalar Base de Dados:**
   Certifique-se de que você tenha o Docker instalado em seu sistema. Em seguida, crie uma instância do banco postgres utilizando o comando `docker compose` abaixo:

   ```
   docker compose up -d
   ```

   Com esse comando será criado um container com o nome de `citel-api-postgres-1`.
   É nele que a nossa aplicação está configurada para rodar por padrão.

4. **Rodar a Aplicação:**
   Agora execute a aplicação.

   Com ela rodando, vamos criar nossos dados executando no contexto:
   `/candidatos/importar`

   Esse procedimento pode levar alguns segundos. Feito isso, os outros endpoints da aplicação estarão disponíveis.

## Uso

Após a instalação e execução bem-sucedidas, você poderá usar a aplicação para processar os dados dos candidatos a doadores e visualizar os resultados desejados.
