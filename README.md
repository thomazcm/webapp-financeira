
<a name="readme-top"></a>

<h1>Webapp Finanças</h1> 

<p align="center">
  <img src="https://img.shields.io/static/v1?label=Spring&message=framework&color=blue&style=for-the-badge&logo=Spring"/>
  <img src="https://img.shields.io/static/v1?label=Heroku&message=deploy&color=blue&style=for-the-badge&logo=Heroku"/>
  <img src="https://img.shields.io/static/v1?label=MongoDB&message=database&color=blue&style=for-the-badge&logo=mongodb"/>
  <img src="http://img.shields.io/static/v1?label=Java&message=17&color=red&style=for-the-badge&logo=openjdk"/>
  <img src="http://img.shields.io/static/v1?label=Vue&message=2.7.1&color=blue&style=for-the-badge&logo=v"/>
  <img src="http://img.shields.io/static/v1?label=axios&message=1.3.4&color=blue&style=for-the-badge&logo=axios"/>
  <img src="http://img.shields.io/static/v1?label=Thymeleaf&message=3.0.15&color=green&style=for-the-badge&logo=thymeleaf"/>
  <img src="http://img.shields.io/static/v1?label=Bootstrap&message=4&color=purple&style=for-the-badge&logo=bootstrap"/>
  <img src="http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge"/>
  <img src="http://img.shields.io/static/v1?label=License&message=MIT&color=green&style=for-the-badge"/>

 
  #### O aplicativo pode ser acessado pelo link: https://financeira.thomazcm.com/
  
### Tópicos 

