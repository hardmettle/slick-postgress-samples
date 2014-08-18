package price

import enumspckg.AddOnType.AddOnType
import enumspckg.ChargeType.ChargeType
import enumspckg.MediaType.MediaType
import enumspckg.OfferStatus.OfferStatus
import enumspckg.PriceStatus.PriceStatus
import models.{EntityTable, Entity, CrudComponent}
import org.joda.time.DateTime
import play.twirl.api.TemplateMagic.anyToDefault
import settings.OfferSetting
import utils.{UserMetaData, DateMetaData}

/**
 * Created by harsh on 10/8/14.
 */
sealed trait GlobalPrice extends Entity[Long] {

  def productGroupId: Option[Long]

  def productId: Long

  def cost: Double

  def unit: Double

  def retail: Double

  def threshold: Double

  def lowerLimit: Double

  def upperLimit: Double

  def dateMetaData: DateMetaData

  def userMetaData: UserMetaData

  def status: PriceStatus
}

sealed trait BaseAddOn extends Entity[Long] {

  def addOnType: AddOnType

  def title: String

  def dateMetaData: DateMetaData

  def userMetaData: UserMetaData

  def precision: Int
}

sealed trait BaseOffer extends Entity[Long] {
  
  def title: String

  def media: List[MediaType]

  def shortDescription: String

  def adSpace: String

  def disclaimer: String

  def detail: String

  def startDate: DateTime

  def endDate: DateTime

  def effectiveStartDate: DateTime

  def effectiveEndDate: DateTime

  def status: OfferStatus

  def dateMetaData: DateMetaData

  def userMetaData: UserMetaData

  def offerSettingsId: Long
}

case class OtherCharge(id: Option[Long], amount: Double, chargeType: ChargeType, addOnType: AddOnType, title: String,
                       dateMetaData: DateMetaData, userMetaData: UserMetaData, precision: Int) extends BaseAddOn


case class Offer(id: Option[Long], title: String, media: List[MediaType],
                 shortDescription: String, adSpace: String, disclaimer: String, detail: String, startDate: DateTime,
                 endDate: DateTime, effectiveStartDate: DateTime, effectiveEndDate: DateTime, status: OfferStatus,
                 dateMetaData: DateMetaData, userMetaData: UserMetaData, offerSettingsId: Long) extends BaseOffer

case class Price(id: Option[Long], productGroupId: Option[Long], productId: Long, cost: Double, unit: Double, retail: Double,
                 threshold: Double, lowerLimit: Double, upperLimit: Double, dateMetaData: DateMetaData, userMetaData: UserMetaData,
                 status: PriceStatus) extends GlobalPrice

trait PriceComponent extends CrudComponent {

  import driver.simple._

  class Prices(tags: Tag) extends Table[Price](tags, "prices") with EntityTable[Long] {


    def id = column[Long]("price_id", O.AutoInc, O.PrimaryKey)

    def productGroupId = column[Option[Long]]("product_group_id")

    def productId = column[Long]("product_id")

    def cost = column[Double]("cost")

    def unit = column[Double]("unit")

    def retail = column[Double]("retail")

    def threshold = column[Double]("threshold")

    def lowerLimit = column[Double]("lower_limit")

    def upperLimit = column[Double]("upper_limit")

    //def dates = column DateMetaData,users:UserMetaData,
    def createdBy = column[Long]("created_by")

    def modifiedBy = column[Option[Long]]("modified_by")

    def userMetaData = (createdBy, modifiedBy) <>(UserMetaData.tupled, UserMetaData.unapply)

    def createdOn = column[DateTime]("created_on")

    def modifiedOn = column[Option[DateTime]]("modified_on")

    def dateMetaData = (createdOn, modifiedOn) <>(DateMetaData.tupled, DateMetaData.unapply)

    def status = column[PriceStatus]("price_status")

    //def productIdFK = foreignKey("fk_product_id", productId, products)(_.id)//TBD

    def * = (id.?, productGroupId, productId, cost, unit, retail, threshold, lowerLimit, upperLimit, dateMetaData, userMetaData,
      status) <>(Price.tupled, Price.unapply)
  }

  object Prices extends Crud[Prices, Price, Long] {

    val query = TableQuery[Prices]

    override def withId(price: Price, id: Long)(implicit session: Session): Price = price.copy(id = Option(id))

  }

}

trait OfferComponent extends CrudComponent {

  import driver.simple._

  class Offers(tags: Tag) extends Table[Offer](tags, "offers") with EntityTable[Long] {

    def id = column[Long]("offer_id")

    def title = column[String]("title")

    def media = column[List[MediaType]]("media_type")

    def shortDescription = column[String]("short_description")

    def adSpace = column[String]("ad_space")

    def disclaimer = column[String]("disclaimer")

    def detail = column[String]("detail")

    def startDate = column[DateTime]("start_date")

    def endDate = column[DateTime]("end_date")

    def effectiveStartDate = column[DateTime]("effective_start_date")

    def effectiveEndDate = column[DateTime]("effective_end_date")

    def status = column[OfferStatus]("offer_Status")

    def createdBy = column[Long]("created_by")

    def modifiedBy = column[Option[Long]]("modified_by")

    def userMetaData = (createdBy, modifiedBy) <>(UserMetaData.tupled, UserMetaData.unapply)

    def createdOn = column[DateTime]("created_on")

    def modifiedOn = column[Option[DateTime]]("modified_on")

    def dateMetaData = (createdOn, modifiedOn) <>(DateMetaData.tupled, DateMetaData.unapply)

    def offerSettingsId = column[Long]("offer_setting_id")

    //def offerSettingIdFK = foreignKey("fk_offer_settings_id", offerSettingsId, offer_settings)(_.id)//TBD

    def * = (id.?, title, media, shortDescription, adSpace, disclaimer, detail, startDate,
      endDate, effectiveStartDate, effectiveEndDate, status, dateMetaData, userMetaData, offerSettingsId) <>(Offer.tupled, Offer.unapply)
  }

  object Offers extends Crud[Offers, Offer, Long] {
    val query = TableQuery[Offers]

    override def withId(offer: Offer, id: Long)(implicit session: Session): Offer
    = offer.copy(id = Option(id))
  }

}

trait OtherChargeComponent extends CrudComponent {

  import driver.simple._

  class OtherCharges(tags: Tag) extends Table[OtherCharge](tags, "othercharges") with EntityTable[Long] {

    def id = column[Long]("other_charge_id")

    def amount = column[Double]("amount")

    def chargeType = column[ChargeType]("charge_type")

    def addOnType = column[AddOnType]("add_on_type")

    def title = column[String]("title")

    def createdOn = column[DateTime]("created_on")

    def modifiedOn = column[Option[DateTime]]("modified_on")

    def dateMetaData = (createdOn, modifiedOn) <>(DateMetaData.tupled, DateMetaData.unapply)

    def createdBy = column[Long]("created_by")

    def modifiedBy = column[Option[Long]]("modified_by")

    def userMetaData = (createdBy, modifiedBy) <>(UserMetaData.tupled, UserMetaData.unapply)

    def precision = column[Int]("precision")

    def * = (id.?, amount, chargeType, addOnType, title, dateMetaData, userMetaData, precision) <>(OtherCharge.tupled, OtherCharge.unapply)
  }

  object OtherCharges extends Crud[OtherCharges, OtherCharge, Long] {

    val query = TableQuery[OtherCharges]

    override def withId(otherCharge: OtherCharge, id: Long)(implicit session: Session): OtherCharge
    = otherCharge.copy(id = Option(id))
  }

}
