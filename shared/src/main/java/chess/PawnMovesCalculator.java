package chess;

import java.util.ArrayList;
import java.util.Collection;

import static chess.ChessGame.TeamColor.*;
import static chess.ChessPiece.PieceType.*;

public class PawnMovesCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();

        ChessPiece myPiece = board.getPiece(myPosition);
        ChessGame.TeamColor myColor = myPiece.getTeamColor();

        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        if (myColor == BLACK) {
            ChessPosition forwardMove = new ChessPosition(row-1,col);
            ChessPosition attackRight = new ChessPosition(row-1,col+1);
            ChessPosition attackLeft = new ChessPosition(row-1,col-1);
            if(row > 2){
                if(board.getPiece(forwardMove)==null){
                    moves.add(new ChessMove(myPosition,forwardMove,null));
                    if(row ==7){
                        ChessPosition moveTwo = new ChessPosition(row-2,col);
                        if(board.getPiece(moveTwo)==null) {
                            moves.add(new ChessMove(myPosition, moveTwo, null));
                        }
                    }
                }
                if(col+1 <=8 && board.getPiece(attackRight) !=null){
                    if(board.getPiece(attackRight).getTeamColor()!=myColor){
                        moves.add(new ChessMove(myPosition,attackRight,null));
                    }
                }
                if(col-1 >=1 && board.getPiece(attackLeft)!=null){
                    if(board.getPiece(attackLeft).getTeamColor()!=myColor){
                        moves.add(new ChessMove(myPosition,attackLeft,null));
                    }
                }
            }
            if(row==2){
                if(board.getPiece(forwardMove)==null){
                    promote(myPosition, moves, forwardMove);
                }
                if(col+1 <=8 && board.getPiece(attackRight) !=null){
                    if(board.getPiece(attackRight).getTeamColor()!=myColor){
                        promote(myPosition, moves, attackRight);
                    }
                }
                if(col-1 >=1 && board.getPiece(attackLeft)!=null){
                    if(board.getPiece(attackLeft).getTeamColor()!=myColor){
                        promote(myPosition, moves, attackLeft);
                    }
                }
            }
        }
        else {
            ChessPosition forwardMove = new ChessPosition(row+1,col);
            ChessPosition attackRight = new ChessPosition(row+1,col+1);
            ChessPosition attackLeft = new ChessPosition(row+1,col-1);
            if(row < 7){
                if(board.getPiece(forwardMove)==null){
                    moves.add(new ChessMove(myPosition,forwardMove,null));
                    if(row ==2){
                        ChessPosition moveTwo = new ChessPosition(row+2,col);
                        if(board.getPiece(moveTwo)==null) {
                            moves.add(new ChessMove(myPosition, moveTwo, null));
                        }
                    }
                }
                if(col+1 <=8 && board.getPiece(attackRight) !=null){
                    if(board.getPiece(attackRight).getTeamColor()!=myColor){
                        moves.add(new ChessMove(myPosition,attackRight,null));
                    }
                }
                if(col-1 >=1 && board.getPiece(attackLeft)!=null){
                    if(board.getPiece(attackLeft).getTeamColor()!=myColor){
                        moves.add(new ChessMove(myPosition,attackLeft,null));
                    }
                }
            }
            if(row==7){
                if(board.getPiece(forwardMove)==null){
                    promote(myPosition, moves, forwardMove);
                }
                if(col+1 <=8 && board.getPiece(attackRight) !=null){
                    if(board.getPiece(attackRight).getTeamColor()!=myColor){
                        promote(myPosition, moves, attackRight);
                    }
                }
                if(col-1 >=1 && board.getPiece(attackLeft)!=null){
                    if(board.getPiece(attackLeft).getTeamColor()!=myColor){
                        promote(myPosition, moves, attackLeft);
                    }
                }
            }
        }
        return moves;
    }

    private static void promote(ChessPosition myPosition, Collection<ChessMove> moves, ChessPosition move) {
        moves.add(new ChessMove(myPosition, move,KNIGHT));
        moves.add(new ChessMove(myPosition, move,ROOK));
        moves.add(new ChessMove(myPosition, move,BISHOP));
        moves.add(new ChessMove(myPosition, move,QUEEN));
    }
}
