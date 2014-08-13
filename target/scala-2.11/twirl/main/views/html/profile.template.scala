
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._

/**/
object profile extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[List[MyProfile],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(users: List[MyProfile]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.26*/("""

"""),_display_(/*3.2*/main("Users")/*3.15*/ {_display_(Seq[Any](format.raw/*3.17*/("""
  """),format.raw/*4.3*/("""<h2>My Profile:</h2>

  <form action="/addprofile" method="POST">
    <input name="userId" type="text" value="1"/>
    <input name="additionalAddress.line1" type="text" value="mflra"/>
    <input name="additionalAddress.line2" type="text" value="R T Nagar"/>
    <input name="additionalAddress.city" type="text" value="Bangalore"/>
    <input name="admin.adminType" type="text" value="ADMIN"/>
    <input name="admin.isAdmin" type="text" value="true"/>
    <input name="country" type="text" value="India"/>
    <input type="submit"/>
  </form>

  <h2>Profiles:</h2>
  <table>
    <tr><th>UID</th><th>Address</th><th>Is Admin?</th><th>Country</th></tr>
    """),_display_(/*20.6*/for(c <-users) yield /*20.20*/{_display_(Seq[Any](format.raw/*20.21*/("""
    """),format.raw/*21.5*/("""<tr><td>"""),_display_(/*21.14*/c/*21.15*/.userId),format.raw/*21.22*/("""</td><td>"""),_display_(/*21.32*/c/*21.33*/.additionalAddress.line1),format.raw/*21.57*/("""</td><td>"""),_display_(/*21.67*/c/*21.68*/.admin._2),format.raw/*21.77*/("""</td><td>"""),_display_(/*21.87*/c/*21.88*/.country),format.raw/*21.96*/("""</td></tr>
    """)))}),format.raw/*22.6*/("""
  """),format.raw/*23.3*/("""<table/>
    
""")))}),format.raw/*25.2*/("""
"""))}
  }

  def render(users:List[MyProfile]): play.twirl.api.HtmlFormat.Appendable = apply(users)

  def f:((List[MyProfile]) => play.twirl.api.HtmlFormat.Appendable) = (users) => apply(users)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Wed Aug 13 18:27:43 IST 2014
                  SOURCE: /workspaces/harsh/SPARK/slick-postgress-samples/app/views/profile.scala.html
                  HASH: d50bc76a834b0b7d00537888f551e2c00aff8d7e
                  MATRIX: 516->1|628->25|656->28|677->41|716->43|745->46|1428->703|1458->717|1497->718|1529->723|1565->732|1575->733|1603->740|1640->750|1650->751|1695->775|1732->785|1742->786|1772->795|1809->805|1819->806|1848->814|1894->830|1924->833|1969->848
                  LINES: 19->1|22->1|24->3|24->3|24->3|25->4|41->20|41->20|41->20|42->21|42->21|42->21|42->21|42->21|42->21|42->21|42->21|42->21|42->21|42->21|42->21|42->21|43->22|44->23|46->25
                  -- GENERATED --
              */
          