package price

import enumspckg.AddOnType.AddOnType
import enumspckg.ChargeType.ChargeType
import enumspckg.MediaType.MediaType
import enumspckg.OfferStatus.OfferStatus
import enumspckg.PriceStatus.PriceStatus
import org.joda.time.DateTime
import settings.OfferSetting
import utils.{UserMetaData, DateMetaData}

/**
 * Created by harsh on 10/8/14.
 */
case class OtherCharge(amount:Double,charge_type:ChargeType)
case class BaseAddOn(id:Long,add_on_type:AddOnType,title:String,dates:DateMetaData,users:UserMetaData,precision:Int)
case class Offer(offer_id:Long,title:String,media:List[MediaType],
                 short_description:String,adspace:String,disclaimer:String,detail:String,startdate:DateTime,
                  enddate:DateTime,effective_startdate:DateTime,effective_enddate:DateTime,status:OfferStatus,
                  dates:DateMetaData,users:UserMetaData,offersettings:OfferSetting)
case class Price(id:Long,product_group_id:Option[Long],product_id:Long,cost:Double,unit:Double,retail:Double,
                  threshold:Double,lower_limit:Double,upper_limit:Double,dates:DateMetaData,users:UserMetaData,
                  status:PriceStatus)
