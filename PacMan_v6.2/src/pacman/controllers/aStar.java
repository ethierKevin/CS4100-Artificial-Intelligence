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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pacman.controllers.examples.StarterGhosts;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/**
 * @author Kevin M. Ethier
 *
 * UP { public MOVE opposite(){return MOVE.DOWN;	};}, RIGHT { public MOVE
 * opposite(){return MOVE.LEFT;	};}, DOWN { public MOVE opposite(){return
 * MOVE.UP;	};}, LEFT { public MOVE opposite(){return MOVE.RIGHT;	};}, NEUTRAL	{
 * public MOVE opposite(){return MOVE.NEUTRAL;	};};
 */
public class AStarController_Kevin_Ethier extends Controller<MOVE> {

    public class CurrentNode {

        public Game current;
        public int f, g, h;

        public CurrentNode(){}; 
        public CurrentNode(Game g, int d) {
            this.current = g;
        }
    }

    public static StarterGhosts ghosts = new StarterGhosts();

    int bestScore = -1;
    MOVE bestMove = MOVE.LEFT;

    @Override
    public MOVE getMove(Game game, long timeDue) {

        List<MOVE> moves = Arrays.asList(MOVE.DOWN, MOVE.LEFT, MOVE.UP, MOVE.RIGHT, MOVE.NEUTRAL);

        // For all possible moves create a game object then copy it.  Run the first 
        // move as a simulation and save in the copy, pr
        for (MOVE m : moves) {
            Game whatIfGame = game.copy();
            CurrentNode freezeGame = new CurrentNode(whatIfGame, 7);
            whatIfGame.advanceGame(m, ghosts.getMove(game, timeDue));

        }

        return MOVE.DOWN;
    }

    public void aStar_KevinEthier(int start, int goal, CurrentNode n) {

        // The set of nodes already evaluated.
        ArrayList<CurrentNode> closedList = new ArrayList<CurrentNode>();
        ArrayList<CurrentNode> openList = new ArrayList<CurrentNode>();
        openList.add(n);
        //while the open list is not empty
        //find the node with the least f on the open list, call it "q"
        while (!openList.isEmpty()) {
            int lowestF = -1; 
            if(n.f < lowestF){
                lowestF = n.f;
                lowestIndex = n.
                }                 
        }
        //pop q off the open list
        for(int i = 0; i < openList.size(); i++){
            if((openList.get(i)).f == lowestF)
        }
    }
    /*
    
    
    generate q's 8 successors and set their parents to q
    for each successor
    	if successor is the goal, stop the search
        successor.g = q.g + distance between successor and q
        successor.h = distance from goal to successor
        successor.f = successor.g + successor.h

        if a node with the same position as successor is in the OPEN list \
            which has a lower f than successor, skip this successor
        if a node with the same position as successor is in the CLOSED list \ 
            which has a lower f than successor, skip this successor
        otherwise, add the node to the open list
    end
    push q on the closed list
end
}
     */

}
