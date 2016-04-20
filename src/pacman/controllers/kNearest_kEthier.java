
/**
 *
 * @author Kevin M. Ethier
 */
/*
This is my implementation for K-Nearest Neighbor to navigate the Pac-man 
controller through the maze. 
 */

package pacman.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import pacman.controllers.examples.StarterGhosts;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/**
 * @author Kevin M. Ethier

UP      { public MOVE opposite(){return MOVE.DOWN;  };},    
RIGHT   { public MOVE opposite(){return MOVE.LEFT;  };},    
DOWN    { public MOVE opposite(){return MOVE.UP;        };},        
LEFT    { public MOVE opposite(){return MOVE.RIGHT; };},    
NEUTRAL { public MOVE opposite(){return MOVE.NEUTRAL;   };};
*/
public class kNearest_kEthier extends Controller<MOVE> {
            
    // game node object used to copy game states for subsequent copies
    public class GameNode{

        public Game current;
        public GameNode(){}
        public GameNode(Game g){
            this.current = g; 
        }               
    }

    public class Neighbor{
        public Neighbor(){}
        public Neighbor(ArrayList m){
            this.moves = m; 
        }
        public ArrayList moves;
        int euclideanScore; 
    }
    
    // initialize the ghosts
    public static StarterGhosts ghosts = new StarterGhosts();

    // Overridden method required for PacMan Controller
    // returns the best move. 
    @Override
    public MOVE getMove(Game game, long timeDue) {
        
        // Create array list of all moves and then store in a List 
        List<MOVE> moves = Arrays.asList(MOVE.DOWN, MOVE.LEFT, MOVE.UP, MOVE.RIGHT, MOVE.NEUTRAL);
        int k = 15; 
        ArrayList moveSequence = new ArrayList(k);
        
        // populate vectors with all of a single move to obtain 
        // vector samples in all directons
        for (int j = 0; j < 5; j++{
            for (int i = 0; i < k; i++){
                moveSequence[i] = moves[j];
            }               
            j++;
            GameNode population = new GameNode(moveSequence);
            population.euclideanScore = currentGame.getEuclideanDistance(0,15);
        }

        // create game state copies each holding a result
        // of what happens with each sample vector            
        for (int i = 0; i < population.moves.size(); i++){

            Game gameCopy = game.copy();
            Game currentGame = gameCopy;
   
        }
        // initialize arraylist to hold all of the vectors containing outcomes
        ArrayList neighborhood = new ArrayList(); 

        // advance game with next move in the move list
        for (int i = 0; i < )
            currentGame.advanceGame(moveSequence.get(l) , ghosts.getMove(currentGame, timeDue));
            MOVE bestMove = kNearest(solutions); 
        }
    }            
        return bestMove ;
    }

}
           



