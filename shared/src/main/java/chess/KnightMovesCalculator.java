package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CancellationException;

public class KnightMovesCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();

        ChessPiece myPiece = board.getPiece(myPosition);
        ChessGame.TeamColor myColor = myPiece.getTeamColor();

        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        int[][] combinations = {
                {2,1},{2,-1},
                {1,2},{1,-2},
                {-1,2},{-1,-2},
                {-2,1},{-2,-1}
        };

        int newRow;
        int newCol;
        for (int i=0; i< combinations.length; i++){
            newRow = row + combinations[i][0];
            newCol = col + combinations[i][1];
            ChessPosition newPos = new ChessPosition(newRow, newCol);

            if(newRow>=1 && newRow<=8 && newCol>=1 && newCol<=8){
                if(board.getPiece(newPos) == null){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                if((board.getPiece(newPos) != null && board.getPiece(newPos).getTeamColor() != myColor)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
            }
        }

        return moves;
    }
}