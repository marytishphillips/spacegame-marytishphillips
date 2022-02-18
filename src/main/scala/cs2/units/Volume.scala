package cs2.units

class Volume(private var lit:Double = 0.0) {
  //Field is in the argument list above - the volume stored in LITERS

  //Basic math operators to add, subtract, and scale volumes
  def + (other:Volume):Volume = {
    new Volume(this.lit + other.lit) 
  }
  def += (other:Volume):Unit  = {
    this.lit += other.lit
  }
  def - (other:Volume):Volume = {
    new Volume(this.lit - other.lit) 
  }
  def -= (other:Volume):Unit  = {
    this.lit -= other.lit
  }
  def * (scalar:Double):Volume = {
    new Volume(this.lit * scalar)
  }
  def *= (scalar:Double):Unit  = {
    this.lit *= scalar
  }
  def / (scalar:Double):Volume = {
    new Volume(this.lit / scalar)
  }
  def /= (scalar:Double):Unit  = {
    this.lit /= scalar
  }

  //Getter functions that return in a variety of units
  def liters():Double = lit
  def milliliters():Double = lit * 1000
  def gallons():Double = lit * 0.264172
  def quarts():Double = lit * 1.0567
  def pints():Double = lit * 2.11338
  def cups():Double = lit * 4.16667
  def teaspoons():Double = lit * 168.936
  def tablespoons():Double = lit * 56.3121
}

object Volume {
  //"Constructor" apply methods operating in liters
  def apply():Volume = new Volume()
  def apply(amt:Double):Volume = new Volume(amt)

  //Alternative "static" methods to create volumes in other units
  def liters(amt:Double):Volume = { new Volume(amt) }
  def milliliters(amt:Double):Volume = { new Volume(amt / 1000) }
  def gallons(amt:Double):Volume = { new Volume(amt * 3.78541) }
  def quarts(amt:Double):Volume = { new Volume(amt * 0.946453) }
  def pints(amt:Double):Volume = { new Volume(amt * 0.473176) }
  def cups(amt:Double):Volume = { new Volume(amt * 0.24) }
  def teaspoons(amt:Double):Volume = { new Volume(amt * 0.00492892) }
  def tablespoons(amt:Double):Volume = { new Volume(amt * 0.0147868) }

  def main(args:Array[String]):Unit = {
    val x = Volume(4.0)
    println(x.gallons())
  }
}

