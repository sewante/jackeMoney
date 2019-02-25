package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/include/header.jsp");
    _jspx_dependants.add("/include/footer.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_out_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_out_value_nobody.release();
    _jspx_tagPool_c_if_test.release();
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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
 pageContext.setAttribute("title", "login");
      out.write('\n');
 pageContext.setAttribute("page", "login"); 
      out.write('\n');
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
 if(pageContext.getAttribute("page").equals("about")){ 
      out.write("\n");
      out.write("                    <li class=\"active\"><a href=\"about.jsp\">About Us</a></li>\n");
      out.write("                    ");
}else{
      out.write("\n");
      out.write("                    <li><a href=\"about.jsp\">About Us</a></li>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                    ");
 if(pageContext.getAttribute("page").equals("contact")){
      out.write("\n");
      out.write("                    <li class=\"active\"><a href=\"contact.jsp\">Contact Us</a></li>\n");
      out.write("                    ");
} else{
      out.write("\n");
      out.write("                    <li><a href=\"contact.jsp\">Contact Us</a></li>\n");
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
      out.write("\n");
      out.write("<div class=\"content\">\n");
      out.write("      <div class=\"content_bg\">\n");
      out.write("        <div class=\"mainbar\">\n");
      out.write("          <div class=\"article\">\n");
      out.write("            <h2><span>Login</span></h2>\n");
      out.write("            <div class=\"clr\"></div>\n");
      out.write("            <form action=\"/JackeMoney/verify.jsp\" method=\"POST\" id=\"login\">\n");
      out.write("              <ol>\n");
      out.write("                <li>\n");
      out.write("                  <label for=\"name\">User Name</label>\n");
      out.write("                  <input id=\"name\" name=\"username\" class=\"text\" />\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                  <label for=\"password\">Password</label>\n");
      out.write("                  <input type=\"password\" id=\"password\" name=\"password\" class=\"text\" />\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                  <input type=\"image\" name=\"submit\" id=\"submit\" src=\"images/submit.gif\" class=\"send\" />\n");
      out.write("                  <div class=\"clr\"></div>\n");
      out.write("                </li>\n");
      out.write("              </ol>\n");
      out.write("            </form>\n");
      out.write("            <p>\n");
      out.write("            <font color='red'>\n");
      out.write("            ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("            </font></p>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("        <div>\n");
      out.write("            \n");
      out.write("        </div>\n");
      out.write("        <div class=\"clr\"></div>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("  </div>\n");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty param.errMsg}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                ");
        if (_jspx_meth_c_out_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("            ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _jspx_tagPool_c_out_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(_jspx_page_context);
    _jspx_th_c_out_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
    _jspx_th_c_out_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.errMsg}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_out_value_nobody.reuse(_jspx_th_c_out_0);
      return true;
    }
    _jspx_tagPool_c_out_value_nobody.reuse(_jspx_th_c_out_0);
    return false;
  }
}
