/**
 * Les objets instances de la classe Client representent des utilisateurs du systeme d'emprunt.
 * Le fonctionnement est le suivant : l'appel a run retire un velo du site de depart,
 * attend un temps proportionnel a la distance entre le site de depart et d'arrivee,
 * puis ajoute un velo au site d'arrivee.
 */
public class Client extends Thread {

	/**
	 * Le point de depart du client
	 */
	private Site Depart;
	/**
	 * Le point d'arrivee du client
	 */
	private Site Arrivee;	
	
	/**
     * Construit un objet instance de Client
     * @param depart Le site de depart du client
     * @param arrivee Le site d'arrivee du client
     */
	public Client(Site depart, Site arrivee) {
		this.Depart = depart;
		this.Arrivee = arrivee;
	}
	
	/**
	 * Effectue l'itineraire du client
	 */
	public void run() {
		int tempsTrajet = Depart.getNumeroSite() - Arrivee.getNumeroSite();
		Depart.emprunt();
		try { Thread.sleep(1000*(Math.abs(tempsTrajet)));}
		catch(InterruptedException e) {}
		Arrivee.restitution();
	}
	
	
	public static void principale() {
		Site A = new Site(0);
		Site B = new Site(5);
		Site D = new Site(2);
		Client test = new Client(A, B);
		Client test2 = new Client(A,D);
		
		A.afficher();
		B.afficher();
		D.afficher();
		test.start();
		test2.start();
		try {
			test.join();
			test2.join();
		}
		catch(InterruptedException e){}
		A.afficher();
		B.afficher();
		D.afficher();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		principale();
	}

}
