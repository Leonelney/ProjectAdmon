package Sources;

//clase para realizar la operaciones de un año en especifico.
public class Evaluaciones {
    int uniVendidas=20000;      //unidades estimadas a vender(iniciales)
    double porRed;              //
    int año;                    //año del analisis
    double ventasPre;           //ventas previstas
    double iva=0.16;            //valor del iva
    double precio=800;          //precio por unidad del producto
    double porAum=0.05;     
    double utilidad;            //utilidad del ejercicio
    double utilidadbruta;       //utilidad bruta
    double costosProd;          //costos de producción
    double depreciacion;        //depreciacion 
    double gastosAdmon;         //gastos de administracion
    double gastosVentas;        //gastos de ventas
    double utilidadOper;        //utilidad de operacion
    double impuestos;           //impuestos
    double ventasNetas;         //Ventas despues de iva
    //constructor que recibe unicamente el año a analizar
    public Evaluaciones(int año) {
        this.año=año;
    }
    
    public double obtenerVentasNetas(){
   //calcula las ventas previstas
        ventasPre=uniVendidas*precio;
        ventasNetas=ventasPre-(ventasPre/(1+iva))*iva;
        return ventasNetas;
    }
    
    public double obtenerCostosProd(){
    //calcula los costos de produccion
        costosProd=((120+80+50+50)*uniVendidas)+150000+100000+200000;
        return costosProd;
    }
    
    public double obtenerDepreciacion(){
     //se calcula la depreciacion
     depreciacion=(500000/30)+((1000000-100000)/5);
       return depreciacion;
    }
    
    public double obtenerUtilidadBruta(){
      //se calcula la utilidad bruta restandole a las ventas prevista los costos de produccion y la depreciacion
      utilidadbruta= obtenerVentasNetas()-obtenerCostosProd()-obtenerDepreciacion();
      return utilidadbruta;
    }
    
    //metodo que calcula el valor de la utilidad del ejercicio en el año en especifico.
    public double obtenerUtilidad(){
     
      
        //calculo de los gastos de administracion y de ventas
        gastosAdmon=(100*uniVendidas);
        gastosVentas=(200*uniVendidas);
        //calcula la utilidad de operacion restandole a la utilidad bruta los gastos de administracion y ventas
        utilidadOper=utilidadbruta-gastosAdmon-gastosVentas;
        //dependiendo del resultado de la utilidad de operacion se calcula el impuesto
        if(utilidadOper > 0 && utilidadOper <= 999){
            impuestos= utilidadOper*0.05;            
        }else if(utilidadOper > 999 && utilidadOper <= 9999){
            impuestos= (utilidadOper*0.1)+50;
        }else if(utilidadOper > 9999 && utilidadOper <= 49999){
            impuestos= (utilidadOper*0.15)+950;
        }else if(utilidadOper > 49999 && utilidadOper <= 99000){
            impuestos= (utilidadOper*0.2)+6950;
        }else if(utilidadOper > 99000 && utilidadOper <= 499000){
            impuestos= (utilidadOper*0.25)+16950;
        }else if(utilidadOper > 499000 && utilidadOper <= 999999){
            impuestos= (utilidadOper*0.3)+116949;
        }else if(utilidadOper >= 1000000){
            impuestos= (utilidadOper*0.35)+266949;
        }
        //calcula la utilidad del ejercicio restandole a la utilidad de operacion los impuestos 
        utilidad=utilidadOper-impuestos;
        return utilidad;
    }
    
}
