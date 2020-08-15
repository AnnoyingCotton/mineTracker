package me.anCot.mineTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scoreboard.ScoreboardManager;

public class LobbyHandler{
	
	public HashSet<String> players;
	
	public HashMap<String,Integer> playerInLobby;
	
	public ArrayList<Lobby> lobbies;
	
	public PriorityQueue<String> playerq;
	
	public int lobbyMaxPlayer = 9;
	
	public int maxLobbies = 3;
	
	public int numLobby = 0;
	
	public int lobbyDistance = 49;
	
	public String lobbyWorldName = "LobbyWorld";
	
	
	
	
	
	//method for setting up the lobby classes when the server starts up!
	public void createLobby(int index) 
	{
		ScoreboardManager manage = Bukkit.getScoreboardManager();
		
		Lobby temp = new Lobby();
		
		World world = Bukkit.getServer().getWorld(lobbyWorldName);
		
		temp.location = new Location(world,5.5, 64, 5.5 + index*lobbyDistance);
		
		temp.score = manage.getMainScoreboard();
		
		temp.Hunt = temp.score.getTeam("Hunters");
		
		temp.Run = temp.score.getTeam("Runners");
		
		temp.roles = new HashMap<String,String>();
		
		temp.playersVoted = new HashSet<String>();
		
		lobbies.add(temp);
		
		
		
	}
	
	//assigns players in queue to a lobby!
	public void assignLobby() {
		

		
		//adds players from queue until it cannot
		while(!playerq.isEmpty()) {
			
			
			boolean lobbyFull = true;
			
			int i = 0;
			
			for(Lobby lobby : lobbies) {
				
				while(lobby.players != lobbyMaxPlayer && !playerq.isEmpty()) {
					
					lobby.join(Bukkit.getPlayer(playerq.peek()));
					
					playerInLobby.put(playerq.poll(),i);
					
					lobbyFull = false;
					
				}
				
				i++;
				

			}
			
			if(lobbyFull) {
				break;
			}
		
		}
		

		
	}

}	
