using PlanningPoker.DataAccess.BaseClasses;
using PlanningPoker.DataAccess.Models;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PlanningPoker.Library.Services
{
    public interface IPlayerService
    {
        Task<int> JoinGame(Player player, Game game);

        Task<int> LeaveGame(Player player);

        Task<bool> IsDuplicate(Player player, Game game);

        Task<IEnumerable<Player>> GetPlayers(Game game);
    }

    public class PlayerService : IPlayerService
    {
        private readonly IRepository<Player> _playerRepository;

        public PlayerService(IRepository<Player> playerRepository)
        {
            _playerRepository = playerRepository;
        }

        public async Task<int> JoinGame(Player player, Game game)
        {
            player.GameId = game.Id;
            player.IsActive = true;

            return await _playerRepository.InsertAsync(player);
        }

        public async Task<int> LeaveGame(Player player)
        {
            player.IsActive = false;

            return await _playerRepository.UpdateAsync(player);
        }

        public async Task<bool> IsDuplicate(Player player, Game game)
        {
            var findResult =
                await _playerRepository.FindByAsync(p => p.GameId == game.Id && p.Name == player.Name && p.IsActive);

            return findResult.Any();
        }

        public async Task<IEnumerable<Player>> GetPlayers(Game game)
        {
            var findResult = await _playerRepository.FindByAsync(p => p.GameId == game.Id && p.IsActive);

            return findResult;
        }
    }
}
