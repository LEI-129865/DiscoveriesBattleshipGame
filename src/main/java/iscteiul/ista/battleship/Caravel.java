package iscteiul.ista.battleship;

/**
 * Representa a embarcação Caravela no jogo Batalha Naval dos Descobrimentos.
 * Ocupa 2 quadrados (dimensão 2) e cada jogador possui 3 unidades na sua frota.
 */
public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Construtor da classe Caravel.
     * Calcula e adiciona as posições ocupadas pela caravela no tabuleiro com base na sua orientação.
     * 
     * @param bearing A orientação para a qual a caravela aponta (Norte, Sul, Este ou Oeste).
     * @param pos     A posição inicial (de referência) para o posicionamento da caravela.
     * @throws NullPointerException     Se a orientação (bearing) fornecida for nula.
     * @throws IllegalArgumentException Se a orientação (bearing) fornecida não for reconhecida.
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /**
     * Obtém a dimensão (tamanho) da caravela.
     * 
     * @return O número de quadrados que a caravela ocupa (2).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
