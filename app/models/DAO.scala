package models


import _root_.inventory.InventoryComponent
import _root_.order._


import play.api.db.slick.DB
import myUtils.MyPostgresDriver

import scala.slick.lifted.{Query, ProvenShape, ForeignKeyQuery}
/**
* All tables can be configured here for DAO operations
*
*/
class DAO(override val driver: MyPostgresDriver) extends CustomerComponent with MyProfileComponent with RoleComponent with CustomerRoleComponent
with OrderComponent with OrderProductCommentUserComponent with CommentComponent with OrderLifeCycleComponent
with OrderLineComponent with InventoryComponent{
  import driver.simple._
  object customers extends TableQuery(new Customers(_))
  object myprofiles extends TableQuery(new MyProfiles(_))
  //object roles extends TableQuery(new Roles(_))
  object baseroles extends TableQuery(tag => new BaseRoles(tag))
  //val roles = TableQuery[Roles]
  val orders = TableQuery(new Orders(_))
  val orderproductcommentusers = TableQuery(new OrderProductCommentUsers(_))
  val ordercomments = TableQuery(new Comments(_))
  val lifecycles = TableQuery(new OrderLifeCycles(_))
  val lineitems = TableQuery(new OrderLines(_))
  val inventories = TableQuery(new Inventories(_))
}

object current {
  /**
  * Use the default db settings from conf file for postgres customized db driver
  *
  */
  val dao = new DAO(DB(play.api.Play.current).driver.asInstanceOf[MyPostgresDriver])
}
