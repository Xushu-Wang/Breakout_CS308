# Breakout Plan
### Andy Wang (xw214)

## Interesting Breakout Variants

 * Idea #1
 * JetBall variant: This version is extremely hard, with moving blocks and various power-ups and even power
   downs. I am really interested in hard simple games.

 * Idea #2
 * Fairy Treasure variant: I like the aesthetic design of this particular variant, which
   introduces an ancient Egyptian style. I would love to use the bricks and background in this variant.



## Paddle Ideas

 * Idea #1
 * Different Bouncing Angles: When the ball hits the edge of the paddle, it will directly
   returns to its incoming angle.

 * Idea #2
 * Paddle Power-up: Paddle might have the ability of shooting lasers at the edge. The laser destroys
   the first block it hits.


## Block Ideas

 *  Normal Block: This kind of block is destroyed after one hit, no special ability. 
 * Extra Health Block: This Block contains an extra health in it. After being
   destroyed, a heart appears at the same position, and vanishes after 3 seconds. 
 * Hard Block: This Block is extra hard, meaning that it takes two hit to be destroyed. 
 * Wall Block: Unbreakable. 
 * Extra Ball Block: This kind of block contains extra bouncer in it. After it is destroyed, it emits
   a bouncer to a random direction. 
 * Exploding Block: Destroy the block around (3 * 3). 
 * Power Up Block: Faster Bouncer, Longer Paddle, Paddle Rockets/lasers.
 * Catch Block: Catch the ball and release it to a random direction after 2 seconds.


## Power-up Ideas

 * Life: Giving Extra Life to the user

 * Key: Directly to next level (very rare).

 * Bigger Ball: The bouncer gets bigger, easier to control and hit the block


## Cheat Key Ideas

 * H: Adding an extra Health. 
 * C: Clear the lowest level of blocks. 
 * N: Jumping to the next level. 
 * T: extra time 
 * P: random power-up


## Level Descriptions

 * Easy Level: Composed mostly of simple bricks, slow bouncer speed, more power ups, unlimited time. 
 * Medium Level: Composed evenly with different kinds of bricks and power ups, unlimited time. 
 * Hard Level: Composed of different kinds of bricks, extremely fast bouncer speed, no power ups, limited time, slower paddle.
   (The brick decomposition is in resources, denoted by level1, 2, & 3)



## Class Ideas

 * Bouncer class: xspeed, yspeed, xposition, yposition, size. Each with a getter and setter. 
 * Brick class: Type, size, hit, xposition, yposition. We have a setter method for position, and a destroyed method for being hit. 
 * Paddle class: xposition, a fixed yposition, boolean power-up. We have a setter method for linking the mouse with paddle. 
 * Scene class: Create scenes for different levels. 
 * Main class: a separate class to launch the software, with cheatKeys method. 
 * Power-Up class: contains multiple types of power ups. parameters may include life, type, etc


