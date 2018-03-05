public class UnminedCell extends Cell{
    private int number;

    public UnminedCell(int number) {
        super(false, false);
        this.number = number;
    }

    //getters and setters
    @Override
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    //getters and setters end


}
