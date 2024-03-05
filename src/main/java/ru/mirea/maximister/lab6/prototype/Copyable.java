package ru.mirea.maximister.lab6.prototype;

public interface Copyable {
    Copyable copy();

    public class SomeCopyableObject implements Copyable {
        private String param1;
        private String param2;

        public SomeCopyableObject(String p1, String p2) {
            param1 = p1;
            param2 = p2;
        }

        @Override
        public Copyable copy() {
            System.out.println("SomeCopyableObject was copied");
            return new SomeCopyableObject(this.param1, this.param2);
        }

        public void setParam1(String param1) {
            this.param1 = param1;
        }

        public void setParam2(String param2) {
            this.param2 = param2;
        }
    }
}
