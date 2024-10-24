# Exercícios Spring Boot Web: Criação de REST APIs Simples

## Exercício 1: Gerenciamento de Livros
**Objetivo:** Criar uma API para gerenciar uma biblioteca de livros.

**Tarefas:**
1. Criar um modelo `Book` com os atributos: `id`, `title`, `author`, `isbn`, e `publishedDate`.
2. Implementar um repositório JPA para gerenciar livros.
3. Criar um controlador REST com os seguintes endpoints:
    - `GET /books` - Listar todos os livros.
    - `POST /books` - Adicionar um novo livro.