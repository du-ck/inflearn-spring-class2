package lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient /*implements InitializingBean, DisposableBean */{


    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 , url == " + url);
        /*connect();
        call("초기화 연결 메세지");*/
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }


    /**
     * 아래 2개의 어노테이션은 스프링에서 권장하는 방법
     * @PostConstruct : 초기화 설명 어노테이션
     * @PreDestroy : 소멸자 설정 어노테이션
     *
     * 단점은 외부라이브러리에 적용 불가
     * 해야한다면 @Bean 안에서 파라미터를 이용할것(initMethod, destoryMethod)
     */
    @PostConstruct
    public void init() throws Exception {
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }
}
