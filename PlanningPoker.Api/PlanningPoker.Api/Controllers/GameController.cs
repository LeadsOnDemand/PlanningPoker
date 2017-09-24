using Microsoft.AspNetCore.Mvc;
using PlanningPoker.Api.Models;
using PlanningPoker.DataAccess.Models;
using PlanningPoker.Library.Services;
using System.Collections.Generic;
using System.Threading.Tasks;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace PlanningPoker.Api.Controllers
{
    [Route("api/[controller]")]
    public class GameController : Controller
    {
        private readonly IGameService _gameService;

        public GameController(IGameService gameService)
        {
            _gameService = gameService;
        }

        [HttpPost("CreateGame")]
        public async Task<IActionResult> CreateGame([FromBody]Game game)
        {

            var checkResult = await _gameService.IsDuplicate(game);

            if (checkResult)
            {
                return Ok(new ApiCallResult<int>
                {
                    ErrorMessage = "A Game with the same name (" + game.Tag + ") is already in progress."
                });
            }

            var initiateGameResult = await _gameService.InitiateGame(game);

            return Ok(new ApiCallResult<Game> { Data = game });
        }

        [HttpPost("StartGame")]
        public async Task<IActionResult> StartGame([FromBody]Game game)
        {
            var startGameResult = await _gameService.StartGame(game);

            return Ok(new ApiCallResult<Game> { Data = game });
        }

        [HttpGet()]
        public async Task<IActionResult> GetGames()
        {
            var notEndedGamest = await _gameService.GetNotEndedGames();

            return Ok(new ApiCallResult<IEnumerable<Game>> { Data = notEndedGamest });
        }

        [HttpPost("EndGame")]
        public async Task<IActionResult> EndGame([FromBody]Game game)
        {
            var endGameResult = await _gameService.EndGame(game);

            return Ok(new ApiCallResult<Game> { Data = game });
        }
    }
}
