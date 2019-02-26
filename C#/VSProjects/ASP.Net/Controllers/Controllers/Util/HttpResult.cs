using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Controllers.Util
{
    public class HttpResult : ActionResult
    {
        string htmlCode;

        public HttpResult(string html)
        {
            htmlCode = html;
        }

        public override void ExecuteResult(ControllerContext context)
        {
            string fullHtmlResult = "<!DOCTYPE> <html></html><head>";
            fullHtmlResult += "<title>Main page</title>";
            fullHtmlResult += "<meta charset='UTF-8' />";
            fullHtmlResult += "</head><body>";
            fullHtmlResult += htmlCode;
            fullHtmlResult += "</body></html>";
            context.HttpContext.Response.Write(fullHtmlResult);
        }
    }
}