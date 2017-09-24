using PlanningPoker.DataAccess.BaseClasses;
using PlanningPoker.DataAccess.Enums;
using PlanningPoker.DataAccess.Models;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PlanningPoker.Library.Services
{
    public interface IUserStoryService
    {
        Task<int> CreateNew(UserStory userStory, Game game);

        Task<int> SetPlanningCompleted(UserStory userStory);

        Task<int> SetPlanningCancelled(UserStory userStory);

        Task<bool> HasOnGoingPlanning(UserStory userStory, Game game);
    }

    public class UserStoryService : IUserStoryService
    {
        private readonly IRepository<UserStory> _userStoryRepository;


        public UserStoryService(IRepository<UserStory> userStoryRepository)
        {
            _userStoryRepository = userStoryRepository;
        }

        public async Task<int> CreateNew(UserStory userStory, Game game)
        {
            userStory.GameId = game.Id;
            userStory.Status = UserStoryStatus.OnGoingPlanning;

            return await _userStoryRepository.InsertAsync(userStory);
        }

        public async Task<int> SetPlanningCompleted(UserStory userStory)
        {
            userStory.Status = UserStoryStatus.CompletedPlanning;

            return await _userStoryRepository.UpdateAsync(userStory);
        }

        public async Task<int> SetPlanningCancelled(UserStory userStory)
        {
            userStory.Status = UserStoryStatus.Cancelled;

            return await _userStoryRepository.UpdateAsync(userStory);
        }

        public async Task<bool> HasOnGoingPlanning(UserStory userStory, Game game)
        {
            var findResult =
                await _userStoryRepository.FindByAsync(
                    u => u.GameId == game.Id && u.Status == UserStoryStatus.OnGoingPlanning);

            return findResult.Any();
        }
        public async Task<IEnumerable<UserStory>> GetUserStories(Game game)
        {
            var findResult = await _userStoryRepository.FindByAsync(u => u.GameId == game.Id);

            return findResult;
        }
    }
}