:small_blue_diamond: [Sobre o projeto](#sobre-o-projeto)

:small_blue_diamond: [Acesso](#acesso)

:small_blue_diamond: [Funcionalidades](#funcionalidades)

:small_blue_diamond: [Executando o projeto](#executando-o-projeto)

:small_blue_diamond: [Tecnologias Utilizadas](#tecnologias-utilizadas)

:small_blue_diamond: [Autor](#autor)

| :placard: Vitrine.Dev |     |
| -------------  | --- |
| :sparkles: Nome        | **Webapp Financeira**
| :label: Tecnologias | Spring, VueJS, Bootstrap, MongoDB
| :rocket: URL         |  https://financeira.thomazcm.com/


## Sobre o projeto 

Aplicativo Web para controle de finanças pessoais dos usuários cadastrados. Desenvolvido para fornecer uma interface para as funcionalidades da [API REST](https://github.com/thomazcm/rest-api-financeira) que fornece o back-end para o funcionamento do aplicativo.
  
O repositório da API pode ser acessado em: https://github.com/thomazcm/rest-api-financeira

O projeto foi desenvolvido em Java usando o framework Spring com Thymeleaf para o servidor e gerenciamento das views, e Vue.js para a interface do usuário e comunicação client-side com a API.


[![home-screenshot](https://github.com/thomazcm/webapp-financeira/blob/master/github/home.png#vitrinedev)](https://webapp-financeira.herokuapp.com/)
<p align="right">(<a href="#readme-top">voltar para o início</a>)</p>

## Acesso
  https://financeira.thomazcm.com/
  
  Disponibiliza opções para:
 - Cadastro de um novo usuário para que tenha seu próprio registro de despesas e receitas
 - Criação rápida de uma conta para demonstração temporária, já com diversos registros salvos

 #### Caso queira rodar o aplicativo localmente 
  
Para hospedar o aplicativo localmente, é preciso que a API  back-end também esteja sendo executada, [as instruções podem ser encontradas aqui](https://github.com/thomazcm/rest-api-financeira#pr%C3%A9-requisitos).

Estando a API online, prossiga para os [próximos passos para executar o aplicativo web]().
<p align="right">(<a href="#readme-top">voltar para o início</a>)</p>

## Funcionalidades

####  :heavy_check_mark: Cadastro de Usuários e Autenticação Stateless  + Stateful
O aplicativo web utiliza autenticação por JWT para administrar o acesso dos usuários aos dados.  Os dados estão armazenados em um banco de dados MongoDB que é acessado por uma [API REST](https://github.com/thomazcm/rest-api-financeira) em um servidor distinto e atua fazendo a comunicação entre o aplicativo web e o banco de dados. Quando um usuário inicia uma sessão, a aplicação web solicita um Token JSON Web Token (JWT) à API, que é armazenado no  sessionStorage do navegador. O token é usado para autenticar pedidos subsequentes à API através do esquema de autenticação Bearer Token.

Se o JWT expirar, o client-side envia um pedido ao servidor da aplicação web para obter um novo token da API. Esta abordagem combina os benefícios de segurança e gestão da autenticação Stateful com os benefícios de escalabilidade e desempenho do uso de uma API separada para armazenamento de dados.

#### :heavy_check_mark: Cadastro, edição e exclusão de novas despesas e receitas

![gif CRUD](https://github.com/thomazcm/webapp-financeira/blob/master/github/gif-crud.gif)

#### :heavy_check_mark: Exibição das receitas e despesas do usuário por mês, bem como um resumo gráfico com base nas categorias das despesas

#### :heavy_check_mark: Geração de usuário demo temporário com despesas e receitas variadas já cadastradas para demonstração

![gif demo](https://github.com/thomazcm/webapp-financeira/blob/master/github/gif-demo.gif)

#### :heavy_check_mark: Layout responsivo para Desktop e Mobile

![gif layout](https://github.com/thomazcm/webapp-financeira/blob/master/github/gif-layout.gif)

<p align="right">(<a href="#readme-top">voltar para o início</a>)</p>

## Executando o projeto
##### O aplicativo pode ser acessado pelo link: https://financeira.thomazcm.com
Caso queira hospedar uma versão localmente, siga os passos abaixo:

#### Pré-requisitos
- Para executar o servidor localmente, você precisa ter instalado as seguintes ferramentas: [JDK](https://www.java.com/pt-BR/download/), [Git](https://git-scm.com/) e [Maven](https://maven.apache.org/install.html).

- Além disso, para que o aplicativo funcione corretamente, é preciso que a API de acesso ao banco de dados também esteja sendo executada.  [Você pode acessar o repositório da API e as intruções para executá-la aqui](https://github.com/thomazcm/rest-api-financeira#pr%C3%A9-requisitos).
- Para que o banco de dados de autenticação do aplicativo funcione, [é preciso ter uma conta grauita no MongoDB Cloud](https://account.mongodb.com/account/register), que pode ser a mesma utilizada no servidor da API.

#### Rodando o servidor do aplicativo web

1. Clone este repositório
```
git clone https://github.com/thomazcm/webapp-financeira
```
2. Na página do [MongoDB Atlas](https://cloud.mongodb.com/), clique em "Browse Collections" e crie uma nova Database com o nome que deseja usar.

3. Volte até a pagina inicial, clique em "Connect" e em seguida "Connect your Application". Salve a URI para se conectar ao seu banco de dados.

4. Popule o arquivo env.properties na pasta raiz do repositório com as configurações do seu banco de dados:

```
DB_URI=
DB_DATABASE=

#DB_URI=Cole aqui a sua URI do MongoDB
#DB_DATABASE=Nome da database que foi criada
```

6. Por fim, navegue na linha de comando até a raiz do projeto e execute o comando:
```
mvnw spring-boot:run

## O servidor inciará na porta:8081 - acesse a página de login pelo navegador em <http://localhost:8081/login>
```

<p align="right">(<a href="#readme-top">voltar para o início</a>)</p>

## Tecnologias Utilizadas
As seguintes ferramentas foram usadas na construção do projeto

#### Tecnologias
- [Spring Boot 2.7.2](https://spring.io/projects/spring-boot) - Framework
- [Maven](https://maven.apache.org/) - Gerenciamento de Dependências
- [Thymeleaf](https://www.thymeleaf.org/) - Gerenciamento de views e templates
- [Boostrap 4](https://getbootstrap.com/) - Layout
- [Vue.Js](https://vuejs.org/) - Interface do usuário

#### Persistência e Deploy
- [MongoDB Atlas](https://www.mongodb.com/atlas/database)
- [Heroku](https://www.heroku.com/)

#### Ferramentas
- [Postman](https://www.postman.com/)
- [Eclipse](https://www.eclipse.org/) e [VSCode](https://code.visualstudio.com/)

<p align="right">(<a href="#readme-top">voltar para o início</a>)</p>

## Licença

Este projeto esta sob a licença [MIT](./LICENSE). Consulte o arquivo LICENSE.md para mais informações.

<p align="right">(<a href="#readme-top">voltar para o início</a>)</p>

## Autor
<b>Thomaz Machado</b>🚀<br />
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/71472870?s=460&u=61b426b901b8fe02e12019b1fdb67bf0072d4f00&v=4" width="100px;" alt=""/><br />
Projeto desenvolvido por Thomaz Machado. Entre em contato!  

[![Linkedin Badge](https://img.shields.io/badge/-Thomaz-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/thomazcm)](https://www.linkedin.com/in/thomazcm) 
[![Gmail Badge](https://img.shields.io/badge/-thomazcm@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:thomazcm@gmail.com)](mailto:thomazcm@gmail.com)
 
 <p align="right">(<a href="#readme-top">voltar para o início</a>)</p>
