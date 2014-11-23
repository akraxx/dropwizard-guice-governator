package fr.mmarie.keepalive;

import org.skife.jdbi.v2.sqlobject.SqlQuery;

/**
 * Created by Maximilien on 23/11/2014.
 */
public interface KeepAliveDao {

    @SqlQuery("select 1")
    int keepalive();
}
