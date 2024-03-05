package ru.mirea.maximister.lab5;

public class DoubleCheckedSingleton {
    private static volatile DoubleCheckedSingleton instance;

    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {
            synchronized(DoubleCheckedSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }

        return instance;
    }

    private DoubleCheckedSingleton() {
    }

    public void doSomething() {
        System.out.println("I'm doing smth");
    }



    public static void main(String[] args) {
        //getting first instance
        DoubleCheckedSingleton instance1 = DoubleCheckedSingleton.getInstance();
        //getting second instance
        DoubleCheckedSingleton instance2 = DoubleCheckedSingleton.getInstance();

        //checking that instances are an equal references
        String res = instance1 == instance2 ? "It is singleton" : "It is not a singleton";
        System.out.println(res);
    }
}
