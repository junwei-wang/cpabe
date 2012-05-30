package main
import cpabe.Cpabe
import org.clapper.argot._
import ArgotConverters._

object JCpabeSetup {
  
  val pubDefault = "pub_key"
  val mskDefault = "master_key"
  
  val parser = new ArgotParser("JCpabeKeygen", preUsage=Some("Version 1.0"))
  
  val pubfile = 
    parser.option[String](List("p", "public-file"), "p","Public Key output location")
   
  var mskfile =
	parser.option[String](List("m", "master-file"), "m","Master Key output location")
  
  def run = {
	  val cp = new Cpabe()
	  println("Setting up...")
	  cp.setup(pubfile.value.getOrElse(pubDefault),
	      mskfile.value.getOrElse(mskDefault))
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