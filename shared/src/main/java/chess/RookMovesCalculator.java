package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CancellationException;

public class RookMovesCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();

        ChessPiece myPiece = board.getPiece(myPosition);
        ChessGame.TeamColor myColor = myPiece.getTeamColor();

        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        int[][] combinations = {
                {1,0},{-1,0},
                {0,1},{0,-1}
        };

        int newRow;
        int newCol;
        for (int i=0; i< combinations.length; i++){
            newRow = row + combinations[i][0];
            newCol = col + combinations[i][1];
            while(newRow>=1 && newRow<=8 && newCol>=1 && newCol<=8) {
                ChessPosition newPos = new ChessPosition(newRow, newCol);
                if (board.getPiece(newPos) == null) {
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                else if ((board.getPiece(newPos) != null && board.getPiece(newPos).getTeamColor() != myColor)) {
                    moves.add(new ChessMove(myPosition, newPos, null));
                    break;
                }
                else{
                    break;
                }
                newRow += combinations[i][0];
                newCol += combinations[i][1];
            }
        }
        return moves;
    }
}