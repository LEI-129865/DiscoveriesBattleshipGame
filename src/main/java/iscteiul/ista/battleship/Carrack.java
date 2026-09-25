package iscteiul.ista.battleship;

/**
 * Representa a embarcação Nau (Carrack) no jogo Batalha Naval dos Descobrimentos.
 * Ocupa 3 quadrados (dimensão 3) e cada jogador possui 2 unidades na sua frota.
 */
public class Carrack extends Ship {
    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Construtor da classe Carrack.
     * Calcula e adiciona as posições ocupadas pela nau no tabuleiro com base na sua orientação.
     * 
     * @param bearing A orientação para a qual a nau aponta (Norte, Sul, Este ou Oeste).
     * @param pos     A posição inicial (de referência) para o posicionamento da nau.
     * @throws IllegalArgumentException Se a orientação (bearing) fornecida não for reconhecida.
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Obtém a dimensão (tamanho) da nau.
     * 
     * @return O número de quadrados que a nau ocupa (3).
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
