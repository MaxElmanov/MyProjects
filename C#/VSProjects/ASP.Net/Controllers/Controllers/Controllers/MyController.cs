using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Mvc;
using System.Web;

namespace Controllers.Controllers
{
    public class MyController : IController
    {
        public void Execute(System.Web.Routing.RequestContext requestContext)
        {
            string ip = requestContext.HttpContext.Request.UserHostAddress;
            var response = requestContext.HttpContext.Response;
            response.Write("<h2>Your IP address " + ip + "</h2>");
        }
    }
}