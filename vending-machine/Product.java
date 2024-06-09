public class Product {

    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // 상품의 정보를 문자열로 반환하는 메서드로, 상품의 이름과 가격을 "상품이름 : 가격원" 형식으로 문자열로 만들어 반환
    public String toString() {
        return String.format("%s\t : %,d원", name, price);
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
