/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.controllers;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import pacman.controllers.examples.StarterGhosts;
import pacman.game.Constants;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/**
 *
 * @author amy
 */
public class BFS_Controller extends Controller<MOVE>{


        
        /*public enum MOVE 
	{
		UP 	{ public MOVE opposite(){return MOVE.DOWN;		};},	
		RIGHT 	{ public MOVE opposite(){return MOVE.LEFT;		};}, 	
		DOWN 	{ public MOVE opposite(){return MOVE.UP;                };},		
		LEFT 	{ public MOVE opposite(){return MOVE.RIGHT;		};}, 	
		NEUTRAL	{ public MOVE opposite(){return MOVE.NEUTRAL;           };};	
		
		public abstract MOVE opposite();
	};*/

	

    public static StarterGhosts ghosts = new StarterGhosts();
        @Override
	public MOVE getMove(Game game,long timeDue)
	{
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
                int tempHighScore = this.bfs_amy(new PacManNode(gameAtM, 0), 7);
                
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
        
        public int bfs_amy(PacManNode rootGameState, int maxdepth)
	{
            // create list of all moves, initialize depth & high score vars
            MOVE[] allMoves=Constants.MOVE.values();
            int depth = 0;
            int highScore = -1;
            
            // create data structure and add this game copy to it 
            Queue<PacManNode> queue = new LinkedList<PacManNode>();         
            queue.add(rootGameState);                                       

            //System.out.println("Adding Node at Depth: " + rootGameState.depth);
                
            // while the queue isn't empty
            while(!queue.isEmpty())                                         
                {
                    // give bottom state to pmnode
                    PacManNode pmnode = queue.remove();                     
                    //System.out.println("Removing Node at Depth: " + pmnode.depth);
                    
                    // if the depth is max that means you've reached the determined depth 
                    // and this will be the top score for this path so compare it to the 
                    // other scores. 
                    if(pmnode.depth >= maxdepth)                            
                    {
                        int score = pmnode.gameState.getScore();
                         if (highScore < score)
                                  highScore = score;
                    }
                    // if this isn't the case then there are more children and they
                    // need to be visited
                    else
                    {

                        //GET CHILDREN
                        for(MOVE m: allMoves)
                        {
                            // Create game copy
                            Game gameCopy = pmnode.gameState.copy();
                            
                            // in this copy try the next move in the order
                            gameCopy.advanceGame(m, ghosts.getMove(gameCopy, 0));
                            
                            // take this simulation in the game copy and put inside
                            // of a gameCopyObject, increment the depth and finally
                            // add to the data structure
                            PacManNode node = new PacManNode(gameCopy, pmnode.depth+1);
                            queue.add(node);
                        }
                    }

		}
                
                return highScore;
	}
        
    
}
