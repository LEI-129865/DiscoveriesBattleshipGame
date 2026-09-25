package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a lógica principal e o estado do jogo.
 * Gere a frota, regista os tiros disparados e mantém estatísticas da partida.
 * 
 * @author fba
 */
public class Game implements IGame {
    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;

    /**
     * Construtor da classe Game.
     * Inicializa as estatísticas a zero e associa a frota ao jogo.
     * 
     * @param fleet A frota de navios associada a este jogo.
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Executa o disparo de um tiro numa determinada posição do tabuleiro.
     * Valida o tiro, verifica se é repetido, regista o acerto num navio e verifica se este afundou.
     * 
     * @param pos A coordenada (posição) onde o tiro é disparado.
     * @return O navio atingido caso o tiro o tenha afundado, ou null caso contrário (tiro na água, navio não afundado ou inválido).
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Obtém a lista de todas as posições onde foram disparados tiros válidos.
     * 
     * @return Uma lista com as posições dos tiros.
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Obtém o número de tiros repetidos disparados durante o jogo.
     * 
     * @return A quantidade de tiros disparados em posições já atacadas.
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Obtém o número de tiros inválidos disparados durante o jogo.
     * 
     * @return A quantidade de tiros disparados fora dos limites do tabuleiro.
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Obtém o número de tiros que atingiram navios com sucesso.
     * 
     * @return A quantidade de tiros certeiros.
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Obtém o número de navios completamente afundados.
     * 
     * @return A quantidade total de navios afundados.
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Obtém o número de navios que ainda não foram afundados (ainda a flutuar).
     * 
     * @return A quantidade de navios restantes na frota.
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Verifica se a posição do tiro está dentro dos limites do tabuleiro.
     * 
     * @param pos A posição do tiro a validar.
     * @return true se a posição for válida, false caso contrário.
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Verifica se a posição indicada já foi alvo de um tiro anterior.
     * 
     * @param pos A posição do tiro a verificar.
     * @return true se já houver um tiro nessa posição, false caso contrário.
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Imprime no terminal a representação visual do tabuleiro, marcando as posições indicadas.
     * 
     * @param positions Lista de posições a serem marcadas no tabuleiro.
     * @param marker    O carácter utilizado para representar as posições marcadas.
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }

    /**
     * Imprime o tabuleiro apresentando apenas os tiros válidos disparados, marcados com 'X'.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Imprime o tabuleiro apresentando o posicionamento de toda a frota, marcada com '#'.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

}
