package order

import address.ShippingAddress
import enumspckg.AddressType._
import enumspckg.Country._
import enumspckg.OrderStatus._
import enumspckg.State._
import models.{CrudComponent, EntityTable, Entity, Customer}
import myUtils.WithMyDriver

import play.api.data.format.Formats
import play.api.data.format.Formatter


import scala.slick.lifted.{ProvenShape, ForeignKeyQuery, Tag}

//import scala.slick.profile.RelationalTableComponent.Table

//import scala.slick.profile.RelationalTableComponent.Table


//import myUtils.WithMyDriver

import org.joda.time.DateTime
import utils.{UserMetaData, DateMetaData}

/**
 * Created by harsh on 10/8/14.
 */


/*
BaseOrder trait which is a superclass of all kinds of orders

def orderId the id of the order
def orderNumber the order number generated during creation of the order
def amount the total amount worth of the order
def numberOfItems the total items present in the order
def ownerId the user who owns the order
def products the list of products present in the order
def user the option of user metadata
def displayStatus the status of display of the order on UI
 */
sealed trait BaseOrder extends Entity[Long] {

  def orderNumber: String

  def amount: Double

  def numberOfItems: Int

  def ownerId: Long

  def products: List[String]

  def userMetaData: UserMetaData

  def displayStatus: String
}

/*
Order class which represents one of the type of orders a user can place.Extends the super trait BaseOrder with mixin of
Shipping Address

@constructor creates an order with various attributes of an order
@param id the id of the order
@param orderNumber the order number generated during creation of the order
@param amount the total amount worth of the order
@param numberOfItems the total items present in the order
@param ownerId the user who owns the order
@param products the list of products present in the order
@param user the option of user metadata
@param displayStatus the status of display of the order on UI

===== These attributes added by shipping address trait
@param line1 Line number 1 of the address
@param line2 Line number 2 of the address
@param line3 Line number 3 of the address which is optional
@param zipCode the zip code of the area of the address
@param landmark Landmark identifying the address
@param location it is the locality or the area part of the address
@param state State where user resides in
@param country Its the country of the user
@param addressType Type of the address which could be home or office
@param phone phone number of the user which he can be contacted during order
@param alternatePhone alternate phone number of user where he can be contacted . It is optional

 */
case class Order(val id: Option[Long], orderNumber: String, amount: Double, numberOfItems: Int, ownerId: Long, products: List[String],
                 userMetaData: UserMetaData, displayStatus: String, billingAddressId: Option[Long], shippingAddressId: Option[Long]) extends BaseOrder

/*
EOrder class which represents one of the type of orders a user can place.It is generally the order which don't need shipping
and can be downloaded or sent online . for e.g pdf books .Extends the super trait BaseOrder

@constructor creates an e-order with various attributes of an order
@param orderId the id of the order
@param orderNumber the order number generated during creation of the order
@param amount the total amount worth of the order
@param numberOfItems the total items present in the order
@param ownerId the user who owns the order
@param products the list of products present in the order
@param user the option of user metadata
@param displayStatus the status of display of the order on UI
*/

case class EOrder(val id: Option[Long], orderNumber: String, amount: Double, numberOfItems: Int, ownerId: Long, products: List[String],
                  userMetaData: UserMetaData, displayStatus: String) extends BaseOrder

/*
Comment added to an order while placing it
@constructor creates new comment with orderId , comment , date metadata and user metadata
@param orderId the id of the order
@param comment the comment added to the order
@param dateMetaData the date metadata of the order
@param userMetaData the user metadata of the order
 */
case class Comment(id: Option[Long], orderId: Long, comment: String, dateMetaData: DateMetaData, userMetaData: UserMetaData) extends Entity[Long]

/*
OrderProduct class which represent a mapping between an order with the corresponding product included in it.

@constructor creates OrderProduct class with orderId ,productId,quantity,status,dateMetaData,userMetaData
@param orderId Id number of the order
@param productId Id number of the product
@param quantity quantity of the product included in the order
@param status tells the status of the order in form of an enum
@param dateMetaData It is the metadata related to the dateMetaData associated with product and order
@param userMetaData It is the metadata related to the users associated with product and order

 */

