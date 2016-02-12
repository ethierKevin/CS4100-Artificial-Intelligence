/*
 This controller class is my implementation for the Breadth First Search 
 Algorithm used to navigate Ms. Pacman, through the maze, while avoiding 
 ghosts and in order to reach a high score. 
 */
package pacman.controllers;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import pacman.controllers.examples.StarterGhosts;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/**
 * @author Kevin M. Ethier

UP 	{ public MOVE opposite(){return MOVE.DOWN;	};},	
RIGHT 	{ public MOVE opposite(){return MOVE.LEFT;	};}, 	
DOWN 	{ public MOVE opposite(){return MOVE.UP;		};},		
LEFT 	{ public MOVE opposite(){return MOVE.RIGHT;	};}, 	
NEUTRAL	{ public MOVE opposite(){return MOVE.NEUTRAL;	};};
*/
public class BFS_Controller_Kevin_Ethier extends Controller<MOVE> {
            
    public class CurrentGameState{
        public Game current;
        public int depth;
        
        public CurrentGameState(Game g, int d){
            this.current = g; 
            this.depth = d; 
        }        
    }

    public static StarterGhosts ghosts = new StarterGhosts();
    

    int bestScore = -1;
    MOVE bestMove = MOVE.RIGHT;

    @Override
    public MOVE getMove(Game game, long timeDue) {
        List<MOVE> moves = Arrays.asList(MOVE.DOWN, MOVE.LEFT, MOVE.UP, MOVE.RIGHT, MOVE.NEUTRAL);

        // For all possible moves create a game object then copy it.  Run the first 
        // move as a simulation and save in the copy, pr
        for (MOVE m : moves) {
            Game whatIfGame = game.copy(); 
            CurrentGameState freezeGame = new CurrentGameState(whatIfGame,7);
            whatIfGame.advanceGame(m, ghosts.getMove(game, timeDue));    
            
            // Compare highest score to current best score and from there determine
            // the best move
            System.out.println(bestMove.toString());
            if (this.dfs_kevin(freezeGame) > bestScore) {                
                bestScore = dfs_kevin(freezeGame);
                bestMove = m;
            }
        }
        return bestMove;
    }
    
    // My implementation of Depth First Search 
    public int dfs_kevin(CurrentGameState current){
        
        int highestScore = 0; 
        List<MOVE> moves = Arrays.asList(MOVE.DOWN, MOVE.LEFT, MOVE.UP, MOVE.RIGHT, MOVE.NEUTRAL);
        
        // Create a Stack and push the current game state onto it 
        Stack<CurrentGameState> S = new Stack<CurrentGameState>();  
        S.push(current);
        

        int i = 0; 
        // While the Stack is not empty, first pop off of the Stack and give it 
        // to new CurrentGameState object
        while(!S.isEmpty()){ 
            
            System.out.println("inside the while loop" + i); 
            i++;
            
            CurrentGameState thisGame = S.pop(); 
            // IF the depth is max that means you've reached the determined depth 
            // and this will be the top score for this path so compare it to the 
            // other scores.
            if(thisGame.depth >= current.depth && thisGame.current.getScore() >= highestScore)
                highestScore = thisGame.current.getScore();
            
            // ELSE this isn't the case then there are more children and they
            // need to be visited.  For all of the moves create a game
            // object, run the move, create a new currentGameState object
            // save this simulation in it and finally push onto the stack
            else{
                for (MOVE m : moves) { 
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
