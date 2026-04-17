//  Fonte: https://www.youtube.com/watch?v=Hwqb12zPT1U

//  Meu primeiro projeto Spring Boot, para aprender a criar rotas e controllers. O projeto é um exemplo simples de uma aplicação Spring Boot que possui um controller, um serviço e um repositório. O controller é responsável por receber as requisições e retornar as respostas. O serviço é responsável por realizar as operações de negócio, como acessar o banco de dados, realizar cálculos, etc. O repositório é responsável por acessar o banco de dados. O projeto utiliza o Spring Data JPA para acessar o banco de dados, e o H2 como banco de dados em memória.

// Definição do que é rota: é o caminho que será acessado para chegar até o controller. 
// O controller é responsável por receber as requisições e retornar as respostas (a resposta vem em formato JSON). 
	// O controller é mapeado para uma rota, que é definida pela annotation @RequestMapping. 
	//  Quando mencionamos mapear, significa associar uma rota a um controller, ou seja, quando uma requisição for feita para aquela rota, o controller associado a ela será acionado para processar a requisição e retornar a resposta.
	//  No nosso projeto, a rota é "/demo", e o controller associado a essa rota é o DemoController.
	// outros exemplos de rota e controller de nosso projeto: a rota "/demo/{id}" é associada ao método getDemo do DemoController, que é acionado quando uma requisição GET é feita para essa rota. O método getDemo recebe o id da demo como parâmetro, e retorna a demo correspondente a esse id.
	// as rotas que usamos em nosso projeto são: "/demo" e "/demo/{id}". 
		// A rota "/demo" é associada ao método getDemo do DemoController, que é acionado quando uma requisição GET é feita para essa rota. 
		// O método getDemo retorna a demo correspondente ao id passado como parâmetro na rota "/demo/{id}".


//  ANNOTATIONS: são usadas para informar ao Spring qual é a função de cada classe, e como elas devem ser tratadas. 
// A annotation @SpringBootApplication é usada para informar que a classe é a classe principal da aplicação Spring Boot. 
// A annotation @Entity é usada para informar que a classe é uma entidade do JPA, ou seja, uma classe que representa uma tabela do banco de dados. 	
// A annotation @RequestMapping é usada para mapear uma rota para um controller. 
// A annotation @GetMapping é usada para mapear uma rota para um método do controller que responde ao método HTTP GET. 
// A annotation @PostMapping é usada para mapear uma rota para um método do controller que responde ao método HTTP POST. 
// A annotation @PutMapping é usada para mapear uma rota para um método do controller que responde ao método HTTP PUT. 
// A annotation @DeleteMapping é usada para mapear uma rota para um método do controller que responde ao método HTTP DELETE.
// A annotation @RestController é usada para informar que a classe é um controller REST, ou seja, um controller que retorna objetos JSON.
// A annotation @Service é usada para informar que a classe é um serviço, ou seja, uma classe que contém os métodos que realizam as operações de negócio.
// A annotation @Repository é usada para informar que a classe é um repositório, ou seja, uma classe que acessa o banco de dados.
// As annotations são usadas para informar ao Spring qual é a função de cada classe, e como elas devem ser tratadas. 

// O Spring é responsável por criar os objetos e injetar as dependências, ou seja, os objetos que são necessários para o funcionamento da aplicação. 
// O Spring usa as annotations para identificar quais classes são componentes do sistema, e como elas devem ser tratadas. 
// Por exemplo, quando o Spring encontra a annotation @RestController, ele sabe que aquela classe é um controller REST, e que os métodos dessa classe retornam objetos JSON. 
// Quando o Spring encontra a annotation @Service, ele sabe que aquela classe é um serviço, e que os métodos dessa classe realizam operações de negócio. 
// Quando o Spring encontra a annotation @Repository, ele sabe que aquela classe é um repositório, e que os métodos dessa classe acessam o banco de dados.

// É COMUM, em outros projetos, dividirmos os códigos referentes a repository, controllers e services em pacotes/pastas separados, para organizar melhor o projeto. 
// Mas, para esse projeto de teste, deixaremos tudo em um único arquivo, para facilitar a compreensão.

