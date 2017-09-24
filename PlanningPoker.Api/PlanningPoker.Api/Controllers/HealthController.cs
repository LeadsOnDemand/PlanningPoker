using Microsoft.AspNetCore.Mvc;

namespace PlanningPoker.Api.Controllers
{
    [Route("api/[controller]")]
    public class HealthController : Controller
    {
        [HttpGet]
        public string Get()
        {
            return "Health Test";
        }
    }
}
