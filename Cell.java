public class Cell {

    protected boolean isRevealed;
    protected boolean hasFlag;

    //default constructor
    public Cell() {
        this.isRevealed = false;
        this.hasFlag = false;
    }

    //for generate new cells in
    public Cell(boolean isRevealed, boolean hasFlag) {
        this.isRevealed = isRevealed;
        this.hasFlag = hasFlag;
    }

    //getters and setters
    public boolean isRevealed() {
        return isRevealed;
    }

    public boolean hasFlag() {
        return hasFlag;
    }

    public void reveal(){
        isRevealed = true;
    }

    public void addFlag(){
        hasFlag = true;
    }

    public void removeFlag(){
        hasFlag = false;
    }
    //getters and setters end


    public void setNumber(int number){};


    public int getNumber(){
        return 0;
    };
}
