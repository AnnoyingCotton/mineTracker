# mineTracker
A man tracker inspired game mode for minecraft custom built for our server.


commands:
  /findlobby <game>
  (for now the game isnt required since there is only minetracker. may add more game modes to our server at a later date)
  /votestart <game>
  (the game is required. must use abbreviation which is mt. this is mainly implemented for now just to test game selection. must be in a lobby to run this.)
  
The lobby queueing uses a queue data structure and sorts through it to fill the max number of lobbies. For now, this is only run when someone uses the /findlobby command.
Later on this will also be run when a game is finished, as that lobby will now be open.

Also there is a stark lack of commenting thus far. This is mostly because I wrote this in one sitting really really fast. I will go through this and add comments in a future commit.

Stay cool B)
