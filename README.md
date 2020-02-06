* **Observações**

   O objetivo deste projeto é trabalhar com o kafka.
   
   Teremos 2 projetos:
   
   1 - API que ira alimentar o Kafka.
   
   2 - Projeto em java que ira consumir o Kafka.

* **Dependências**

   <p>SDK Java11</p>
   Instalar o docker compose 

#### Entendendo a aplicação
* **1ª Iniciando**

    <p>Após você ter instalado o docker compose em seu pc, vamos precisar iniciar o servidor do kafka</p>
    <p>Na raiz deste projeto possui um arquivo chamado docker-compose.yml</p>
    <p>Navegue até esse arquivo por sua IDE ou linha de comando do seu sistema operacional</p>
    <p>Execute o comando a seguir</p>
    <p>(docker-compose up)</p>
    <p>Em seguida as imagens do docker vão baixar e se iniciar sozinhas</p>

* **2ª Iniciando a API que ira alimentar o kafka** 

    <p>Execute a classe KafkaApplication.java nela esta o metodo main</p>
    
    Nossa API está no ar agora.
    
* **3ª Iniciando o consumidor**

    <p>Na raiz deste projeto possui uma pasta com o nome kafka-client</p>
    <p>Esta pasta é um outro projeto em java que ira consumir o kafka, abra esse projeto em uma outra GUIA de sua IDE</p>
    <p>Execute a classe Main.java, e pronto nosso consumidor já esta olhando para o kafka, quando alimentarmos o kafka, oberseve o console da IDE, os dados vão ir para lá.</p>
    
* **4ª Alimentando o Kafka**

    <p>Com nossa API do ar, podemos agora alimentar o kafka e verificar o consumidor.</p>
    
    Metodo: POST
    
    url: http://localhost:8080/kafka/sendData
    
    body: {"data": "Estou enviando para o kafka esta mensagem."}
    
    resposta: leia a resposta da requisição!
     