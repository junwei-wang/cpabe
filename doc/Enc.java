/*
 * The policy is specified as a simple string which encodes a
 * postorder traversal of threshold tree defining the access 
 * policy. As an example,
 * 
 * "foo bar fim 2of3 baf 1of2"
 * 
 * specifies a policy with two threshold gates and four leaves.
 * It is not possible to specify an attribute with whitespace in 
 * it (although "_" is allowed).
 * 
 * Numerical attributes and any other fancy stuff are not 
 * supported.
 * 
 */
public void enc(String pubfile, String policy, String inputfile, String encfile)
