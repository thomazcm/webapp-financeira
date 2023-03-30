
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

### Tópicos 

:small_blue_diamond: [Sobre o projeto](#sobre-o-projeto)

:small_blue_diamond: [Acesso](#acesso)

:small_blue_diamond: [Funcionalidades](#funcionalidades)

:small_blue_diamond: [Pré-Requisitos e como Rodar o Servidor](#pré-requisitos)

:small_blue_diamond: [Tecnologias](#tecnologias)

:small_blue_diamond: [Autor](#autor)

## Sobre o projeto 
Aplicativo Web para controle de finanças pessoais dos usuários cadastrados. Desenvolvido para fornecer uma interface para as funcionalidades da [API REST](https://github.com/thomazcm/rest-api-financeira) que fornece o back-end para o funcionamento do aplicativo.

O projeto foi desenvolvido em Java usando o framework Spring para o servidor, e Vue.js para a interface e comunicação client-side com a API.


![home-screenshot](https://github.com/thomazcm/webapp-financeira/blob/master/github/home.png?raw=true)

## Acesso
  O aplicativo pode ser acessado pelo link: https://webapp-financeira.herokuapp.com/
  
  Disponibiliza opções para:
 - Cadastro de um novo usuário para que tenha seu próprio registro de despesas e receitas
 - Criação rápida de uma conta para demonstração temporária, já com diversos registros salvos

Para hospedar o aplicativo localmente, é preciso que a API  back-end também esteja sendo executada, [as instruções podem ser encontradas aqui]().

Estando a API online, prossiga para os [próximos passos para executar o aplicativo web]().

## Funcionalidades

####  :heavy_check_mark: Cadastro de Usuários e Autenticação Stateless JWT  + Stateful
O aplicativo web utiliza autenticação por JWT para administrar o acesso dos usuários aos dados.  Os dados estão armazenados em um banco de dados MongoDB que é acessado por uma [API REST](https://github.com/thomazcm/rest-api-financeira) em um servidor distinto e atua fazendo a comunicação entre o aplicativo web e o banco de dados. Quando um usuário inicia uma sessão, a aplicação web solicita um Token JSON Web Token (JWT) à API, que é armazenado no  sessionStorage do navegador. O token é usado para autenticar pedidos subsequentes à API através do esquema de autenticação Bearer Token.

Se o JWT expirar, o client-side envia um pedido ao servidor da aplicação web para obter um novo token da API. Esta abordagem combina os benefícios de segurança e gestão da autenticação Stateful com os benefícios de escalabilidade e desempenho do uso de uma API separada para armazenamento de dados.

#### :heavy_check_mark: Cadastro, edicão e exclusão de novas despesas e receitas

![gif CRUD](https://github.com/thomazcm/webapp-financeira/blob/master/github/edicao-exclusao.gif)

#### :heavy_check_mark: Exibição das receitas e despesas do usuário por mês, bem como um resumo gráfico com base nas categorias das despesas
![gif meses](https://github.com/thomazcm/webapp-financeira/blob/master/github/meses.gif)

:heavy_check_mark: Geração de usuário demo temporário com despesas e receitas variadas já cadastradas para demonstração

## Licença

Este projeto esta sob a licença [MIT](./LICENSE). Consulte o arquivo LICENSE.md para mais informações.

<p align="right">(<a href="#readme-top">voltar para o início</a>)</p>

## Autor
<b>Thomaz Machado</b>🚀<br />
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/71472870?s=460&u=61b426b901b8fe02e12019b1fdb67bf0072d4f00&v=4" width="100px;" alt=""/><br />
Projeto desenvolvido por Thomaz Machado 👊 Entre em contato!  

[![Linkedin Badge](https://img.shields.io/badge/-Thomaz-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/tgmarinho/)](https://www.linkedin.com/in/tgmarinho/) 
[![Gmail Badge](https://img.shields.io/badge/-thomazcm@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:thomazcm@gmail.com)](mailto:thomazcm@gmail.com)
 
 <p align="right">(<a href="#readme-top">voltar para o início</a>)</p>
