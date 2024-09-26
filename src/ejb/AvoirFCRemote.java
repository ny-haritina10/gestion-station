package remote;

import javax.ejb.Local;

import prevision.Prevision;
import java.sql.Connection;

@Local
public interface AvoirFCRemote {
    Prevision genererPrevision(String u, Connection c) throws Exception;
}