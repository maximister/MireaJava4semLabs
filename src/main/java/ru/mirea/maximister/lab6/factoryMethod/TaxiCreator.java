package ru.mirea.maximister.lab6.factoryMethod;

public abstract class TaxiCreator {
    protected String type;
    public abstract TaxiCar createCar();
    public String getType() {
        return type;
    }

    public static class PremiumCreator extends TaxiCreator {
        public PremiumCreator() {
            this.type = "Premium";
        }

        @Override
        public TaxiCar createCar() {
            return new TaxiCar.PremiumClassTaxi();
        }
    }

    public static class EconomyCreator extends TaxiCreator {
        public EconomyCreator() {
            this.type = "Economy";
        }

        @Override
        public TaxiCar createCar() {
            return new TaxiCar.EconomyClassTaxi();
        }
    }
}