case class OrderProduct(id: Option[Long], orderId: Long, productId: Long, quantity: Int, status: OrderStatus,
                        dateMetaData: DateMetaData, userMetaData: UserMetaData) extends Entity[Long]


/*
OrderProductCommentUser  class represents a mapping between order product comment and user

@constructor creates OrderProductCommentUser with orderId,productId,commentId,userID
@param orderId id of the order
@param productId id of the product
@param commentId id of the comment
@param userId id of the user
 */
case class OrderProductCommentUser(id: Option[Long], orderId: Long, productId: Long, commentId: Long, userID: Long) extends Entity[Long]

/*
OrderStockRecord class which represent a mapping between an order with the corresponding stock record of products
 included in it.

@constructor creates OrderStockRecord class with orderId ,productId,stockRecordId ,dateMetaData,users
@param orderId Id number of the order
@param productId Id number of the product
@param stockRecordId Id of the stock of the product included in the order
@param dateMetaData It is the metadata related to the dateMetaData associated with product and order
@param userMetaData It is the metadata related to the users associated with product and order

 */

case class OrderStockRecord(id: Option[Long], orderId: Long, productId: Long, stockRecordId: Long,
                            dateMetaData: DateMetaData, userMetaData: UserMetaData) extends Entity[Long]

/*
class OrderPayment representing mapping between order and the payment made for it

@constructor creates OrderPayment class with orderId and paymentId
@param orderId Id number of the order
@param productId Id number of the product
 */
case class OrderPayment(id: Option[Long], orderId: Long, paymentId: Long) extends Entity[Long]


/*
=============================================For the Display on UI=============================================
 */


/*
class OrderPrice representing mapping between order and the pricing detail related to it

@constructor creates OrderPrice class with orderPriceId cost and taxId
@param orderId Id number of the order
@param orderPriceId Id number of order pricing
@param cost total cost of the order
@param productId Id number of the product
 */
case class OrderPrice(id: Option[Long], orderId: Long, orderPriceId: Long, cost: Int, taxId: Long) extends Entity[Long]

/*
class OrderLine representing list of all items in an order

@constructor creates OrderLine class with orderLineId ,orderId,productId,quantity,price,taxId
@param orderLineId Id number of order line
@param orderId Id number of the order
@param productId Id number of product
@param quantity its the total quantity of the product
@param price price of the order in that order line
@param taxId Id number of the tax applied
 */
case class OrderLine(id: Option[Long], orderId: Long, productId: Long, quantity: Int, price: Double, taxID: Long) extends Entity[Long]


/*
Represents the order life cycle of an order

@constructor creates OrderLifeCycle with orderLifeCycleId status statusLabel description comments date
@param orderLifeCycleId id of the order life cycle
@param status final status of the order
@param statusLabel label to show on the UI for the status of the order
@param description description about the order
@param comments comments about the product in the order
@param date date and time of placing the order
 */
case class OrderLifeCycle(id: Option[Long], status: OrderStatus, statusLabel: String,
                          description: String, commentId: Long, date: DateTime) extends Entity[Long]

/*
class OrderSummary representing the summary information about an order

@constructor creates OrderSummary with OrderSummary orderNumber status amount productNames createdOn

@param orderSummaryId Id as primary key of the order summary
@param orderId Id number of the order
@param orderNumber order number generated for the order
@param productNames names of the product in the order
@param createdOn date metadata information about the order
 */
case class OrderSummary(id: Option[Long], orderId: Long, orderNumber: String,
                        status: OrderStatus, amount: Double, productNames: List[String], createdOn: DateMetaData) extends Entity[Long]

/*
class OrderDetail representing the full detail of the order placed

@constructor creates OrderDetail  class with productSummaryId lifeCycleId shippingAddressId owner
dateMetaData,userMetaData, lineItems

@params orderDetailId primary key id of this table
@params productSummaryId mapping product summary to the order details
@params lifeCycleId mapping order life cycle to order details
@params shippingAddressId mapping shipping address to the order details
@params owner mapping user to the order details
@param dateMetaData It is the metadata related to the dateMetaData associated with product and order
@param userMetaData It is the metadata related to the users associated with product and order
@param lineItemsId It is a list of line items of the product included in order
 */

