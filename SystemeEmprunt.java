import java.util.Random;

class SystemeEmprunt {

	/* Constantes */

	static final int NB_SITES = 5;
	static final int MAX_CLIENTS = 100;

	/* Attributs */

	private Site[] sites = new Site[NB_SITES];
	private Client[] clients = new Client[MAX_CLIENTS];
	private Camion camion = null;

	private int nbClients = 0;

	/* Constructeur du systeme d'emprunt */
	SystemeEmprunt() {

		/* Instanciation des sites */
		for(int i = 0; i < NB_SITES; i++) {
			sites[i] = new Site(i);
		}
		Random r = new Random();
		/* Instanciation des clients */
		for(int i = 0; i < MAX_CLIENTS; i++) {

			int siteDepId = r.nextInt(NB_SITES);
			int siteArrId = r.nextInt(NB_SITES);
			clients[i] = new Client(sites[siteDepId], sites[siteArrId]);

		}

		/* Instanciation du camion */
		camion = new Camion(sites);

	}

	/* Point d'entree du programme */

	public static void main(String[] args) {

		SystemeEmprunt TP2 = new SystemeEmprunt();
		//TP2.camion.tournee();
		//incrementer nbClients qlq part et decrementer quand le thread est fini
		for(Client c : TP2.clients) {
			c.start();
		}
		for(Client c : TP2.clients) {
			try {
				c.join();
			}
			catch(InterruptedException e){}
		}
		if(TP2.nbClients == 0) {
			//stop le camtar
		}

		for(Site s : TP2.sites) {
			s.afficher();
		}
	}


} // class SystemeEmprunt