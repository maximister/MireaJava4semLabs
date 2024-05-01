package ru.mirea.maximister.lab8.state;

public class Context {
    private State state;

    public Context() {
        state = new StateA();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void doAction() {
        state.doAction(this);
    }

    public static void main(String[] args) {
        Context context = new Context();

        context.doAction();
        context.doAction();
        context.doAction();
    }
}
