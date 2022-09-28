# Publisher/Subscriber Java RMI - Sistemas Distribuídos
## Autores:

Pedro Henrique Mendes Pereira</br>
Yunguer Dvorak Mendes

------------------------------

Este trabalho consiste na implementação do padrão Publisher/Subscriber por meio do Java RMI para a disciplina de Sistemas Distribuídos do curso de Ciência da Computação da UFF de Rio das Ostras.
O cliente possui duas opções de execução: modo publish e modo subscribe. Em ambos os modos o cliente seleciona o canal que deseja publicar/inscrever-se.

## Para executar o projeto, siga os seguintes passos:

1 - Os arquivos já estão compilados, mas para compilá-los, entrar na pasta PubSub e executar o código para compilação:

    cd PubSub
    javac -d classes Cell.java Client.java ClientImpl.java Server.java ServerImpl.java

2 - entrar na pasta PubSub/classes e executar rmiregistry

    cd classes
    rmiregistry

3 - Na pasta PubSub, em um novo terminal, executar o comando para iniciar o servidor:

    cd PubSub
    java -classpath classes -Djava.rmi.server.codebase=file:classes/ PubSub.ServerImpl

   *obs.: caso tenha funcionado, irá aparecer "Server Ready".*

4 - Na pasta PubSub, em um novo terminal para cada cliente, executar o comando para iniciar o cliente:

    cd PubSub
    java -classpath classes -Djava.rmi.server.codebase=file:classes/ PubSub.ClientImpl [user] [mode] [channel]

   onde:
   - [user] é uma string para identificar esse usuário;
   - [mode] é um inteiro que define o modo (0 - subscribe; 1 - publish);
   - [channel] é um inteiro que define o canal de inscrição/publicação.

   *obs.: No modo publish, o usuário deve digitar no terminal, após iniciar a execução, as mensagens que deseja enviar para aquele canal (definido anteriormente).
   Para visualizar as mensagens publicadas em um canal, o cliente precisa estar inscrito no mesmo.*
