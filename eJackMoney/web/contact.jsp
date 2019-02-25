<%-- 
    Document   : contact
    Created on : May 16, 2017, 12:54:53 PM
    Author     : MWESIGYE


                                    GROUP MEMBERS
                                    =============
		______________________________________________________
		* MWESIGYE  ROBERT          |  15 / U / 771           |
		* NANSUBUGA JOYCE   EUZEBIA |  15 / U / 10807 / EVE   |
		* NAKAALI   PHIONA	    | 	15 / U / 9286 / PS    |
		* BWAYO     JUDE      	    |  15 / U / 4718 / EVE    |
		____________________________|_________________________|
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("page","contact");%>
<% pageContext.setAttribute("title", "contact");%>
<%@include file="/include/header.jsp" %>
<div class="content">
      <div class="content_bg">
        <div class="mainbar">
          <div class="article">
            <h2><span>Contact</span></h2>
            <div class="clr"></div>
            <p>You can find more about  Jack eMoney by.</p>
          </div>
          <div class="article">
            <h2><span>Sending us</span> a mail</h2>
            <div class="clr"></div>
            <form action="#" method="post" id="sendemail">
              <ol>
                <li>
                  <label for="name">Name (required)</label>
                  <input id="name" name="name" class="text" />
                </li>
                <li>
                  <label for="email">Email Address (required)</label>
                  <input id="email" name="email" class="text" />
                </li>
                <li>
                  <label for="website">Website</label>
                  <input id="website" name="website" class="text" />
                </li>
                <li>
                  <label for="message">Your Message</label>
                  <textarea id="message" name="message" rows="8" cols="50"></textarea>
                </li>
                <li>
                  <input type="image" name="imageField" id="imageField" src="images/submit.gif" class="send" />
                  <div class="clr"></div>
                </li>
              </ol>
            </form>
          </div>
        </div>
        <div class="sidebar">
        </div>
        <div class="clr"></div>
      </div>
    </div>
  </div>
</div>
<%@include file="/include/footer.jsp" %>