package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/include/header.jsp");
    _jspx_dependants.add("/include/footer.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
 pageContext.setAttribute("page","index");
      out.write('\n');
 pageContext.setAttribute("title","JackeMoney"); 
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>");
 out.print(pageContext.getAttribute("title"));
      out.write("</title>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n");
      out.write("        <link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("        <script type=\"text/javascript\" src=\"js/jquery-1.3.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/script.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/cufon-yui.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/arial.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/cuf_run.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"main\">\n");
      out.write("          <div class=\"main_resize\">\n");
      out.write("            <div class=\"header\">\n");
      out.write("              <div class=\"logo\">\n");
      out.write("                <h1><a href=\"#\"><span>Jack</span>eMoney<small>easy and convenient</small></a></h1>\n");
      out.write("              </div>\n");
      out.write("              <div class=\"search\">\n");
      out.write("\n");
      out.write("                <!--/searchform -->\n");
      out.write("                <div class=\"clr\"></div>\n");
      out.write("              </div>\n");
      out.write("              <div class=\"clr\"></div>\n");
      out.write("              <div class=\"menu_nav\">\n");
      out.write("                <ul>\n");
      out.write("                    ");
 if (pageContext.getAttribute("page").equals("index")) { 
      out.write("\n");
      out.write("                    <li class=\"active\"><a href=\"index.jsp\">Home</a></li>\n");
      out.write("                    ");
 } else { 
      out.write("\n");
      out.write("                    <li><a href=\"index.jsp\">Home</a></li>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                    \n");
      out.write("                    ");
 if(pageContext.getAttribute("page").equals("contact")){
      out.write("\n");
      out.write("                    <li class=\"active\"><a href=\"contact.jsp\">Contact</a></li>\n");
      out.write("                    ");
} else{
      out.write("\n");
      out.write("                    <li><a href=\"contact.jsp\">Contact</a></li>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                  \n");
      out.write("                    ");
 if (pageContext.getAttribute("page").equals("login")) { 
      out.write("\n");
      out.write("                    <li class=\"active\"><a href=\"login.jsp\">Login</a></li>\n");
      out.write("                    ");
 } else { 
      out.write("\n");
      out.write("                    <li><a href=\"login.jsp\">Login</a></li>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                  \n");
      out.write("                </ul>\n");
      out.write("                <div class=\"clr\"></div>\n");
      out.write("              </div>");
      out.write("\n");
      out.write("              <div class=\"hbg\"><img src=\"images/money.jpg\" width=\"923\" height=\"291\" alt=\"\" /></div>\n");
      out.write("            </div>\n");
      out.write("        <div class=\"content\">\n");
      out.write("            <div class=\"content_bg\">\n");
      out.write("            <div class=\"mainbar\">\n");
      out.write("                    <div class=\"article\">\n");
      out.write("                        <h2><span>Who are we?</span></h2>\n");
      out.write("                        <div class=\"clr\"></div>\n");
      out.write("                        <p class=\"post-data\">Our Vision<br>\n");
      out.write("                            <b>Extending Money transactions closer to people with ease.</b></p>\n");
      out.write("                        <p>JackeMoney is a small retail company that partnered with telecommunication services i.e (Warid and MTN) \n");
      out.write("                            to deal in extension of financial services closer to its customers.\n");
      out.write("                            Among the services we offer include; customer deposits on their phone accounts, offer cash to our customers\n");
      out.write("                            in form of cash withdrawals, we also offer financial advice to our esteemed customers</p>\n");
      out.write("                        <p>For more information:\n");
      out.write("                            Email us: <a href=\"#\">jackeMoney@financialserviceslimited.com</a><br>\n");
      out.write("                            Plot 28,Eagle Plaza, Lumumba Avenue.<br>\n");
      out.write("                            Tel: +256(0) 414 467 971/34/78\n");
      out.write("                            <br></p>\n");
      out.write("                       \n");
      out.write("                        <div class=\"clr\"></div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"pagenavi\"> </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        <div class=\"clr\"></div>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("  </div>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("    \n");
      out.write("    \n");
      out.write("    <div class=\"footer\">\n");
      out.write("      <div class=\"footer_resize\">\n");
      out.write("          <p class=\"lf\">&copy; Copyright <a href=\"#\"> Jack eMoney </a>. ");
      out.print( new GregorianCalendar().get(Calendar.YEAR) );
      out.write("</p>\n");
      out.write("        <div class=\"clr\"> </div>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