case class OrderDetail(id: Option[Long], productSummaryId: Long, lifeCycleId: Long, shippingAddressId: Option[Long], owner: Long,
                       dateMetaData: DateMetaData, userMetaData: UserMetaData, lineItemsId: Long) extends Entity[Long]


/*
================== Table Definition =================
 */
trait OrderComponent extends CrudComponent {


  import driver.simple._

  abstract class AbstractOrders[T](tags: Tag) extends Table[T](tags, "orders") with EntityTable[Long] {

    def id = column[Long]("order_id", O.AutoInc, O.PrimaryKey)

    def orderNumber = column[String]("order_number")

    def amount = column[Double]("amount")

    def numberOfItems = column[Int]("number_of_items")

    def ownerId = column[Long]("owner_id")

    def products = column[List[String]]("products")

    def createdBy = column[Long]("created_by")

    def modifiedBy = column[Option[Long]]("modified_by")

    def userMetaData = (createdBy, modifiedBy) <>(UserMetaData.tupled, UserMetaData.unapply)

    def displayStatus = column[String]("display_status")

    def billingAddressId = column[Option[Long]]("billing_address_id")

    def shippingAddressId = column[Option[Long]]("shipping_address_id")

    def * : ProvenShape[T]
  }

  class BaseOrders(tags: Tag) extends AbstractOrders[BaseOrder](tags) {
    def * : ProvenShape[BaseOrder] = (id.?, orderNumber, amount, numberOfItems, ownerId, products,
      userMetaData, displayStatus, billingAddressId, shippingAddressId).shaped <>( { t => t match {

       case (a, b, c, d, e, f, g, h, i@None, j@None) =>  EOrder(a, b, c, d, e, f, g, h): BaseOrder
        case (a, b, c, d, e, f, g, h, i, j) => Order(a, b, c, d, e, f, g, h, i, j): BaseOrder

      }
    }, { t:BaseOrder => t match {
        case EOrder(a, b, c, d, e, f, g, h) =>  Some((a, b, c, d, e, f, g, h, None, None))
        case Order(a, b, c, d, e, f, g, h, i, j) =>          Some((a, b, c, d, e, f, g, h, i, j))
        case _ => None
     }
    }
      )
  }

  object BaseOrders extends Crud[BaseOrders, BaseOrder, Long] {
    val query = TableQuery[BaseOrders]
    override def withId(baseorder:BaseOrder, id: Long)(implicit session: Session): BaseOrder = baseorder match {

      case x @ EOrder(_,_,_,_,_,_,_,_) => x.copy(id = Option(id))
      case x @ Order(_,_,_,_,_,_,_,_,_,_) => x.copy(id = Option(id))
    }
  }




}


//import models.or//
trait CommentComponent extends CrudComponent {

  import driver.simple._
  import models.current.dao._

  class Comments(tags: Tag) extends Table[Comment](tags, "comment") with EntityTable[Long] {

    def id = column[Long]("commentId", O.AutoInc, O.PrimaryKey)

    def orderId = column[Long]("orderid")

    def comment = column[String]("comment")

    def commentIdFK = foreignKey("fk_comment_id", id, orderproductcommentusers)(_.commentId)

    def createdOn = column[DateTime]("created_on")

    def modifiedOn = column[Option[DateTime]]("modified_on")

    def dateMetaData = (createdOn, modifiedOn) <>(DateMetaData.tupled, DateMetaData.unapply)

    def createdBy = column[Long]("created_by")

    def modifiedBy = column[Option[Long]]("modified_by")

    def userMetaData = (createdBy, modifiedBy) <>(UserMetaData.tupled, UserMetaData.unapply)

    def * = (id.?, orderId, comment, dateMetaData, userMetaData) <>(Comment.tupled, Comment.unapply)

  }

  object Comments extends Crud[Comments, Comment, Long] {
    val query = TableQuery[Comments]

