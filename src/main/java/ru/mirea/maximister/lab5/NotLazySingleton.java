package ru.mirea.maximister.lab5;

public class NotLazySingleton {
    public static final NotLazySingleton INSTANCE = new NotLazySingleton();

    public static NotLazySingleton getInstance() {
        return INSTANCE;
    }

    private NotLazySingleton() {
    }

    public void doSomething() {
        System.out.println("I'm doing smth");
    }

    public static void main(String[] args) {
        //getting first instance
        NotLazySingleton instance1 = NotLazySingleton.getInstance();
        //getting second instance
        NotLazySingleton instance2 = NotLazySingleton.getInstance();

        //checking that instances are an equal references
        String res = instance1 == instance2 ? "It is singleton" : "It is not a singleton";
        System.out.println(res);
    }
}
