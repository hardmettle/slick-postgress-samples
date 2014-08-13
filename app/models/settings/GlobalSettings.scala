package settings

import enumspckg.AppearancePosition.AppearancePosition
import enumspckg.DefaultTaxType.DefaultTaxType
import enumspckg.DoNotDisturb.DoNotDisturb
import enumspckg.ProductViewType.ProductViewType
import org.joda.time.DateTime
import users.Contact
import utils._

/**
 * Created by harsh on 10/8/14.
 */
sealed trait GlobalSettings {
  def id:Long
  def enable:Boolean
}
case class OfferSetting(id:Long,enable:Boolean,attachments:List[String],
                        position:AppearancePosition,date:DateTime,size:Size) extends GlobalSettings
case class ReviewSetting(id:Long,enable:Boolean,defaultNotToShow:Boolean,approval_needed:Boolean,
                         alert_on_negative_reviews:Boolean) extends GlobalSettings
case class AppearanceSettings(id:Long,enable:Boolean,logo:Media,headers:String,footer:String) extends GlobalSettings

case class ProductSettings(id:Long,enable:Boolean,default_view_type:ProductViewType) extends GlobalSettings

case class SupportSettings(tollfreenum:Option[TollFreeNumber],customer_care_email:String,
                           primary_contact:Contact,secondary_contact:Contact,worktimings:WorkTimings,businessDays:List[String])
case class FAQSettings()//TBD

case class TenantSettings(id:Long,enable:Boolean,datetimeformat:DateTimeFormat,systemDND:DoNotDisturb,dnd:DoNotDisturb,
                           supportsetting:SupportSettings,reviewsettings:ReviewSetting,productsetting:ProductSettings,
                           faqsettings:FAQSettings,appearancesettings:AppearanceSettings) extends GlobalSettings

case class MediaSettings(id:Long,enable:Boolean,thumbnailsize:Size,orderofthumbnail:String) extends GlobalSettings
case class TaxSettings(id:Long,enable:Boolean,show_tax_by_default:Boolean,
                       precision:Int,default_tax_value:Double,default_tax_type:DefaultTaxType) extends GlobalSettings

case class MyAccountSettings(datetimeformat:DateTimeFormat,dnd:DoNotDisturb,media:MediaSettings,tax:TaxSettings)