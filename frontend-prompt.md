# PROMPT PARA CRIAÇÃO DO FRONTEND - SISTEMA DE BANCO DE SANGUE (CITEL)

Você deve agir como um Desenvolvedor Frontend Senior especialista em criar aplicações web modernas, responsivas e de altíssimo nível estético. Sua tarefa é construir o frontend completo para uma aplicação de **Processamento de Dados de Doadores de Banco de Sangue**, utilizando **Angular (última versão estável)** e **TailwindCSS (última versão estável)**.

---

## 🎨 Requisitos Visuais e Estilização (TailwindCSS)
*   **Estética Premium:** Crie uma interface moderna com paleta de cores baseada em tons de vermelho/vinho (ex: `bg-rose-900`, `text-rose-200`, `border-rose-800`), cinzas escuros/chumbo (ex: `bg-zinc-900`, `text-zinc-100`) e brancos/neutros para compor um painel elegante (estilo glassmorphism ou dashboards modernos e escuros).
*   **Componentes Estilizados:** Utilize as classes utilitárias do TailwindCSS para espaçamentos (`p-4`, `m-2`), flexbox/grid (`flex`, `grid`, `grid-cols-1 md:grid-cols-2`), cantos arredondados (`rounded-xl`, `rounded-2xl`), efeitos de foco/hover (`transition-all duration-300 hover:scale-[1.02]`), e sombras elegantes (`shadow-lg`, `shadow-rose-950/20`).
*   **Responsividade:** A aplicação deve ser totalmente responsiva usando os prefixos do Tailwind (`sm:`, `md:`, `lg:`).
*   **Tipografia:** Configurar e usar fontes limpas como *Inter*, *Roboto* ou *Outfit* no arquivo de estilos globais.

---

## 🛠️ Arquitetura e Componentes no Angular
Organize o projeto Angular com boas práticas modernas (utilizando componentes autônomos/standalone se aplicável):

1.  **DashboardComponent (Dashboard de Relatórios):**
    *   **Candidatos por Estado:** Gráfico interativo ilustrando a quantidade de candidatos doadores por estado (UF).
    *   **IMC Médio por Faixa de Idade:** Tabela ou gráfico exibindo o IMC médio dos candidatos divididos em faixas etárias de 10 em 10 anos.
    *   **Percentual de Obesidade por Sexo:** Cartões estatísticos ou gráfico de pizza/rosca mostrando o percentual de obesidade entre Homens e Mulheres.
    *   **Média de Idade por Tipo Sanguíneo:** Tabela comparativa mostrando a idade média de candidatos para cada tipo sanguíneo.
    *   **Quantidade de Possíveis Doadores por Receptor:** Painel detalhado exibindo a quantidade de potenciais doadores para cada tipo sanguíneo receptor.
    *(Utilize uma biblioteca como `ng2-charts`, `apexcharts` com suporte a Angular, ou construa gráficos nativos com SVG/Tailwind).*
2.  **CandidatoListComponent (Lista de Candidatos):**
    *   Tabela estilizada com paginação, filtros e busca textual, listando todos os candidatos contendo: Nome, CPF, E-mail e Tipo Sanguíneo.
3.  **CandidatoFormComponent (Cadastro de Candidato):**
    *   Formulário robusto usando **Reactive Forms** (`FormBuilder`, `FormGroup`, `Validators`).
    *   Validações assíncronas/síncronas no lado do cliente com exibição visual imediata de campos inválidos (ex: borda vermelha e texto de erro sob o campo).
    *   Uso de máscaras de entrada (para CPF, RG, CEP, Celular, Telefone e Data de Nascimento).
4.  **ImportacaoComponent (Módulo de Importação):**
    *   Botão ou zona de clique para iniciar a importação em massa no backend. Deve apresentar um estado visual de carregamento (Spinner) e desabilitar cliques para evitar envios duplos.
