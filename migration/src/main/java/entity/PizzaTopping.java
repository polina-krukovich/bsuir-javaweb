package entity;

public class PizzaTopping extends Entity {
    private int toppingId;
    private int pizzaId;

    public PizzaTopping() {
        super();
    }
    public PizzaTopping(int id, int toppingId, int pizzaId) {
        super(id);
        this.toppingId = toppingId;
        this.pizzaId = pizzaId;
    }

    public int getToppingId() {
        return toppingId;
    }
    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }
    public int getPizzaId() {
        return pizzaId;
    }
    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    @Override
    public String toString() {
        return String.format(
                "%s@%h {super:%s, toppingId:%d, pizzaId:%d}",
                getClass().getSimpleName(),
                this,
                super.toString(),
                toppingId,
                pizzaId
        );
    }

    @Override
    public int hashCode() {
        final int PRIME_NUMBER = 31;
        final int SHIFT_BITS_COUNT = 16;
        int hashCode = super.hashCode();
        hashCode = PRIME_NUMBER * hashCode + (toppingId ^ toppingId >>> SHIFT_BITS_COUNT);
        hashCode = PRIME_NUMBER * hashCode + (pizzaId ^ pizzaId >>> SHIFT_BITS_COUNT);
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PizzaTopping other = (PizzaTopping) o;
        return Integer.compare(toppingId, other.toppingId) == 0
               && Integer.compare(pizzaId, other.pizzaId) == 0;
    }

    public int compareTo(PizzaTopping o) {
        return Integer.compare(pizzaId, o.pizzaId);
    }
}
