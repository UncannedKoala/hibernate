package hibernateStudy.entity.generator;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UUIDGenerator implements IdentifierGenerator{

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		System.out.println("ID generating");
		return sign(UUID.randomUUID());		//for needless fun
//		return UUID.randomUUID();			//this is how it should have been
	}
	
	/**
	 * signing the generated UUID for fun, not needed at all
	 */
	public Object sign(UUID primaryKeyValue) {
		try {
			KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
			KeyPair pair = kpGen.generateKeyPair();
			
			PrivateKey privateKey = pair.getPrivate();
			
			Signature sign = Signature.getInstance("SHA256withRSA");
			sign.initSign(privateKey);
			
			sign.update(primaryKeyValue.toString().getBytes());
			byte[] result = sign.sign();
			
			return primaryKeyValue+"."+Base64.getEncoder().encodeToString(result);
			
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
