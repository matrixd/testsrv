package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by ulanov on 16.01.17.
 */
public class Server {
    public static Observable<Socket> start(Config cfg) {
        return Observable.create(new ObservableOnSubscribe<Socket>() {
            @Override
            public void subscribe(ObservableEmitter<Socket> s) throws Exception {
                try {
                    ServerSocket socket = new ServerSocket(cfg.port);
                    while (true) {
                        s.onNext(socket.accept());
                    }
                } catch (IOException e) {
                    s.onError(e);
                }
            }
        });
    }
}
