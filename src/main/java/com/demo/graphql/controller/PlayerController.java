package com.demo.graphql.controller;

import com.demo.graphql.model.Player;
import com.demo.graphql.model.Team;
import com.demo.graphql.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @MutationMapping
    public Player save(@Argument String name, @Argument Team team){
        return playerService.save(name, team);
    }

    @MutationMapping
    public Player update(@Argument Integer id, @Argument String name, @Argument Team team){
        return playerService.update(id, name, team);
    }

    @MutationMapping
    public Player delete(@Argument Integer id){
        return playerService.delete(id);
    }

    @QueryMapping
    public Optional<Player> findOne(@Argument Integer id){
        return playerService.findOne(id);
    }

    @QueryMapping
    public List<Player> findAll(){
        return playerService.findAll();
    }
}
