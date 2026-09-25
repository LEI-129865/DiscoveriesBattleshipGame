/**
 * Representa as propriedades e ações associadas a uma coordenada no tabuleiro do jogo.
 */
package iscteiul.ista.battleship;

/**
 * @author fba
 */
public interface IPosition {
    
    /**
     * Obtém o índice da linha da posição.
     * 
     * @return o número da linha
     */
    int getRow();

    /**
     * Obtém o índice da coluna da posição.
     * 
     * @return o número da coluna
     */
    int getColumn();

    /**
     * Compara se esta posição é igual a outra (mesma linha e coluna).
     * 
     * @param other o objeto a comparar
     * @return {@code true} se as posições forem iguais, caso contrário {@code false}
     */
    boolean equals(Object other);

    /**
     * Verifica se esta posição é adjacente (incluindo diagonais) a uma outra posição.
     * 
     * @param other a posição com a qual será testada a adjacência
     * @return {@code true} se for adjacente, caso contrário {@code false}
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marca esta posição como estando ocupada por um navio.
     */
    void occupy();

    /**
     * Regista um disparo sobre esta posição.
     */
    void shoot();

    /**
     * Verifica se existe algum navio a ocupar esta posição.
     * 
     * @return {@code true} se estiver ocupada, caso contrário {@code false}
     */
    boolean isOccupied();

    /**
     * Verifica se esta posição já foi alvo de um disparo.
     * 
     * @return {@code true} se já foi atingida, caso contrário {@code false}
     */
    boolean isHit();
}