package Sources;

//clase para realizar la operaciones de un año en especifico.
public class Evaluacion {
    private int uniVendidas = 20000;      //unidades estimadas a vender(iniciales)
    int uniVendidasA;           // unidades vendidas con aumento por año
    double porRed;              //
    double ventasPre;           //ventas previstas
    double iva = 0.16;            //valor del iva
    double precio = 800;          //precio por unidad del producto
    double porAum = 0.05;
    double utilidad;            //utilidad del ejercicio
    double utilidadbruta;       //utilidad bruta
    double costosProd;          //costos de producción
    double subcontatacion = 0;      //Subcontratacion.
    double depreciacion;        //depreciacion 
    double gastosAdmon;         //gastos de administracion
    double gastosVentas;        //gastos de ventas
    double utilidadOper;        //utilidad de operacion
    double impuestos;           //impuestos
    double ventasNetas;         //Ventas despues de iva
    

    public int obtenerUnidadesVen(int año,double porRed){
    //calcula las unidades que se venden dependiendo del año
        uniVendidasA = (int) (uniVendidas * (1 - porRed) * (int) Math.pow(1 + .05, año - 2017));
        return uniVendidasA;
    }
    public double obtenerVentasNetas(int año) {
        //calcula las ventas previstas
        uniVendidas=obtenerUnidadesVen(año, porRed);//ajustando las unidades vendidas respectivas al año
        ventasPre = (uniVendidas * precio) * Math.pow( 1 + 0.05 , año-2017 );
        ventasNetas = ventasPre - (ventasPre / (1 + iva)) * iva;
        return ventasNetas;
    }

    public double obtenerCostosProd(int año) {
        //calcula los costos de produccion
        uniVendidas = obtenerUnidadesVen(año, porRed);//ajustando las unidades vendidas respectivas al año
        costosProd = ((((120 + 80 + 50 + 50) * uniVendidas) + 150000) * Math.pow( 1 + 0.08 , año-2017 ));
        return costosProd;
    }
    
    public double obtenerSubcontra(int año){
        if(obtenerUnidadesVen(año, porRed)>25000){
            subcontatacion=(obtenerUnidadesVen(año, porRed)-25000)*400;
        }
        return subcontatacion;
        
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
        utilidadbruta = obtenerVentasNetas(año) - obtenerCostosProd(año) - obtenerDepreciacion(año);
        return utilidadbruta;
    }

    public double obtenerGastosAdmon(int año) {
        uniVendidas=obtenerUnidadesVen(año, porRed);//ajustando las unidades vendidas respectivas al año
        //calculo de los gastos de administracion
        gastosAdmon = (100 * uniVendidas) * Math.pow( 1 + 0.05 , año-2017 );
        return gastosAdmon;
    }

    public double obtenerGastosVentas(int año) {
        //calculo de los gastos de ventas
        uniVendidas=obtenerUnidadesVen(año, porRed);//ajustando las unidades vendidas respectivas al año
        gastosVentas = (200 * uniVendidas) * Math.pow( 1 + 0.05 , año-2017 );
        return gastosVentas;
    }

    public double obtenerUtilidadOper(int año) {
        //calcula la utilidad de operacion restandole a la utilidad bruta los gastos de administracion y ventas
        utilidadOper = obtenerUtilidadBruta(año) - obtenerGastosAdmon(año) - obtenerGastosVentas(año);
        return utilidadOper;
    }

    public double obtenerImpuestos(int año) {
        utilidadOper = obtenerUtilidadOper(año);
        //dependiendo del resultado de la utilidad de operacion se calcula el impuesto
        if (utilidadOper > 0 && utilidadOper <= 999) {
            impuestos = utilidadOper * 0.05;
        } else if (utilidadOper > 999 && utilidadOper <= 9999) {
            impuestos = (utilidadOper * 0.1);
        } else if (utilidadOper > 9999 && utilidadOper <= 49999) {
            impuestos = (utilidadOper * 0.15);
        } else if (utilidadOper > 49999 && utilidadOper <= 99000) {
            impuestos = (utilidadOper * 0.2);
        } else if (utilidadOper > 99000 && utilidadOper <= 499000) {
            impuestos = (utilidadOper * 0.25);
        } else if (utilidadOper > 499000 && utilidadOper <= 999999) {
            impuestos = (utilidadOper * 0.3);
        } else if (utilidadOper >= 1000000) {
            impuestos = (utilidadOper * 0.35);
        }
        return impuestos;
    }

    //metodo que calcula el valor de la utilidad del ejercicio en el año en especifico.
    public double obtenerUtilidad(int año) {
        //calcula la utilidad del ejercicio restandole a la utilidad de operacion los impuestos 
        utilidad = obtenerUtilidadOper(año) - obtenerImpuestos(año);
        return utilidad;
    }
    
