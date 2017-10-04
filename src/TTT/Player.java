import java.util.*;

public class Player {
    final int MAX_DEPTH = 3;

    /**
     * Performs a move
     *
     * @param gameState
     *            the current state of the board
     * @param deadline
     *            time before which we must have returned
     * @return the next state the board is in after our move
     */

    public GameState play(final GameState gameState, final Deadline deadline) {
        Vector<GameState> nextStates = new Vector<GameState>();
        gameState.findPossibleMoves(nextStates);
        GameState bestpossiblestate = null;
        minimax(gameState, gameState.getNextPlayer(), bestpossiblestate, 0);
        return bestpossiblestate;

        /**
         * Here you should write your algorithms to get the best next move, i.e.
         * the best next state. This skeleton returns a random move instead.
         */
        // Random random = new Random();
        // return nextStates.elementAt(random.nextInt(nextStates.size()));
    }

    public int minimax(GameState gameState, int player, GameState bestpossiblestate,
                    int currentDepth){

        Vector<GameState> nextStates = new Vector<GameState>();
        gameState.findPossibleMoves(nextStates);

        if (nextStates.size() == 0 || ++currentDepth == MAX_DEPTH) {
            // Must play "pass" move if there are no other moves possible.
            //return new GameState(gameState, new Move());
            if(gameState.isXWin()){
                return 1;
            }else if(gameState.isOWin()){
                return -1;
            }else{
                return 0;
            }
        }


        if(player == 1){ //player A
            int bestpossible = Integer.MIN_VALUE;
            for(GameState nextstate : nextStates){
                int v = minimax(nextstate, nextstate.getNextPlayer(), null, currentDepth);
                if(v > bestpossible){
                    bestpossiblestate = nextstate;
                    bestpossible = v;
                }
            }
            return bestpossible;
        }else{
            int bestpossible = Integer.MAX_VALUE;
            for(GameState nextstate : nextStates){
                int v = minimax(nextstate, nextstate.getNextPlayer(), null, currentDepth);
                if(v < bestpossible){
                    bestpossiblestate = nextstate;
                    bestpossible = v;
                }
            }
            return bestpossible;
        }
    }
}
