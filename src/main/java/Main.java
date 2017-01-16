/**
 * Created by ulanov on 16.01.17.
 */

import Server.Config;
import Server.Server;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import io.reactivex.*;

import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Single<Config> scfg = Single.just(new Config()).cache();
        Single <JCommander> sjc = scfg
                .map(JCommander::new)
                .cache();

        Single <Boolean> parseResult = sjc.zipWith(Single.just(args), Main::tryParse).cache();
        parseResult.subscribe((Boolean b) -> { if (!b) System.exit(1); });
        parseResult.zipWith(scfg, (Boolean b, Config cfg) ->  { return Server.start(cfg); } )
                .subscribe((Observable<Socket> o) -> { o.subscribe(System.out::println); });

    }

    static Boolean tryParse(JCommander jc, String[] args) {
        boolean ret = false;

        try {
            jc.parse(args);
            ret = true;
        } catch (ParameterException ignore) {
            jc.usage();
        }
        return ret;
    }
}
