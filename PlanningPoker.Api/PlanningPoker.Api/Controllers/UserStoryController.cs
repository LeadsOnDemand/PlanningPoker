using Microsoft.AspNetCore.Mvc;
using PlanningPoker.Api.Models;
using PlanningPoker.DataAccess.Models;
using PlanningPoker.Library.Services;
using System.Threading.Tasks;

namespace PlanningPoker.Api.Controllers
{
    [Route("api/[controller]")]
    public class UserStoryController : Controller
    {
        private readonly IUserStoryService _userStoryService;

        public UserStoryController(IUserStoryService userStoryService)
        {
            _userStoryService = userStoryService;
        }

        [HttpPost("CreateUserStory")]
        public async Task<IActionResult> CreateUserStory([FromBody]CreateUserStoryRequest request)
        {
            var userStory = request.UserStory;
            var game = request.Game;

            var hasOnGoingPlanning = await _userStoryService.HasOnGoingPlanning(userStory, game);

            if (hasOnGoingPlanning)
            {
                return Ok(new ApiCallResult<int>
                {
                    ErrorMessage = "There is an on-going User Story planning for this game."
                });
            }

            var createResutlt = await _userStoryService.CreateNew(userStory, game);
            return Ok(new ApiCallResult<UserStory>{Data = userStory });
        }

        [HttpPost("SetPlanningComplete")]
        public async Task<IActionResult> SetPlanningComplete([FromBody]UserStory userStory)
        {
            var setPlanningCompleteResult = await _userStoryService.SetPlanningCompleted(userStory);

            return Ok(new ApiCallResult<UserStory> {Data = userStory });
        }

        [HttpPost("SetPlanningCancelled")]
        public async Task<IActionResult> SetPlanningCancelled([FromBody]UserStory userStory)
        {
            var setPlanningCompleteResult = await _userStoryService.SetPlanningCancelled(userStory);

            return Ok(new ApiCallResult<UserStory> { Data = userStory });
        }
    }
}
