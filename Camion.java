/**
 * Les objets instance de Camion representent un camion qui dessert un ensemble de site.
 * Le fonctionnement est le suivant : l'appel a run va verifier que le site sur lequel est 
 * gare le camion a bien un nombre reglementaire de velo. Si c'est le cas, le camion passe au site 
 * suivant, sinon il fais au mieux pour regulariser le nombre de velo du site puis passe au site suivant.
 * Une fois arrive au dernier site, le camion revient au 1er site sans s'arreter sur les autres.
 */
public class Camion extends Thread{

	/**
	 * Nombre de velos dans le camion
	 */
	private int stockCamion;
	/**
	 * Le tableau qui contient les numeros de tous les sites
	 */
	private Site[] ensembleSite;
	
	/**
	 * Construit un objet instance de Camion
	 * @param sites un tableau des sites du systeme
	 */
	public Camion(Site[] sites) {
		this.stockCamion = 0;
		this.ensembleSite = sites;
	}

	/**
	 * Lance le camion 
	 */
	public void run() {
		Thread.currentThread().setName("d'equilibrage");
		int j = 0;
		Site siteActuel = ensembleSite[j];
		int tempsTrajet;
		while(!isInterrupted()) {
			Thread.currentThread().setPriority(NORM_PRIORITY);
			tempsTrajet = 250; //le camion va deux fois plus vite qu'un cycliste, le temps du trajet est toujours le meme puisque chaque site est a equidistance de ses voisins
			System.out.println("Le camion arrive au site n°" + j);
			//System.out.println("Je suis bien dans la boucle");
			if(siteActuel.getNbVelos() > Site.BORNE_SUP) { //Si le site a plus de velos que la borne sup, on le ramene au stock initial
				Thread.currentThread().setPriority(MAX_PRIORITY); //permet au camion de ne pas reveiller les threads client pendant qu'il recupere les velos
				for(int i = siteActuel.getNbVelos(); i>Site.STOCK_INIT+1; i--) { 
					siteActuel.emprunt();
					stockCamion++;
					System.out.println("Le camion " + Thread.currentThread().getName() +" recupere 1 velo du site n°" + siteActuel.getNumeroSite() +", il reste : " + siteActuel.getNbVelos() + " velo(s). Le camion a " + stockCamion + " velo(s) dans son coffre.");
				}
				Thread.currentThread().setPriority(NORM_PRIORITY); //Le camion reveille les threads clients quand il prend le dernier velo
				siteActuel.emprunt();
				stockCamion++;
				System.out.println("Le camion " + Thread.currentThread().getName() +" recupere 1 velo du site n°" + siteActuel.getNumeroSite() +", il reste : " + siteActuel.getNbVelos() + " velo(s). Le camion a " + stockCamion + " velo(s) dans son coffre.");
				//ca fonctionne tres bien mais il y a un probleme d'affichage pour ce dernier lorsqu'un client est en attente il s'affiche avant
			}
			else if(siteActuel.getNbVelos() < Site.BORNE_INF && stockCamion > 0) { //Si le site a moins de velos que la borne inf, on le ramene au stock initial, dans la limite du possible
				Thread.currentThread().setPriority(MAX_PRIORITY);
				while(stockCamion > 1 && siteActuel.getNbVelos() < Site.STOCK_INIT-1) {
					siteActuel.restitution();
					stockCamion--;
					System.out.println("Le camion " + Thread.currentThread().getName() +" rajoute 1 velo au site n°" + siteActuel.getNumeroSite() +", il y a : " + siteActuel.getNbVelos() + " velo(s). Il reste " + stockCamion + " velo(s) dans le camion.");
				}
				Thread.currentThread().setPriority(NORM_PRIORITY);
				siteActuel.restitution();
				stockCamion--;
				System.out.println("Le camion " + Thread.currentThread().getName() +" rajoute 1 velo au site n°" + siteActuel.getNumeroSite() +", il y a : " + siteActuel.getNbVelos() + " velo(s). Il reste " + stockCamion + " velo(s) dans le camion.");
				//ca fonctionne tres bien mais il y a un probleme d'affichage pour ce dernier, lorsqu'un client est en attente il s'affiche avant, ce qui fausse siteActuel.getNbVelos()
			}
			j++;
			if(j>=SystemeEmprunt.NB_SITES) {
				tempsTrajet = tempsTrajet*(j-1); //le temps de retour au point 0
				j = 0;
				siteActuel = ensembleSite[j];				
			}
			else {
				siteActuel = ensembleSite[j];
			}
			
			try {Thread.sleep(tempsTrajet);} 
			catch (InterruptedException e) {
				System.out.println("Fin de la tournee du camion !");
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Site[] test = new Site[SystemeEmprunt.NB_SITES];
		for(int i = 0; i < SystemeEmprunt.NB_SITES; i++)
			test[i] = new Site(i);
		Camion camion = new Camion(test);
		camion.start();
		camion.interrupt();
	}

}
