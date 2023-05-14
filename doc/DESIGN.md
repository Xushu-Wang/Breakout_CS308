## Breakout Design
### Andy


### Design Goals (From Course Website)
1. code a project from scratch (and determine if the course is at the right level for your abilities)
2. learn the basics of the OpenJFX framework (you will learn about more it in successive course projects)
3. use the programming tools (IntelliJ and Git Version Control)
4. follow the policies (submission requirements and the plan, code, reflect assignment cycle)
5. try some basic Clean Code design practices to find areas to refactor your code rather than settling for the first draft just because it works

Remark: I personally plan to make a Chinese New Year version of BreakOut game. The first scene
is just an introduction, the second scene is actually a firecracker, the third scene is firework rising up to the sky. 
I would really love to add the elements of firecrackers, red envelops, Lion dancing, even replacing the bouncer with
dumplings. Due to the limit of time, I couldn't complete it. I will definitely improve it as the course going.
Also, I do need a more aesthetically appealing design. 

### High-Level Design

1. Game Components:
   1. Bouncer: 
      1. Constructor: x,y position and speed, size
      2. Method: hitPaddle
      3. Method: isDead
      4. Method: move
      5. Mutator and Accessor
   2. Paddle:
      1. Constructor: x, y position (y is fixed), and length
      2. Method: setPosition(set its x position)
      3. Method: receivePowerUp(check whether the paddle hit the powerup).
      4. Mutator and Accessor
   3. Brick:
      1. Constructor: type, x, y position
      2. Method 
      3. Mutator and Accessor.
2. LevelScene interface
   1. GameScene (Set the scene for different game levels)
   2. InitialScene (The opening scene of the game, including name, author, instructions)
   3. EndScene (The last scene of the game, indicating winning or losing)
3. Main controller
   1. control the game by setting the right scene at the right time.


### Assumptions or Simplifications
1. When the bouncer hit the bricks, it changes its y velocity, instead of making a real physical collision.
2. Ideally, the paddle shouldn't have height (thickness). However, in the real situation, the thickness of paddle does produce some bugs. 


### Changes from the Plan

1. Miss the implementation of a more sophisticated paddle as the plan suggested. 
2. Miss the implementation of more sophisticated bricks, I only complete three kinds of bricks out of 7 listed


### How to Add New Levels

To add a new level, we can simply add another GameScene variable in the sceneGroup array in Main. The only
thing we need to do is to specify the brick geometry as well as a set of parameters, including 
bouncer speed, number of power ups, etc.

