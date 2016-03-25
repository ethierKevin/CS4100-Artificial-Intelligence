/
*
 *
 * @author Kevin M. Ethier
 */
/*
 This controller class is my implementation for the aStar 
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
 */
public class AStarController_Kevin_Ethier extends Controller<MOVE> {

    public class CurrentNode {

        public Game current;
        public CurrentNode parent;
        public int f, g, h;

        public CurrentNode() {
        }

        ;

        public CurrentNode(Game g) {
            this.current = g;
        }
    }

    public static StarterGhosts ghosts = new StarterGhosts();

    // required method to be overwritten but actual algorithm is implemented 
    // in aStar_KevinEthier()
    @Override
    public MOVE getMove(Game game, long timeDue) {
        aStar_KevinEthier(game);
        return MOVE.DOWN;
    }

    public void aStar_KevinEthier(Game game) {

        int start, goal;
        goal = 0;
        List<MOVE> moves = Arrays.asList(MOVE.DOWN, MOVE.LEFT, MOVE.UP, MOVE.RIGHT, MOVE.NEUTRAL);
        // Create a game state copy
        Game whatIfGame = game.copy();
        CurrentNode freezeGame = new CurrentNode(whatIfGame);

        // The set of nodes already evaluated.
        ArrayList<CurrentNode> closedList = new ArrayList<CurrentNode>();
        ArrayList<CurrentNode> openList = new ArrayList<CurrentNode>();
        openList.add(freezeGame);

        //while the open list is not empty
        //find the node with the least f on the open list, call it "q"
        while (!openList.isEmpty()) {
            int lowestF = -1;
            if (freezeGame.f < lowestF) {
                lowestF = freezeGame.f;
//                lowestIndex = n.
            }
            //pop q off the open list
            for (int i = 0; i < openList.size(); i++) {
                if ((openList.get(i)).f == lowestF) ;
            }
            // generate q's successors and set their parents to q
            for (MOVE move : moves) {
                whatIfGame = freezeGame.current.copy();
                whatIfGame.advanceGame(move, ghosts.getMove(whatIfGame, 0));

                CurrentNode successor = new CurrentNode();
                successor.parent = freezeGame;
                successor.g = whatIfGame.getNumberOfActivePills() - goal;
                successor.h = this.game.getNumberOfActivePills() - goal;
                successor.f = successor.g + successor.h;


                //if a node with the same position as successor is in the OPEN list \
                //which has a lower f than successor, skip this successor
                boolean containsF = false;
                int fIndex = -1;
                for (int i = 0; i < openList.size(); i++) {
                    if (openList.get(i).f <= successor.f) {
                        fIndex = i;
                    }
                    continue;

                    //if a node with the same position as successor is in the CLOSED list \
                    //which has a lower f than successor, skip this successor
                    for (int k = 0; i < openList.size(); i++) {
                        if (openList.get(i).f == successor.f) {
                            fIndex = k;
                        }
                    }
                    //otherwise, add the node to the open list
                    openList.add(successor);
                }
                closedList.add(freezeGame);
            }
        }
    }
}
