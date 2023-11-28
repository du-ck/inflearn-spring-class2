package hello.core.singleton;

public class SingletonService {

    /**
     * 프로그램 내에 1개의 인스턴스만 생성하는 부분
     */
    private static final SingletonService instance = new SingletonService();

    /**
     * 위의 instance 는 아래의 메서드로만 호출 가능
     * @return
     */
    public static SingletonService getInstance() {
        return instance;
    }

    /**
     * 1개의 인스턴스만 존재해야 하므로, 아래의 private 생성자를 통해 외부에서
     * new 키워드로 객체 인스턴스가 또 생성되는것을 막는다
     */
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
