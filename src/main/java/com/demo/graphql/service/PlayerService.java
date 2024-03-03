package com.demo.graphql.service;

import com.demo.graphql.model.Player;
import com.demo.graphql.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    @PostConstruct
    private void init(){
        players.add(new Player(id.incrementAndGet(), "Sanath Jayasuriya", Team.SL));
        players.add(new Player(id.incrementAndGet(), "M.S Dhoni", Team.IND));
        players.add(new Player(id.incrementAndGet(), "D.J Bravo", Team.WI));
        players.add(new Player(id.incrementAndGet(), "Michel Stark", Team.AUS));
        players.add(new Player(id.incrementAndGet(), "Kane", Team.NZ));
        players.add(new Player(id.incrementAndGet(), "Ben Stock", Team.END));
    }

    public Player save(String name, Team team){
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player update(Integer id, String name, Team team){
        Player updatedPlayer = new Player(id, name, team);
        Optional<Player> optionalPlayer = players.stream()
                .filter(player1 -> player1.id() == id).findFirst();

        if(optionalPlayer.isPresent()){
            Player player = optionalPlayer.get();
            int index = players.indexOf(player);
            players.set(index, player);
        } else {
            throw new IllegalArgumentException("Invalid Player");
        }
        return updatedPlayer;
    }

    public Player delete(Integer id){
        Player player = players.stream()
                .filter(player1 -> player1.id() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
        players.remove(player);
        return player;
    }

    public Optional<Player> findOne(Integer id){
        return players.stream()
                .filter(player -> player.id() == id).findFirst();
    }

    public List<Player> findAll(){
        return players;
    }

}
