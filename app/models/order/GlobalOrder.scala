package order

import address.ShippingAddress
import enumspckg.AddressType._
import enumspckg.Country._
import enumspckg.OrderStatus._
import enumspckg.State._
import models.Customer
import myUtils.WithMyDriver


import scala.slick.lifted.{ForeignKeyQuery, Tag}

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
sealed trait BaseOrder {
  def orderId: Long

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
@param orderId the id of the order
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
case class Order(orderId: Long, orderNumber: String, amount: Double, numberOfItems: Int, ownerId: Long, products: List[String],
                 userMetaData: UserMetaData, displayStatus: String,
                 line1: String, line2: String, line3: Option[String], zipCode: Long, landmark: String,
                 location: String, state: State, country: Country, addressType: AddressType,
                 phone: String, alternatePhone: Option[String], shippingAddressId: Long) extends BaseOrder with ShippingAddress

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

case class EOrder(orderId: Long, orderNumber: String, amount: Double, numberOfItems: Int, ownerId: Long, products: List[String],
                  userMetaData: UserMetaData, displayStatus: String) extends BaseOrder

/*
Comment added to an order while placing it
@constructor creates new comment with orderId , comment , date metadata and user metadata
@param orderId the id of the order
@param comment the comment added to the order
@param dateMetaData the date metadata of the order
@param userMetaData the user metadata of the order
 */
case class Comment(commentId: Long, orderId: Long, comment: String, dateMetaData: DateMetaData, userMetaData: UserMetaData)

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

case class OrderProduct(orderId: Long, productId: Long, quantity: Int, status: OrderStatus, dateMetaData: DateMetaData, userMetaData: UserMetaData)


/*
OrderProductCommentUser  class represents a mapping between order product comment and user

@constructor creates OrderProductCommentUser with orderId,productId,commentId,userID
@param orderId id of the order
@param productId id of the product
@param commentId id of the comment
@param userId id of the user
 */
case class OrderProductCommentUser(orderId: Long, productId: Long, commentId: Long, userID: Long)

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

case class OrderStockRecord(orderId: Long, productId: Long, stockRecordId: Long, dateMetaData: DateMetaData, userMetaData: UserMetaData)

/*
class OrderPayment representing mapping between order and the payment made for it

@constructor creates OrderPayment class with orderId and paymentId
@param orderId Id number of the order
@param productId Id number of the product
 */
case class OrderPayment(orderId: Long, paymentId: Long)

/*
class OrderPrice representing mapping between order and the pricing detail related to it

@constructor creates OrderPrice class with orderPriceId cost and taxId
@param orderId Id number of the order
@param orderPriceId Id number of order pricing
@param cost total cost of the order
@param productId Id number of the product
 */
case class OrderPrice(orderId: Long, orderPriceId: Long, cost: Int, taxId: Long)

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
case class OrderLine(orderLineId: Long, orderId: Long, productId: Long, quantity: Int, price: Double, taxID: Long)

/*
For the Display on UI
 */

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
case class OrderLifeCycle(orderLifeCycleId: Long, status: OrderStatus, statusLabel: String, description: String, comments: List[Long], date: DateTime)

/*
class OrderSummary represnting the summary information about an order

@constructor creates OrderSummary with OrderSummary orderNumber status amount productNames createdOn

@param orderId Id number of the order
@param orderNumber order number generated for the order
@param productNames names of the product in the order
@param createdOn date metadata information about the order
 */
case class OrderSummary(orderId: Long, orderNumber: String, status: OrderStatus, amount: Double, productNames:List[String], createdOn: DateMetaData)

/*
class OrderDetail representing the full detail of the order placed

@constructor creates OrderDetail  class with productSummaryId lifeCycleId shippingAddressId owner
dateMetaData,userMetaData, lineItems

@params productSummaryId mapping product summary to the order details
@params lifeCycleId mapping order life cycle to order details
@params shippingAddressId mapping shipping address to the order details
@params owner mapping user to the order details
@param dateMetaData It is the metadata related to the dateMetaData associated with product and order
@param userMetaData It is the metadata related to the users associated with product and order
@param lineItems It is a list of line items of the product included in order
 */

case class OrderDetail(productSummaryId: List[Long], lifeCycleId: Long, shippingAddressId: Long, owner: Long,
                        dateMetaData: DateMetaData, userMetaData: UserMetaData, lineItems: List[Long])


/*
================== Table Definition =================
 */
trait OrderComponent extends WithMyDriver {

  import driver.simple._

