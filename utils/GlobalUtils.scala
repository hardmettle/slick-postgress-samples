package utils

import enumspckg.DateFormat.DateFormat
import enumspckg.MediaType.MediaType
import enumspckg.TimeFormat.TimeFormat
import org.joda.time.DateTime
import users.User

/**
 * Created by harsh on 10/8/14.
 */
case class DateMetaData(createdOn:DateTime,modifiedOn:Option[DateTime])
case class UserMetaData(createdBy:Long,modifiedBy:Option[Long])
case class Media(media_id:Long,url:String,local_path:Option[String],title:String,media_type:MediaType,dates:DateMetaData)
case class DateTimeFormat(dateformat:DateFormat,timeformat:TimeFormat,timezone:String)
case class TollFreeNumber(primary:String,secondary:Option[String])
case class WorkTimings(start:DateTime,end:DateTime)
case class Size(width:Int,height:Int)