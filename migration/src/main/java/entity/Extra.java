package entity;

public class Extra extends Entity {
    private String name;
    private int weight;
    private double price;
    private int sectionId;

    public Extra() {
        super();
    }
    public Extra(int id, String name, int weight, double price, int sectionId) {
        super(id);
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.sectionId = sectionId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getSectionId() {
        return sectionId;
    }
    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public String toString() {
        return String.format(
                "%s@%h {super:%s, name:'%s', weight:%d, price:%f, sectionId:%d}",
                getClass().getSimpleName(),
                this,
                super.toString(),
                name,
                weight,
                price,
                sectionId
        );
    }

    @Override
    public int hashCode() {
        final int PRIME_NUMBER = 31;
        final int SHIFT_BITS_COUNT = 16;
        int hashCode = super.hashCode();
        hashCode = PRIME_NUMBER * hashCode + (name == null ? 0 : name.hashCode());
        hashCode = PRIME_NUMBER * hashCode + (weight ^ weight >>> SHIFT_BITS_COUNT);
        hashCode = PRIME_NUMBER * hashCode + (int) Double.doubleToLongBits(price);
        hashCode = PRIME_NUMBER * hashCode + (sectionId ^ sectionId >>> SHIFT_BITS_COUNT);
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Extra other = (Extra) o;
        return name.equals(other.name)
               && Integer.compare(weight, other.weight) == 0
               && Double.compare(price, other.price) == 0
               && Integer.compare(sectionId, other.sectionId) == 0;
    }

    public int compareTo(Extra o) {
        return name.compareTo(o.name);
    }
}
