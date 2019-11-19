package tpmetodosagiles.entidades;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Licencia.class)
public abstract class Licencia_ {

	public static volatile SingularAttribute<Licencia, Integer> idLicencia;
	public static volatile SingularAttribute<Licencia, LocalDate> fechaVencimiento;
	public static volatile SingularAttribute<Licencia, Integer> numeroDeCopia;
	public static volatile SingularAttribute<Licencia, Usuario> usuarioResponsable;
	public static volatile SingularAttribute<Licencia, Integer> numeroDeRenovacion;
	public static volatile SingularAttribute<Licencia, LocalDate> fechaEmision;
	public static volatile SingularAttribute<Licencia, Character> claseLicencia;
	public static volatile SingularAttribute<Licencia, Titular> titular;

}

