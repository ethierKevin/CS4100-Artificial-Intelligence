
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
//        boolean[] attributes = new boolean[10]; 
        ArrayList moves = new ArrayList(); 
        
        public Chromosome(){}
        public Chromosome(Game g){
            this.current = g; 
//            this.setAttributes();
        }     
        
        public ArrayList returnMoves(int index){
            ArrayList sequence = new ArrayList(); 
            for (int i = 0; i < 10; i++){
                sequence = (ArrayList)this.moves.get(index);
            }
            return sequence; 
        }
        
         // function to set attributes to be used later in the fitness 
	 // function
//        public void setAttributes(){
//            attributes[0] = (current.getNumberOfActivePills() != 0); 
//            attributes[1] = (current.getNumberOfPills() != 0); 
//            attributes[2] = (current.getNumberOfPowerPills() != 0); 
//            attributes[3] = (current.getNumGhostsEaten() != 0); 
//            attributes[4] = (current.getScore() != 0);  
//            attributes[5] = (current.getGhostCurrentEdibleScore() != 0); 
//            attributes[6] = (current.getPacmanNumberOfLivesRemaining() != 0);  
//        }          
    }
    // initialize the ghosts
    public static StarterGhosts ghosts = new StarterGhosts();

    // Overridden method required for PacMan Controller
    // returns the best move. 
    @Override
    public MOVE getMove(Game game, long timeDue) {
        
        // Create array list of all moves then another list to store random 
        // moves and give them a Chromosome object 
        List<MOVE> moves = Arrays.asList(MOVE.DOWN, MOVE.LEFT, MOVE.UP, MOVE.RIGHT, MOVE.NEUTRAL);
        ArrayList moveSequence = new ArrayList();
        Chromosome solutions = new Chromosome();

            
    for(int k = 0; k < 6; k++){
        for (int i = 0; i < 10; i++){
            Random generator = new Random(); 
            int n = generator.nextInt(5) + 1;
            moveSequence.add(moves.get(n));
        }
        solutions.moves.add(moveSequence);
        moveSequence.clear();
    }
    
    for (int j = 0; j < 6; j++){
        moveSequence = solutions.returnMoves(j); 
       
        for (int l = 0; l < solutions.moves.size(); l++){
             // copy game state and store in gamecopy var
            Game gameCopy = game.copy();
            Game currentGame = gameCopy; 
	    // advance game with next move in the move list
            currentGame.advanceGame(moveSequence.get(l) , ghosts.getMove(currentGame, timeDue));
            MOVE bestMove = fitnessFunction(solutions); 
        }
    }    
        return bestMove ;
    }
    
    
    public MOVE fitnessFunction(Chromosome s){
        // mutate and crossover code here 
        return null; 
    }
           
}


