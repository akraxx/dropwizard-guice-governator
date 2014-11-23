package fr.mmarie.keepalive;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Maximilien on 23/11/2014.
 */
@Path("/keepalive")
public class KeepAliveResource {

    private KeepAliveDao keepAliveDao;

    @Inject
    public KeepAliveResource(KeepAliveDao keepAliveDao) {
        this.keepAliveDao = keepAliveDao;
    }

    @GET
    @Timed
    public String keepalive() {
        if(keepAliveDao.keepalive() == 1) {
            return "OK";
        } else {
            return "KO";
        }
    }
}
