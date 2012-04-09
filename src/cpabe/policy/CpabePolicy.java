package cpabe.policy;

import bswabe.BswabePolicy;

public class CpabePolicy {
	int k; /* one if leaf, otherwise threshold */
	String attr; /* attribute string if leaf, null otherwise */
	BswabePolicy[] children; /* null for leaf */
}
