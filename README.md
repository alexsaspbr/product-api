# Exercícios Spring Boot Web: Criação de REST APIs Simples

## Exercício 1: Gerenciamento de Livros
**Objetivo:** Criar uma API para gerenciar uma biblioteca de livros.

**Tarefas:**
1. Criar um modelo `Book` com os atributos: `id`, `title`, `author`, `isbn`, e `publishedDate`.
2. Implementar um repositório JPA para gerenciar livros.
3. Criar um controlador REST com os seguintes endpoints:
    - `GET /books` - Listar todos os livros.
    - `POST /books` - Adicionar um novo livro.

----

# Exercício 2: Sistema de Reservas de Restaurantes


## Objetivo: Criar uma API para gerenciar reservas em um restaurante.

**Tarefas:**

1. Criar um modelo Reservation com os atributos: id, customerName, date, time, e tableNumber.
2. Implementar um repositório JPA para gerenciar reservas.
3. Criar um controlador REST com os seguintes endpoints:
   - `GET /reservations/all` - Listar todas as reservas.
   - `POST /reservations` - Criar uma nova reserva
   - `PUT /reservations` - Alterar uma reserva.
   - `DELETE /reservations/{table-number}` - Remover uma nova reserva pelo numero da mesa e data e hora.

**Entidade:**

```java
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private LocalDate date;
    private LocalTime time;
    private int tableNumber;
    
}

```