// há também pacotes adicionais que podemos implementar, como: dto, config, models,security, type. 
// dto é um pacote que contém as classes de transferência de dados, ou seja, as classes que representam os dados que serão enviados e recebidos pelas rotas.
// config é um pacote que contém as classes de configuração, ou seja, as classes que configuram o comportamento da aplicação, como a configuração do banco de dados, a configuração de segurança, etc.
// models é um pacote que contém as classes de modelo, ou seja, as classes que representam os dados do sistema, como as entidades do banco de dados, as classes de domínio, etc.
// security é um pacote que contém as classes de segurança, ou seja, as classes que configuram a segurança da aplicação, como a configuração de autenticação, a configuração de autorização, etc.
// type é um pacote que contém as classes de tipo, ou seja, as classes que representam os tipos de dados do sistema, como as classes de enumeração, as classes de valor, etc.	
// essas implementações são de spring avançado, e envolvem spring security

//  o package está depois dos imports, e precisa ser sempre a primeira linha do arquivo:
package br.com.iasminsantiago.demo_springboot;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Optional;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// Geralmente, o próprio spring faz os imports.

@SpringBootApplication
public class DemoSpringbootApplication {
	// Automaticamente foi criada a classe application.
	// o initialzr cria uma classe application. Adicionaremos algumas classes e annotations:
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootApplication.class, args);
	}

}


// para informar que demo é entidade, pro JPA, usa a anotacao
@Entity

// definindo a entidade demo, que será usada em getDemo ali embaixo
class Demo {
	// Demo terá propriedades, getters e setters
	// informar que esse id é o id do registro da demo, ele deve identificar unicamente essad emo no banco de dados
	@Id
	Long id;
	String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}


// CONTROLADOR ------------------------
@RestController  // informa que a classe DemoController vai ser um controlador REST.
@RequestMapping("/demo") // informa recurso que ele tratará: o recurso será demo. Mapping quer dizer mapeamento, ou seja, o caminho que será acessado para chegar até o controller.
// nao foi API,mas sim demo porque é um projeto de teste, para aprender a criar rotas e controllers.
// Mas não tem problema, o nome da rota é apenas um exemplo, pode ser qualquer nome.
// Esse nome da rota é o caminho que será acessado para chegar até o controller.
// Esse nome demo também é o nome do endpoint, ou seja, o caminho que será acessado para chegar até o controller.
// Escreveremos esse nome demo também na URL para acessar o controller.
class DemoController {
	// precisaremos interagir com a web, por isso criamos esta classe.
	// Criamos um controller para criar uma rota.
	// O controller é responsável por receber as requisições e retornar as respostas.
	// Para criar um controller, precisamos adicionar a annotation @RestController.
	// A annotation @RestController é uma combinação de @Controller e @ResponseBody.
	// Ela indica que a classe é um controller e que os métodos retornam objetos JSON.


	// Implementando a operação, que é feita pelo service. Nao pelo controller.
	DemoService demoService; // componente.
	// injetar o serviço, para usar os métodos do serviço. 
	// O serviço é responsável por realizar as operações de negócio, como acessar o banco de dados, realizar cálculos, etc.
	// o demoservice será chamdo mais abaixo, para retornar a demo.
	
		// criando os construtores das classes, para que o spring possa injetar as dependencias. O spring é responsável por criar os objetos e injetar as dependencias, ou seja, os objetos que são necessários para o funcionamento da aplicação.
		public DemoController(DemoService demoService) {
			this.demoService = demoService;
		}    
	
	 // faremos consulta pelo metodo GET
	// passei o id na rota, o id será um parametro da rota. 
	// para consultar o recurso demo, usaremos o método GET. O método POST é usado para criar recursos. O método PUT é usado para atualizar recursos. O método DELETE é usado para deletar recursos.
	// como farei consulta, uso método http GET; Informo que essa rota responde ao método GET http.
		@GetMapping("/{id}")
		public Demo getDemo(@PathVariable("id") Long id) {
			return demoService.getDemo(id).orElse(null);
			// operaçao - consulta por demo
			// com o @pathvariable pedi pro string injetar o valor que foi passado aqui nessa variavel 
				// informei que o noenm da vraivel que quero injetar é id
			// demo é a entidade do meu sistema, defini ela mais acima.
			// O método getDemo é responsável por retornar um objeto do tipo Demo.
			// O método demo é mapeado para a rota /demo, pois a classe DemoController está anotada com @RequestMapping("demo").
			// o método getDemo do serviço retorna um optional, pois é possível que não exista
			
		}
}