    //Metodos de proyecto
    //1.- Metodo de recuperacion de la inversion
    public String recuperacionInversion (){
        double FE = 0;
        double resto;
        int anio = 0;
        double mes;
        double dia;
        int contador;
        int inversion = 100000+500000+1000000+200000;
        for(contador = 0; contador<10; contador++){
            FE = (obtenerUtilidad(2017+contador)+obtenerDepreciacion(2017 + contador))+FE;
            System.out.println("Año"+ (2017+contador)+"\n"+FE);
            if(FE>inversion){//Si ya pasamos la inversion inicial
                FE = FE - (obtenerUtilidad(2017+contador)+obtenerDepreciacion(2017 + contador));   //Restamos para tener el año y el FE donde aun no pasa la inversion
                anio = contador;
                contador = 11;  //Sale del for
            }
        }
        resto = inversion - FE;
        //System.out.println("resto  "+FE);
        mes = (resto/(obtenerUtilidad(2017+anio+1)+obtenerDepreciacion(2017 + anio + 1)))*12;
        dia = (mes-(int)mes)*30;
        return "Recuperacion de la inversion en:\nAño "+anio+" mes "+(int)mes+" dia "+(int)dia;
    }
    
    //2.- Metodo de recuperacion de la inversion descontado
    public String recInvDescontado (){
        double VP = 0;
        double resto;
        int anio = 0;
        double k = 0.45;
        double mes;
        double dia;
        int contador;
        int inversion = 100000+500000+1000000+200000;
        for(contador = 0; contador<10; contador++){
            VP = ((obtenerUtilidad(2017+contador)+obtenerDepreciacion(2017 + contador))/Math.pow((1+k), contador+1))+VP;
            //System.out.println("Año"+ (2017+contador)+"\n"+VP);
            if(VP>inversion){//Si ya pasamos la inversion inicial
                VP = VP - ((obtenerUtilidad(2017+contador)+obtenerDepreciacion(2017 + contador))/Math.pow((1+k), contador+1));   //Restamos para tener el año y el FE donde aun no pasa la inversion
                anio = contador;
                contador = 11;  //Sale del for
        }
    }
        resto = inversion - VP;
        //System.out.println("resto  "+VP);
        mes = (resto/((obtenerUtilidad(2017+anio+1)+obtenerDepreciacion(2017 + anio+1))/Math.pow((1+k), anio+1)))*12;
        dia = (mes-(int)mes)*30;
        return "Recuperacion de la inversion descontada en:\nAño "+anio+" mes "+(int)mes+" dia "+(int)dia;
    }
    
    //3.- Metodo anual promedio (RAP)
    public String RAP (){//(Suma de los ingresos entre los años del proyecto) entre la inversion
        double FE = 0;
        double dividendo;
        double RAP;
        double k = 0.45;
        boolean flag = false;
        String respuesta = "El proyecto será rechazado";
        int contador;
        int inversion = 100000+500000+1000000+200000;
        for(contador = 0; contador<10; contador++){
            FE = (obtenerUtilidad(2017+contador)+obtenerDepreciacion(2017 + contador))+FE;//Obtiene la suma de los Flujos de ejectivo cada año (utilidad del ejercicio)  
        }
        dividendo = FE/10;  //Suma de los flujos entre los años totales
        RAP = (dividendo/inversion)*100;    //Obtiene el porcentaje del rendimiento, si menor a k el proyecto no es valido.
        if(RAP >= k)
            flag = true;
        if(flag)
            respuesta = "El proyecto será aprobado";
        return "RAP  "+ RAP + "\n" + respuesta;
    }
    
    //4.- Indice de rentabilidad (IR)
    public String IR (){//Suma de valores presentes entre la inversion
        double VP = 0;  //Valor presente
        double IR;
        double k = 0.45;
        int contador;
        boolean flag = false;
        String respuesta = "El proyecto será rechazado";
        int inversion = 100000+500000+1000000+200000;
        for(contador = 0; contador<10; contador++){
            VP = ((obtenerUtilidad(2017+contador)+obtenerDepreciacion(2017 + contador))/Math.pow((1+k), contador+1))+VP; //Obtiene la suma de los VP cada año 
            //System.out.println("divisor  "+Math.pow((1+k), contador+1));
        }
        IR = VP/inversion;    //Obtiene el indice de rentabilidad, si menor a 1 el proyecto pierde.
        if(IR > 1)
            flag = true;
        else if(IR == 1)
            respuesta = "No hay ganancia ni pérdida en el proyecto";
        if(flag)
            respuesta = "El proyecto será aprobado";
        return "IR  "+ IR + "\n" + respuesta;
    }
    
