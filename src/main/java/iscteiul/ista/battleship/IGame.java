/**
 * Define o contrato para o controlo e acompanhamento de uma partida de Batalha Naval.
 * Permite a execução de disparos, obtenção de estatísticas e visualização do estado do jogo.
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IGame {
    
    /**
     * Efetua um disparo na posição especificada do tabuleiro.
     * 
     * @param pos a posição alvo do disparo
     * @return o navio atingido ({@link IShip}), ou {@code null} caso seja um tiro na água
     */
    IShip fire(IPosition pos);

    /**
     * Obtém o histórico de todos os disparos efetuados.
     * 
     * @return uma lista com as posições ({@link IPosition}) onde foram realizados tiros
     */
    List<IPosition> getShots();

    /**
     * Devolve o número total de disparos repetidos (tiros dados numa posição previamente atingida).
     * 
     * @return o total de tiros repetidos
     */
    int getRepeatedShots();

    /**
     * Devolve o número total de disparos inválidos (ex: tiros fora dos limites do tabuleiro).
     * 
     * @return o total de tiros inválidos
     */
    int getInvalidShots();

    /**
     * Devolve o número total de tiros certeiros (que atingiram um navio).
     * 
     * @return o total de acertos
     */
    int getHits();

    /**
     * Devolve o número de navios da frota adversária que já foram totalmente afundados.
     * 
     * @return o número de navios afundados
     */
    int getSunkShips();

    /**
     * Devolve o número de navios da frota adversária que ainda continuam a flutuar.
     * 
     * @return o número de navios restantes
     */
    int getRemainingShips();

    /**
     * Imprime na consola o registo de todos os tiros considerados válidos.
     */
    void printValidShots();

    /**
     * Imprime na consola a disposição atual da frota no tabuleiro.
     */
    void printFleet();
}