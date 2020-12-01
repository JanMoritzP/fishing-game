public class Mutable<T> {
    
    private T variable;
    
    public Mutable(T variable) {
        this.variable = variable;
    }

    public T getVariable() {
        return variable;
    }

    public void setVariable(T variable) {
        this.variable = variable;
    }
}
