// @SOURCE:/workspaces/harsh/SPARK/slick-postgress-samples/conf/routes
// @HASH:ac18507da9d3dd781d91849007e2db9e3b88e2ff
// @DATE:Wed Aug 13 18:27:43 IST 2014


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:7
private[this] lazy val controllers_Application_explicitlyUseDb1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("all"))))
private[this] lazy val controllers_Application_explicitlyUseDb1_invoker = createInvoker(
controllers.Application.explicitlyUseDb,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "explicitlyUseDb", Nil,"GET", """""", Routes.prefix + """all"""))
        

// @LINE:8
private[this] lazy val controllers_Application_directlyUseDb2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("directall"))))
private[this] lazy val controllers_Application_directlyUseDb2_invoker = createInvoker(
controllers.Application.directlyUseDb,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "directlyUseDb", Nil,"GET", """""", Routes.prefix + """directall"""))
        

// @LINE:10
private[this] lazy val controllers_Application_insert3_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("create"))))
private[this] lazy val controllers_Application_insert3_invoker = createInvoker(
controllers.Application.insert,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "insert", Nil,"POST", """""", Routes.prefix + """create"""))
        

// @LINE:12
private[this] lazy val controllers_Application_profiles4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("profiles"))))
private[this] lazy val controllers_Application_profiles4_invoker = createInvoker(
controllers.Application.profiles,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "profiles", Nil,"GET", """""", Routes.prefix + """profiles"""))
        

// @LINE:13
private[this] lazy val controllers_Application_usingAuthenticatedCustomerAction5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("allprofiles"))))
private[this] lazy val controllers_Application_usingAuthenticatedCustomerAction5_invoker = createInvoker(
controllers.Application.usingAuthenticatedCustomerAction,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "usingAuthenticatedCustomerAction", Nil,"GET", """""", Routes.prefix + """allprofiles"""))
        

// @LINE:15
private[this] lazy val controllers_Application_addProfile6_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addprofile"))))
private[this] lazy val controllers_Application_addProfile6_invoker = createInvoker(
controllers.Application.addProfile,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "addProfile", Nil,"POST", """""", Routes.prefix + """addprofile"""))
        

// @LINE:18
private[this] lazy val controllers_Assets_at7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at7_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """all""","""controllers.Application.explicitlyUseDb"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """directall""","""controllers.Application.directlyUseDb"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """create""","""controllers.Application.insert"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """profiles""","""controllers.Application.profiles"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """allprofiles""","""controllers.Application.usingAuthenticatedCustomerAction"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addprofile""","""controllers.Application.addProfile"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(controllers.Application.index)
   }
}
        

// @LINE:7
case controllers_Application_explicitlyUseDb1_route(params) => {
   call { 
        controllers_Application_explicitlyUseDb1_invoker.call(controllers.Application.explicitlyUseDb)
   }
}
        

// @LINE:8
case controllers_Application_directlyUseDb2_route(params) => {
   call { 
        controllers_Application_directlyUseDb2_invoker.call(controllers.Application.directlyUseDb)
   }
}
        

// @LINE:10
case controllers_Application_insert3_route(params) => {
   call { 
        controllers_Application_insert3_invoker.call(controllers.Application.insert)
   }
}
        

// @LINE:12
case controllers_Application_profiles4_route(params) => {
   call { 
        controllers_Application_profiles4_invoker.call(controllers.Application.profiles)
   }
}
        

// @LINE:13
case controllers_Application_usingAuthenticatedCustomerAction5_route(params) => {
   call { 
        controllers_Application_usingAuthenticatedCustomerAction5_invoker.call(controllers.Application.usingAuthenticatedCustomerAction)
   }
}
        

// @LINE:15
case controllers_Application_addProfile6_route(params) => {
   call { 
        controllers_Application_addProfile6_invoker.call(controllers.Application.addProfile)
   }
}
        

// @LINE:18
case controllers_Assets_at7_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at7_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     