    override def withId(comment: Comment, id: Long)(implicit session: Session): Comment = comment.copy(id = Option(id))
  }

}

trait OrderProductComponent extends CrudComponent {

  import driver.simple._

  class OrderProducts(tags: Tag) extends Table[OrderProduct](tags, "orderproduct") with EntityTable[Long] {

    def id = column[Long]("order_product_id", O.AutoInc, O.PrimaryKey)

    def orderId = column[Long]("orderid")

    def productId = column[Long]("productid")

    def quantity = column[Int]("quantity")

    def status = column[OrderStatus]("status")

    def createdOn = column[DateTime]("created_on")

    def modifiedOn = column[Option[DateTime]]("modified_on")

    def dateMetaData = (createdOn, modifiedOn) <>(DateMetaData.tupled, DateMetaData.unapply)

    def createdBy = column[Long]("created_by")

    def modifiedBy = column[Option[Long]]("modified_by")

    def userMetaData = (createdBy, modifiedBy) <>(UserMetaData.tupled, UserMetaData.unapply)

    def * = (id.?, orderId, productId, quantity, status, dateMetaData, userMetaData) <>(OrderProduct.tupled, OrderProduct.unapply)

  }

  object OrderProducts extends Crud[OrderProducts, OrderProduct, Long] {
    val query = TableQuery[OrderProducts]

    override def withId(orderproduct: OrderProduct, id: Long)(implicit session: Session): OrderProduct = orderproduct.copy(id = Option(id))
  }

}

trait OrderProductCommentUserComponent extends CrudComponent {

  import driver.simple._

  class OrderProductCommentUsers(tags: Tag) extends Table[OrderProductCommentUser](tags, "orderproductcommentuser") with EntityTable[Long] {

    def id = column[Long]("order_product_comment_user_id", O.AutoInc, O.PrimaryKey)

    def orderId = column[Long]("orderid")

    def productId = column[Long]("productid")

    def commentId = column[Long]("commentid")

    def userId = column[Long]("userid")

    def * = (id.?, orderId, productId, commentId, userId) <>(OrderProductCommentUser.tupled, OrderProductCommentUser.unapply)

  }

  object OrderProductCommentUsers extends Crud[OrderProductCommentUsers, OrderProductCommentUser, Long] {
    val query = TableQuery[OrderProductCommentUsers]

    override def withId(orderproductcommentuser: OrderProductCommentUser, id: Long)(implicit session: Session): OrderProductCommentUser
    = orderproductcommentuser.copy(id = Option(id))
  }

}

trait OrderStockRecordComponent extends CrudComponent {

  import driver.simple._

  class OrderStockRecords(tags: Tag) extends Table[OrderStockRecord](tags, "orderstockrecord") with EntityTable[Long] {

    def id = column[Long]("order_stockrecord_id", O.AutoInc, O.PrimaryKey)

    def orderId = column[Long]("orderid")

    def productId = column[Long]("productid")

    def stockRecordId = column[Long]("stockrecordid")

    def createdOn = column[DateTime]("created_on")

    def modifiedOn = column[Option[DateTime]]("modified_on")

    def dateMetaData = (createdOn, modifiedOn) <>(DateMetaData.tupled, DateMetaData.unapply)

    def createdBy = column[Long]("created_by")

    def modifiedBy = column[Option[Long]]("modified_by")

    def userMetaData = (createdBy, modifiedBy) <>(UserMetaData.tupled, UserMetaData.unapply)

    import models.current.dao._

    def orderIdFK = foreignKey("fk_order_id", orderId, orders)(_.id)

    //def productIdFK = foreignKey("fk_role_id", productId, products)(_.id) //TBD

    //def stockRecordId = foreignKey("fk_role_id", productId, products)(_.id) //TBD

    def * = (id.?, orderId, productId, stockRecordId, dateMetaData, userMetaData) <>(OrderStockRecord.tupled, OrderStockRecord.unapply)
  }

  object OrderStockRecords extends Crud[OrderStockRecords, OrderStockRecord, Long] {
    val query = TableQuery[OrderStockRecords]

