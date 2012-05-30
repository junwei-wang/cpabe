package main
import cpabe.Cpabe
import org.clapper.argot._
import ArgotConverters._

object JCpabeKeygen {

  val parser = new ArgotParser("JCpabeKeygen", preUsage=Some("Version 1.0"))
  
  var defPrivfile = "priv_key"
  var defPubfile = "pub_key"
  var defMskfile = "master_key"
    
  var attr_str = 
    parser.parameter[String]("attr_string", "Attribute string", false)
    
      
    
  val pubfile = 
    parser.option[String](List("p", "public-file"), "p","Public Key location")
    

  var mskfile =
	parser.option[String](List("m", "master-file"), "m","Master Key location")
	
	
  var privfile =
	parser.option[String](List("o", "private-file"), "o","Private Key output location")
	
  
  def run = {
	  val cp = new Cpabe()
	  println("Starting keygen...")
	  cp.keygen(pubfile.value.getOrElse(defPubfile),
	      privfile.value.getOrElse(defPrivfile),
	      mskfile.value.getOrElse(defMskfile),
	      attr_str.value.get)
	  println("Done.")
  }
  
  def main(args: Array[String]) {
    try {
      parser.parse(args)
      run
    } catch {
      case e: ArgotUsageException => println(e.message)
    }
  }
	  
  
}