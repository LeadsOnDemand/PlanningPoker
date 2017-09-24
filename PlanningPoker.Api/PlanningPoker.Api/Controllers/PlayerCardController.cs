using Microsoft.AspNetCore.Mvc;
using PlanningPoker.Api.Models;
using PlanningPoker.DataAccess.Models;
using PlanningPoker.Library.Models;
using PlanningPoker.Library.Services;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace PlanningPoker.Api.Controllers
{
    [Route("api/[controller]")]
    public class PlayerCardController : Controller
    {
        private readonly IPlayerCardService _playerCardService;

        public PlayerCardController(IPlayerCardService playerCardService)
        {
            _playerCardService = playerCardService;
        }

        [HttpPost("GetPlayerCards")]
        public async Task<IActionResult> GetPlayerCards([FromBody]UserStory userStory)
        {
            var playerCards = await _playerCardService.GetPlayerCards(userStory);

            return Ok(new ApiCallResult<IEnumerable<PlayerCard>> {Data = playerCards});
        }
    }
}
