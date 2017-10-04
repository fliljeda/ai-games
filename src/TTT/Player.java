import java.util.*;

public class Player {
    final int MAX_DEPTH = 5;
    GameState bestpossiblestate = null;

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
        minimax(gameState, gameState.getNextPlayer(), 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if(bestpossiblestate != null){
            return bestpossiblestate;
        }else{
            return gameState;
        }

        /**
         * Here you should write your algorithms to get the best next move, i.e.
         * the best next state. This skeleton returns a random move instead.
         */
        // Random random = new Random();
        // return nextStates.elementAt(random.nextInt(nextStates.size()));
    }

    public int minimax(GameState gameState, int player,
                    int currentDepth, int alpha, int beta){

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
                //PRUNING?

                int v = minimax(nextstate, nextstate.getNextPlayer(), currentDepth, alpha, beta);
                if(bestpossible < v && currentDepth == 1){
                    bestpossiblestate = nextstate;
                }
                bestpossible = bestpossible > v ? bestpossible : v;
                alpha = alpha > bestpossible ? alpha : bestpossible;
                if(beta <= alpha){
                    return bestpossible;
                }
            }
            return bestpossible;
        }else{
            int bestpossible = Integer.MAX_VALUE;
            for(GameState nextstate : nextStates){

                int v = minimax(nextstate, nextstate.getNextPlayer(), currentDepth, alpha, beta);
                if(bestpossible > v && currentDepth == 1){
                    bestpossiblestate = nextstate;
                }
                bestpossible = bestpossible < v ? bestpossible : v;
                beta = beta < bestpossible ? beta : bestpossible;
                if(beta <= alpha){
                    return bestpossible;
                }
            }
            return bestpossible;
        }
    }
}
