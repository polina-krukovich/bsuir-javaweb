package entity;

public class PizzaSection extends Entity {
    private String name;
    private double priceS;
    private double priceM;
    private double priceL;

    public PizzaSection() {
        super();
    }
    public PizzaSection(int id, String name, double priceS, double priceM, double priceL) {
        super(id);
        this.name = name;
        this.priceS = priceS;
        this.priceM = priceM;
        this.priceL = priceL;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPriceS() {
        return priceS;
    }
    public void setPriceS(double priceS) {
        this.priceS = priceS;
    }
    public double getPriceM() {
        return priceM;
    }
    public void setPriceM(double priceM) {
        this.priceM = priceM;
    }
    public double getPriceL() {
        return priceL;
    }
    public void setPriceL(double priceL) {
        this.priceL = priceL;
    }

    @Override
    public String toString() {
        return String.format(
                "%s@%h {super:%s, name:'%s', priceS:%f, priceM:%f, priceL:%f}",
                getClass().getSimpleName(),
                this,
                super.toString(),
                name,
                priceS,
                priceM,
                priceL
        );
    }

    @Override
    public int hashCode() {
        final int PRIME_NUMBER = 31;
        int hashCode = super.hashCode();
        hashCode = PRIME_NUMBER * hashCode + (name == null ? 0 : name.hashCode());
        hashCode = PRIME_NUMBER * hashCode + (int) Double.doubleToLongBits(priceS);
        hashCode = PRIME_NUMBER * hashCode + (int) Double.doubleToLongBits(priceM);
        hashCode = PRIME_NUMBER * hashCode + (int) Double.doubleToLongBits(priceL);
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PizzaSection other = (PizzaSection) o;
        return name.equals(other.name)
               && Double.compare(priceS, other.priceS) == 0
               && Double.compare(priceM, other.priceM) == 0
               && Double.compare(priceL, other.priceL) == 0;
    }

    public int compareTo(PizzaSection o) {
        return name.compareTo(o.name);
    }
}
