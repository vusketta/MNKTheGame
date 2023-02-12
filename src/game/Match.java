package game;

public class Match {
    private final int bestOf;
    private TwoPlayerGame game;
    private int gameCounter = 0;
    private int firstScore = 0;
    private int secondScore = 0;

    public Match(TwoPlayerGame game, int bestOf){
        this.game = game;
        this.bestOf = bestOf;
    }

    public int playMatch(boolean log) {
        while (firstScore < bestOf && secondScore < bestOf) {
            gameCounter++;
            score();
            int gameResult = game.play(log);

            if (gameResult == 0) {
                System.out.println("\nDraw");
            } else if (gameCounter % 2 == 0) {
                switch (gameResult) {
                    case 1 -> {
                        System.out.println("\nSecond win");
                        secondScore++;
                    }
                    case 2 -> {
                        System.out.println("\nFirst win");
                        firstScore++;
                    }
                }
            } else {
                switch (gameResult) {
                    case 1 -> {
                        System.out.println("\nFirst win");
                        firstScore++;
                    }
                    case 2 -> {
                        System.out.println("\nSecond win");
                        secondScore++;
                    }
                }
            }

            game.getBoard().clear();
            game = new TwoPlayerGame(game.getBoard(), game.getPlayer2(), game.getPlayer1());
        }
        score();
        if (firstScore == bestOf) {
            return 1;
        } else {
            return 2;
        }
    }

    public void score(){
        System.out.println("\n------------");
        System.out.println("Score " + firstScore + " : " + secondScore);
        System.out.println("------------");
    }

}
