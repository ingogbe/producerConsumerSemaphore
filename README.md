# producerConsumer

Solução do problema dos Produtores e Consumidores usando semáforos em Java

## Ambiente usado para implementação e testes

* Sistema Operacional Windows 10
* Eclipse IDE Oxygen Release (4.7.0)
* Eclipse IDE Neon.1a Release (4.6.1)

## Como executar o programa

Abra o Eclipse e adicione o projeto.

1. New
2. Java Project
3. Desmaque o checkbox `Use default location`
4. Browse
5. Selecione a pasta do projeto
6. Finish

Abra o arquivo `Main.java`.

Dentro do arquivo poderão ser feitas algumas mudanças de configuração nos `Producers` e nos `Consumers`. O construtor de cada um deles é composto pelo nome de identificação, a lista compartilhada, o semáfoto para sincronização, e o semáforo para exclusão mútua, além de outros dados, como mostrador abaixo:

```java
/*
* Parâmetros do construtor do Producer:
* (nome de identificação, lista compartilhada, semáforo para sincronização, semáfoto para exclusão mútua, quantidade de produções)
*/
Producer p1 = new Producer("Tobias", list, sem, mutex, 5);

/*
* Parâmetros do construtor do Consumer:
* (nome de identificação, lista compartilhada, semáforo para sincronização, semáforo para exclusão mútua, quantidade de consumos)
*/
Consumer c1 = new Consumer("Alice", list, sem, mutex, 1);
```

### Exemplo de uso com um ou mais produtores:

```java
LinkedList<Content> list = new LinkedList<Content>();
Semaphore sem = new Semaphore(0);
Semaphore mutex = new Semaphore(1);

new Producer("Tobias", list, sem, mutex, 5).start();
new Producer("Nico", list, sem, mutex, 2).start();
new Consumer("Alice", list, sem, mutex, 1).start();
new Consumer("Bob", list, sem, mutex, 3).start();
new Consumer("Toni", list, sem, mutex, 1).start();
new Consumer("Rick", list, sem, mutex, 2).start();
```

> Não altere a `list`, `sem` e `mutex` que são compartilhados. Precisam ser iguais para que os `Producers` e `Consumers` interajam entre si.

## Implementação

### List

A `list` é uma lista encadeada com finalidade de armazenar os dados compartilhados entre os produtores e consumidores.

### Sem

O `sem` é um semáforo com finalidade de garantir a sincronização entre a comunicação dos `Producers` e `Consumers`.

### Mutex

O `mutex` é um semáforo para a exclusão mútua com finalidade de assegurar a exclusividade mútua e evitar deadlocks entre os `Producers` e `Consumers`.

## Exemplo do formato de saída:

### Configuração utilizada

```java
new Producer("Tobias", list, sem, mutex, 5).start();
new Producer("Nico", list, sem, mutex, 2).start();
new Consumer("Alice", list, sem, mutex, 1).start();
new Consumer("Bob", list, sem, mutex, 3).start();
new Consumer("Toni", list, sem, mutex, 1).start();
new Consumer("Rick", list, sem, mutex, 2).start();
```

### Saída

```javascript
Producer "Tobias" write: 4
Producer "Nico" write: 1
Consumer "Alice" read: 4 from Tobias. Consumptions left: 0
Consumer "Bob" read: 1 from Nico. Consumptions left: 2
Producer "Nico" write: 0
Producer "Tobias" write: 3
Consumer "Toni" read: 0 from Nico. Consumptions left: 0
Consumer "Rick" read: 3 from Tobias. Consumptions left: 1
Producer "Tobias" write: 2
Consumer "Bob" read: 2 from Tobias. Consumptions left: 1
Producer "Tobias" write: 1
Consumer "Rick" read: 1 from Tobias. Consumptions left: 0
Producer "Tobias" write: 0
Consumer "Bob" read: 0 from Tobias. Consumptions left: 0
```
