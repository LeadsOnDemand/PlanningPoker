using PlanningPoker.DataAccess.BaseClasses;
using PlanningPoker.DataAccess.Enums;
using PlanningPoker.DataAccess.Models;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PlanningPoker.Library.Services
{
    public interface IGameService
    {
        Task<int> InitiateGame(Game game);

        Task<int> StartGame(Game game);

        Task<int> EndGame(Game game);

        Task<IEnumerable<Game>> GetNotEndedGames();

        Task<bool> IsDuplicate(Game game);
    }

    public class GameService : IGameService
    {
        private readonly IRepository<Game> _gameRepository;

        public GameService(IRepository<Game> gameRepository)
        {
            _gameRepository = gameRepository;
        }

        public async Task<int> InitiateGame(Game game)
        {
            return await _gameRepository.InsertAsync(game);
        }

        public async Task<int> StartGame(Game game)
        {
            game.Progress = GameProgress.Started;
            return await _gameRepository.UpdateAsync(game);
        }

        public async Task<int> EndGame(Game game)
        {
            game.Progress = GameProgress.Ended;
            return await _gameRepository.UpdateAsync(game);
        }

        public async Task<IEnumerable<Game>> GetNotEndedGames()
        {
            return await _gameRepository.FindByAsync(g => g.Progress != GameProgress.Ended);
        }

        public async Task<bool> IsDuplicate(Game game)
        {
            var findResult = await _gameRepository.FindByAsync(g => g.Tag == game.Tag && g.Progress != GameProgress.Ended);

            return findResult.Any();
        }
    }
}
