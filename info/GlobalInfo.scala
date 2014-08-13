package info

import address.Address
import enumspckg.Country._
import enumspckg.Gender.Gender
import enumspckg.MaritalStatus._
import enumspckg.Salutation._
import org.joda.time.DateTime


/**
 * Created by harsh on 10/8/14.
 */
trait BaseInfo{
  def email:String
  def salutation:Salutation
  def first_name:String
  def last_name: String
}
trait AccountInfo extends BaseInfo{
  def mobile : String
  def dob : DateTime
  def country : Country
}

trait AdditionalInfo extends AccountInfo{
  def avatar :String
  def address : Address
  def city : String
  def state : String
  def marital_status : MaritalStatus
  def wedding_anniversary : DateTime
  def profession : String
  def telephone : String
  def gender : Gender
}