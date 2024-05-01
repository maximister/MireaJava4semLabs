package ru.mirea.maximister.lab8.observer;

class ConcreteObserver implements Observer {
    private ConcreteSubject subject;

    public ConcreteObserver(ConcreteSubject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Observer updated with new state: " + subject.getState());
    }
}
