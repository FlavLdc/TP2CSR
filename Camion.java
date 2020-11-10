public class Camion {
	
	/**
	 * Nombre de velos dans le camion
	 */
	private int VelosCam;
	/**
	 * Le site ou le camion se trouve actuellement
	 */
	private Site siteActuel;
	
	public Camion(Site[] sites) {
		this.VelosCam = 0;
		this.siteActuel = sites[0];
	}

	public void tournee() {
		//while(reste des clients dans le systemeEmprunt)
		if(siteActuel.getNbVelos() > Site.BORNE_SUP) { //Si le site a plus de velos que la borne sup, 
			for(int i = siteActuel.getNbVelos(); i>Site.STOCK_INIT; i--) { //on le ramene au stock initial
				siteActuel.emprunt();
				VelosCam++;
			}
		}
		else if(siteActuel.getNbVelos() < Site.BORNE_INF) {//Si le site a moins de velos que la borne inf,
			while(VelosCam > 0 && siteActuel.getNbVelos() < Site.STOCK_INIT) {//on le ramene au stock initial, dans la limite du possible
				siteActuel.restitution();
				VelosCam--;
			}
		}
		
		try {Thread.sleep(1000);} //le temps du trajet est toujours le meme puisque chaque site est a equidistance de ses voisins
		 catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
