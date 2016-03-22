
/**
 *
 * @author Kevin M. Ethier
 */
/*
This is my implementation for homeworkThree and I'm applying a genetic algorithm
to the PacMan game. 
 */

package pacman.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class geneticAlgoController extends Controller<MOVE> {
            
     public class GameNode{
        public Game current;
        
        public GameNode(){}
        public GameNode(Game g){
            this.current = g; 
        }               
    }
    
    // class used to represent evaluated solutions 
    public class Chromosome{
        Game current;
        int[] attributes = new int[10]; 
        
        public Chromosome(){}
        public Chromosome(Game g){
            this.current = g; 
            this.setAttributes();
        }     
        
         // fitness function for the genetic algorithm
        public void setAttributes(){
            attributes[0] = current.getNumberOfActivePills(); 
            attributes[1] = current.getNumberOfPills(); 
            attributes[2] = current.getNumberOfPowerPills();
            attributes[3] = current.getNumGhostsEaten(); 
            attributes[4] = current.getScore(); 
            attributes[5] = current.getGhostCurrentEdibleScore();
            attributes[6] = current.getPacmanNumberOfLivesRemaining(); 
        }
          
    }
    // initialize the ghosts
    public static StarterGhosts ghosts = new StarterGhosts();

    // Overridden method required for PacMan Controller
    // returns the best move. 
    @Override
    public MOVE getMove(Game game, long timeDue) {
        
        List<MOVE> moves = Arrays.asList(MOVE.DOWN, MOVE.LEFT, MOVE.UP, MOVE.RIGHT, MOVE.NEUTRAL);

        // For all possible solutions create a gameNode object then 
        // evaluate with the fitness function and finally store into 
        // solutionList
        for (MOVE m: moves){
            Game gameCopy = game.copy();
            Game currentGame = gameCopy; 
            currentGame.advanceGame(m, ghosts.getMove(currentGame, timeDue));
            Chromosome solution =  new Chromosome(currentGame);
            List solutionList = new ArrayList();
            solutionList.add(solution);            
        }
        
        return MOVE.DOWN ;
    }
           
}

