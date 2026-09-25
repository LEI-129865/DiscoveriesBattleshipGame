package iscteiul.ista.battleship;

/**
 * Representa a embarcação Barca no jogo Batalha Naval dos Descobrimentos.
 * Ocupa 1 quadrado (dimensão 1) e cada jogador possui 4 unidades na sua frota.
 */
public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barcas";

    /**
     * Construtor da classe Barge.
     * 
     * @param bearing A orientação da barca (horizontal ou vertical).
     * @param pos     A posição de origem (canto superior esquerdo) da barca no tabuleiro.
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Obtém a dimensão (tamanho) da barca.
     * 
     * @return O número de quadrados que a barca ocupa (1).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}
