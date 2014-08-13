package inventory

import enumspckg.InventoryStatus.InventoryStatus
import utils.{UserMetaData, DateMetaData}

/**
 * Created by harsh on 10/8/14.
 */
case class Inventory(id:Long,product_id:Long,total:Int,saleable_quantity:Int,
                     offline_quantity:Int=0,threshold:Int,available_quantity:Int,
                      sold_quantity:Int,offline_available_quantity:Int=0,offline_sold_quantity:Int=0,
                      in_stock:Int,in_stock_offline:Int=0,threshold_offline:Int=0,date:DateMetaData,user:UserMetaData,
                      status:InventoryStatus) {

}
