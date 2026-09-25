/**
 * Classe utilitária responsável pela simulação interativa de tarefas e testes do jogo.
 * Serve como ambiente de teste para operações como leitura de navios, montagem da frota, e gestão de rondas de tiros.
 */
package iscteiul.ista.battleship;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tasks {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final int NUMBER_SHOTS = 3;

    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /**
     * Comandos disponíveis para o utilizador introduzir na consola
     */
    private static final String NOVAFROTA = "nova";
    private static final String DESISTIR = "desisto";
    private static final String RAJADA = "rajada";
    private static final String VERTIROS = "ver";
    private static final String BATOTA = "mapa";
    private static final String STATUS = "estado";


    /////////////////////////////////////////////////////////////////////////////
    // Daqui em diante, encontra-se código que pode ser convertido em testes automáticos,
    // desde que sejam feitas as devidas alterações. Isto também demonstra que devemos
    // desenvolver o nosso código incrementalmente, ex: primeiro os navios, depois a frota,
    // em seguida a verificação de regras, lidar com disparos, e assim por diante.
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Testa a construção individual de navios: Para cada navio lido a partir dos 
     * dados de entrada (input), lê várias posições e indica se o navio ocupa ou não cada uma delas.
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * Testa o processo de inicialização e construção de uma frota completa.
     * Responde apenas aos comandos de criar frota, mostrar o estado e desistir.
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Testa a construção de uma frota, introduzindo a capacidade de ver o mapa na íntegra ("batota").
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Testa a simulação completa de combate, processando rondas de disparos (rajadas),
     * monitorizando acertos e analisando o estado final do jogo.
     */
    public static void taskD() {

        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());
                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Constrói uma frota processando as instruções passadas pelo utilizador.
     * 
     * @param in o analisador (Scanner) para ler os dados da entrada-padrão
     * @return a frota construída
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0; // i representa o total de navios criados com sucesso

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * Lê a informação de um navio a partir dos dados introduzidos pelo utilizador
     * e instancia o objeto correspondente.
     * 
     * @param in o analisador (Scanner) de onde os dados serão lidos
     * @return o navio (Ship) criado com base nos dados fornecidos
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * Lê uma posição do mapa a partir dos dados do utilizador (linha e coluna).
     * 
     * @param in o analisador (Scanner) de onde as coordenadas serão lidas
     * @return a posição no tabuleiro que foi lida
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * Executa uma ronda (rajada) de tiros contra uma frota no contexto de uma partida de jogo.
     * Por omissão, lê um número de posições definido pela constante NUMBER_SHOTS (3).
     * 
     * @param in   o analisador (Scanner) contendo os dados dos disparos
     * @param game a instância do jogo que será atacada
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }
    }
}