package tpmetodosagiles.entidades;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Titular.class)
public abstract class Titular_ {

	public static volatile SingularAttribute<Titular, LocalDate> fechaNacimiento;
	public static volatile SingularAttribute<Titular, Boolean> esDonante;
	public static volatile SingularAttribute<Titular, Usuario> usuarioResponsable;
	public static volatile SingularAttribute<Titular, String> nombre;
	public static volatile SingularAttribute<Titular, String> grupoSanguinio;
	public static volatile SingularAttribute<Titular, String> domicilio;
	public static volatile SingularAttribute<Titular, Integer> idTitular;
	public static volatile SingularAttribute<Titular, String> apellido;
	public static volatile SingularAttribute<Titular, LocalDate> fechaEntradaSistema;
	public static volatile SingularAttribute<Titular, String> observaciones;
	public static volatile SingularAttribute<Titular, String> tipoDeDocumento;
	public static volatile SingularAttribute<Titular, Integer> numeroDocumento;
	public static volatile SingularAttribute<Titular, Character> sexo;
	public static volatile SingularAttribute<Titular, LocalDate> fechaEmisionLicenciaTipoB;
	public static volatile ListAttribute<Titular, Licencia> licencias;

}

