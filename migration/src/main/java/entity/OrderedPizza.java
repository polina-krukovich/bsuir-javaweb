package entity;

public class OrderedPizza extends Entity {
    private Size size;
    private int count;
    private int pizzaId;
    private int orderId;

    public OrderedPizza() {
        super();
    }
    public OrderedPizza(int id, Size size, int count, int pizzaId, int orderId) {
        super(id);
        this.size = size;
        this.count = count;
        this.pizzaId = pizzaId;
        this.orderId = orderId;
    }

    public Size getSize() {
        return size;
    }
    public void setSize(Size size) {
        this.size = size;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getPizzaId() {
        return pizzaId;
    }
    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return String.format(
                "%s@%h {super:%s, size:%s, count:%d, pizzaId:%d, orderId:%d}",
                getClass().getSimpleName(),
                this,
                super.toString(),
                size,
                count,
                pizzaId,
                orderId
        );
    }

    @Override
    public int hashCode() {
        final int PRIME_NUMBER = 31;
        final int SHIFT_BITS_COUNT = 16;
        int hashCode = super.hashCode();
        hashCode = PRIME_NUMBER * hashCode + size.hashCode();
        hashCode = PRIME_NUMBER * hashCode + (count ^ count >>> SHIFT_BITS_COUNT);
        hashCode = PRIME_NUMBER * hashCode + (pizzaId ^ pizzaId >>> SHIFT_BITS_COUNT);
        hashCode = PRIME_NUMBER * hashCode + (orderId ^ orderId >>> SHIFT_BITS_COUNT);
        return hashCode;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderedPizza other = (OrderedPizza) o;
        return size.compareTo(other.size) == 0
               && Integer.compare(count, other.count) == 0
               && Integer.compare(pizzaId, other.pizzaId) == 0
               && Integer.compare(orderId, other.orderId) == 0;
    }

    public int compareTo(OrderedPizza o) {
        return Integer.compare(orderId, o.orderId);
    }
}
