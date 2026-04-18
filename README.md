# **Primeiro projeto Spring Boot — API REST com Java 21**


Projeto básico para melhor entendimento de rotas, annotations, dependências e conceitos de uma API feita com Spring Boot.
Você pode conferir anotações que refletem meu aprendizado detalhadamente nos arquivos [DemoSpringbootApplication.java](https://github.com/iasminsantiago/API_Demo_Spring/blob/main/demo-springboot/src/main/java/br/com/iasminsantiago/demo_springboot/DemoSpringbootApplication.java), [application.properties](https://github.com/iasminsantiago/API_Demo_Spring/blob/main/demo-springboot/src/main/resources/application.properties) e [pom.xml](https://github.com/iasminsantiago/API_Demo_Spring/blob/main/demo-springboot/pom.xml). 



Desenvolvido por **Iasmin Santiago**  
Baseado na aula de Giuliana Bezerra

---

## 📌 Sobre o Projeto

Este é meu primeiro projeto prático com Spring Boot, desenvolvido seguindo uma aula do YouTube. O objetivo foi aprender na prática como funciona uma API REST em Java criando rotas, controllers, services e repositories do zero.

O projeto é intencionalmente simples: uma entidade `Demo` que pode ser consultada pelo ID via uma rota GET.

👉 Toda a complexidade está nos conceitos aprendidos ao longo do caminho.

🎓 Fonte: https://www.youtube.com/watch?v=Hwqb12zPT1U

---

## 🛠️ Stack e Tecnologias

| Tecnologia | O que significa |
|----------|----------------|
| Java 21 | Versão LTS mais recente do Java |
| Spring Boot 4.1 | Framework que elimina configuração manual |
| Spring Data JPA | Abstração de acesso ao banco |
| H2 Database | Banco em memória/arquivo |
| Maven | Gerenciador de dependências |
| Springdoc OpenAPI | Documentação automática da API |
| Spring DevTools | Hot reload da aplicação |

---

## 🏗️ Arquitetura do Projeto

| Camada | Função |
|------|--------|
| Controller | Recebe requisições HTTP e retorna JSON |
| Service | Contém a lógica de negócio |
| Repository | Acessa o banco de dados |
| Entity | Representa uma tabela |

💡 **Nota:** Em projetos maiores, cada camada fica em uma pasta separada.

---

## 📁 Estrutura de Arquivos

```bash
demo-springboot/
├── src/main/java/br/com/iasminsantiago/demo_springboot/
│   └── DemoSpringbootApplication.java
├── src/main/resources/
│   └── application.properties
├── data/
│   └── demo-springboot.mv.db
└── pom.xml
```

---

## ⚙️ Configuração — application.properties

```properties
spring.datasource.url=jdbc:h2:file:./data/demo-springboot
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

### O que cada configuração faz:

- `jdbc:h2:file:./data/demo-springboot` → caminho do banco  
- `username=sa / password vazio` → acesso padrão  
- `h2.console.enabled=true` → ativa console web  
- `ddl-auto=update` → atualiza tabelas automaticamente  

⚠️ H2 é para desenvolvimento. Em produção, use PostgreSQL ou MySQL.

---

## 📦 Dependências — pom.xml

- spring-boot-starter-data-jpa  
- spring-boot-starter-webmvc  
- h2  
- spring-boot-devtools  
- springdoc-openapi-starter-webmvc-ui (2.3.0)

---

## 🏷️ Annotations Aprendidas

| Annotation | O que significa |
|-----------|----------------|
| @SpringBootApplication | Classe principal |
| @Entity | Representa tabela |
| @Id | Chave primária |
| @RestController | Retorna JSON automaticamente |
| @RequestMapping("/demo") | Rota base |
| @GetMapping("/{id}") | Requisição GET |
| @PathVariable | Captura valor da URL |
| @Service | Define camada de serviço |

---

## 🔗 Rotas da API

| Método | Descrição |
|------|-----------|
| GET /demo/{id} | Busca uma Demo por ID |

Exemplo:

```bash
GET /demo/1
```

Resposta:
```json
{ "id": 1, "description": "..." }
```

---

## 📖 Swagger / OpenAPI

URL:
```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔄 Injeção de Dependência

```java
public DemoController(DemoService demoService) {
    this.demoService = demoService;
}

public DemoService(DemoRepository demoRepository) {
    this.demoRepository = demoRepository;
}
```

---

## 🔍 Optional e orElse

```java
public Optional<Demo> getDemo(Long id) {
    return demoRepository.findById(id);
}
```

```java
return demoService.getDemo(id).orElse(null);
```

Evita `NullPointerException`.

---

## ▶️ Como Rodar o Projeto

### Pré-requisitos

- Java 21
- Maven

### Comando:

```bash
mvn spring-boot:run
```

⚠️ Não usar `javac` diretamente.

---

## 🌐 URLs

- http://localhost:8080/demo/{id}  
- http://localhost:8080/h2-console  
- http://localhost:8080/swagger-ui/index.html  

---

## Códigos de resposta HTTP

| Código | Nome | Significa |
|---|---|---|
| 200 | OK | Requisição bem-sucedida |
| 201 | Created | Recurso criado com sucesso |
| 204 | No Content | Sucesso, sem conteúdo pra retornar (ex: DELETE) |
| 400 | Bad Request | Requisição inválida — dados incorretos ou faltando |
| 401 | Unauthorized | Não autenticado — precisa fazer login |
| 403 | Forbidden | Autenticado, mas sem permissão pra acessar |
| 404 | Not Found | Recurso não encontrado |
| 500 | Internal Server Error | Erro no servidor |

> 💡 Diferença entre 401 e 403:
> - **401** = você não está logado
> - **403** = você está logado, mas não tem permissão

---

## 📝 Aprendizados

- `package` vem antes de tudo no Java  
- Spring detecta automaticamente o H2  
- `@RestController` já retorna JSON  
- JpaRepository já possui CRUD pronto  
- Swagger gera docs automaticamente  

---

## 🚀 Próximos Passos

- Adicionar POST, PUT, DELETE  
- Separar em pacotes  
- Adicionar validações  
- Migrar para PostgreSQL  
- Implementar Spring Security  
- Criar testes  
- Usar DTOs  

---

## 🌱 Conclusão

Projeto desenvolvido com muito aprendizado e curiosidade.
