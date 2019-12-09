/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpmetodosagiles.gestores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import tpmetodosagiles.entidades.Licencia;
import tpmetodosagiles.entidades.Titular;
import tpmetodosagiles.entidades.Usuario;
import tpmetodosagiles.gestores.util.MySession;


public class GestorDeBaseDeDatos {
    private Session session = null;
    
    public GestorDeBaseDeDatos() {
        try {
            session = MySession.get();
        } catch (Exception ex) {
            Alert mensajeErrores = new Alert(Alert.AlertType.ERROR);
            mensajeErrores.setTitle("Sin conexión al servidor");
            mensajeErrores.setHeaderText("No se pudo establecer conexión con el servidor. Intente nuevamente más tarde.");
            mensajeErrores.setContentText(ex.getMessage());
            
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
        }
    }
    
    
    public boolean guardarLicencia(Licencia unaLicencia){
        try {
            session.beginTransaction();
            session.save(unaLicencia);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("------------------------\n" + e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean modificarLicencia(Licencia unaLicencia){
        try {
            session.beginTransaction();
            session.saveOrUpdate(unaLicencia);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("------------------------\n" + e.getMessage());
            return false;
        }
        return true;
    }
    
    
    public boolean guardarTitular(Titular unTitular){
        try {
           
            session.beginTransaction();
            session.saveOrUpdate(unTitular);
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("------------------------\n" + e.getMessage());
            return false;
        }
        return true;
    }
    
    
    public List<Licencia> getLicenciasExpiradas(String nombre, String apellido, char clase, LocalDate fechaDesde, LocalDate fechaHasta){
        
        LocalDate hoy = LocalDate.now();
        
        LocalDate fechaFinal = (fechaHasta!=null && fechaHasta.compareTo(hoy)<=0)? fechaHasta : null;
        
        String arreglo = "";

        if((fechaDesde!=null)||(fechaFinal!=null)){
            if((fechaDesde!=null)&&(fechaFinal!=null)){
                arreglo = arreglo + "fecha_vencimiento BETWEEN " + fechaDesde + " AND " + fechaFinal + " "; 
            }
            else{
                if(fechaDesde!=null){
                arreglo = arreglo + "fecha_vencimiento BETWEEN " + fechaDesde + " AND now() "; 
                }
                else{
                arreglo = arreglo + "fecha_vencimiento < " + fechaFinal + " "; 
                }
            }
        }
        else{
            arreglo = arreglo + "fecha_vencimiento < now() ";
        }
        if(!nombre.isEmpty()){
           arreglo = arreglo + "AND t.nombre LIKE '" + nombre + "%' "; 
        }
        if(!apellido.isEmpty()){
           arreglo = arreglo + "AND t.apellido LIKE '" + apellido +"%' "; 
        }
        if(clase!='Z'){
           arreglo = arreglo + "AND l.clase_licencia = '" + clase + "' "; 
        }
        
        
        session.beginTransaction();
        
        List<Licencia> query = session.createSQLQuery("SELECT l.* FROM licencia l JOIN titular t ON l.id_titular=t.id "
                + "WHERE " + arreglo + ";")
                .addEntity(Licencia.class).list();
//                .setParameter("hoy", hoy);

        return query;
    }
    
    public Titular getTitularPorDNI(String tipoDocumento, String numDocumento) {
        session.beginTransaction();
        List<Titular> result = session.createSQLQuery("SELECT * FROM titular t "
            + "WHERE t.numero_documento = " + numDocumento
            + " AND t.tipo_de_documento = '" + tipoDocumento + "'").addEntity(Titular.class).list();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        else{
            System.out.println("No se encontró el titular");
        }
        return null;
    }
    
    /**
     *Verifica que el titular ya tenga una licencia registrada de la clase claseLicencia
     */
    public boolean titularRenovanteDeLicenciaClaseX(int idTitular, char claseLicencia) {
        session.beginTransaction();
        
        List<Licencia> result = session.createSQLQuery("SELECT * FROM licencia l "
            + "WHERE l.id_titular = " + idTitular
            + " AND l.clase_licencia = '" + claseLicencia + "'").addEntity(Licencia.class).list();
        if (!result.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public List<Licencia> getLicenciasPorIDTitular(int idTitular) {
        session.beginTransaction();
        
        List<Licencia> result = session.createSQLQuery("SELECT * FROM licencia l "
            + "WHERE l.id_titular = " + idTitular).addEntity(Licencia.class).list();
        if (!result.isEmpty()){
            return result;
        }
        else{
            System.out.println("Lista vacia");
        }
        return null;
    }
    
    //TODO retornar null en caso de no encontrar el usuario en lugar de unUsuario que solo está inicializado?
    public Usuario getUsuarioPorId(int id) {
        session.beginTransaction();
        Usuario unUsuario = new Usuario();
        List<Usuario> result = session.createSQLQuery("SELECT * FROM usuario u "
            + "WHERE u.id = " + id).addEntity(Usuario.class).list();
        if (!result.isEmpty()) {
            unUsuario = result.get(0);
        }
        return unUsuario;
    }
    
    public List<List<Double>> obtenerCostos(){
        
        List<List<Double>> lista = new ArrayList<List<Double>>();
        
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("SELECT vigencia5anios,vigencia4anios,vigencia3anios,vigencia1anio FROM costo_licencias ");
        
        List<Object[]> rows = query.list();
        for(Object[] row : rows){
            List<Double> a = new ArrayList<Double>();
            a.add(Double.parseDouble(row[0].toString()));
            a.add(Double.parseDouble(row[1].toString()));
            a.add(Double.parseDouble(row[2].toString()));
            a.add(Double.parseDouble(row[3].toString()));
            lista.add(a);
        }

        
//        List<Integer> listaA = (List<Integer>)unaQuery.list().get(0);


        return lista;
    }
    
    public Double obtenerCostoAdministrativo(){
        
        session.beginTransaction();
        SQLQuery unaQuery = session.createSQLQuery("SELECT valor FROM gastos_generales ");
        
        Double retorno = Double.parseDouble(unaQuery.list().get(0).toString());
        return retorno;
    }
    
    public void guardarValoresLicencia(List<Double> costos, char tipoLicencia){
        
        session.beginTransaction();
        String query = "UPDATE costo_licencias SET "
                + "vigencia5anios="+ costos.get(0) +", "
                + "vigencia4anios="+ costos.get(1) +", "
                + "vigencia3anios="+ costos.get(2) +", "
                + "vigencia1anio="+ costos.get(3)
                + " WHERE  clase = '"+ tipoLicencia + "';";
        SQLQuery sqlQuery = session.createSQLQuery(query);
        sqlQuery.executeUpdate();
        
    }
    
    public void guardarCostoAdministrativo(Double costoAdm){
        
        session.beginTransaction();
        String query = "UPDATE gastos_generales SET "
                + "valor="+ costoAdm +""
                + " WHERE  tipo_gasto= 'Administrativo';";
        SQLQuery sqlQuery = session.createSQLQuery(query);
        sqlQuery.executeUpdate();
        session.getTransaction().commit();
//        UPDATE `gastos_generales` SET `id_gasto`=[value-1],`tipo_gasto`=[value-2],`valor`=[value-3] WHERE 1
    }
    public double getCostoLicencia(char claseLicencia, int aniosVigencia){
        double costo = 0;
        String columnaAniosVigencia = "";
        Query unaQuery;
        session.beginTransaction();
        switch (aniosVigencia){
            case 5: columnaAniosVigencia = "vigencia5anios"; break;
            case 4: columnaAniosVigencia = "vigencia4anios"; break;
            case 3: columnaAniosVigencia = "vigencia3anios"; break;
            case 1: columnaAniosVigencia = "vigencia1anios"; break;
        }
        
        unaQuery = session.createSQLQuery("SELECT "+columnaAniosVigencia+
                " FROM costo_licencias WHERE clase = '"+claseLicencia+"';");
        costo = (double) unaQuery.list().get(0);
        //System.out.println("Valor del costo: "+costo);
        return costo;
    }
    public List<Object[]> getGastosGenerales (){
        session.beginTransaction();
        SQLQuery unaQuery = session.createSQLQuery("SELECT tipo_gasto, valor FROM gastos_generales;");
        
        return unaQuery.list();
    }
    
    /**
     * Guarda un archivo (se pensó originalmente para usarse en imágenes) en una ubicación predefinida del sistema para su posterior uso.
     * @param rutaImagenDeEntrada - Ruta (relativa o absoluta) del archivo, con extensión de archivo incluida.
     * @param nombreImagenSalida - El nombre de salida de la imagen, sin la ruta del archivo ni su extensión. Ej: 'archivo', 'imagen'.
     * @return Retorna la ruta de la foto guardada si se logra guardar el archivo de entrada en la carpeta predefinida del sistema pero con el nuevo nombre indicado. Retorna 'null' en otro caso.
     */
    public static String guardarFotoTitular(String rutaImagenDeEntrada, String nombreImagenSalida) {
        File in = new File(rutaImagenDeEntrada);
        File out = new File("src\\tpmetodosagiles\\recursos\\fotos_titulares\\" + nombreImagenSalida + '.' + rutaImagenDeEntrada.substring(rutaImagenDeEntrada.lastIndexOf('.')+1));
        try {
            Files.copy(in.toPath(), out.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return out.getPath();
        } catch (IOException ex) {
            //No se pudo guardar la imagen en el directorio debido a que no se encontró el archivo de entrada o que la ruta de salida es invalida
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Error al guardar foto de titular");
            mensajeErrores.setHeaderText("No se pudo guardar la foto de titular");
            mensajeErrores.setContentText("-Ruta de entrada: " + in.getAbsolutePath() + "\n-Ruta de salida: " + out.getAbsolutePath());
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
            
            return null;
        }
    }
    
    public static Image cargarFotoTitular(String rutaImagen){
        File file = new File(rutaImagen);
        if(file.exists()){
            Image foto = new Image(file.toURI().toString());
            return foto;
        }
        else{
            //No se pudo encontrar el archivo en la ruta especificada => se muestra la foto por defecto
            Alert mensajeErrores = new Alert(Alert.AlertType.INFORMATION);
            mensajeErrores.setTitle("Error al cargar foto de titular");
            mensajeErrores.setHeaderText("No se pudo cargar la foto de titular indicada en el registro.");
            mensajeErrores.setContentText("No se encontró la foto del titular en la ruta: " + file.getAbsolutePath() + "\nSe cargará un icono representativo en su lugar.");
            mensajeErrores.initModality(Modality.APPLICATION_MODAL);
            mensajeErrores.show();
            
            return getFotoTitularPorDefecto();
        }
    }
    
    public static Image getFotoTitularPorDefecto(){
        File file = new File("src\\tpmetodosagiles\\recursos\\imagenes\\imgDeInterface\\foto_usuario.png");
        Image foto = new Image(file.toURI().toString());
        return foto;
    }
    
    public static String getRutaFotoTitularPorDefecto(){
        return "src\\tpmetodosagiles\\recursos\\imagenes\\imgDeInterface\\foto_usuario.png";
    }
}