using PlanningPoker.DataAccess.BaseClasses;
using PlanningPoker.DataAccess.Models;
using PlanningPoker.Library.Models;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PlanningPoker.Library.Services
{
    public interface IPlayerCardService
    {
        Task<List<PlayerCard>> GetPlayerCards(UserStory userStory);
    }

    public class PlayerCardService : IPlayerCardService
    {
        private readonly IRepository<UserStory> _userStoryRepository;

        private readonly IRepository<Player> _playerRepository;

        private readonly IRepository<CardCall> _callCardRepository;

        public PlayerCardService(IRepository<UserStory> userStoryRepository, IRepository<Player> playerRepository, 
            IRepository<CardCall> callCardRepository)
        {
            _userStoryRepository = userStoryRepository;
            _playerRepository = playerRepository;
            _callCardRepository = callCardRepository;
        }

        public async Task<List<PlayerCard>> GetPlayerCards(UserStory userStory)
        {
            var players = await _playerRepository.FindByAsync(p => p.GameId == userStory.GameId && p.IsActive);
            var callCards = await _callCardRepository.FindByAsync(c => c.UserStoryId == userStory.Id);

            var playerCards = new List<PlayerCard>();

            players.ForEach(p =>
            {
                playerCards.Add(
                    new PlayerCard
                    {
                        Player = p,
                        Card = callCards.FirstOrDefault(c => c.PlayerId == p.Id)
                    }
                );
            });

            return playerCards;
        }
    }
}
