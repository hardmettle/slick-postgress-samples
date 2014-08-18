package enumspckg



/**
 * Created by harsh on 8/8/14.
 */

object Status extends Enumeration{
  type Status = Value
  val Active,Inactive = Value
}
object AddressType extends Enumeration{
  type AddressType = Value
  val Home,Office,Shipping,Billing = Value
}
object MediaType extends Enumeration{
  type MediaType = Value
  val Image,Video,Gif = MediaType
}

object Salutation extends Enumeration{
  type Salutation = Value
  val Mr,Ms,Dr,Mrs = Value
}

object Country extends Enumeration{
  type Country = Value
  val India,USA,UK,Russia = Value
}

object State extends Enumeration{
  type State = Value
  val state1,state2,state3 = Value
}

object MaritalStatus extends Enumeration{
  type MaritalStatus = Value
  val married,unmarried = Value
}
object Gender extends Enumeration{
  type Gender = Value
  val Male,Female = Value
}
object Industry extends Enumeration{
  type Industry = Value
  val industry1,industry2 = Value
}
object SocialNetwork extends Enumeration{
  type SocialNetwork = Value
  val facebook,twitter,youtube,linkedin = Value
}
object Registration extends Enumeration{
  type Registration = Value
  val pvt_ltd,org,inc = Value
}
object AuthType extends Enumeration{
  type AuthType = Value
  val oAuth1,oAuth2 = Value
}
object InventoryStatus extends Enumeration{
  type InventoryStatus = Value
  val isActive_NotEditable_NotHistorical,NotActive_isEditable_NotHistorical,NotActive_NotEditable_isHistorical,
  isActive_isEditable_NotHistorical,isActive_NotEditable_isHistorical,NotActive_isEditable_isHistorical,
  isActive_isEditable_isHistorical=Value
}
object PriceStatus extends Enumeration{
  type PriceStatus = Value
  val isActive_NotEditable_NotHistorical,NotActive_isEditable_NotHistorical,NotActive_NotEditable_isHistorical,
  isActive_isEditable_NotHistorical,isActive_NotEditable_isHistorical,NotActive_isEditable_isHistorical,
  isActive_isEditable_isHistorical=Value
}
object AddOnType extends Enumeration{
  type AddOnType = Value
  val Absolute,Percentage =Value
}
object ChargeType extends Enumeration{
  type ChargeType = Value
  val Delivery,Return,Gift = Value
}
object OfferStatus extends Enumeration{
  type OfferStatus = Value
  val Live,Draft = Value
}
object AppearancePosition extends Enumeration{
  type AppearancePosition = Value
  val left,right,center,bottom,top,topleft,bottomleft,topright,bottomright = Value
}
object ProductViewType extends Enumeration{
  type ProductViewType = Value
  val viewtype1 , viewtype2 = Value
}
object DoNotDisturb extends Enumeration{
  type DoNotDisturb = Value
  val sendemail_nosms_notenabled,noemail_sendsms_notenabled,noemail_nosms_isenabled
  ,sendemail_sendsms_notenabled,sendemail_nosms_isenabled,noemail_sendsms_isenabled,
  sendemail_sendsms_isenabled,noemail_nosms_notenabled = Value
}
object DateFormat extends Enumeration{
  type DateFormat = Value
  val df1 , df2,df3,df4 = Value
}
object TimeFormat extends Enumeration{
  type TimeFormat = Value
  val tf1 , tf2,tf3,tf4 = Value
}
object DefaultTaxType extends Enumeration{
  type DefaultTaxType = Value
  val vat,servicetax,othertax = Value
}

object OrderStatus extends Enumeration{
  type OrderStatus = Value
  val New,Approved,Dispatched,Shipped,Delivered,Received,Returned,Undelivered,Cancelled,Rejected,Completed = Value
}