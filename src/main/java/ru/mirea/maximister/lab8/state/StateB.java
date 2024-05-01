package ru.mirea.maximister.lab8.state;

class StateB implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("State B");
        context.setState(new StateA());
    }
}
