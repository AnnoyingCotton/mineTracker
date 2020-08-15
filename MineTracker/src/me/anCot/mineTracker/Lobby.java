package me.anCot.mineTracker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class Lobby {
	
	public int players = 0;
	
	public int hunters = 0;
	
	public int runners = 0;
	
	public int reqRunners = 6;
	
	public int reqHunters = 3;
	
	public HashMap<String,String> roles;
	
	public Location location;
	
	public World world;
	
	public Team Hunt;
	
	public Team Run;
	
	public Scoreboard score;
	
	public int votesToStart = 0;
	
	public HashSet<String> playersVoted;
	
	public int maxPlayers = 9;
	
	public boolean gameStart = false;
	
	//method for joining the lobby!
	//teleports the player to the lobby, assigns them a team randomly unless teams are full in which case it prioritises the hunters over the runners!
	public void join(Player player) {
		
		//player.sendMessage("TEST");
		
		Random rd = new Random();
		
		players++;
		
		player.teleport(location);
		
		if(runners == 0) {
			joinRunners(player);
		}
		else if(hunters == 0) {
			joinHunters(player);
		}
		else if(hunters != reqHunters && runners != reqRunners) {
			
			double chance = rd.nextDouble();
			
			if(chance > 0.5) {
				joinRunners(player);
			}
			else {
				joinHunters(player);
			}
			
		}
		else if(hunters != reqHunters) {
			joinHunters(player);
		}
		else {
			joinRunners(player);
		}
		
		player.sendMessage("JOINED LOBBY");
		
		if(players == maxPlayers) {
			
			startGame();
			
		}
		
		
	}
	
	//method for joining the runners team
	
	private void joinRunners(Player player) {
		
		player.sendMessage("YOU ARE A RUNNER");
		
		runners++;
		
		Run.addEntry(player.getName());
		
		//player.sendMessage("YOU ARE A RUNNER!!!!!!");
		
		roles.put(player.getName(),"Runners");
		
		//player.sendMessage("YOU ARE A RUNNER!!!!!!AAAAAAAAAAAAAAA");
	
		
		
	}
	
	//method for joining the hunters team
	
	private void joinHunters(Player player) {
		
		player.sendMessage("YOU ARE A HUNTER");
		
		hunters++;
		
		Hunt.addEntry(player.getName());
		
		//player.sendMessage("YOU ARE A HUNTER!!!!!");
		
		roles.put(player.getName(),"Hunters");
		
		//player.sendMessage("YOU ARE A HUNTER!!!!! AAAAAAAAAA");
		
		
	}
	
	public void startGame() {
	
		Bukkit.getServer().broadcastMessage("GAME STARTED");
		
		gameStart = true;
		
	}
}
