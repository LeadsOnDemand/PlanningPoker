using PlanningPoker.DataAccess.BaseClasses;
using PlanningPoker.DataAccess.Models;
using System;
using System.Linq;
using System.Threading.Tasks;

namespace PlanningPoker.Library.Services
{
    public interface ICardCallService
    {
        Task<int> SelectCard(UserStory userStory, Player player, int? value);
    }

    public class CardCallService : ICardCallService
    {
        private readonly IRepository<CardCall> _cardCallRepository;

        public CardCallService(IRepository<CardCall> cardCallRepository)
        {
            _cardCallRepository = cardCallRepository;
        }

        public async Task<int> SelectCard(UserStory userStory, Player player, int? value)
        {
            var findResult =
                await _cardCallRepository.FindByAsync(cc => cc.UserStoryId == userStory.Id && cc.PlayerId == player.Id);

            CardCall cardCall;

            if (!findResult.Any())
            {
                cardCall = new CardCall
                {
                    UserStoryId = userStory.Id,
                    PlayerId = player.Id,
                    CardValue = value
                };

                return await _cardCallRepository.InsertAsync(cardCall);
            }

            cardCall = findResult.First();

            cardCall.CardValue = value;
            cardCall.ModifiedDate = DateTime.Now;

            return await _cardCallRepository.UpdateAsync(cardCall);
        }
    }
}
