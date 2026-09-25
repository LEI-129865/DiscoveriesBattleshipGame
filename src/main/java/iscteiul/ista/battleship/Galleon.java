package iscteiul.ista.battleship;

/**
 * Representa a embarcação Galeão no jogo Batalha Naval dos Descobrimentos.
 * Ocupa 5 quadrados (dimensão 5) e cada jogador possui 1 unidade na sua frota.
 */
public class Galleon extends Ship {
    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";

    /**
     * Construtor da classe Galleon.
     * Calcula e adiciona as posições ocupadas pelo galeão no tabuleiro com base na sua orientação.
     * 
     * @param bearing A orientação para a qual o galeão aponta (Norte, Sul, Este ou Oeste).
     * @param pos     A posição inicial (de referência) para o posicionamento do galeão.
     * @throws IllegalArgumentException Se a orientação (bearing) fornecida não for reconhecida.
     * @throws NullPointerException     Se a orientação (bearing) fornecida for nula.
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * Obtém a dimensão (tamanho) do galeão.
     * 
     * @return O número de quadrados que o galeão ocupa (5).
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Preenche as posições do galeão quando orientado para Norte.
     * 
     * @param pos A posição de referência.
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Preenche as posições do galeão quando orientado para Sul.
     * 
     * @param pos A posição de referência.
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Preenche as posições do galeão quando orientado para Este.
     * 
     * @param pos A posição de referência.
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Preenche as posições do galeão quando orientado para Oeste.
     * 
     * @param pos A posição de referência.
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
