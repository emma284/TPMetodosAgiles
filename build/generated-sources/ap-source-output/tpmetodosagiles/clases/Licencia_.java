package tpmetodosagiles.clases;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Licencia.class)
public abstract class Licencia_ {

	public static volatile SingularAttribute<Licencia, String> idLicencia;
	public static volatile SingularAttribute<Licencia, Integer> numeroDeCopia;
	public static volatile SingularAttribute<Licencia, Integer> numeroDeRenovacion;
	public static volatile SingularAttribute<Licencia, Date> fechaEmision;
	public static volatile SingularAttribute<Licencia, Character> claseLicencia;

}

