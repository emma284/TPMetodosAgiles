package tpmetodosagiles.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> domicilio;
	public static volatile SingularAttribute<Usuario, Date> fechaNacimiento;
	public static volatile SingularAttribute<Usuario, Integer> idUsuario;
	public static volatile SingularAttribute<Usuario, String> apellido;
	public static volatile SingularAttribute<Usuario, String> contrasenia;
	public static volatile SingularAttribute<Usuario, String> tipoDeDocumento;
	public static volatile SingularAttribute<Usuario, Integer> numeroDocumento;
	public static volatile SingularAttribute<Usuario, Character> sexo;
	public static volatile SingularAttribute<Usuario, String> nombre;
	public static volatile SingularAttribute<Usuario, Character> rol;
	public static volatile SingularAttribute<Usuario, String> username;

}

