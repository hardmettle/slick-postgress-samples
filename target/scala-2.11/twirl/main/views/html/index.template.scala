
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
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[List[Customer],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(users: List[Customer]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.25*/("""

"""),_display_(/*3.2*/main("Users")/*3.15*/ {_display_(Seq[Any](format.raw/*3.17*/("""
  """),format.raw/*4.3*/("""<h2>Add customer:</h2>

  <form action="/create" method="POST">
    <input name="id" type="text" value="1"/>
    <input name="name" type="text" value="Test"/>
    <input name="email" type="text" value="test@gmail.com"/>
    <input name="address.line1" type="text" value="mflra"/>
    <input name="address.line2" type="text" value="R T Nagar"/>
    <input name="address.city" type="text" value="Bangalore"/>
    <input name="status" type="text" value="REGISTERED"/>
    <input name="active" type="text" value="true"/>
    <input name="dob" type="text" value="2014-08-09"/>
    <input name="interests[0]" type="text" value="Trekking"/>
    <input name="interests[1]" type="text" value="Programming"/>
    <input name="others" type="text" value=""/>
    <input name="enabled" type="text" value="True"/>
    <input name="createdOn" type="text" value="2014-08-09T10:10:00Z"/>
    <input type="submit"/>
  </form>

  <h2>All customers:</h2>
  <table>
    <tr><th>ID</th><th>Name</th><th>Time</th></tr>
    """),_display_(/*27.6*/for(c <-users) yield /*27.20*/{_display_(Seq[Any](format.raw/*27.21*/("""
    """),format.raw/*28.5*/("""<tr><td>"""),_display_(/*28.14*/c/*28.15*/.id),format.raw/*28.18*/("""</td><td>"""),_display_(/*28.28*/c/*28.29*/.name),format.raw/*28.34*/("""</td><td>"""),_display_(/*28.44*/c/*28.45*/.dob),format.raw/*28.49*/("""</td></tr>
    """)))}),format.raw/*29.6*/("""
  """),format.raw/*30.3*/("""<table/>
    
""")))}),format.raw/*32.2*/("""
"""))}
  }

  def render(users:List[Customer]): play.twirl.api.HtmlFormat.Appendable = apply(users)

  def f:((List[Customer]) => play.twirl.api.HtmlFormat.Appendable) = (users) => apply(users)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Wed Aug 13 18:27:43 IST 2014
                  SOURCE: /workspaces/harsh/SPARK/slick-postgress-samples/app/views/index.scala.html
                  HASH: 3e36014e9e84a28b783a97d2e63bec8e84c07667
                  MATRIX: 513->1|624->24|652->27|673->40|712->42|741->45|1768->1047|1798->1061|1837->1062|1869->1067|1905->1076|1915->1077|1939->1080|1976->1090|1986->1091|2012->1096|2049->1106|2059->1107|2084->1111|2130->1127|2160->1130|2205->1145
                  LINES: 19->1|22->1|24->3|24->3|24->3|25->4|48->27|48->27|48->27|49->28|49->28|49->28|49->28|49->28|49->28|49->28|49->28|49->28|49->28|50->29|51->30|53->32
                  -- GENERATED --
              */
          