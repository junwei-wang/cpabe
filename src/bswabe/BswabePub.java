package bswabe;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

public class BswabePub{
	/*
	 * A public key
	 */
	int[] pairing_desc;
	Pairing p;				
	Element g;				/* G_1 */
	Element h;				/* G_1 */
	Element gp;			/* G_2 */
	Element g_hat_alpha;	/* G_T */
}
