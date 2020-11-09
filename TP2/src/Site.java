class Site {

/* Constantes associees au site */

public Site(int i) {
    }
    // Nombre de vélos au départ sur le site
static final int STOCK_INIT = 5;

    // Nombre max de vélos sur le site
static final int STOCK_MAX = 10;
    // Nombre de vélos à partir duquel le camion récupère les vélos jusqu'à atteindre le stock init
static final int BORNE_SUP = 8;
    //Nombre de vélos à partir duquel le camion dépose les vélos jusqu'à atteindre le stock init si possible
static final int BORNE_INF = 2;

private int nbVelos = 0;

    // Méthode correspondant à la récupération des vélos lorsque le nombre de vélos dépassent la borne supérieure
public void PriseVelos() {
    if (nbVelos > BORNE_SUP) {
       StockCam = nbVelos - STOCK_INIT;
    }
}
    // Méthode correspondant au dépôt des vélos lorsque le nombre de vélos est inférieur à la borne inférieure
public void DepotVelos() {

}


}