5.  **CandidatoService (Serviço Angular):**
    *   Serviço injetável que encapsula as requisições HTTP (`HttpClient`) para a API.
    *   Gerenciamento de estado local ou uso de Angular Signals / RxJS Behaviors para compartilhar e atualizar os dados entre componentes de forma reativa.

---

## 🔌 Especificação dos Endpoints (Base URL: `http://localhost:8080`)

### 1. Status da API (Host Check)
*   **URL:** `GET /`
*   **Resposta (200 OK):**
    ```json
    {
      "hostcheck": "Ok - Mon Jun 08 14:30:00 BRT 2026"
    }
    ```

### 2. Importação Inicial de Candidatos
*   **URL:** `POST /candidatos/importar`
*   **Resposta (201 Created):**
    ```json
    {
      "quantidade": 1000,
      "mensagem": "Importação concluída com Sucesso!"
    }
    ```
*   **Erros (400 Bad Request):**
    ```json
    {
      "timestamp": "2026-06-08T14:32:00.000",
      "status": 400,
      "error": "Bad Request",
      "messages": ["Sua importação já foi realizada!"]
    }
    ```

### 3. Listar Todos os Candidatos
*   **URL:** `GET /candidatos/listar-todos`
*   **Resposta (200 OK):**
    ```json
    [
      {
        "nome": "Milena Analu Pires",
        "cpf": "775.256.099-50",
        "email": "mmilenaanalupires@keffin.com.br",
        "tipoSanguineo": "O-"
      }
    ]
    ```
    *(Nota: a propriedade do tipo sanguíneo no retorno é `tipoSanguineo` em camelCase)*