// SERVICE ------------------------
// informa que a classe DemoService é um serviço. O serviço é responsável por realizar as operações de negócio, como acessar o banco de dados, realizar cálculos, etc.
// sem essa anotacao, o DemoService nao sera injetado no controlador, e o controller nao podera usar os métodos do serviço.
@Service 
class DemoService {
		// o serviço é responsável por realizar as operações de negócio, como acessar o banco de dados, realizar cálculos, etc.
		// O serviço é uma classe que contém os métodos que realizam as operações de negócio.
		// O serviço é injetado no controller, para que o controller possa usar os métodos do serviço.
		

		// para consultar essa demo, precisamos ir ao banco. Usamos o componente repository pra isso:
		DemoRepository demoRepository; 
		// componente. O repository é responsável por acessar o banco de dados.
		// injetar o repository, para usar os métodos do repository. 
		// O repository é uma interface que estende a interface JpaRepository, que é a interface de acesso a dados do Spring Data JPA. A interface JpaRepository é uma interface genérica
		// que recebe dois parâmetros: a entidade que será persistida e o tipo do id da entidade. A interface JpaRepository já possui métodos pré-definidos para realizar operações de CRUD (Create, Read, Update, Delete) no banco de dados, como save, findById, findAll, deleteById, etc. A interface DemoRepository herda esses métodos da interface JpaRepository, e pode ser usada para acessar o banco de dados e realizar operações de CRUD na entidade Demo.

		
		// construtor, para injetar o repository
		public DemoService(DemoRepository demoRepository) {
			this.demoRepository = demoRepository;
		}


		public Optional<Demo> getDemo(Long id) {
			// operacao publica que retornoa  ademo e obtem a demo peo id que passarei.
			//  por isso, colocamos public demo getdemo(Long id)
			// informamos, como parametro, o id da demo que queremos obter/uasr na consulta.
			// operação - consulta por demo
			// o método getDemo é responsável por retornar um objeto do tipo Demo.
			
			// Demo demo = new Demo();
			// demo.setId(1L);
			// demo.setDescription("Demo description");
			// return demo;

			// montando a consulta por id
			return demoRepository.findById(id);
			// essa consulta retorna um optional, pois é possivel que nao exista uma demo com esse id
			// por isso colocamos um optional
		
		}

}


// REPOSITORIO -----------------------

// nao precisa de anotacao pois ja extendemos a inerface jparepositiry do spring data, colocaremos sometne o construtor com or epository:

// extendendo a interface do JPA, que é a interface de acesso a dados do Spring. O JPA é uma especificação de persistência de dados em Java. O Spring Data JPA é uma implementação do JPA.
		// O repository é uma interface que estende a interface JpaRepository, que é a interface de acesso a dados do Spring Data JPA. A interface JpaRepository é uma interface genérica
interface DemoRepository extends JpaRepository<Demo, Long> {
			// recebe a entidade que ta sendo consultada por esse repository, que é a demo; e o identificar dessa demo, que é do tipo long
			// depois, o spring data vai montar tudo magicamente pra mim.
			// uma entidade é uma classe que representa uma tabela do banco de dados. A entidade é anotada com @Entity, e possui propriedades que representam as colunas da tabela, e getters e setters para essas propriedades.
			// a interface JpaRepository é uma interface genérica, que recebe dois parâmetros: a entidade que será persistida e o tipo do id da entidade.
			// a interface JpaRepository já possui métodos pré-definidos para realizar operações de CRUD (Create, Read, Update, Delete) no banco de dados, como save, findById, findAll, deleteById, etc.
			// a interface DemoRepository herda esses métodos da interface JpaRepository, e pode ser usada para acessar o banco de dados e realizar operações de CRUD na entidade Demo.
			
}


// para podermos inicializar esses atributos e dependencias de cada classe, usaremos a injecaod e dependencias
// por enqunato esses atriibutos estao nulos, mas o spring permite inicialzia-los pelo construtor 

// em cada componente do spring, que tem as anotacoes, criaremos um construtor