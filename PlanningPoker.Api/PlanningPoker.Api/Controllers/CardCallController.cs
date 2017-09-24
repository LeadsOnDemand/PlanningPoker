using Microsoft.AspNetCore.Mvc;
using PlanningPoker.Api.Models;
using PlanningPoker.Library.Services;
using System.Threading.Tasks;

namespace PlanningPoker.Api.Controllers
{
    [Route("api/[controller]")]
    public class CardCallController : Controller
    {
        private readonly ICardCallService _cardCallService;

        public CardCallController(ICardCallService cardCallService)
        {
            _cardCallService = cardCallService;
        }

        [HttpPost("SelectCard")]
        public async Task<IActionResult> SelectCard([FromBody]SelectCardRequest request)
        {
            var userStory = request.UserStory;
            var player = request.Player;

            var selectCardResult = await _cardCallService.SelectCard(userStory, player, request.CardValue);

            return Ok(new ApiCallResult<int> {Data = selectCardResult});
        }
    }
}
