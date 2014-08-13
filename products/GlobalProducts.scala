package products

import enumspckg.MediaType.MediaType
import utils.{UserMetaData, DateMetaData}

/**
 * Created by harsh on 10/8/14.
 */
sealed trait GlobalProducts{
  def productId:Long
  def upc:String
  def name:String
  def description:String
}

sealed trait Additional{
  def isDiscountable:Boolean
  def isSaleable:Boolean
  def isStockAvailable:Boolean
  def isCOD:Boolean
  def isRedeemable:Boolean
  def isEtype:Boolean
  def isEMIAvailable:Boolean
  def isGiftWrapAvailable:Boolean
  def dates:DateMetaData
  def users:UserMetaData
}

sealed trait Attribute{
  def attributeId:Long
  def attributeName:String
  def value:String
}
sealed trait Media{
  def mediaId:Long
  def url:String
  def localPath:Option[String]
  def title:String
  def mediaType:MediaType
  def dates:DateMetaData
}
case class Product(productId:Long,upc:String,name:String,description:String)  extends GlobalProducts

case class ProductAdditional(productId:Long,upc:String,name:String,description:String,
                   isDiscountable:Option[Boolean], isSaleable:Option[Boolean], isStockAvailable:Option[Boolean],
                   isCOD:Option[Boolean], isRedeemable:Option[Boolean],isEtype:Option[Boolean],
                   isEMIAvailable:Option[Boolean], isGiftWrapAvailable:Option[Boolean]
                   , dates:DateMetaData, users:UserMetaData) extends GlobalProducts with Additional


case class ProductAttribute(productId:Long,upc:String,name:String,description:String,
                            priority:Int,attributeId:Long,attributeName:String,value:String) extends GlobalProducts with Attribute

case class ProductMedia(productId:Long,upc:String,name:String,description:String,
                        mediaId:Long,url:String,localPath:Option[String],
                        title:String,mediaType:MediaType,dates:DateMetaData) extends GlobalProducts with Media

case class ProductSummary(productSummaryId:Long,code:String,name:String,description:String,image:String)

case class ProductGroup(productGroupId:Long,title:String,description:Option[String])