    override def withId(orderstockrecord: OrderStockRecord, id: Long)(implicit session: Session): OrderStockRecord
    = orderstockrecord.copy(id = Option(id))
  }

}

trait OrderPaymentComponent extends CrudComponent {

  import driver.simple._
  import models.current.dao._

  class OrderPayments(tags: Tag) extends Table[OrderPayment](tags, "orderpayment") with EntityTable[Long] {

    def id = column[Long]("order_payment_id", O.AutoInc, O.PrimaryKey)

    def orderId = column[Long]("orderid")

    def paymentId = column[Long]("paymentid")

    def orderIdFK = foreignKey("fk_order_id", orderId, orders)(_.id)

    //def productIdFK = foreignKey("fk_product_id", productId, products)(_.id) //TBD
    def * = (id.?, orderId, paymentId) <>(OrderPayment.tupled, OrderPayment.unapply)
  }

  object OrderPayments extends Crud[OrderPayments, OrderPayment, Long] {
    val query = TableQuery[OrderPayments]

    override def withId(orderpayment: OrderPayment, id: Long)(implicit session: Session): OrderPayment
    = orderpayment.copy(id = Option(id))
  }

}

trait OrderPriceComponent extends CrudComponent {

  import driver.simple._
  import models.current.dao._

  class OrderPrices(tags: Tag) extends Table[OrderPrice](tags, "orderprices") with EntityTable[Long] {

    def id = column[Long]("order_price_id", O.AutoInc, O.PrimaryKey)

    def orderId = column[Long]("orderid")

    def orderPriceId = column[Long]("orderpriceid")

    def cost = column[Int]("cost")

    def taxId = column[Long]("taxid")

    def orderIdFK = foreignKey("fk_order_id", orderId, orders)(_.id)

    //def taxIdFK = foreignKey("fk_tax_id", taxId, taxes)(_.id) //TBD
    //def orderPriceIdFK = foreignKey("fk_tax_id", orderPriceId, orderprices)(_.id) //TBD
    def * = (id.?, orderId, orderPriceId, cost, taxId) <>(OrderPrice.tupled, OrderPrice.unapply)
  }

  object OrderPrices extends Crud[OrderPrices, OrderPrice, Long] {
    val query = TableQuery[OrderPrices]

    override def withId(orderprice: OrderPrice, id: Long)(implicit session: Session): OrderPrice
    = orderprice.copy(id = Option(id))
  }

}

trait OrderLineComponent extends CrudComponent {

  import driver.simple._
  import models.current.dao._

  class OrderLines(tags: Tag) extends Table[OrderLine](tags, "orderlines") with EntityTable[Long] {

    def id = column[Long]("orderlineid", O.AutoInc, O.PrimaryKey)

    def orderId = column[Long]("orderid")

    def productId = column[Long]("productid")

    def quantity = column[Int]("quantity")

    def price = column[Double]("price")

    def taxId = column[Long]("taxid")

    def orderIdFK = foreignKey("fk_order_id", orderId, orders)(_.id)

    //def productIdFK = foreignKey("fk_product_id", productId, products)(_.id) //TBD
    //def taxIdFK = foreignKey("fk_tax_id", taxId, taxes)(_.id) //TBD
    def * = (id.?, orderId, productId, quantity, price, taxId) <>(OrderLine.tupled, OrderLine.unapply)
  }

  object OrderLines extends Crud[OrderLines, OrderLine, Long] {
    val query = TableQuery[OrderLines]

    override def withId(orderline: OrderLine, id: Long)(implicit session: Session): OrderLine
    = orderline.copy(id = Option(id))
  }

}

trait OrderLifeCycleComponent extends CrudComponent {

  import driver.simple._
  import models.current.dao._

  class OrderLifeCycles(tags: Tag) extends Table[OrderLifeCycle](tags, "order_lifecycle") with EntityTable[Long] {

    def id = column[Long]("order_lifecycle_id", O.AutoInc, O.PrimaryKey)

    def status = column[OrderStatus]("status")

    def statusLabel = column[String]("status_label")

    def description = column[String]("description")

    def date = column[DateTime]("date")

    def commentId = column[Long]("commentid")

