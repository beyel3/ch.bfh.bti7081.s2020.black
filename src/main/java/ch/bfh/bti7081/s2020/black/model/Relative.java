package ch.bfh.bti7081.s2020.black.model;

public class Relative extends Account {

    private int level;

    public Relative(String firstName, String lastName, String email, String password){
        super(firstName, lastName, email, password, AccountType.RELATIVE);
    }

    public int getLvl() {
        return level;
    }

    public void setLvl(int lvl) {
        this.level = level;
    }

    public void increaseLevel(int amount){
        level += amount;
    }

    @Override
    public String toString() {
        //super toString
        return "Relative{" +
                "lvl=" + level +
                '}';
    }
}
