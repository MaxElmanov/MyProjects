using Controllers.Util;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Controllers.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            HttpContext.Response.Cookies["id"].Value = "kkkk-kkkkkkk";
            Session["name"] = "JJJ";

            ViewBag.R = "Ha-ha-Ha!!!";
            ViewBag.fruits = new List<string>()
            {
                "Apple",
                "Banana",
                "Tomato"
            };
            return View();
        }

        public string GetData()
        {
            string id = HttpContext.Request.Cookies["id"].Value;
            string name = Session["name"].ToString();
            return name;
        }

        public ActionResult GetVoid(int id)
        {
            if(id > 3) {
                return new HttpUnauthorizedResult("YYYYYYYYYYYYYYYY");
            }

            return View("About");
        }

        public ActionResult Contact()
        {
            ViewBag.sms = "xyu";
            return View();
        }

        public string Square(int h, int a)
        {
            return "h*a= " + h * a;
        }

        public FilePathResult GetFile()
        {
            string path = Server.MapPath("~/File/doc.rtf");

            string type = "application/rtf";

            string name = "doc.rtf";

            return File(path, type, name);
        }

        public FileContentResult GetByteFile()
        {
            string path = Server.MapPath("~/File/doc.rtf");
            byte[] mass = System.IO.File.ReadAllBytes(path);

            string type = "application/octet-stream";

            string name = "doc.rtf";

            return File(mass, type, name);
        }

        public void GetContext()
        {
            HttpContext.Response.Write("hellowWW");
            string browser = HttpContext.Request.Browser.Browser;
            string user_agent = HttpContext.Request.UserAgent;
            string url = HttpContext.Request.RawUrl;
            string ip = HttpContext.Request.UserHostAddress;
            string referer = HttpContext.Request.UrlReferrer == null ? "" : HttpContext.Request.UrlReferrer.AbsoluteUri;

            string massCookies = null;
            foreach(var cookie in HttpContext.Request.Cookies) 
            {
                massCookies += cookie + "/t";
            }

            HttpContext.Response.Write("<p>Browser: "+browser+"</p><p>User-agent: "+user_agent+"</p><p>URL request: "+url+"</p><p>Referer: "+referer+"</p><p>IP user: "+ip+"</p><p>Cookies: "+ massCookies +"</p>");
        }
    }
}