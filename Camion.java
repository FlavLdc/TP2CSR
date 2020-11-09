public class Camion {
	
	/**
	 * Nombre de velos dans le camion
	 */
	private int VelosCam;
	private Site depart;
	
	public Camion(Site[] sites) {
		this.VelosCam = 0;
		Site depart = sites[0]; 
		Site fin = sites[SystemeEmprunt.NB_SITES-1];
	}

	public void VeloExc(){
		if(depart.getNbVelos() > Site.BORNE_SUP) {
			for(int i = depart.getNbVelos(); i> Site.STOCK_INIT;i--){
				VelosCam++;
				depart.emprunt();
			}
		}
	}
	
	public void VeloInf(){
		if(depart.getNbVelos()<Site.BORNE_INF){
			for(int i =depart.getNbVelos();i<Site.STOCK_INIT;i++){
				VelosCam--;
				depart.restitution();
			}
		}
	}


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
