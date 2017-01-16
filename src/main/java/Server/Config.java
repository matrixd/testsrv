package Server;

import com.beust.jcommander.Parameter;

/**
 * Created by ulanov on 16.01.17.
 */
public class Config {

    @Parameter(names = {"-p", "-port"}, description = "port to listen on")
    int port;

    @Parameter(names = {"-a", "-addr"}, description = "address to listen on")
    String addr;

}
