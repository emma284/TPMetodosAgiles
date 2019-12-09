/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.gestores;

import com.lowagie.text.DocumentException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import tpmetodosagiles.entidades.Licencia;
import tpmetodosagiles.entidades.Titular;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.YEARS;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class GestorDeLicencias {
    
    private static GestorDeBaseDeDatos gbd = new GestorDeBaseDeDatos();
    
    public static boolean emitirLicencia(Date fechaEmision, Date fechaVencimiento, char claseLicencia, 
        int numeroDeRenovacion, int numeroDeCopia){
        
        /*Licencia unaLicencia = new Licencia(fechaEmision, fechaVencimiento, claseLicencia, 
                numeroDeRenovacion, numeroDeCopia);
        
        return gbd.guardarLicencia(unaLicencia);*/return true;
    }

        
    public LocalDate calcularVigenciaDeLicencia(LocalDate fechaNacimiento,Integer idTitular, char claseLicencia){

        long edad = YEARS.between(fechaNacimiento,LocalDate.now());
	LocalDate fechaVencimiento = fechaNacimiento.plusYears(edad);
	Boolean aMenosDeDosMesesDeCumpleanios = MONTHS.between(fechaNacimiento,LocalDate.now().minusYears(edad)) >= 10;
	if(aMenosDeDosMesesDeCumpleanios){
		//se encuentra a menos de 2 meses de cumplir años
		fechaVencimiento = fechaVencimiento.plusYears(1);
	}
        
        if(edad < 17){
            //Caso menor de 17 años
            return null;
        }
        else if((edad > 16 && edad < 20) || (edad == 20 && !aMenosDeDosMesesDeCumpleanios)){
            //caso entre 17 y 21
            if(idTitular==null)
                return null ;
            else
                //Se verifica si el titular ya tiene alguna licencia registrada
                if(!gbd.titularRenovanteDeLicenciaClaseX(idTitular, claseLicencia)) {
			//Caso primera vez que obtiene licencia
                	return fechaVencimiento.plusYears(1);
                }
                else{
                    //caso renovación
                        return fechaVencimiento.plusYears(3); 
                }
        }
        else if((edad < 46) || (edad == 46 && !aMenosDeDosMesesDeCumpleanios)){
            //caso menor a 47 años
                return fechaVencimiento.plusYears(5);
        }
        else if((edad < 60) || (edad == 60 && !aMenosDeDosMesesDeCumpleanios)){
            //caso menor a 61 años
                return fechaVencimiento.plusYears(4);
        }
        else if((edad < 70) || (edad == 70 && !aMenosDeDosMesesDeCumpleanios)){
            //caso menor a 71 años
                return fechaVencimiento.plusYears(3);
        }
        else{
            //caso de 71 en adelante
                return fechaVencimiento.plusYears(1);
        }
    }
     
    public static Double calcularCostoDeLicencia(Licencia licencia){
        
        Double costo = 0.0;
        long aniosVigencia = 0;
        char clase = licencia.getClaseLicencia();
        LocalDate fechaNacimiento = licencia.getTitular().getFechaNacimiento();
        long edad = YEARS.between(fechaNacimiento,licencia.getFechaEmision());
        
        if(MONTHS.between(fechaNacimiento,licencia.getFechaEmision().minusYears(edad)) >= 10){
            aniosVigencia = YEARS.between(licencia.getFechaEmision(), licencia.getFechaVencimiento());
        }else{
            aniosVigencia = YEARS.between(licencia.getFechaEmision(), licencia.getFechaVencimiento())+1;
        }
        costo = gbd.getCostoLicencia(licencia.getClaseLicencia(), (int)aniosVigencia);
      return costo;
    }
    
    public List<Licencia> buscarLicenciasExpiradas(String nombre,String apellido,char clase,LocalDate fechaDesde,LocalDate fechaHasta){
        List<Licencia> lista = gbd.getLicenciasExpiradas(nombre,apellido,clase,fechaDesde,fechaHasta);
        
        return lista;
    }
    
    public List<List<Double>> obtenerCostos(){
        List<List<Double>> lista = gbd.obtenerCostos();
//        double lista = gbd.obtenerCostos();
        
        return lista;
    }

    public Double obtenerCostoAdministrativo(){
        Double retorno = gbd.obtenerCostoAdministrativo();
        
        return retorno;
    }
    
    public void guardarValoresLicencia(ArrayList<List<Double>> costos){
        
        String tipos = "ABCDEFG";
        int i = 0;
        for(List<Double> costosLicencia : costos){
            gbd.guardarValoresLicencia(costosLicencia,tipos.charAt(i));
            i++;
        }
        
    }
    
    public void guardarCostoAdministrativo(Double costoAdm){
        
        gbd.guardarCostoAdministrativo(costoAdm);
        
    }
    
    /**
     * Devuelve los costos de emitir una licencia según el tipo.
     * @return Retorna un arreglo de double con tres elementos: el costo de la licencia según la clase de licencia; el costo fijo debido a gastos administrativos; el costo total (suma de los anteriores)
     */
    public static double[] getArrayCostoLicencia(Licencia unaLicencia){
        double costoLicencia = GestorDeLicencias.calcularCostoDeLicencia(unaLicencia);   //Costo de la clase licencia
        double costoAdministrativoTotal = 0.0;
        List<Object[]> gastos = gbd.getGastosGenerales();
        for(Object[] gasto : gastos){
            costoAdministrativoTotal += Double.parseDouble(gasto[1].toString());
        }
        
        double [] resultado = {costoLicencia,costoAdministrativoTotal, (costoLicencia + costoAdministrativoTotal) };
        
        return resultado;
    }
    
    private static String generarHTMLLicencia(Licencia licencia){
        StringBuffer html = new StringBuffer();
        html.append("<html>\n<head>\n<meta charset=\"UTF-8\"></meta>\n<link rel=\"stylesheet\" type=\"text/css\" href=\"");
        //Indica la ubicación del archivo index.css que le da su estilo al HTML
        html.append("src/tpmetodosagiles/recursos/generacionDePDFs/index.css");
        html.append("\" />\n</head>\n");
        html.append("<body>\n<div class=\"main\">\n<table style=\"width: 498px;\">\n<tbody>\n<tr>\n");
        html.append("<td style=\"width: 490px; padding: 5px 0px 0px 0px;\">\n<h3 class=\"main\">LICENCIA DE CONDUCIR - SANTA FE CIUDAD</h3>\n");
        html.append("<hr/>\n</td>\n</tr>\n<tr>\n<td style=\"width: 490px; padding-top: 0px;\">\n<table style=\"width: 482px;\">\n<tbody>\n<tr>\n");
        html.append("<td style=\"width: 154px; padding: 5px 5px 0px 10px;\" valign=\"top\"><img src=\"");
        if( (new File(licencia.getTitular().getRutaDeFotoDeTitular())).exists() )
            html.append(licencia.getTitular().getRutaDeFotoDeTitular().replace('\\', '/'));
        else
            html.append( GestorDeBaseDeDatos.getRutaFotoTitularPorDefecto().replace('\\', '/'));
        html.append("\" alt=\"\" width=\"100\" height=\"100\" /></td>\n");
        html.append("<td style=\"width: 291px;\">\n<table style=\"width: 359px;\">\n<tbody>\n<tr>\n<td class=\"small\">Nº Licencia:</td>\n<td class=\"dato\">");
        html.append(licencia.getIdLicenciaFisica());
        html.append("</td>\n</tr>\n<tr>\n<td class=\"small\">APELLIDO:</td>\n<td class=\"dato\">");
        html.append(licencia.getTitular().getApellido());
        html.append("</td>\n</tr>\n<tr>\n<td class=\"small\">NOMBRE:</td>\n<td class=\"dato\">");
        html.append(licencia.getTitular().getNombre());
        html.append("</td>\n</tr>\n<tr>\n<td class=\"small\">FECHA DE NAC:</td>\n<td class=\"dato\">");
        html.append(licencia.getTitular().getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
        html.append("</td>\n</tr>\n<tr>\n<td class=\"small\">DOMICILIO:</td>\n<td class=\"dato\">");
        html.append(licencia.getTitular().getCalle() + " " + licencia.getTitular().getAltura());
        html.append("</td>\n</tr>\n<tr>\n<td class=\"small\"> </td>\n<td class=\"dato\"> <em class=\"small\">nro.Dpto:</em> ");
        if(licencia.getTitular().getnroDepartamento().equals(" "))
            html.append("<span style=\"margin-right: 8px;\">-</span>");
        else
            html.append(licencia.getTitular().getnroDepartamento());
        html.append("<em class=\"small\" style=\"margin-left:10px;\">Piso:</em> ");
        if(licencia.getTitular().getPiso().equals(" "))
            html.append("<span style=\"margin-right: 8px;\">-</span>");
        else
            html.append(licencia.getTitular().getPiso());
        html.append("</td>\n</tr>\n<tr style=\" height: 8px;\">\n<td class=\"small\"/>\n<td class=\"dato\"/>\n</tr>\n<tr>\n");
        html.append("<td class=\"small\">OTORGAMIENTO:</td>\n");
        html.append("<td class=\"dato\"> ");
        html.append(licencia.getFechaEmision().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
        html.append("</td>\n</tr>\n<tr>\n<td class=\"small\">VTO:</td>\n<td class=\"dato\"> ");
        html.append(licencia.getFechaVencimiento().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
        html.append("</td>\n</tr>\n<tr>\n<td class=\"small\"> CLASE:</td>\n<td class=\"dato\"> ");
        html.append(licencia.getClaseLicencia());
        html.append("</td>\n</tr>\n</tbody>\n</table>\n</td>\n</tr>\n</tbody>\n</table>\n</td>\n</tr>\n");
        html.append("</tbody>\n</table>\n</div>\n");
        
        
        //Cara trasera de la licencia
        html.append("<div style=\"height: 265px;\" class=\"main\">\n<table style=\"margin: 15px 20px;\">\n<tbody>\n<tr>\n");
        html.append("<td style=\"width: 490px; height: 90px;\"><img style=\"display: block; margin-left: auto; margin-right: auto; margin-bottom: 15px;\" src=\"");
        switch (licencia.getClaseLicencia()){
            case 'A':
            html.append("src/tpmetodosagiles/recursos/generacionDePDFs/Licencia-A-Indicación.png");
            break;
            case 'B':
            html.append("src/tpmetodosagiles/recursos/generacionDePDFs/Licencia-B-Indicación.png");
            break;
            case 'C':
            html.append("src/tpmetodosagiles/recursos/generacionDePDFs/Licencia-C-Indicación.png");
            break;
            case 'D':
            html.append("src/tpmetodosagiles/recursos/generacionDePDFs/Licencia-D-Indicación.png");
            break;
            case 'E':
            html.append("src/tpmetodosagiles/recursos/generacionDePDFs/Licencia-E-Indicación.png");
            break;
            case 'F':
            html.append("src/tpmetodosagiles/recursos/generacionDePDFs/Licencia-F-Indicación.png");
            break;
            case 'G':
            html.append("src/tpmetodosagiles/recursos/generacionDePDFs/Licencia-G-Indicación.png");
            break;
        }
        html.append("\" width=\"350\"/></td>\n</tr>\n<tr>\n");
        html.append("<td style=\"width: 490px;\"><span class=\"atras-small\">Es donante voluntario:</span><span class=\"atras-dato\"> ");
        if (licencia.getTitular().getEsDonante())
            html.append("Sí");
        else
            html.append("No");
        html.append("</span><span class=\"atras-small\" style=\"margin-left: 30px;\">Grupo y Factor sanguíneo:</span><span class=\"atras-dato\"> ");
        html.append(licencia.getTitular().getGrupoSanguinio());
        html.append("</span></td>\n</tr>\n<tr>\n");
        html.append("<td style=\"width: 490px;\"><span class=\"atras-small\">Documento:</span><span class=\"atras-dato\"> ");
        html.append(licencia.getTitular().getTipoDeDocumento());
        html.append("</span><span class=\"atras-small\" style=\"margin-left: 30px;\">nro.</span><span class=\"atras-dato\"> ");
        html.append(licencia.getTitular().getNumeroDocumento());
        html.append("</span></td>\n</tr>\n<tr>\n");
        html.append("<td style=\"width: 490px;\"><span class=\"atras-small\">Observaciones:</span><span class=\"atras-dato\"> ");
        if(licencia.getTitular().getObservaciones()==null){
            html.append(" - ");
        }
        else if(licencia.getTitular().getObservaciones().equals("Ninguna")){
            html.append(" - ");
        }
        else{
            html.append(licencia.getTitular().getObservaciones());
        }
        html.append("</span></td>\n</tr>\n<tr>\n");
        if(licencia.getTitular().getFechaEmisionLicenciaTipoB()!= null){
            if(LocalDate.now().isAfter( licencia.getTitular().getFechaEmisionLicenciaTipoB().plusYears(1) )){
                html.append("<td style=\"width: 490px;\"><span class=\"atras-small\"><em>Conductor profesional.</em></span></td>\n");
            }
            else{
                html.append("<td style=\"width: 490px;\"><span class=\"atras-small\">Principiante hasta:</span><span class=\"atras-dato\"> ");
                html.append(licencia.getTitular().getFechaEmisionLicenciaTipoB().plusYears(1).format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
                html.append("</span></td>\n");
            }
        }
        else{
            html.append("<td style=\"width: 490px;\"><span class=\"atras-small\"><em>No apto para conducción profesional.</em></span></td>\n");
        }
        html.append("</tr>\n<tr>\n");
        html.append("<td style=\"width: 490px; height: 40px; padding: 0px; vertical-align: super;\"><hr style=\"margin-top: 0px;\" /></td>\n");
        html.append("</tr>\n</tbody>\n</table>\n</div>");
        
        
        html.append("</body>\n</html>");
        return html.toString();
    } 
    
    public static void crearPDFLicencia(Licencia licencia, String destinationPath, int nroIntento) {
        try{
            File licenciaPDF = new File(destinationPath);
            OutputStream os = new FileOutputStream(licenciaPDF);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(generarHTMLLicencia(licencia));
            renderer.layout();
            renderer.createPDF(os);

            os.close();
            
            Desktop desktop = Desktop.getDesktop();
            if(licenciaPDF.exists())
                desktop.open(licenciaPDF);
            
            System.out.println("Se creó la licencia en el intento Nro. " + nroIntento);
       }
        catch (FileNotFoundException fnf){
            System.out.println("El archivo está siendo utilizado. Cierrelo para continuar.");
            crearPDFLicencia(licencia,destinationPath.replaceFirst("\\d?.pdf\\z",nroIntento++ + ".pdf"), nroIntento++);
        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    private static String generarHTMLComprobante(String costoLicencia, String costoFijo, String costoTotal){
        StringBuffer html = new StringBuffer();
        
        html.append("<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n");
        html.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
        //Indica la ubicación del archivo index.css que le da su estilo al HTML
        html.append("src/tpmetodosagiles/recursos/generacionDePDFs/index.css");
        html.append("\"/>\n</head>\n<body>\n");
        html.append("<div class=\"comprobante\">\n<table style=\"margin-left: auto; margin-right: auto;\" width=\"230\">\n<tbody>\n");
        html.append("<tr>\n<td>\n<h3 class=\"comprobante-titulo\">Comprobante de Emisión de Licencia Vehicular</h3>\n</td>\n");
        //Agrega el logo actual de la Municipalidad de Santa Fe
        html.append("<td>\n<img style=\"margin: 0px;\" src=\"src/tpmetodosagiles/recursos/generacionDePDFs/santafe-ciudad.png\" alt=\"\" width=\"60\" />\n</td>\n");
        html.append("</tr>\n</tbody>\n</table>\n<hr/>\n<table class=\"comprobante-costo\">\n<tbody>\n<tr>\n<td>Costo licencia</td>\n<td align=\"right\">$");
        html.append(costoLicencia);
        html.append("</td>\n</tr>\n<tr>\n<td>Gastos administrativos</td>\n<td align=\"right\">$");
        html.append(costoFijo);
        html.append("</td>\n</tr>\n</tbody>\n</table>\n<p style=\"margin: 45px 10px 15px 0px;\" align=\"right\">Total: $");
        html.append(costoTotal);
        html.append("</p>\n</div>\n</body>\n</html>");
        
        return html.toString();
    } 
    
    public static void crearPDFComprobante(String costoLicencia, String costoFijo, String total, String destinationPath, int nroIntento) {
        try{
            File comprobantePDF = new File(destinationPath);
            OutputStream os = new FileOutputStream(comprobantePDF);

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(generarHTMLComprobante(costoLicencia, costoFijo, total));
            renderer.layout();
            renderer.createPDF(os);

            os.close();
            
            Desktop desktop = Desktop.getDesktop();
            if(comprobantePDF.exists())
                desktop.open(comprobantePDF);
            
            System.out.println("Se creó el comprobante en el intento Nro. " + nroIntento);
       }
        catch (FileNotFoundException fnf){
            System.out.println("El archivo está siendo utilizado. Cierrelo para continuar.");
            crearPDFComprobante(costoLicencia, costoFijo, total,destinationPath.replaceFirst("\\d?.pdf\\z",nroIntento++ + ".pdf"), nroIntento++);
        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
