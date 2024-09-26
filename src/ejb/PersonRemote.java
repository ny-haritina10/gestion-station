package remote;

import person.Person;
import javax.ejb.Local;

@Local
public interface PersonRemote {

    public Person getPers();
    
}