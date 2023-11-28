package hello.core.singleton;

public class StatefulService {

    /**
     * 아래같은 공유필드가 있으면 다른곳에서 요청시 데이터가 바뀔 수 있음
     */
    /*private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제!
    }
    public int getPrice() {
        return price;
    }
    */


    /**
     * 위를 아래처럼 바꿔야함
     * @return
     */
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price; //여기가 문제!
    }



}
