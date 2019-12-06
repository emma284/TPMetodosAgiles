package tpmetodosagiles.entidades;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tpmetodosagiles.enums.SexoEnum;
import tpmetodosagiles.enums.TipoDocumentoEnum;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContribuyenteDTO.class)
public abstract class ContribuyenteDTO_ {

	public static volatile SingularAttribute<ContribuyenteDTO, String> apellidos;
	public static volatile SingularAttribute<ContribuyenteDTO, TipoDocumentoEnum> tipoDocumento;
	public static volatile SingularAttribute<ContribuyenteDTO, LocalDate> fechaNacimiento;
	public static volatile SingularAttribute<ContribuyenteDTO, Short> altura;
	public static volatile SingularAttribute<ContribuyenteDTO, String> calle;
	public static volatile SingularAttribute<ContribuyenteDTO, String> departamento;
	public static volatile SingularAttribute<ContribuyenteDTO, Integer> idContribuyente;
	public static volatile SingularAttribute<ContribuyenteDTO, SexoEnum> sexo;
	public static volatile SingularAttribute<ContribuyenteDTO, Integer> nroDocumento;
	public static volatile SingularAttribute<ContribuyenteDTO, String> nombres;

}

