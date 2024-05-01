package ru.mirea.maximister.lab8.state;

public class StateA implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("State A");
        context.setState(new StateB());
    }
}
