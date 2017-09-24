using Microsoft.AspNetCore.Mvc;
using PlanningPoker.Api.Models;
using PlanningPoker.DataAccess.Models;
using PlanningPoker.Library.Services;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace PlanningPoker.Api.Controllers
{
    [Route("api/[controller]")]
    public class PlayerController : Controller
    {
        private readonly IPlayerService _playerService;

        public PlayerController(IPlayerService playerService)
        {
            _playerService = playerService;
        }

        [HttpPost("JoinGame")]
        public async Task<IActionResult> JoinGame([FromBody]JoinGameRequest request)
        {
            var player = request.Player;
            var game = request.Game;

            var checkResult = await _playerService.IsDuplicate(player, game);

            if (checkResult)
            {
                return Ok(new ApiCallResult<int>
                {
                    ErrorMessage = "Player with the same name (" + player.Name + ") has already joined this game."
                });
            }

            var joinResult = await _playerService.JoinGame(player, game);

            return Ok(new ApiCallResult<Player> { Data = player });
        }

        [HttpPost("LeaveGame")]
        public async Task<IActionResult> LeaveGame([FromBody]Player player)
        {
            var leaveGameResult = await _playerService.LeaveGame(player);

            return Ok(new ApiCallResult<Player> { Data = player });
        }

        [HttpPost("GetPlayers")]
        public async Task<IActionResult> GetPlayers([FromBody]Game game)
        {
            var getPlayersResult = await _playerService.GetPlayers(game);

            return Ok(new ApiCallResult<IEnumerable<Player>> { Data = getPlayersResult });
        }

    }
}
