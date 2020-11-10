/**
 * Les objets instances de la classe Site representent un ensemble velos, 
 * stockes au meme endroit. il n'est pas possible que deux client saisissent deux velos au meme moment. 
 */
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
	private int NumeroSite;

	/**
	 * Creer un nouvel objet instance de site
	 * @param numeroSite Le numero du site
	 */
	public Site(int numeroSite) {
		this.NumeroSite = numeroSite;
		this.nbVelos = STOCK_INIT;
	}

	/**
	 * 
	 * @return Le numero du site 
	 */
	public int getNumeroSite() {
		return NumeroSite;
	}

	/**
	 * 
	 * @return Le nombre de velo sur le site
	 */
	public int getNbVelos() {
		return nbVelos;
	}

	/**
	 * Prend un velo sur le site, si il n'y a plus de velos disponible on attend
	 */
	public synchronized void emprunt() {
		while(nbVelos == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nbVelos--;
		notify();

		System.out.println("Le client " + Thread.currentThread().getName() +" emprunte 1 velo du site n°" + NumeroSite +", il reste : " + nbVelos + " velo(s).");
	}

	/**
	 * Restitue un velo sur le site, si le nombre max de velos est atteint, on attend
	 */
	public synchronized void restitution() {
		while(nbVelos == STOCK_MAX) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nbVelos++;
		notify();

		System.out.println("Le client " + Thread.currentThread().getName() +" restitue 1 velo au site n°" + NumeroSite +", il y a : " + nbVelos + " velo(s).");
	}

	/**
	 * Affiche l'etat du site
	 */
	public void afficher() {
		System.out.println("Le site n°" + NumeroSite + " contient " + nbVelos + " velo(s).");
	}

	/**
	 * Fonction pour tester toute les methodes 
	 */
	public static void principale() {
		Site principale = new Site(1);
		principale.afficher();
		for(int i=0; i<5; i++) {
			principale.emprunt();
		}
		principale.afficher();
		//principale.emprunt();
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
