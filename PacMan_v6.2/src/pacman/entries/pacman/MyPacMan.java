package pacman.entries.pacman;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import static pacman.controllers.BFS_Controller.ghosts;
import pacman.controllers.Controller;
import pacman.controllers.PacManNode;
import pacman.game.Constants;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */

 /*public enum MOVE 
	{
		UP 	{ public MOVE opposite(){return MOVE.DOWN;		};},	
		RIGHT 	{ public MOVE opposite(){return MOVE.LEFT;		};}, 	
		DOWN 	{ public MOVE opposite(){return MOVE.UP;		};},		
		LEFT 	{ public MOVE opposite(){return MOVE.RIGHT;		};}, 	
		NEUTRAL	{ public MOVE opposite(){return MOVE.NEUTRAL;	};};	
		
		public abstract MOVE opposite();
	};*/
public class MyPacMan extends Controller<MOVE> {

    private static final int MIN_DISTANCE = 10;
    private final MOVE myMove = MOVE.NEUTRAL;

    @Override
    public MOVE getMove(Game game, long timeDue) {
        Random rnd=new Random();
            MOVE[] allMoves=MOVE.values();
        
            int highScore = -1;
            MOVE highMove = null;
            
           
            for(MOVE m: allMoves)
            {
                //System.out.println("Trying Move: " + m);
                Game gameCopy = game.copy();
                Game gameAtM = gameCopy;
                gameAtM.advanceGame(m, ghosts.getMove(gameAtM, timeDue));
                int tempHighScore = -1;
                
                if(highScore < tempHighScore)
                {
                    highScore = tempHighScore;
                    highMove = m;
                }
                
                System.out.println("Trying Move: " + m + ", Score: " + tempHighScore);
               
            }
            
            System.out.println("High Score: " + highScore + ", High Move:" + highMove);
              return highMove;
                
	}
    
    
    public int dfs_kevin(PacManNode rootGameState, int maxDepth){
     
            MOVE[] allMoves=Constants.MOVE.values();
            int depth = 0;
            int highScore = -1;
            
            Deque<PacManNode> stack = new ArrayDeque<PacManNode>();
            
        return 0;
    }

    /*
        BFS
          unmark all vertices
          choose some starting vertex x
          mark x
            list L = x
            tree T = x
            while L nonempty
            choose some vertex v from front of list
            visit v
            for each unmarked neighbor w
        mark w
        add it to end of list
        add edge vw to T

        
        
        function SIMPLE-PROBLEM-SOLVING-AGENT(percept) returns an action persistent: seq, an action sequence, initially empty
        state, some description of the current world state goal, a goal, initially null
        problem, a problem formulation
        state ← UPDATE-STATE(state,percept) if seq is empty then
        goal ← FORMULATE-GOAL(state)
        problem ←FORMULATE-PROBLEM(state,goal) seq ← SEARCH(problem)
        if seq = failure then return a null action
        action ← FIRST(seq) seq ← REST(seq) return action
     */
}
