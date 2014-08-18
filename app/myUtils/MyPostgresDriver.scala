package myUtils

import com.github.tminglei.slickpg._
import enumspckg.AddOnType._

import slick.driver.PostgresDriver

import models.AccountStatuses

import enumspckg._

trait WithMyDriver {
  val driver: MyPostgresDriver
}

////////////////////////////////////////////////////////////
trait MyPostgresDriver extends PostgresDriver
with PgArraySupport
with PgDateSupportJoda
with PgRangeSupport
with PgHStoreSupport
with PgPlayJsonSupport
with PgSearchSupport
with PgEnumSupport
with PgPostGISSupport {

  //override lazy val Implicit = new ImplicitsPlus {}
  //override val simple = new SimpleQLPlus {}

  override lazy val Implicit = new ImplicitsPlus with MyEnumImplicits {}
  override val simple = new SimpleQLPlus with MyEnumImplicits {}

  trait MyEnumImplicits {
    implicit val accountStatusTypeMapper = createEnumJdbcType("account_status", AccountStatuses)
    implicit val accountStatusListTypeMapper = createEnumListJdbcType("account_status", AccountStatuses)

    implicit val accountStatusColumnExtensionMethodsBuilder = createEnumColumnExtensionMethodsBuilder(AccountStatuses)
    implicit val accountStatusOptionColumnExtensionMethodsBuilder = createEnumOptionColumnExtensionMethodsBuilder(AccountStatuses)




    implicit val addressTypeTypeMapper = createEnumJdbcType("address_type",AddressType)
    implicit val addressTypeListTypeMapper = createEnumListJdbcType("address_type",AddressType)

    implicit val addressTypeColumnExtensionMethodsBuilder = createEnumColumnExtensionMethodsBuilder(AddressType)
    implicit val addressTypeOptionColumnExtensionMethodsBuilder = createEnumOptionColumnExtensionMethodsBuilder(AddressType)

    implicit val countryTypeMapper = createEnumJdbcType("country",Country)
    implicit val countryListTypeMapper = createEnumListJdbcType("country",Country)

    implicit val countryColumnExtensionMethodsBuilder = createEnumColumnExtensionMethodsBuilder(Country)
    implicit val countryOptionColumnExtensionMethodsBuilder = createEnumOptionColumnExtensionMethodsBuilder(Country)

    implicit val orderStatusTypeMapper = createEnumJdbcType("order_status",OrderStatus)
    implicit val orderStatusListTypeMapper = createEnumListJdbcType("order_status",OrderStatus)

    implicit val orderStatusExtensionMethodsBuilder = createEnumColumnExtensionMethodsBuilder(OrderStatus)
    implicit val orderStatusOptionColumnExtensionMethodsBuilder = createEnumOptionColumnExtensionMethodsBuilder(OrderStatus)

    implicit val stateTypeMapper = createEnumJdbcType("state",State)
    implicit val stateListTypeMapper = createEnumListJdbcType("state",State)

    implicit val stateExtensionMethodsBuilder = createEnumColumnExtensionMethodsBuilder(State)
    implicit val stateOptionColumnExtensionMethodsBuilder = createEnumOptionColumnExtensionMethodsBuilder(State)

    implicit val inventoryStatusTypeMapper = createEnumJdbcType("state",InventoryStatus)
    implicit val inventoryStatusListTypeMapper = createEnumListJdbcType("state",InventoryStatus)

    implicit val inventoryStatusExtensionMethodsBuilder = createEnumColumnExtensionMethodsBuilder(InventoryStatus)
    implicit val inventoryStatusOptionColumnExtensionMethodsBuilder = createEnumOptionColumnExtensionMethodsBuilder(InventoryStatus)


    implicit val priceStatusTypeMapper = createEnumJdbcType("state",PriceStatus)
    implicit val priceStatusListTypeMapper = createEnumListJdbcType("state",PriceStatus)

    implicit val priceStatusExtensionMethodsBuilder = createEnumColumnExtensionMethodsBuilder(PriceStatus)
    implicit val priceStatusOptionColumnExtensionMethodsBuilder = createEnumOptionColumnExtensionMethodsBuilder(PriceStatus)

    implicit val mediaTypeTypeMapper = createEnumJdbcType("state",MediaType)
    implicit val mediaTypeListTypeMapper = createEnumListJdbcType("state",MediaType)

    implicit val mediaTypeExtensionMethodsBuilder = createEnumColumnExtensionMethodsBuilder(MediaType)
    implicit val mediaTypeOptionColumnExtensionMethodsBuilder = createEnumOptionColumnExtensionMethodsBuilder(MediaType)

    implicit val offerStatusTypeMapper = createEnumJdbcType("state",OfferStatus)
    implicit val offerStatusListTypeMapper = createEnumListJdbcType("state",OfferStatus)

    implicit val offerStatusExtensionMethodsBuilder = createEnumColumnExtensionMethodsBuilder(OfferStatus)
    implicit val offerStatusOptionColumnExtensionMethodsBuilder = createEnumOptionColumnExtensionMethodsBuilder(OfferStatus)

    implicit val chargeTypeTypeMapper = createEnumJdbcType("state",ChargeType)
    implicit val chargeTypeListTypeMapper = createEnumListJdbcType("state",ChargeType)

    implicit val chargeTypeExtensionMethodsBuilder = createEnumColumnExtensionMethodsBuilder(ChargeType)
    implicit val chargeTypeOptionColumnExtensionMethodsBuilder = createEnumOptionColumnExtensionMethodsBuilder(ChargeType)

    implicit val addOnTypeTypeMapper = createEnumJdbcType("state",AddOnType)
    implicit val addOnTypeListTypeMapper = createEnumListJdbcType("state",AddOnType)

    implicit val addOnTypeExtensionMethodsBuilder = createEnumColumnExtensionMethodsBuilder(AddOnType)
    implicit val addOnTypeOptionColumnExtensionMethodsBuilder = createEnumOptionColumnExtensionMethodsBuilder(AddOnType)
  }

  //////
  trait ImplicitsPlus extends Implicits
  with ArrayImplicits
  with DateTimeImplicits
  with RangeImplicits
  with HStoreImplicits
  with JsonImplicits
  with SearchImplicits
  with PostGISImplicits

  trait SimpleQLPlus extends SimpleQL
  with ImplicitsPlus
  with SearchAssistants
  with PostGISAssistants

}

object MyPostgresDriver extends MyPostgresDriver