    def commentsIdFK = foreignKey("fk_comments_id", commentId, ordercomments)(_.id) // need to discuss

    def * = (id.?, status, statusLabel, description, commentId, date) <>(OrderLifeCycle.tupled, OrderLifeCycle.unapply)
  }

  object OrderLifeCycles extends Crud[OrderLifeCycles, OrderLifeCycle, Long] {
    val query = TableQuery[OrderLifeCycles]

    override def withId(orderlifecycle: OrderLifeCycle, id: Long)(implicit session: Session): OrderLifeCycle
    = orderlifecycle.copy(id = Option(id))
  }

}

trait OrderSummaryComponent extends CrudComponent {

  import driver.simple._
  import models.current.dao._

  class OrderSummaries(tags: Tag) extends Table[OrderSummary](tags, "ordersummary") with EntityTable[Long] {

    def id = column[Long]("order_summary_id", O.AutoInc, O.PrimaryKey)

    def orderId = column[Long]("order_id")

    def orderNumber = column[String]("order_number")

    def status = column[OrderStatus]("status")

    def amount = column[Double]("amount")

    def productNames = column[List[String]]("productnames")

    def createdOn = column[DateTime]("created_on")

    def modifiedOn = column[Option[DateTime]]("modified_on")

    def dateMetaData = (createdOn, modifiedOn) <>(DateMetaData.tupled, DateMetaData.unapply)

    def orderIdFK = foreignKey("fk_order_id", orderId, orders)(_.id)

    def * = (id.?, orderId, orderNumber, status, amount, productNames, dateMetaData) <>(OrderSummary.tupled, OrderSummary.unapply)
  }

  object OrderSummaries extends Crud[OrderSummaries, OrderSummary, Long] {
    val query = TableQuery[OrderSummaries]

    override def withId(ordersummary: OrderSummary, id: Long)(implicit session: Session): OrderSummary
    = ordersummary.copy(id = Option(id))
  }

}

//case class OrderDetail(productSummaryId: List[Long], lifeCycleId: Long, shippingAddressId: Long, owner: Long,
//dateMetaData: DateMetaData, userMetaData: UserMetaData, lineItems: List[Long])
trait OrderDetailComponent extends CrudComponent {

  import driver.simple._
  import models.current.dao._

  class OrderDetails(tags: Tag) extends Table[OrderDetail](tags, "orderdetails") with EntityTable[Long] {

    def id = column[Long]("order_detail_id", O.AutoInc, O.PrimaryKey)

    def productSummaryId = column[Long]("prooductsummaryid")

    def lifeCycleId = column[Long]("lifecycleid")

    def shippingAddressId = column[Option[Long]]("shippingaddressid")

    def owner = column[Long]("owner")

    def createdOn = column[DateTime]("created_on")

    def modifiedOn = column[Option[DateTime]]("modified_on")

    def dateMetaData = (createdOn, modifiedOn) <>(DateMetaData.tupled, DateMetaData.unapply)

    def createdBy = column[Long]("created_by")

    def modifiedBy = column[Option[Long]]("modified_by")

    def userMetaData = (createdBy, modifiedBy) <>(UserMetaData.tupled, UserMetaData.unapply)

    def lineItemsId = column[Long]("line_items_id")

    def lifecycleIdFK = foreignKey("fk_lifecycle_id", lifeCycleId, lifecycles)(_.id)

    def shippingAddressIdFK = foreignKey("fk_shipping_address_id", shippingAddressId, orders)(_.shippingAddressId)

    def lineItemsIdFK = foreignKey("fk_lineitems_id", lineItemsId, lineitems)(_.id)

    def * = (id.?, productSummaryId, lifeCycleId, shippingAddressId, owner, dateMetaData, userMetaData, lineItemsId) <>
      (OrderDetail.tupled, OrderDetail.unapply)
  }

  object OrderDetails extends Crud[OrderDetails, OrderDetail, Long] {
    val query = TableQuery[OrderDetails]

    override def withId(orderdetail: OrderDetail, id: Long)(implicit session: Session): OrderDetail
    = orderdetail.copy(id = Option(id))
  }

}