    //5.- VPN
    public String VPN (){//Suma de valores presentes menos la inversion
        double VP = 0;  //Valor presente
        double VPN;
        double k = 0.45;
        int contador;
        boolean flag = false;
        String respuesta = "El proyecto será rechazado";
        int inversion = 100000+500000+1000000+200000;
        for(contador = 0; contador<10; contador++){
            VP = ((obtenerUtilidad(2017+contador)+obtenerDepreciacion(2017 + contador))/Math.pow((1+k), contador+1))+VP; //Obtiene la suma de los VP cada año 
            //System.out.println("divisor  "+Math.pow((1+k), contador+1));
        }
        VPN = VP-inversion;    //Obtiene el Valor presente neto.
        if(VPN > 0)
            flag = true;
        else if(VPN == 0)
            respuesta = "No hay ganancia ni pérdida en el proyecto";
        if(flag)
            respuesta = "El proyecto será aprobado";
        return "VPN  "+ VPN + "\n" + respuesta;
    }
    
    //6.- TIR
    public String TIR (){
        double VP1 = 0;  //Valor presente
        double VP2 = 0;  //Valor presente con la k modificada
        double TIR;
        double resta1;  //VPN
        double resta2;  //inversion-VP
        double cociente;
        double k1 = 0.45;
        double k2 = 0.61;
        int contador;
        boolean flag = false;
        String respuesta = "El proyecto será rechazado";
        int inversion = 100000+500000+1000000+200000;
        for(contador = 0; contador<10; contador++){
            VP1 = ((obtenerUtilidad(2017+contador)+obtenerDepreciacion(2017 + contador))/Math.pow((1+k1), contador+1))+VP1; //Obtiene la suma de los VP cada año 
            //System.out.println("divisor  "+Math.pow((1+k), contador+1));
        }
        for(contador = 0; contador<10; contador++){
            VP2 = ((obtenerUtilidad(2017+contador)+obtenerDepreciacion(2017 + contador))/Math.pow((1+k2), contador+1))+VP2; //Obtiene la suma de los VP cada año 
            //System.out.println("divisor  "+Math.pow((1+k), contador+1));
        }
        resta1 = VP1-inversion;    //Obtiene el Valor presente neto.
        resta2= VP1-VP2;    //Obtiene la diferencia del original con el modificado
        cociente=resta1/resta2; //Los divide
        TIR=cociente*16;    
        if(TIR >= k1)
            flag = true;
        if(flag)
            respuesta = "El proyecto será aprobado";
        return "TIR  "+ TIR + "\n" + respuesta;//Multiplica por la diferencia de las k's
        /*System.out.println("VP1  "+VP1);
        System.out.println("VP2  "+VP2);
        System.out.println("resta1  "+resta1);
        System.out.println("resta2  "+resta2);
        System.out.println("cociente  "+cociente);
        System.out.println("TIR  "+TIR);*/
    }
    
   public double calcularPrecio(int año){
        double precio = 0.0;
        precio = 800*Math.pow(1 + 0.05 , año - 2017);
        return precio;
    }
    
    public double calcularCostoTotal(int año){
        double ctotal = 0.0;
        ctotal = obtenerCostosProd(año);
        return ctotal;
    }
    
    public double calcularCostoFijo(int año, double ctotal){
        double cfijo = 0.0;
        cfijo = ctotal - (ctotal - ((150000) * Math.pow( 1 + 0.08 , año-2017 ))); //Obtenemos el costo del mantenimiento de ese año ya que es el unico fijo
        return cfijo;
    }
    
    public double calcularCostoVariable(int año){
        double cvariable = 0.0;
        cvariable = 300*Math.pow(1 + 0.08 , año - 2017);
        return cvariable;
    }
            
    public double puntoEquilibrio(int año){
        double cvariable = calcularCostoVariable(año);
        double cfijo;
        double ctotal;
        double puntoEquilibrio;
        double RazonContribucion;   //Necesario para punto de equilibrio (1-(Costo variable/precio por unidad))
        double precio = calcularPrecio(año);   //Dice que el precio aumentara 5% cada año y el precio inicial es 800
        ctotal = calcularCostoTotal(año);
        cfijo = calcularCostoFijo(año, ctotal); //Obtenemos el costo del mantenimiento de ese año ya que es el unico fijo
        RazonContribucion = 1 - (cvariable/precio);
        puntoEquilibrio = cfijo/RazonContribucion;
        return puntoEquilibrio;
        /*System.out.println("precio= "+precio);
        System.out.println("variable= "+cvariable);
        System.out.println("fijo= "+cfijo);
        System.out.println("PE= "+puntoEquilibrio);*/
    }
    
}

