package iscteiul.ista.battleship;

/**
 * Representa a embarcação Fragata no jogo Batalha Naval dos Descobrimentos.
 * Ocupa 4 quadrados (dimensão 4) e cada jogador possui 1 unidade na sua frota.
 */
public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Construtor da classe Frigate.
     * Calcula e adiciona as posições ocupadas pela fragata no tabuleiro com base na sua orientação.
     * 
     * @param bearing A orientação para a qual a fragata aponta (Norte, Sul, Este ou Oeste).
     * @param pos     A posição inicial (de referência) para o posicionamento da fragata.
     * @throws IllegalArgumentException Se a orientação (bearing) fornecida não for reconhecida.
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the frigate");
        }
    }

    /**
     * Obtém a dimensão (tamanho) da fragata.
     * 
     * @return O número de quadrados que a fragata ocupa (4).
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}
