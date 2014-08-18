package inventory

import enumspckg.InventoryStatus.InventoryStatus
import models.{Entity, EntityTable, CrudComponent}
import myUtils.WithMyDriver
import org.joda.time.DateTime
import utils.{UserMetaData, DateMetaData}
import play.api.data.format.Formats
import play.api.data.format.Formatter

/**
 * Created by harsh on 10/8/14.
 */
/*
Inventory class representing detailed information about inventory record of products

@constructor creates an inventory with inventoryId,productId,total,saleableQuantity,offlineQuantity
threshold,availableQuantity,soldQuantity,offlineAvailableQuantity,offlineSoldQuantity,inStock,inStockOffline,
thresholdOffline,dateMetaData,userMetaData,status

@param inventoryId
@param productId
@param total
@param saleableQuantity
@param offlineQuantity
@param threshold
@param availableQuantity
@param soldQuantity
@param offlineAvailableQuantity
@param offlineSoldQuantity
@param inStock
@param inStockOffline
@param thresholdOffline
@param dateMetaData
@param userMetaData
@param inventoryStatus

 */
case class Inventory(id: Option[Long], productId: Long, total: Int, saleableQuantity: Int,
                     offlineQuantity: Int, threshold: Int, availableQuantity: Int,
                     soldQuantity: Int, offlineAvailableQuantity: Int, offlineSoldQuantity: Int ,
                     inStock: Int, inStockOffline: Int , thresholdOffline: Int, dateMetaData: DateMetaData, 
                     userMetaData: UserMetaData,inventoryStatus: InventoryStatus) extends Entity[Long]

trait InventoryComponent extends CrudComponent {

  import driver.simple._
  import models.current.dao._

  class Inventories(tags: Tag) extends Table[Inventory](tags, "inventories") with EntityTable[Long]{

    def id = column[Long]("inventory_id")

    def productId = column[Long]("product_id")

    def total = column[Int]("total")

    def saleableQuantity = column[Int]("saleable_quantity")

    def offlineQuantity = column[Int]("offline_quantity")

    def threshold = column[Int]("threshold")

    def availableQuantity = column[Int]("available_quantity")

    def soldQuantity = column[Int]("sold_quantity")

    def offlineAvailableQuantity = column[Int]("offline_available_quantity")

    def offlineSoldQuantity = column[Int]("offline_sold_quantity")

    def inStock = column[Int]("in_stock")

    def inStockOffline = column[Int]("in_stock_offline")

    def thresholdOffline = column[Int]("threshold_offline")

    def createdOn = column[DateTime]("created_on")

    def modifiedOn = column[Option[DateTime]]("modified_on")

    def dateMetaData = (createdOn, modifiedOn) <>(DateMetaData.tupled, DateMetaData.unapply)

    def createdBy = column[Long]("created_by")

    def modifiedBy = column[Option[Long]]("modified_by")

    def userMetaData = (createdBy, modifiedBy) <> (UserMetaData.tupled, UserMetaData.unapply)

    def inventoryStatus = column[InventoryStatus]("inventory_status")

    //def productIDFK = foreignKey("fk_product_id",productId,products)(._productId)//TODO


    def * = (id.?,productId,total,saleableQuantity,offlineQuantity,threshold,availableQuantity,soldQuantity,
      offlineAvailableQuantity,offlineSoldQuantity,inStock,inStockOffline,
      thresholdOffline,dateMetaData,userMetaData,inventoryStatus) <> (Inventory.tupled,Inventory.unapply)
  }
  object Inventories extends Crud[Inventories, Inventory, Long] {
    val query = TableQuery[Inventories]

    override def withId(inventory: Inventory, id: Long)(implicit session: Session): Inventory
    = inventory.copy(id = Option(id))
  }


}