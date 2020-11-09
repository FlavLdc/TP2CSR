public class Site {
	/**
	 * Nombre de velo initialement sur le site
	 */
	static final int STOCK_INIT = 5;
	/**
	 * Nombre maximum de velo sur le site
	 */
	static final int STOCK_MAX = 10;
	/**
	 * Nombre de velo a partir duquel le camion prend 
	 * les velos excedentaires par rapport au STOCK_INIT
	 */
	static final int BORNE_SUP = 8; //strictement
	/**
	 * Nombre de velo a partir duquel le camion rajoute
	 * des velos jusqu'a atteindre STOCK_INIT (si possible)
	 */
	static final int BORNE_INF = 2; //strictement	
	/**
	 * Nombre de velos sur le site
	 */
	private int nbVelos;
	/**
	 * Numero du site
	 */
	public int site;
	
	public Site(int numeroSite) {
		this.site = numeroSite;
		this.nbVelos = STOCK_INIT;
	}

	
	public synchronized void emprunt() {
		nbVelos--;
	}
	
	public synchronized void restitution() {
		nbVelos++;
	}
	
	/**
	 * Affiche l'etat du site
	 */
	public void afficher() {
		System.out.println("Le site n°" + site + " contient " + nbVelos + " velo(s).");
	}
	
	/**
	 * Fonction pour tester toute les méthodes 
	 */
	public static void principale() {
		Site principale = new Site(1);
		principale.afficher();
		for(int i=0; i<5; i++) {
			principale.emprunt();
		}
		principale.afficher();
		principale.emprunt();
		principale.afficher();
		principale.restitution();
		principale.restitution();
		principale.restitution();
		principale.afficher();
	}

	/** 
	 * Methode d'auto-test pour la classe Stock
	 * @param args Non utilise
	 */
	static public void main(String[] args) {
		principale();

	}
	
}
