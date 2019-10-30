package tpmetodosagiles.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Titular.class)
public abstract class Titular_ {

	public static volatile SingularAttribute<Titular, String> grupoSanguinio;
	public static volatile SingularAttribute<Titular, Integer> idTitular;
	public static volatile SingularAttribute<Titular, Date> fechaNacimiento;
	public static volatile SingularAttribute<Titular, String> apellido;
	public static volatile SingularAttribute<Titular, Boolean> esDonante;
	public static volatile SingularAttribute<Titular, String> direccion;
	public static volatile SingularAttribute<Titular, Date> fechaEntradaSistema;
	public static volatile SingularAttribute<Titular, String> tipoDeDocumento;
	public static volatile SingularAttribute<Titular, String> nombre;
	public static volatile SingularAttribute<Titular, Date> fechaEmisionLicenciaTipoB;

}

