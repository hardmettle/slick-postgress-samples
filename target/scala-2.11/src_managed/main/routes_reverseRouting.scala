// @SOURCE:/workspaces/harsh/SPARK/slick-postgress-samples/conf/routes
// @HASH:ac18507da9d3dd781d91849007e2db9e3b88e2ff
// @DATE:Wed Aug 13 18:27:43 IST 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:18
// @LINE:15
// @LINE:13
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:18
class ReverseAssets {


// @LINE:18
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:15
// @LINE:13
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:12
def profiles(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "profiles")
}
                        

// @LINE:13
def usingAuthenticatedCustomerAction(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "allprofiles")
}
                        

// @LINE:7
def explicitlyUseDb(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "all")
}
                        

// @LINE:10
def insert(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "create")
}
                        

// @LINE:15
def addProfile(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "addprofile")
}
                        

// @LINE:8
def directlyUseDb(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "directall")
}
                        

// @LINE:6
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

}
                          
}
                  


// @LINE:18
// @LINE:15
// @LINE:13
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:18
class ReverseAssets {


// @LINE:18
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:15
// @LINE:13
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:12
def profiles : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.profiles",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "profiles"})
      }
   """
)
                        

// @LINE:13
def usingAuthenticatedCustomerAction : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.usingAuthenticatedCustomerAction",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "allprofiles"})
      }
   """
)
                        

// @LINE:7
def explicitlyUseDb : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.explicitlyUseDb",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "all"})
      }
   """
)
                        

// @LINE:10
def insert : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.insert",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "create"})
      }
   """
)
                        

// @LINE:15
def addProfile : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addProfile",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addprofile"})
      }
   """
)
                        

// @LINE:8
def directlyUseDb : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.directlyUseDb",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "directall"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

}
              
}
        


// @LINE:18
// @LINE:15
// @LINE:13
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:18
class ReverseAssets {


// @LINE:18
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:15
// @LINE:13
// @LINE:12
// @LINE:10
// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:12
def profiles(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.profiles(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "profiles", Seq(), "GET", """""", _prefix + """profiles""")
)
                      

// @LINE:13
def usingAuthenticatedCustomerAction(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.usingAuthenticatedCustomerAction(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "usingAuthenticatedCustomerAction", Seq(), "GET", """""", _prefix + """allprofiles""")
)
                      

// @LINE:7
def explicitlyUseDb(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.explicitlyUseDb(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "explicitlyUseDb", Seq(), "GET", """""", _prefix + """all""")
)
                      

// @LINE:10
def insert(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.insert(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "insert", Seq(), "POST", """""", _prefix + """create""")
)
                      

// @LINE:15
def addProfile(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addProfile(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "addProfile", Seq(), "POST", """""", _prefix + """addprofile""")
)
                      

// @LINE:8
def directlyUseDb(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.directlyUseDb(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "directlyUseDb", Seq(), "GET", """""", _prefix + """directall""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

}
                          
}
        
    