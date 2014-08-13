package users

/**
 * Created by harsh on 10/8/14.
 */


//import java.util.Enumeration

import address.Address
import enumspckg.AddressType.AddressType
import enumspckg.AuthType.AuthType
import enumspckg.Country.Country
import enumspckg.Gender.Gender
import enumspckg.Industry.Industry
import enumspckg.Registration.Registration
import enumspckg.Salutation.Salutation
import enumspckg.SocialNetwork.SocialNetwork
import enumspckg.State.State
import enumspckg.Status.Status
import info.AdditionalInfo
import org.joda.time.DateTime
import utils.UserMetaData


case class Contact(name: String, email: String, mobile: String, telephone: String, fax: Option[String])

case class Authentication(password: String, hasher: String, type_of_auth: AuthType, active: Boolean)

trait ID {
  def id: Long
}

trait User {
  def username: String

  def status: Status
}

trait BaseUser extends User {
  def metadata: UserMetaData

  //def id:ID
  def active: Boolean
}

case class Guest(username: String, status: Status, metadata: UserMetaData, id: ID, active: Boolean) extends BaseUser with ID

case class Consumer(first_name: String, last_name: String, username: String, city: String, profession: String,
                    address: Address, country: Country, metadata: UserMetaData, state: String,
                    wedding_anniversary: DateTime, active: Boolean, dob: DateTime, salutation: Salutation,
                    mobile: String, gender: Gender, status: Status, telephone: String, email: String,
                    avatar: String, marital_status: String, id: ID) extends BaseUser with AdditionalInfo with ID

case class TenantUser(username: String, status: Status, organisation: String,
                      url: String, industry: Industry, contacts: Seq[Contact], TAN: String,
                      company_size: (Int, Int), company_registration: Registration,
                      social_links: SocialNetwork, id: ID) extends User with ID