  class Orders(tags: Tag) extends Table[Order](tags, "orders") {
    def orderId = column[Long]("orderid", O.AutoInc, O.PrimaryKey)

    def orderNumber = column[String]("order_number")

    def amount = column[Double]("amount")

    def numberOfItems = column[Int]("number_of_items")

    def ownerId = column[Long]("owner_id")

    def products = column[List[String]]("products")

    def createdBy = column[Long]("created_by")

    def modifiedBy = column[Option[Long]]("modified_by")

    def userMetaData = (createdBy, modifiedBy) <>(UserMetaData.tupled, UserMetaData.unapply)

    def displayStatus = column[String]("display_status")

    def line1 = column[String]("line1")

    def line2 = column[String]("line1")

    def line3 = column[Option[String]]("line3")

    def zipCode = column[Long]("zip_code")

    def landmark = column[String]("landmark")

    def location = column[String]("location")

    def state = column[State]("state")

    def country = column[Country]("country")

    def addressType = column[AddressType]("address_type")

    def phone = column[String]("phone")

    def alternatePhone = column[Option[String]]("alternate_phone")

    def shippingAddressId = column[Long]("shipping_address_id")

    def * = (orderId, orderNumber, amount, numberOfItems, ownerId, products, userMetaData, displayStatus, line1, line2, line3, zipCode,
      landmark, location, state, country, addressType, phone, alternatePhone, shippingAddressId) <>(Order.tupled, Order.unapply)
  }

}

trait EOrderComponent extends WithMyDriver {

  import driver.simple._

  class EOrders(tags: Tag) extends Table[EOrder](tags, "eorders") {

    def orderId = column[Long]("orderid", O.AutoInc, O.PrimaryKey)

    def orderNumber = column[String]("order_number")

    def amount = column[Double]("amount")

    def numberOfItems = column[Int]("number_of_items")

    def ownerId = column[Long]("owner_id")

    def products = column[List[String]]("products")

    def createdBy = column[Long]("created_by")

    def modifiedBy = column[Option[Long]]("modified_by")

    def userMetaData = (createdBy, modifiedBy) <>(UserMetaData.tupled, UserMetaData.unapply)

    def displayStatus = column[String]("display_status")

    def * = (orderId, orderNumber, amount, numberOfItems, ownerId, products, userMetaData, displayStatus) <>
      (EOrder.tupled, EOrder.unapply)

  }

}
//import models.or//
trait CommentComponent extends WithMyDriver {

  import driver.simple._
  import models.current.dao._
  class Comments(tags: Tag) extends Table[Comment](tags, "comment") {

    def commentId = column[Long]("commentId", O.AutoInc, O.PrimaryKey)

    def orderId = column[Long]("orderid")

    def comment = column[String]("comment")

    def commentIdFK = foreignKey("fk_comment_id", commentId,orderproductcommentusers)(_.orderId)

    def createdOn = column[DateTime]("created_on")

    def modifiedOn = column[Option[DateTime]]("modified_on")

    def dateMetaData = (createdOn, modifiedOn) <>(DateMetaData.tupled, DateMetaData.unapply)

    def createdBy = column[Long]("created_by")

    def modifiedBy = column[Option[Long]]("modified_by")

    def userMetaData = (createdBy, modifiedBy) <>(UserMetaData.tupled, UserMetaData.unapply)

    def * = (commentId, orderId, comment, dateMetaData, userMetaData) <>(Comment.tupled, Comment.unapply)

  }

}

trait OrderProductComponent extends WithMyDriver {

  import driver.simple._

  class OrderProducts(tags: Tag) extends Table[OrderProduct](tags, "orderproduct") {

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

    def * = (orderId, productId, quantity, status, dateMetaData, userMetaData) <>(OrderProduct.tupled, OrderProduct.unapply)

  }

}

trait OrderProductCommentUserComponent extends WithMyDriver {

  import driver.simple._

  class OrderProductCommentUsers(tags: Tag) extends Table[OrderProductCommentUser](tags, "orderproductcommentuser") {

    def orderId = column[Long]("orderid")

    def productId = column[Long]("productid")

    def commentId = column[Long]("commentid")

    def userId = column[Long]("userid")

    def * = (orderId, productId, commentId, userId) <>(OrderProductCommentUser.tupled, OrderProductCommentUser.unapply)

  }

}
trait OrderStockRecordComponent extends WithMyDriver {
  import driver.simple._
  class OrderStockRecords(tags:Tag) extends Table[OrderStockRecord](tags,"orderstockrecord"){

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
    def orderIdFK = foreignKey("fk_order_id",orderId,orders)

