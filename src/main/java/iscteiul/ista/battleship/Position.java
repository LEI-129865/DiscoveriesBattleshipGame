/**
 * Implementação concreta de uma posição bidimensional no tabuleiro.
 */
package iscteiul.ista.battleship;

import java.util.Objects;

public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * Construtor da classe Position.
     * Inicializa a posição com a linha e coluna indicadas, definindo os estados
     * de ocupação e de disparo como falsos por omissão.
     * 
     * @param row    a linha do tabuleiro
     * @param column a coluna do tabuleiro
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Calcula o código de dispersão (hash) com base nos atributos da posição.
     * 
     * @return o código hash gerado
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Devolve uma representação em formato de texto das coordenadas da posição.
     * 
     * @return uma string contendo a linha e a coluna
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }
}