package address

import enumspckg.AddressType._
import enumspckg.Country._
import enumspckg.State._

/**
 * Created by harsh on 10/8/14.
 */
sealed trait GlobalAddress{
  def line1:String
  def line2:String
  def line3:Option[String]
  def zipCode:Long
  def landmark:String
  def location:String
  def state:State
  def country:Country
  def addressType:AddressType
}
trait Address extends GlobalAddress {
  def addressId:Long
  def line1:String
  def line2:String
  def line3:Option[String]
  def zipCode:Long
  def landmark:String
  def location:String
  def state:State
  def country:Country
  def addressType:AddressType
}
trait ShippingAddress extends GlobalAddress {

  def shippingAddressId:Long
  def line1:String
  def line2:String
  def line3:Option[String]
  def zipCode:Long
  def landmark:String
  def location:String
  def state:State
  def country:Country
  def addressType:AddressType
  def phone:String
  def alternatePhone:Option[String]
}