    //def productIdFK = foreignKey("fk_role_id", productId, products)(_.id) //TBD

    def * =  (orderId, productId,stockRecordId,createdOn,modifiedOn,dateMetaData,userMetaData)<>(OrderStockRecord.tupled,OrderStockRecord.unapply)
  }
}
trait OrderPaymentComponent extends WithMyDriver{
  import driver.simple._
  import models.current.dao._
  class OrderPayments(tags:Tag) extends Table[OrderPayment](tags,"orderpayment"){
    def orderId = column[Long]("orderid")
    def paymentId = column[Long]("paymentid")
    def orderIdFK = foreignKey("fk_order_id",orderId,orders)
    //def productIdFK = foreignKey("fk_product_id", productId, products)(_.id) //TBD
    def * = (orderId,paymentId)<>(OrderPayment.tupled,OrderPayment.unapply)
  }
}

trait OrderPriceComponent extends WithMyDriver{
  import driver.simple._
  import models.current.dao._
  class OrderPrices(tags:Tag) extends Table[OrderPrice](tags,"orderprices"){
    def orderId = column[Long]("orderid")
    def orderPriceId = column[Long]("orderpriceid")
    def cost = column[Int]("cost")
    def taxId = column[Long]("taxid")
    def orderIdFK = foreignKey("fk_order_id",orderId,orders)
    //def taxIdFK = foreignKey("fk_tax_id", taxId, taxes)(_.id) //TBD
    def * = (orderId,orderPriceId,cost,taxId) <> (OrderPrice.tupled,OrderPrice.unapply)
  }
}
trait OrderLineComponent extends WithMyDriver{

  import driver.simple._
  import models.current.dao._
  class OrderLines(tags:Tag) extends Table[OrderLine](tags,"orderlines"){
    def orderLineId = column[Long]("orderlineid")
    def orderId = column[Long]("orderid")
    def productId = column[Long]("productid")
    def quantity = column[Int]("quantity")
    def price = column[Double]("price")
    def taxId = column[Long]("taxid")

    def orderIdFK = foreignKey("fk_order_id",orderId,orders)
    //def productIdFK = foreignKey("fk_product_id", productId, products)(_.id) //TBD
    //def taxIdFK = foreignKey("fk_tax_id", taxId, taxes)(_.id) //TBD
    def * = (orderLineId, orderId, productId,quantity,price,taxId)<>(OrderLine.tupled,OrderLine.unapply)
  }
}
trait OrderLifeCycleComponent extends WithMyDriver {
  import driver.simple._
  import models.current.dao._

  class OrderLifeCycles(tags: Tag) extends Table[OrderLifeCycle](tags, "orderlifecycle") {

    def orderLifeCycleId = column[Long]("orderlifecycleid")

    def status = column[OrderStatus]("status")

    def statusLabel = column[String]("statuslabel")

    def description = column[String]("description")

    def date = column[DateTime]("date")

    def comments = column[List[Long]]("comments")

    //def commentsIdFK = foreignKey("fk_comments_id",comments,ordercomments) // need to discuss

    def * = (orderLifeCycleId, status, statusLabel,description,date,comments)<>(OrderLifeCycle.tupled,OrderLifeCycle.unapply)
  }

}
trait OrderSummaryComponent extends WithMyDriver {

  import driver.simple._
  import models.current.dao._

  class OrderSummaries(tags: Tag) extends Table[OrderSummary](tags, "ordersummary") {

    def orderId = column[Long]("orderid")

    def orderNumber = column[String]("order_number")

    def status = column[OrderStatus]("status")

    def amount = column[Double]("amount")

    def  productNames= column[List[String]]("productnames")

    def createdOn = column[DateTime]("created_on")

    def modifiedOn = column[Option[DateTime]]("modified_on")

    def dateMetaData = (createdOn, modifiedOn) <>(DateMetaData.tupled, DateMetaData.unapply)

    def orderIdFK = foreignKey("fk_order_id",orderId,orders)

    def * = (orderId,orderNumber,status,amount,productNames,createdOn,dateMetaData)<>(OrderSummary.tupled,OrderSummary.unapply)
  }

}
//case class OrderDetail(productSummaryId: List[Long], lifeCycleId: Long, shippingAddressId: Long, owner: Long,
//dateMetaData: DateMetaData, userMetaData: UserMetaData, lineItems: List[Long])
trait OrderDetailComponent extends WithMyDriver {
  import driver.simple._
  import models.current.dao._
  class OrderDetails(tags:Tag) extends Table[OrderDetail](tags,"orderdetails"){

  }
}