### 4. Salvar Novo Candidato
*   **URL:** `POST /candidatos/salvar`
*   **Corpo da Requisição (JSON):**
    ```json
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
*   **Resposta (201 Created):**
    ```json
    {
      "nome": "Milena Analu Pires",
      "cpf": "775.256.099-50",
      "email": "mmilenaanalupires@keffin.com.br",
      "tipoSanguineo": "O-"
    }
    ```
*   **Erros (400 Bad Request):**
    ```json
    {
      "timestamp": "2026-06-08T14:35:00.000",
      "status": 400,
      "error": "Bad Request",
      "messages": ["Candidato já Cadastrado em nossa Base de Dados"]
    }
    ```

### 5. Relatório: Candidatos por Estado (UF)
*   **URL:** `GET /candidatos/por-estado`
*   **Resposta (200 OK):**
    ```json
    [
      { "quantidade": 12, "estado": "SP" },
      { "quantidade": 8, "estado": "MG" }
    ]
    ```

### 6. Relatório: IMC Médio por Faixa de Idade (De 10 em 10 anos)
*   **URL:** `GET /candidatos/imc-por-faixa-idade`
*   **Resposta (200 OK):**
    ```json
    [
      { "faixaDeIdade": "De 0 a 10 anos", "imc": 18 },
      { "faixaDeIdade": "De 10 a 20 anos", "imc": 23 }
    ]
    ```

### 7. Relatório: Percentual de Obesidade por Sexo
*   **URL:** `GET /candidatos/percentual-obesidade-por-sexo`
*   **Resposta (200 OK):**
    ```json
    [
      { "sexo": "Homens", "percentual": 21 },
      { "sexo": "Mulheres", "percentual": 24 }
    ]
    ```

### 8. Relatório: Média de Idade por Tipo Sanguíneo
*   **URL:** `GET /candidatos/idade-media-tipo-sanguineo`
*   **Resposta (200 OK):**
    ```json
    [
      { "tipoSanguineo": "A+", "idadeMedia": 41 },
      { "tipoSanguineo": "A-", "idadeMedia": 37 }
    ]
    ```

### 9. Relatório: Possíveis Doadores por Tipo Sanguíneo Receptor
*   **URL:** `GET /candidatos/possiveis-doadores-por-tipo-sanguineo`
*   **Resposta (200 OK):**
    ```json
    [
      { "tipoSanguineo": "A+", "quantidade": 145 },
      { "tipoSanguineo": "AB-", "quantidade": 12 }
    ]
    ```

---

## ⚖️ Regras de Negócio e Validações no Client-Side (Angular Validators)

### 1. Validação de Formulário
Crie validadores personalizados no Angular (`ValidatorFn`) para garantir consistência:
*   **Nome:** Obrigatório, comprimento de 3 a 255.
*   **CPF:** Obrigatório, com máscara `999.999.999-99`. Desenvolva um validador de algoritmo de CPF brasileiro.
*   **RG:** Obrigatório.
*   **Data de Nascimento (`data_nasc`):** Obrigatório, máscara `DD/MM/AAAA`. Validar se é uma data válida e se o formato corresponde exatamente a `dd/MM/yyyy`.
*   **Sexo:** Obrigatório. Valores aceitos: `Masculino` ou `Feminino`.
*   **Mãe e Pai:** Nomes obrigatórios.
*   **E-mail:** Obrigatório. Validador de e-mail padrão do Angular (`Validators.email`).
*   **CEP, Endereço, Número, Bairro, Cidade e Estado:** Todos obrigatórios.
*   **Telefone Fixo (`telefone_fixo`):** Obrigatório, formato `(99) 9999-9999`.
*   **Celular:** Obrigatório, formato `(99) 99999-9999`.
*   **Altura:** Obrigatório, numérico decimal em metros (Ex: 1.75).
*   **Peso:** Obrigatório, numérico inteiro em kg (Ex: 80).
*   **Tipo Sanguíneo (`tipo_sanguineo`):** Obrigatório, seleção entre `A+`, `A-`, `B+`, `B-`, `AB+`, `AB-`, `O+`, `O-`.

### 2. Lógica dos Relatórios (Regras do Backend)
*   **Cálculo do IMC:** `peso / (altura * altura)`.
*   **Critério de Obesidade:** IMC maior que 30 (`IMC > 30`).
*   **Critérios para Aptidão de Doação:**
    *   **Peso:** Deve ser superior a 50 kg (`peso > 50`).
    *   **Idade:** Deve ter entre 16 e 69 anos inclusivos (`idade >= 16` e `idade <= 69`).
*   **Matriz de Compatibilidade Sanguínea (Receptor -> Doadores Compatíveis):**
    *   `A+` recebe de: `A+`, `A-`, `O+`, `O-`
    *   `A-` recebe de: `A-`, `O-`
    *   `B+` recebe de: `B+`, `B-`, `O+`, `O-`
    *   `B-` recebe de: `B-`, `O-`
    *   `AB+` recebe de: `A+`, `B+`, `O+`, `AB+`, `A-`, `B-`, `O-`, `AB-` (Receptor Universal)
    *   `AB-` recebe de: `A-`, `B-`, `O-`, `AB-`
    *   `O+` recebe de: `O+`, `O-`
    *   `O-` recebe de: `O-` (Doador Universal)

### 3. Tratamento de Erros de Requisição (HttpInterceptor)
*   Implemente um `HttpInterceptor` no Angular para capturar respostas com código de erro `400` que possuem a estrutura `ErrorResponse`.
*   As mensagens contidas na lista `messages` devem ser exibidas dinamicamente na tela usando um serviço de Toast estilizado com Tailwind (Ex: fundo vermelho, texto branco, ícone de erro e botão de fechar).

---

## 🚀 Instruções de Geração de Código
1.  Escreva a aplicação em TypeScript moderno usando Angular Standalone Components (ou NgModules clássicos, conforme preferência).
2.  Use o `HttpClient` configurado no `app.config.ts` ou `app.module.ts`.
3.  Crie componentes reutilizáveis, organizados e bem documentados.
4.  Certifique-se de configurar o arquivo `tailwind.config.js` para escanear todos os arquivos de template html e componentes ts.
