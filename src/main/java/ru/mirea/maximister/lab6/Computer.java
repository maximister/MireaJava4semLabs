package ru.mirea.maximister.lab6;

import java.util.Objects;

public class Computer {
    private final String powerUnit;
    private final String gpu;
    private final String cpu;
    private final String motherBoard;
    private final String pcCase;

    private Computer(Builder builder) {
        gpu = builder.gpu;
        cpu = builder.cpu;
        motherBoard = builder.motherBoard;
        powerUnit = builder.powerUnit;
        pcCase = builder.pcCase;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Computer{" +
                "powerUnit='" + powerUnit + '\'' +
                ", gpu='" + gpu + '\'' +
                ", cpu='" + cpu + '\'' +
                ", motherBoard='" + motherBoard + '\'' +
                ", pcCase='" + pcCase + '\'' +
                '}';
    }

    public static class Builder {
        private String powerUnit = "none";
        private String gpu = "imagination";
        private String cpu = "stone";
        private String motherBoard = "none";
        private String pcCase = "cardboard box";

        private Builder() {
        }

        public Builder powerUnit(String powerUnit) {
            this.powerUnit = Objects.requireNonNull(powerUnit);
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = Objects.requireNonNull(gpu);
            return this;
        }

        public Builder cpu(String cpu) {
            this.cpu = Objects.requireNonNull(cpu);
            return this;
        }

        public Builder motherBoard(String motherBoard) {
            this.motherBoard = Objects.requireNonNull(motherBoard);
            return this;
        }

        public Builder pcCase(String pcCase) {
            this.pcCase = Objects.requireNonNull(pcCase);
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
