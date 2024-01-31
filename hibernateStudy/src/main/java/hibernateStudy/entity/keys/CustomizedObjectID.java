package hibernateStudy.entity.keys;

import java.io.Serializable;

/**
 * the compound Key class must have the hashCode() and equals implementations to
 * compare the IDs of 2 objects
 */
public abstract class CustomizedObjectID implements Serializable {

	public abstract int hashCode();

	public abstract boolean equals(Object obj) ;
	
	public abstract String toString();
}
