public class PlayerOne {
    private String userName;
    private int TotalWins;
    public PlayerOne (String userName, int Wins ){
        this.userName = userName;
        this.TotalWins = Wins;
    }
    public int getTotalWins(){
        return TotalWins;
    }
    public void setTotalWins(int Wins){
        TotalWins = Wins;
    }
}

