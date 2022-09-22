package lesson_3_concurrent.ping_pong;

public class Main {

    public static void main(String[] args) {
        PingPong pingPong = new PingPong(100);
//        pingPong.startWithWaitNotify();
        pingPong.startWithLock();
        pingPong.stop();

    }
}
