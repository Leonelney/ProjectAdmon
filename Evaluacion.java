package Sources;

//clase para realizar la operaciones de un año en especifico.
public class Evaluaciones {
    int uniVendidas = 20000;      //unidades estimadas a vender(iniciales)
    int uniVendidasA;           // unidades vendidas con aumento por año
    double porRed;              //
    double ventasPre;           //ventas previstas
    double iva = 0.16;            //valor del iva
    double precio = 800;          //precio por unidad del producto
    double porAum = 0.05;
    double utilidad;            //utilidad del ejercicio
    double utilidadbruta;       //utilidad bruta
    double costosProd;          //costos de producción
    double depreciacion;        //depreciacion 
    double gastosAdmon;         //gastos de administracion
    double gastosVentas;        //gastos de ventas
    double utilidadOper;        //utilidad de operacion
    double impuestos;           //impuestos
    double ventasNetas;         //Ventas despues de iva

    public int obtenerUnidadesVen(int año){
    //calcula las unidades que se venden dependiendo del año
        uniVendidasA = uniVendidas*Math.pow(1+.05,año-2017);
        return unidadesVendidasA;
    }
    public double obtenerVentasNetas(int año) {
        //calcula las ventas previstas
        uniVendidas=obtenerUnidadesVen();//ajustando las unidades vendidas respectivas al año
        ventasPre = (uniVendidas * precio) * Math.pow( 1 + 0.05 , año-2017 );
        ventasNetas = ventasPre - (ventasPre / (1 + iva)) * iva;
        return ventasNetas;
    }

    public double obtenerCostosProd(int año) {
        //calcula los costos de produccion
        uniVendidas=obtenerUnidadesVen();//ajustando las unidades vendidas respectivas al año
        costosProd = ((((120 + 80 + 50 + 50) * uniVendidas) + 150000) * Math.pow( 1 + 0.08 , año-2017 )) + 100000 + 200000;
        return costosProd;
    }

    public double obtenerDepreciacion(int año) {
        //se calcula la depreciacion
        depreciacion = (500000 / 30)*(año-2016);
        
       if(año-2016<=5){
       depreciacion= depreciacion+(180000*(año-2016));
       }else
       depreciacion = depreciacion+(900000)+(180000*(año-2016-5));
        return depreciacion;
    }

    public double obtenerUtilidadBruta(int año) {
        //se calcula la utilidad bruta restandole a las ventas prevista los costos de produccion y la depreciacion
        utilidadbruta = obtenerVentasNetas() - obtenerCostosProd() - obtenerDepreciacion();
        return utilidadbruta;
    }

    public double obtenerGastosAdmon(int año) {
        uniVendidas=obtenerUnidadesVen();//ajustando las unidades vendidas respectivas al año
        //calculo de los gastos de administracion
        gastosAdmon = (100 * uniVendidas) * Math.pow( 1 + 0.05 , año-2017 );
        return gastosAdmon;
    }

    public double obtenerGastosVentas(int año) {
        //calculo de los gastos de ventas
        uniVendidas=obtenerUnidadesVen();//ajustando las unidades vendidas respectivas al año
        gastosVentas = (200 * uniVendidas) * Math.pow( 1 + 0.05 , año-2017 );
        return gastosVentas;
    }

    public double obtenerUtilidadOper() {
        //calcula la utilidad de operacion restandole a la utilidad bruta los gastos de administracion y ventas
        utilidadOper = obtenerUtilidadBruta() - obtenerGastosAdmon() - obtenerGastosVentas();
        return utilidadOper;
    }

    public double obtenerImpuestos() {
        utilidadOper = obtenerUtilidadOper();
        //dependiendo del resultado de la utilidad de operacion se calcula el impuesto
        if (utilidadOper > 0 && utilidadOper <= 999) {
            impuestos = utilidadOper * 0.05;
        } else if (utilidadOper > 999 && utilidadOper <= 9999) {
            impuestos = (utilidadOper * 0.1) + 50;
        } else if (utilidadOper > 9999 && utilidadOper <= 49999) {
            impuestos = (utilidadOper * 0.15) + 950;
        } else if (utilidadOper > 49999 && utilidadOper <= 99000) {
            impuestos = (utilidadOper * 0.2) + 6950;
        } else if (utilidadOper > 99000 && utilidadOper <= 499000) {
            impuestos = (utilidadOper * 0.25) + 16950;
        } else if (utilidadOper > 499000 && utilidadOper <= 999999) {
            impuestos = (utilidadOper * 0.3) + 116949;
        } else if (utilidadOper >= 1000000) {
            impuestos = (utilidadOper * 0.35) + 266949;
        }
        return impuestos;
    }

    //metodo que calcula el valor de la utilidad del ejercicio en el año en especifico.
    public double obtenerUtilidad() {
        //calcula la utilidad del ejercicio restandole a la utilidad de operacion los impuestos 
        utilidad = obtenerUtilidadOper() - obtenerImpuestos();
        return utilidad;
    }
}
