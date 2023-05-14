# Breakout
### Andy Wang (xw214)

This project implements the game of Breakout with multiple levels.

### Timeline

 * Start Date: Jan 13th, 2023

 * Finish Date: Jan 23th, 2023

 * Hours Spent: 25



### Attributions

 * Resources used for learning (including AI assistance)
   * Software design on BreakOut -- https://www.gamedeveloper.com/api/redirects?to=/view/feature/1630/breaking_down_breakout_system_and_.php
   * JavaFX tutorial -- https://www.javatpoint.com/javafx-tutorial
 
 * Resources used directly (including AI assistance)
   * Breakout resources including bouncer/block/powerup -- https://opengameart.org
   * Background Image -- https://www.canva.com


### Running the Program

 * Main class: The Main class controls the switch of different scenes. 

 * Data files needed: 
   * Level1.txt, Level2.txt, Level3.txt, specify the geometry of bricks (including types and position)
   * Other png files are the images I used in the game. 

 * Key/Mouse inputs:
   * Left: move the paddle to the left
   * Right: move the paddle to the right
   * R: Return the bouncer to the original position

 * Cheat keys:
   * H: Extra Life 
   * S: Double Score 
   * P: Get one more power up 
   * T: Expand TimeLimit
   * N: Jump to the next Level


### Notes/Assumptions

 * Assumptions or Simplifications:
   * When the bouncer hit the bricks, it changes its y velocity, instead of making a real physical collision. 
   * Ideally, the paddle shouldn't have height (thickness). However, in the real situation, the thickness of paddle does produce some bugs.


 * Known Bugs:
   * The Ball sometimes act weirdly, especially when the size is bigger (after receiving power up) 
   * When the game is lost, the player returns the first scene as designed. However, the player could not advance to the next scene. 

 * Features implemented:
   * Allow the player to control a paddle to keep the ball from moving out of the window 
   * The ball bounces off blocks, possibly destroying them, releasing a power-up, or some other reaction, and accumulating a score 
   * The ball bounces off some of the window's sides, but if it moves off a specific side (typically the bottom), the player loses a life, and the ball resets to its starting position 
   * If the player misses blocking the ball three times, the game ends with a message announcing the player lost 
   * If all the blocks are cleared from the screen, the level should end and a new variation is loaded (i.e., with a different configuration of blocks and new forms of interactions)
   * There should be at least three different levels, with each one's configuration of blocks read from a data file 
   * If all the levels are completed, the game ends with a message announcing the player won

 * Features unimplemented:
   * The paddle lacks special ability. 
   * The bricks lack variety, with only non-destroyable blocks, single hit block and double hit block. 
   * The game could be aesthetically fancier, but I tried a few times changing font/background/color, I still couldn't make it.

 * Noteworthy Features:
   * It happens to be Chinese New Year on the day of submission. I was trying to implement a NewYear theme
   as almost every game does. Here are some future thoughts. 
   * The paddle can emit firecrackers when receiving certain powerup.
   * There can be red envelop dropping for extra scores.


### Assignment Impressions
 * This first project truly lets me know the difference between industrial programming and 
programming in classes or simply leetcode. Writing maintainable and straightforward code does 
requires extensive training. I love to see a project starts from nothing to its completeness. 


