# Reprova

Trabalho prático da disciplina de Reutilização de Software, DCC/UFMG, 2021/2

## Instruções de construção/ambiente (UNIX)

### Compilação e execução

O makefile do projeto contém instruções para construção do projeto utilizando [Maven](https://maven.apache.org/), ferramenta de gerenciamento de projetos Java e inicialização, usando [docker](https://www.docker.com/), ferremanta de virtualização de ambientes, usada para rodar a aplicação. Para construir o projeto é necessário primeiro [instalar o Maven](https://maven.apache.org/install.html) e o [docker](https://docs.docker.com/get-docker/). No tutorial de instalação do maven se menciona o uso da variável de ambiente `JAVA_HOME`; instruções para sua configuração **em MacOS** pode ser encontrada [aqui](https://mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/).

Com o mvn instalado e docker instalados, basta rodar o comando `make build-run` (assumindo que `make` esteja instalado em sua máquina) que vai fazer todo o processo desde a compilação até a criação e a execução do programa no container docker, caso o programa já esteja compilado e se deseje apenas executar a aplicação, pode ser usado o comando `make docker-build-run` ou semelhantemente apenas `make docker-run` caso o container já esteja montada.

### Dependências

A aplicação depende de uma conexão com um banco de dados MongoDB, por esse motivo são criadas dois containers no momento de execução da aplicação, em um container está presente a instância do banco de dados e na outra a aplicação.

Para a execução do programa é necessário a criação de um arquivo de nome `.env` no diretório inicial do projeto (mesmo nível do README e dockerfile) com as seguintes variáveis:

* `REPROVA_MONGO`: URL do servidor Mongo,`mongodb://mongo:27017/?connectTimeoutMS=5000` para usar a instância do banco rodando em paralelo localmente.

* `PORT`: Porta na qual o serviço do Reprova irá rodar, `8080` por padrão para ser compatível com as requisições na collection disponibilizada.

* `REPROVA_TOKEN`: Token utilizado para autenticação, `d2fad245dd1d8a4f863e3f1c32bdada723361e6f63cfddf56663e516e47347bb` por padrão para ser compatível com as requisições na collection disponibilizada.

Além disso, também é necessário configurar as features usando as seguintes variáveis ainda no arquivo `.env` que são variáveis booleanas, elas precisam estar sempre preenchidas, até mesmo quando a feature não é desejada, nesse caso se atribuindo o valor `false`:

* `OPEN`: Se questões abertas estarão habilitadas ou não no sistema.

* `MULTIPLE_CHOICE`: Se questões de múltipla escolha estarão habilitadas ou não no sistema.

* `OPTIONS`: Quantidade de opções presentes nas questões fechadas caso o sistema tenha questões fechadas.

## Postman para testes

Nesta [coleção do Postman](https://raw.githubusercontent.com/VictorGazzinelli/reprova/master/Reprova.postman_collection.json) estão inclusos os endpoints da api com seus respectivos exemplos de saída, ao importar essa coleção para seu postman é possível testar e interagir com a api. 
Para importar a coleção siga os passos a seguir:
1) Copie o [este link](https://raw.githubusercontent.com/VictorGazzinelli/reprova/master/Reprova.postman_collection.json)
2) Abra seu postman (precisa ser a aplicação desktop)
3) Em uma workspace de escolha clique no botão `import` na parte de cima da barra a esquerda onde ficam as requisições
4) Selecione a opção `link`, cole o link copiado e continue
5) Pronto, a coleção deve aparecer na barra esquerda com as requisições implementadas, caso você tenha usado as credenciais sugeridas na seção do `.env` não é necessário fazer nenhuma alteração
