public class Bord {
    private Mastermind.COLORS $bordStatus[][] = new Mastermind.COLORS[Mastermind.TURNS][Mastermind.COLS];
    private Mastermind.COLORS $bordCode[];

    public void InitBordCode(Mastermind.COLORS bordCode[]){
        $bordCode = bordCode;
    }

    public boolean ValidateSequence(Mastermind.COLORS toCheckSequence[]){
        boolean correctSequence = true;
        for (int i = 0; i < Mastermind.COLS ; i++){
            if (toCheckSequence[i] != $bordCode[i]){
                correctSequence = false;
            }
        }

        return correctSequence;
    }

    private void AddSequenceToBoard(Mastermind.COLORS toCheckSequence[]){
        int counter = Mastermind.TURNS-1;
        for (int i = 0; i < Mastermind.TURNS; i++){
            if ($bordStatus[i][0] == null){
                counter = i;
            }
        }

        for (int j = 0; j < Mastermind.COLS; j++){
            $bordStatus[counter][j] = toCheckSequence[j];
        }

    }

    public int[] getScore(Mastermind.COLORS toCheckSequence[]){
        int[] score = new int[2];
        score[0] = calculateRedPins(toCheckSequence);
        score[1] = calculateWhitePins(toCheckSequence) - score[0];

        return score;
    }

    private int calculateRedPins(Mastermind.COLORS toCheckSequence[]){
        int amountOfRedPins = 0;
        for (int i = 0; i < Mastermind.COLS; i++){
            if (toCheckSequence[i] == $bordCode[i]){
                amountOfRedPins++;
            }
        }
        return amountOfRedPins;
    }
    private int calculateWhitePins(Mastermind.COLORS toCheckSequence[]){
        int amountOfWhitePins = 0;
        int foundWhitePins[] = InitFoundWhitePins();
        int foundWhitePinsCounter = 0;

        for (int i = 0; i < Mastermind.COLS ; i++){
            for (int j = 0; j < Mastermind.COLS; j++){

                if (notInFound(foundWhitePins, j) && $bordCode[i] == toCheckSequence[j]){
                    foundWhitePins[foundWhitePinsCounter] = j;
                    foundWhitePinsCounter++;
                    amountOfWhitePins++;
                }
            }
        }
        return amountOfWhitePins;
    }
    private int[] InitFoundWhitePins(){
        int foundWhitePins[] = new int[Mastermind.COLS];
        for (int i = 0; i < Mastermind.COLS; i++){
            foundWhitePins[i] = -1;
        }
        return foundWhitePins;
    }
    private boolean notInFound(int foundWhitePins[], int j){
        for (int i = 0; i < Mastermind.COLS; i++){
            if (foundWhitePins[i] == j){
                return false;
            }
        }
        return true;
    }
}
