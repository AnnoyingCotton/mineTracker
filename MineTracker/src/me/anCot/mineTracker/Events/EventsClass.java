package me.anCot.mineTracker.Events;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.anCot.mineTracker.Lobby;
import me.anCot.mineTracker.LobbyHandler;


public class EventsClass implements Listener {
	
	public LobbyHandler handle;
	
	public EventsClass(LobbyHandler handle) {
		this.handle = handle;
	}
	

	//updates all mt lobbies if a player leaves and the game has not started!!
	@EventHandler
	public void leaveGame(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		System.out.println(player.getName());
		
		
		if(handle.players.contains(player.getName())) {
			
			System.out.println("Finding Lobby");
			
			Lobby currLob = handle.lobbies.get(handle.playerInLobby.get(player.getName()));
			
			if(!currLob.gameStart) {
				
				currLob.players--;
				
				
				if(currLob.roles.get(player.getName()) == "Runners") {
					currLob.runners--;
					currLob.Run.removeEntry(player.getName());
				}
				else {
					currLob.hunters--;
					currLob.Hunt.removeEntry(player.getName());
				}
				
				System.out.println(currLob.players + ":" + currLob.runners + ":" + currLob.hunters);
				
				handle.players.remove(player.getName());
				
				currLob.roles.remove(player.getName());
				
				handle.playerInLobby.remove(player.getName());
				
				if(currLob.playersVoted.contains(player.getName())){
					
					currLob.votesToStart--;
					
					currLob.playersVoted.remove(player.getName());
					
				}
				
				handle.playerq.remove(player.getName());
			}
			

			
		}
		
		
	}
	
	
}
