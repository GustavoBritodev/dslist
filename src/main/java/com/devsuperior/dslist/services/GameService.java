package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Como o service é uma classe comum que não está herdando nada,
// é necessário registrá-la como um componente do sistema
// Durante o desenvolvimento com alguns frameworks existem alguns componentes que precisam ser registrados
// porque o framework que vai gerenciar esses componentes, como por exemplo instanciar, reaproveitar e etc.
// No spring é possível registrar o componente usando @Component ou no caso do service usar o @Service.
// Ambos irão registrar o GameService como um componente do sistema e assim será possível utilizá-lo em outros componentes, injetando um componente no outro
@Service
public class GameService {

    // A linha abaixo injeta uma instância do GameRepository dentro do GameService
    // A injeção é feita usando a notation @Autowired
    @Autowired
    private GameRepository gameRepository;

    // A notation @Transactional é uma boa prática para _todo metodo do service
    // para que ele garanta que a operação com o banco de dados vai acontecer
    // obedecendo os princípios das transações ACID (Atômica, consistente, isolada e durável)
    //Lembrar de usar o @Transactional do spring e não do jakarta
   @Transactional(readOnly = true)
    public GameDTO findById(Long gameId){
        // O metodo do repository findById recebe um id e retorna um objeto
        Game result = gameRepository.findById(gameId).get();
        return new GameDTO(result);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAllGames(){
        List<Game> result = gameRepository.findAll();
        //Stream é uma lib do java que permite fazer operações com sequência de dados, como, por exemplo, o map
        // O map transforma objetos de uma coisa para outra, no trecho de código abaixo ele diz que
        // todo_ objeto x original que era game será transformado em um new GameMinDTO passando x como argumento
        // e para voltar o stream para uma lista é necessário o .toList
        // A linha abaixo transforma tudo que é Game em GameMinDTO
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId){
        List<GameMinProjection> result = gameRepository.searchByList(listId);

        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }
}
