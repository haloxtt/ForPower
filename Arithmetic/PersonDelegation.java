public class PersonDelegation {
    private Person delegation = new Person();
    static final int t = 1;
    public void eat(int i) {
        delegation.eat(i);
    }
    public void drink(int i) {
        delegation.drink(i);
    }

    public static void main(String[] args) {

        PersonDelegation delegation = new PersonDelegation();
        delegation.eat(200);
    }
}
