public class Client {

	/**
	 * Le point de depart du client
	 */
	private Site Depart;
	/**
	 * Le point d'arrivee du client
	 */
	private Site Arrivee;
	
	public Client(Site depart, Site arrivee) {
		this.Depart = depart;
		this.Arrivee = arrivee;
	}
	
	public void itineraire() {
		int tempsTrajet = Depart.site - Arrivee.site;
		Depart.emprunt();
		try { Thread.sleep(1000*(Math.abs(tempsTrajet))
				); } catch(InterruptedException e) {}
		Arrivee.restitution();
	}
	
	
	public static void principale() {
		Site A = new Site(0);
		Site B = new Site(2);
		Client test = new Client(A, B);
		
		A.afficher();
		B.afficher();
		test.itineraire();
		A.afficher();
		B.afficher();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		principale();
	}

}
