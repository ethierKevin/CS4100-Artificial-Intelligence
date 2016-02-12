
/**
 *
 * @author Kevin M. Ethier
 */
/*
 This controller class is my implementation for the Breadth First Search 
 Algorithm used to navigate Ms. Pacman, through the maze, while avoiding 
 ghosts and in order to reach a high score. 
 */

package pacman.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import pacman.controllers.examples.StarterGhosts;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/**
 * @author Kevin M. Ethier

UP  { public MOVE opposite(){return MOVE.DOWN;  };},    
RIGHT   { public MOVE opposite(){return MOVE.LEFT;  };},    
DOWN    { public MOVE opposite(){return MOVE.UP;        };},        
LEFT    { public MOVE opposite(){return MOVE.RIGHT; };},    
NEUTRAL { public MOVE opposite(){return MOVE.NEUTRAL;   };};
*/
public class DFS_Controller_Kevin_Ethier extends Controller<MOVE> {
            
    public class CurrentGameState{
        public Game current;
        public int depth;
        
        public CurrentGameState(Game g, int d){
            this.current = g; 
            this.depth = d; 
        }        
    }

    public static StarterGhosts ghosts = new StarterGhosts();

    @Override
    public MOVE getMove(Game game, long timeDue) {

        List<MOVE> moves = Arrays.asList(MOVE.DOWN, MOVE.LEFT, MOVE.UP, MOVE.RIGHT, MOVE.NEUTRAL);
        int bestScore = -1;
        MOVE bestMove = MOVE.LEFT;

        // For all possible moves create a game object then copy it.  Run the first 
        // move as a simulation and save in the copy, pr
        for (MOVE m : moves) {
            Game whatIfGame = game.copy();


            whatIfGame.advanceGame(m, ghosts.getMove(whatIfGame, timeDue));
            
            // Compare highest score to current best score and from there determine
            // the best move
            System.out.println(bestMove.toString());

            CurrentGameState freezeGame = new CurrentGameState(whatIfGame,0);
            if (this.dfs_kevin(freezeGame,7) > bestScore) {
                bestScore = dfs_kevin(freezeGame,7);
                bestMove = m;
            }
        }
        return bestMove;
    }
    
    // My implementation of Depth First Search 
    public int dfs_kevin(CurrentGameState current, int maxDepth){
        
        int highestScore = -1;
        List<MOVE> moves = Arrays.asList(MOVE.DOWN, MOVE.LEFT, MOVE.UP, MOVE.RIGHT, MOVE.NEUTRAL);
        
        // Create a Stack and push the current game state onto it 
        Stack<CurrentGameState> S = new Stack<CurrentGameState>();  
        S.push(current);
        

        int i = 0; 
        // While the Stack is not empty, first pop off of the Stack and give it 
        // to new CurrentGameState object
        while(!S.isEmpty()){ 
            
            System.out.println("inside the while loop --> " + i + "times !!");
            i++;
            
            CurrentGameState thisGame = S.pop(); 
            // IF the depth is max that means you've reached the determined depth 
            // and this will be the top score for this path so compare it to the 
            // other scores.
            if(thisGame.depth >= maxDepth && thisGame.current.getScore() >= highestScore)
                highestScore = thisGame.current.getScore();
            
            // ELSE this isn't the case then there are more children and they
            // need to be visited.  For all of the moves create a game
            // object, run the move, create a new currentGameState object
            // save this simulation in it and finally push onto the stack
            else{
                for (MOVE m : thisGame.current.getPossibleMoves(thisGame.current.getPacmanCurrentNodeIndex())) { 
                    Game gameState = thisGame.current.copy();
                    gameState.advanceGame(m,ghosts.getMove(gameState,0));
                    CurrentGameState resultGame = new CurrentGameState(gameState,thisGame.depth+1);
                    S.push(resultGame);
                }
            }                
        }        
        return highestScore; 
    }       
}

