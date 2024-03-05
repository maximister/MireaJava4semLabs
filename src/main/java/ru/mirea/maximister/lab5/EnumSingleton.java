package ru.mirea.maximister.lab5;

public enum EnumSingleton {
    ENUM_SINGLETON;

    public void doSomething() {
        System.out.println("I'm doing smth");
    }


    public static void main(String[] args) {
        //getting first instance
        EnumSingleton instance1 = EnumSingleton.ENUM_SINGLETON;
        //getting second instance
        EnumSingleton instance2 = EnumSingleton.ENUM_SINGLETON;

        //checking that instances are an equal references
        String res = instance1 == instance2 ? "It is singleton" : "It is not a singleton";
        System.out.println(res);
    }
}
